# ezPark
A small application that demonstrates my learning of DDD and hexagonal. the app manages a real-time availability, intelligent reservations, and automated payments.µ

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


