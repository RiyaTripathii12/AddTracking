package com.example.add.tracking.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.add.tracking.Entity.Trackers;

public interface TrackersRepository extends JpaRepository<Trackers,UUID> {
    List<Trackers> findByTransactionId(String transactionId);

    List<Trackers> findByTrackingNumber(String trackingNumber);
}
