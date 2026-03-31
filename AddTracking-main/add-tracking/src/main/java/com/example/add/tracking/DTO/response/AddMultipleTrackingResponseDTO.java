package com.example.add.tracking.DTO.response;

import java.util.List;

import com.example.add.tracking.DTO.common.TrackerResponseDTO;

import lombok.Data;

@Data
public class AddMultipleTrackingResponseDTO {
    private List<TrackerResponseDTO> trackers;
}
