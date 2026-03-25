package com.example.add.tracking.DTO.common;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class TrackerResponseDTO {
    @JsonProperty("tracker_id")
    private String trackerId;

    @JsonProperty("last_updated_time")
    private OffsetDateTime lastUpdatedTime;

    private List<LinksDTO> links;
}
