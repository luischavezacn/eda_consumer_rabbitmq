package com.luis.eda_consumer_rabbitmq.listener;

import com.luis.eda_consumer_rabbitmq.model.Song;
import com.luis.eda_consumer_rabbitmq.model.SongEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SongListener {

    private static final Logger logger = LoggerFactory.getLogger(SongListener.class);

    List<Song> playlist = new ArrayList<>();

    @RabbitListener(queues = "restaurant.playlist.queue")
    public void songListener(SongEvent songEvent, @Headers Map<String, Object> headers) {
        logger.info("Received new song={}, correlationId={}", songEvent.song(), headers.get("correlationId"));
        if (exist(songEvent.song())) {
            logger.info("Song is already in the playlist");
            return;
        }
        playlist.add(songEvent.song());
        logger.info("Song has been added to playlist, song={}", songEvent.song());
    }

    private boolean exist(Song song) {
        return playlist.stream().anyMatch(s -> s.equals(song));
    }

    @Scheduled(cron = "${playlist.cron}")
    public void play() {
        try {
            var song = playlist.stream().findFirst();
            if (song.isEmpty()) {
                logger.info("No songs in playlist");
                return;
            }
            logger.info("Starting song={}",song.get());
            playlist.remove(song.get());
            logger.info("Next in playlist={}", playlist.toString());
        } catch (Exception exception) {
            logger.error("Unexpected exception", exception);
        }
    }
}
