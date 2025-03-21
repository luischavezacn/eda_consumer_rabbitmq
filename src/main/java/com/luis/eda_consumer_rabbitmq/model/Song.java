package com.luis.eda_consumer_rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Song(
        @JsonProperty String name,
        @JsonProperty String album
) {

    @Override
    public boolean equals(Object obj) {
        Song other = (Song) obj;
        return this.name.equals(other.name) && this.album.equals(other.album);
    }
}
