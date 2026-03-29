package com.example.add.tracking.DTO.common;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TrackersDTO {
    
    private String id;

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("carrier_name_other")
    private String carrierNameOther;

    @JsonProperty("notify_buyer")
    private Boolean notifyBuyer;

    @JsonProperty("shipment_direction")
    private String shipmentDirection;

    @JsonProperty("tracking_url")
    private String trackingUrl;

    @JsonProperty("fulfillment_provider")
    private String fulfillmentProvider;

    @JsonProperty("tracking_number_type")
    private String trackingNumberType;

    private String status;

    @JsonProperty("shipment_date")
    private OffsetDateTime shipmentDate;

    private String carrier;
}
