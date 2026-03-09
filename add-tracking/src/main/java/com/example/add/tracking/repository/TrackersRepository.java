package com.example.add.tracking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.add.tracking.Entity.Trackers;

public interface TrackersRepository extends JpaRepository<Trackers,UUID> {

}
