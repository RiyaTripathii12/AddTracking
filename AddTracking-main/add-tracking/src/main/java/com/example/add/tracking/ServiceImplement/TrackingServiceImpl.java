package com.example.add.tracking.ServiceImplement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

        res.setId(saved.getId().toString());
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

res.setId(saved.getId().toString());
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

List<Trackers> trackers;

    //  Filter logic
    if (transactionId != null) {
        trackers = trackersRepository.findByTransactionId(transactionId);
    } else if (trackingNumber != null) {
        trackers = trackersRepository.findByTrackingNumber(trackingNumber);
    } else {
        trackers = trackersRepository.findAll();
    }

    //  Convert Entity → DTO
    List<TrackersDTO> responseList = new ArrayList<>();

    for (Trackers t : trackers) {

        TrackersDTO res = new TrackersDTO();

        res.setId(t.getId().toString());
        res.setTransactionId(t.getTransactionId());
        res.setTrackingNumber(t.getTrackingNumber());
        res.setCarrierNameOther(t.getCarrierNameOther());
        res.setNotifyBuyer(t.getNotifyBuyer());
        res.setShipmentDirection(t.getShipmentDirection());
        res.setTrackingUrl(t.getTrackingUrl());
        res.setFulfillmentProvider(t.getFulfillmentProvider());
        res.setTrackingNumberType(t.getTrackingNumberType());
        res.setStatus(t.getStatus());
        res.setCarrier(t.getCarrier());

        if (t.getShipmentDate() != null) {
            res.setShipmentDate(
                t.getShipmentDate()
                 .atStartOfDay()
                 .atOffset(java.time.ZoneOffset.UTC)
            );
        }

        responseList.add(res);
    }

    //  Response
    ListTrackingResponseDTO response = new ListTrackingResponseDTO();
    response.setTrackers(responseList);

    return response;
}

    @Override
public UpdateTrackingResponseDTO updateTracking(String id, UpdateTrackingRequestDTO requestDTO) {

    //  Find by UUID
    Trackers tracker = trackersRepository.findById(java.util.UUID.fromString(id))
            .orElseThrow(() -> new RuntimeException("Tracker not found"));

    TrackersDTO dto = requestDTO.getTracker();

    //  Update only if not null
    if (dto.getStatus() != null) {
        tracker.setStatus(dto.getStatus());
    }

    if (dto.getTrackingNumber() != null) {
        tracker.setTrackingNumber(dto.getTrackingNumber());
    }

    if (dto.getCarrier() != null) {
        tracker.setCarrier(dto.getCarrier());
    }

    if (dto.getTrackingUrl() != null) {
        tracker.setTrackingUrl(dto.getTrackingUrl());
    }

    tracker.setLastUpdatedTime(LocalDateTime.now());

    //  Save
    Trackers saved = trackersRepository.save(tracker);

    //  Response
    TrackersDTO res = new TrackersDTO();

    res.setId(saved.getId().toString());
    res.setTransactionId(saved.getTransactionId());
    res.setTrackingNumber(saved.getTrackingNumber());
    res.setStatus(saved.getStatus());
    res.setCarrier(saved.getCarrier());
    res.setCarrierNameOther(saved.getCarrierNameOther());
    res.setNotifyBuyer(saved.getNotifyBuyer());
    res.setShipmentDirection(saved.getShipmentDirection());
    res.setTrackingUrl(saved.getTrackingUrl());
    res.setFulfillmentProvider(saved.getFulfillmentProvider());
    res.setTrackingNumberType(saved.getTrackingNumberType());

    UpdateTrackingResponseDTO response = new UpdateTrackingResponseDTO();
    response.setTrackers(Arrays.asList(res));

    return response;
}

    @Override
    public ShowTrackingResponseDTO showTracking(String id, String accountId) {

    Trackers tracker = trackersRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new RuntimeException("Tracker not found"));

    TrackersDTO res = new TrackersDTO();
    
    res.setId(tracker.getId().toString());
    res.setTransactionId(tracker.getTransactionId());
    res.setTrackingNumber(tracker.getTrackingNumber());
    res.setStatus(tracker.getStatus());
    res.setCarrier(tracker.getCarrier());
    res.setCarrierNameOther(tracker.getCarrierNameOther());
    res.setNotifyBuyer(tracker.getNotifyBuyer());
    res.setShipmentDirection(tracker.getShipmentDirection());
    res.setTrackingUrl(tracker.getTrackingUrl());
    res.setFulfillmentProvider(tracker.getFulfillmentProvider());
    res.setTrackingNumberType(tracker.getTrackingNumberType());
    if (tracker.getShipmentDate() != null) {
        res.setShipmentDate(
            tracker.getShipmentDate()
                   .atStartOfDay()
                   .atOffset(java.time.ZoneOffset.UTC)
        );
    }

    ShowTrackingResponseDTO response = new ShowTrackingResponseDTO();
    response.setTrackers(Arrays.asList(res));

    return response;
}
}
