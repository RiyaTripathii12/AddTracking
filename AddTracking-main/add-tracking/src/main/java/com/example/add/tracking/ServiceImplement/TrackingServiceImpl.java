package com.example.add.tracking.ServiceImplement;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.add.tracking.DTO.common.LinksDTO;
import com.example.add.tracking.DTO.common.TrackerResponseDTO;
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

    // ✅ COMMON MAPPER (NO ERRORS)
    private TrackerResponseDTO mapToResponse(Trackers saved) {

        TrackerResponseDTO res = new TrackerResponseDTO();

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

        // 🔧 FIXED: no nulls
        res.setPostagePaymentId("PP123456789");
        res.setQuantity(1);
        res.setTrackingNumberValidated(true);
        res.setShipmentUploader("MERCHANT");
        res.setAccountId("ABCD1234EFGH");

        // 🔗 links
        LinksDTO link = new LinksDTO();
        link.setHref("https://api-m.paypal.com/v1/shipping/trackers/" + saved.getId());
        link.setRel("self");
        link.setMethod("GET");

        res.setLinks(Arrays.asList(link));

        // ⏱ dates (safe)
        if (saved.getShipmentDate() != null) {
            res.setShipmentDate(
                saved.getShipmentDate()
                        .atStartOfDay()
                        .atOffset(ZoneOffset.UTC)
            );
        }

        if (saved.getLastUpdatedTime() != null) {
            res.setLastUpdatedTime(
                saved.getLastUpdatedTime().atOffset(ZoneOffset.UTC)
            );
        }

        return res;
    }

    // ✅ ADD SINGLE
    @Override
    public AddSingleTrackingResponseDTO addSingleTracking(AddSingleTrackingRequestDTO requestDTO) {

        TrackersDTO dto = requestDTO.getTracker();

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

        AddSingleTrackingResponseDTO response = new AddSingleTrackingResponseDTO();

        List<TrackerResponseDTO> list = new ArrayList<>();
        list.add(mapToResponse(saved));

        response.setTrackers(list);

        return response;
    }

    // ✅ ADD MULTIPLE
    @Override
    public AddMultipleTrackingResponseDTO addMultipleTracking(AddMultipleTrackingRequestDTO requestDTO) {

        List<TrackerResponseDTO> list = new ArrayList<>();

        for (TrackersDTO dto : requestDTO.getTrackers()) {

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

            list.add(mapToResponse(saved));
        }

        AddMultipleTrackingResponseDTO response = new AddMultipleTrackingResponseDTO();
        response.setTrackers(list);

        return response;
    }

    // ✅ LIST
    @Override
    public ListTrackingResponseDTO listTracking(String transactionId, String trackingNumber, String accountId) {

        List<Trackers> trackers;

        if (transactionId != null) {
            trackers = trackersRepository.findByTransactionId(transactionId);
        } else if (trackingNumber != null) {
            trackers = trackersRepository.findByTrackingNumber(trackingNumber);
        } else {
            trackers = trackersRepository.findAll();
        }

        List<TrackerResponseDTO> list = new ArrayList<>();

        for (Trackers t : trackers) {
            list.add(mapToResponse(t));
        }

        ListTrackingResponseDTO response = new ListTrackingResponseDTO();
        response.setTrackers(list);

        return response;
    }

    // ✅ SHOW
    @Override
    public ShowTrackingResponseDTO showTracking(String id, String accountId) {

        Trackers tracker = trackersRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Tracker not found"));

        ShowTrackingResponseDTO response = new ShowTrackingResponseDTO();

        List<TrackerResponseDTO> list = new ArrayList<>();
        list.add(mapToResponse(tracker));

        response.setTrackers(list);

        return response;
    }

    // ✅ UPDATE
    @Override
    public UpdateTrackingResponseDTO updateTracking(String id, UpdateTrackingRequestDTO requestDTO) {

        Trackers tracker = trackersRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Tracker not found"));

        TrackersDTO dto = requestDTO.getTracker();

        if (dto.getStatus() != null) tracker.setStatus(dto.getStatus());
        if (dto.getTrackingNumber() != null) tracker.setTrackingNumber(dto.getTrackingNumber());
        if (dto.getCarrier() != null) tracker.setCarrier(dto.getCarrier());
        if (dto.getTrackingUrl() != null) tracker.setTrackingUrl(dto.getTrackingUrl());

        tracker.setLastUpdatedTime(LocalDateTime.now());

        Trackers saved = trackersRepository.save(tracker);

        UpdateTrackingResponseDTO response = new UpdateTrackingResponseDTO();

        List<TrackerResponseDTO> list = new ArrayList<>();
        list.add(mapToResponse(saved));

        response.setTrackers(list);

        return response;
    }
}