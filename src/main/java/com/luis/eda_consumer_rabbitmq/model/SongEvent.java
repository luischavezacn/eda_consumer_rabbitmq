package com.luis.eda_consumer_rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SongEvent(
        @JsonProperty String correlationId,
        @JsonProperty Song song
) { }
