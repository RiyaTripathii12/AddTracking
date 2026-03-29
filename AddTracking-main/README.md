# Add Tracking API

A Spring Boot backend project that provides APIs to manage shipment tracking details (similar to PayPal Tracking API).

## Features

*  Add Single Tracking
*  Add Multiple Tracking
*  List All Trackings
*  Show Tracking by ID (UUID)
*  Update Tracking
*  MySQL Database Integration
*  Clean DTO + Service + Controller Architecture

## Tech Stack

* Java (Spring Boot)
* MySQL
* Spring Data JPA
* Maven
* Postman (API Testing)

## Project Structure

com.example.add.tracking
│── Controller
│── Service
│── ServiceImplement
│── DTO
│   ├── request
│   ├── response
│   └── common
│── Entity
│── repository


## API Endpoints

### Add Single Tracking

POST /trackers/path

### Add Multiple Tracking

POST /trackers/multiple

### List Tracking

GET /trackers

Optional query params:

* `transaction_id`
* `tracking_number`

---

### Show Tracking (UUID)

GET /trackers/{id}

### Update Tracking

PUT /trackers/{id}

## Author

**Riya Tripathi** 
GitHub: https://github.com/RiyaTripathii12