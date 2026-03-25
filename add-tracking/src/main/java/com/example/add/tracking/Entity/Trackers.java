package com.example.add.tracking.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "trackers")
@AllArgsConstructor
@NoArgsConstructor
public class Trackers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;  

    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "carrier_name_other", length = 100)
    private String carrierNameOther;

    @Column(name = "notify_buyer")
    private Boolean notifyBuyer;

    @Column(name = "shipment_direction", length = 50)
    private String shipmentDirection;

    @Column(name = "tracking_url", length = 255)
    private String trackingUrl;

    @Column(name = "fulfillment_provider", length = 100)
    private String fulfillmentProvider;

    @Column(name = "tracking_number_type", length = 50)
    private String trackingNumberType;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "shipment_date")
    private LocalDate shipmentDate;

    @Column(name = "carrier", length = 100)
    private String carrier;

    @Column(name = "last_updated_time")
    private LocalDateTime lastUpdatedTime;

    // Relationship mapping
    @OneToMany(mappedBy = "tracker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Links> links = new ArrayList<>();

    // Helper method to sync relationship
    public void addLink(Links link) {
        links.add(link);
        link.setTracker(this);
    }
}
