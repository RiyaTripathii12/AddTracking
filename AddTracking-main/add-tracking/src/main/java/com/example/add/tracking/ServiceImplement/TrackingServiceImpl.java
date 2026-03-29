package com.example.add.tracking.ServiceImplement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.add.tracking.DTO.common.TrackersDTO;
import com.example.add.tracking.DTO.request.AddMultipleTrackingRequestDTO;
import com.example.add.tracking.DTO.request.AddSingleTrackingRequestDTO;
import com.example.add.tracking.DTO.request.UpdateTrackingRequestDTO;
import com.example.add.tracking.DTO.response.AddMultipleTrackingResponseDTO;
import com.example.add.tracking.DTO.response.AddSingleTrackingResponseDTO;
import com.example.add.tracking.DTO.response.ListTrackingResponseDTO;
import com.example.add.tracking.DTO.response.ShowTrackingResponseDTO;
import com.example.add.tracking.DTO.response.UpdateTrackingResponseDTO;
import com.example.add.tracking.Entity.Trackers;
import com.example.add.tracking.Service.TrackingService;
import com.example.add.tracking.repository.TrackersRepository;

@Service
public class TrackingServiceImpl implements TrackingService {
    private final TrackersRepository trackersRepository;
    public TrackingServiceImpl(TrackersRepository trackersRepository) {
    this.trackersRepository = trackersRepository;
    }
    
    @Override
    public AddMultipleTrackingResponseDTO addMultipleTracking(
            AddMultipleTrackingRequestDTO addMultipleTrackingRequestDTO) {

    List<TrackersDTO> dtoList = addMultipleTrackingRequestDTO.getTrackers();

    List<TrackersDTO> responseList = new ArrayList<>();

    for (TrackersDTO dto : dtoList) {

        Trackers tracker = new Trackers();

        tracker.setTransactionId(dto.getTransactionId());
        tracker.setTrackingNumber(dto.getTrackingNumber());
        tracker.setCarrierNameOther(dto.getCarrierNameOther());
        tracker.setNotifyBuyer(dto.getNotifyBuyer());
        tracker.setShipmentDirection(dto.getShipmentDirection());
        tracker.setTrackingUrl(dto.getTrackingUrl());
        tracker.setFulfillmentProvider(dto.getFulfillmentProvider());
        tracker.setTrackingNumberType(dto.getTrackingNumberType());
        tracker.setStatus(dto.getStatus());
        tracker.setCarrier(dto.getCarrier());

        if (dto.getShipmentDate() != null) {
            tracker.setShipmentDate(dto.getShipmentDate().toLocalDate());
        }

        tracker.setLastUpdatedTime(LocalDateTime.now());

        Trackers saved = trackersRepository.save(tracker);

        TrackersDTO res = new TrackersDTO();

        res.setTransactionId(saved.getTransactionId());
        res.setTrackingNumber(saved.getTrackingNumber());
        res.setCarrierNameOther(saved.getCarrierNameOther());
        res.setNotifyBuyer(saved.getNotifyBuyer());
        res.setShipmentDirection(saved.getShipmentDirection());
        res.setTrackingUrl(saved.getTrackingUrl());
        res.setFulfillmentProvider(saved.getFulfillmentProvider());
        res.setTrackingNumberType(saved.getTrackingNumberType());
        res.setStatus(saved.getStatus());
        res.setCarrier(saved.getCarrier());

        if (saved.getShipmentDate() != null) {
            res.setShipmentDate(
                saved.getShipmentDate()
                     .atStartOfDay()
                     .atOffset(java.time.ZoneOffset.UTC)
            );
        }

        responseList.add(res);
    }

    AddMultipleTrackingResponseDTO response = new AddMultipleTrackingResponseDTO();
    response.setTrackers(responseList);

return response;
}

@Override
public AddSingleTrackingResponseDTO addSingleTracking(AddSingleTrackingRequestDTO addSingleTrackingRequestDTO) {
        
        TrackersDTO dto = addSingleTrackingRequestDTO.getTracker();

    // Convert DTO → Entity
    Trackers tracker = new Trackers();

    tracker.setTransactionId(dto.getTransactionId());
    tracker.setTrackingNumber(dto.getTrackingNumber());
    tracker.setCarrierNameOther(dto.getCarrierNameOther());
    tracker.setNotifyBuyer(dto.getNotifyBuyer());
    tracker.setShipmentDirection(dto.getShipmentDirection());
    tracker.setTrackingUrl(dto.getTrackingUrl());
    tracker.setFulfillmentProvider(dto.getFulfillmentProvider());
    tracker.setTrackingNumberType(dto.getTrackingNumberType());
    tracker.setStatus(dto.getStatus());
    tracker.setCarrier(dto.getCarrier());

    // Date conversion
    if (dto.getShipmentDate() != null) {
        tracker.setShipmentDate(dto.getShipmentDate().toLocalDate());
    }

    tracker.setLastUpdatedTime(LocalDateTime.now());

    //  Save
    Trackers saved = trackersRepository.save(tracker);

    //  Response
AddSingleTrackingResponseDTO response = new AddSingleTrackingResponseDTO();

List<TrackersDTO> list = new ArrayList<>();

TrackersDTO res = new TrackersDTO();

res.setTransactionId(saved.getTransactionId());
res.setTrackingNumber(saved.getTrackingNumber());
res.setCarrierNameOther(saved.getCarrierNameOther());
res.setNotifyBuyer(saved.getNotifyBuyer());
res.setShipmentDirection(saved.getShipmentDirection());
res.setTrackingUrl(saved.getTrackingUrl());
res.setFulfillmentProvider(saved.getFulfillmentProvider());
res.setTrackingNumberType(saved.getTrackingNumberType());
res.setStatus(saved.getStatus());
res.setCarrier(saved.getCarrier());

if (saved.getShipmentDate() != null) {
    res.setShipmentDate(
        saved.getShipmentDate()
             .atStartOfDay()
             .atOffset(java.time.ZoneOffset.UTC)
    );
}

list.add(res);

response.setTrackers(list);

return response;
    }

    @Override
    public ListTrackingResponseDTO listTracking(String transactionId, String trackingNumber, String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listTracking'");
    }

    @Override
    public UpdateTrackingResponseDTO updateTracking(String id, UpdateTrackingRequestDTO updateTrackingRequestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTracking'");
    }

    @Override
    public ShowTrackingResponseDTO showTracking(String id, String accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showTracking'");
    }
    
}
