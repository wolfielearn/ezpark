# ezPark
A small application that demonstrates my learning of DDD and hexagonal. the app manages a real-time availability, intelligent reservations, and automated payments.

-  Business Problem

The Parking Pain Points

      - Time Wastage: Drivers waste significant time searching for parking spots.
      - Inefficient Utilization: Parking lots suffer from poor space utilization.
      - Manual Processes: Cash payments and manual ticketing cause delays
      - No Real-time Visibility: Lack of live spot availability information
      - Reservation Conflicts: Double-booking and scheduling issues

The Solution
Business Requirements Implementation

      1. Customer Management 
      2. Parking Spot Management 
      3. Reservation System 
      4. Parking Sessions 
      5. Payment Processing 


#### Documentation

- [Business rules](ezpark-main/.claude/business-rules.md): scope, ubiquitous language, rules, lifecycles, saga, events, API
- [Implementation plan](ezpark-main/.claude/PLAN.md): step by step


# Domain Driven Design

## PHASE 1: STRATEGIC DDD

### 1. BOUNDED CONTEXTS IDENTIFICATION

     - CUSTOMER MANAGEMENT - Responsibility: User accounts and vehicles
      - Customer registration and profiles
      - Vehicle management (multiple vehicles per customer)
      - Authentication and authorization
         
    - PARKING MANAGEMENT - Responsibility: Physical parking infrastructure
      - Spots management
      - Real-time availability
      - Spot types and restrictions
      
    - RESERVATION & SESSIONS - Responsibility: Booking and actual parking
      - Reservation lifecycle
      - Time slot management
      - Session tracking (check-in/check-out)    
      
    - PAYMENT  - Responsibility: Money matters


### 2. UBIQUITOUS LANGUAGE DEFINITION

      Customer Context: 
            - Customer, Vehicle, LicensePlate, VehicleType
      Parking Context: 
            - ParkingLot, ParkingSpot, ParkingSpotType, Occupancy
      Reservation Context: 
            - Reservation, TimeSlot, ParkingSession, CheckIn, CheckOut
      Payment Context: 
            - Payment, Receipt

### 3. CONTEXT MAPPING & INTEGRATION

<img width="400" height="500" alt="image" src="https://github.com/user-attachments/assets/78111907-8bc9-4d91-93b4-f3cd8b3eaf09" />

      Customer ↔ Reservation: Partnership
      Parking → Reservation: Customer-Supplier  
      Payment → Reservation: Customer-Supplier
      Communication: REST APIs + Kafka Events

### 4. EVENT STORMING
      1.  Customer Registers → Adds Vehicles
      2.  Requests Reservation → System checks availability
      3.  Availability Confirmed → Payment authorized (hold funds)
      4.  Payment Authorized → Spot reserved
      5.  Spot Reserved → Reservation confirmed
      6.  Customer checks in → Parking session starts
      7.  Customer checks out → Parking session ends
      8.  System generates invoice → Processes payment
      9.  Payment processed → Spot released

    * This is the flow as implemented today. The target flow (time slot held first, then payment
      authorized, confirmed and captured) is described in ezpark-main/.claude/business-rules.md, section 8.


    * For now only the customer registration, adding vehicles to customer and reservation creation.
    * Later will be the check in/ check out process


#### Start the application 
    - Java 17
    - Maven
    --------------------------------------
    - docker compose up : the ezpark-api service is not working yet (fixed in Step 0 of PLAN.md)
    - locally (from ezpark-main) :
        - clone project
        - start postgres and kafka from the docker-compose file :
              docker compose up ezpark-db kafka kafka-ui pgadmin
        - build the modules, shared first (the root pom does not build them) :
              mvn -f shared/pom.xml install -DskipTests
              mvn -f customer-ctx/pom.xml install -DskipTests
              mvn -f parking-ctx/pom.xml install -DskipTests
              mvn -f payment-ctx/pom.xml install -DskipTests
              mvn -f reservation-ctx/pom.xml install -DskipTests
        - start the application (com.ezpark.io.Application) as spring boot :
              mvn spring-boot:run

#### Endpoints:

    - Customer :
      - POST localhost:8080/api/customers
      - payload :
            {
                "name": "donas",
                "email": "donas@mail.com"
              }
      - GET localhost:8080/api/customers/{id}
  
    - Vehicles :
      - POST localhost:8080/api/customers/{customerId}/vehicles
      - payload :
          {
            "license":"a123-4556",
            "type":"CAR"
          }
          
    - Reservation :
      - POST localhost:8080/api/reservations
      - payload (spotId is the UUID of a spot in the parking.parking table) :
            {
            "customerId": "7aff1316-4fe0-452c-bd92-c67f69e04964",
            "spotId": "0d0cb739-57a1-45f5-ab98-ec796a988c12",
            "startTime": "2026-12-25T10:30:00Z",
            "endTime": "2026-12-25T11:30:00Z"
          }
