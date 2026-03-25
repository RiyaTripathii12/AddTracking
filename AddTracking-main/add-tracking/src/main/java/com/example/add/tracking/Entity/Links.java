package com.example.add.tracking.Entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "links")
@AllArgsConstructor
@NoArgsConstructor
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "href", length = 500)
    private String href;

    @Column(name = "rel", length = 50)
    private String rel;

    @Column(name = "method", length = 20)
    private String method;

    // Is field ko add karne se 'setTracker' wala error solve ho jayega
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tracker_id")
    private Trackers tracker;
}