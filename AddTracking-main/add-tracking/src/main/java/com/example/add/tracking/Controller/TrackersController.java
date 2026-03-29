package com.example.add.tracking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.add.tracking.DTO.request.AddMultipleTrackingRequestDTO;
import com.example.add.tracking.DTO.request.AddSingleTrackingRequestDTO;
import com.example.add.tracking.DTO.request.UpdateTrackingRequestDTO;
import com.example.add.tracking.DTO.response.AddMultipleTrackingResponseDTO;
import com.example.add.tracking.DTO.response.AddSingleTrackingResponseDTO;
import com.example.add.tracking.DTO.response.ListTrackingResponseDTO;
import com.example.add.tracking.DTO.response.ShowTrackingResponseDTO;
import com.example.add.tracking.DTO.response.UpdateTrackingResponseDTO;
import com.example.add.tracking.Service.TrackingService;

@RestController
@RequestMapping("/trackers")
public class TrackersController {

    private final TrackingService trackingService;

    @Autowired
    public TrackersController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    // ✅ Add Single
    @PostMapping("/path")
    public AddSingleTrackingResponseDTO addTracking(
            @RequestBody AddSingleTrackingRequestDTO requestDTO) {

        return trackingService.addSingleTracking(requestDTO);
    }

    // ✅ Add Multiple
    @PostMapping("/multiple")
    public AddMultipleTrackingResponseDTO addMultipleTracking(
            @RequestBody AddMultipleTrackingRequestDTO requestDTO) {

        return trackingService.addMultipleTracking(requestDTO);
    }

    // ✅ List
    @GetMapping
    public ListTrackingResponseDTO listTracking(
            @RequestParam(required = false) String transaction_id,
            @RequestParam(required = false) String tracking_number,
            @RequestParam(required = false) String account_id) {

        return trackingService.listTracking(transaction_id, tracking_number, account_id);
    }

    // ✅ Show (UUID)
    @GetMapping("/{id}")
    public ShowTrackingResponseDTO showTracking(
            @PathVariable String id,
            @RequestParam(required = false) String account_id) {

        return trackingService.showTracking(id, account_id);
    }

    // ✅ Update (UUID)
    @PutMapping("/{id}")
    public UpdateTrackingResponseDTO updateTracking(
            @PathVariable String id,
            @RequestBody UpdateTrackingRequestDTO requestDTO) {

        return trackingService.updateTracking(id, requestDTO);
    }
}