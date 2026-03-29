package com.example.add.tracking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.add.tracking.DTO.request.AddMultipleTrackingRequestDTO;
import com.example.add.tracking.DTO.request.AddSingleTrackingRequestDTO;
import com.example.add.tracking.DTO.response.AddMultipleTrackingResponseDTO;
import com.example.add.tracking.DTO.response.AddSingleTrackingResponseDTO;
import com.example.add.tracking.Service.TrackingService;

@RestController
@RequestMapping("/trackers")
public class TrackersController {

    private final TrackingService trackingService;

    @Autowired
    public TrackersController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    // ✅ Single
    @PostMapping("/path")
    public AddSingleTrackingResponseDTO addTracking(
            @RequestBody AddSingleTrackingRequestDTO requestDTO) {

        return trackingService.addSingleTracking(requestDTO);
    }

    // ✅ Multiple (FIXED)
    @PostMapping("/multiple")
    public AddMultipleTrackingResponseDTO addMultipleTracking(
            @RequestBody AddMultipleTrackingRequestDTO requestDTO) {

        return trackingService.addMultipleTracking(requestDTO);
    }
}