package com.example.add.tracking.Service;


import com.example.add.tracking.DTO.request.AddMultipleTrackingRequestDTO;
import com.example.add.tracking.DTO.request.AddSingleTrackingRequestDTO;
import com.example.add.tracking.DTO.request.UpdateTrackingRequestDTO;
import com.example.add.tracking.DTO.response.AddMultipleTrackingResponseDTO;
import com.example.add.tracking.DTO.response.AddSingleTrackingResponseDTO;
import com.example.add.tracking.DTO.response.ListTrackingResponseDTO;
import com.example.add.tracking.DTO.response.ShowTrackingResponseDTO;
import com.example.add.tracking.DTO.response.UpdateTrackingResponseDTO;

public interface TrackingService {

    // Add Multiple Tracking 
    AddMultipleTrackingResponseDTO addMultipleTracking(
            AddMultipleTrackingRequestDTO addMultipleTrackingRequestDTO
    );

    // Add Single Tracking
    AddSingleTrackingResponseDTO addSingleTracking(
            AddSingleTrackingRequestDTO addSingleTrackingRequestDTO
    );

    // List Tracking
    ListTrackingResponseDTO listTracking(
            String transactionId,
            String trackingNumber,
            String accountId
    );

    //  Update Tracking
    UpdateTrackingResponseDTO updateTracking(
            String id,
            UpdateTrackingRequestDTO updateTrackingRequestDTO
    );

    // Show Tracking
    ShowTrackingResponseDTO showTracking(
            String id,
            String accountId
    );

}
