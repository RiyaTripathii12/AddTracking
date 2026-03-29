package com.example.add.tracking.DTO.request;

import com.example.add.tracking.DTO.common.TrackersDTO;

import java.util.List;
import lombok.Data;

@Data
public class AddMultipleTrackingRequestDTO {
    private List<TrackersDTO> trackers;
}
