# ezpark -  Smart Parking System 🅿️

A comprehensive digital platform that eliminates urban parking challenges through real-time availability, intelligent reservations, and automated payments.µ

🎯 Business Problem We're Solving

The Parking Pain Points

      ⏰ Time Wastage: Drivers waste significant time searching for parking spots.
      🚗 Inefficient Utilization: Parking lots suffer from poor space utilization.
      💳 Manual Processes: Cash payments and manual ticketing cause delays
      👻 No Real-time Visibility: Lack of live spot availability information
      📅 Reservation Conflicts: Double-booking and scheduling issues

Our Solution

A digital ecosystem connecting drivers with parking spots in real-time, enabling seamless reservations, automated payments, and optimized space management.

🏢 Business Requirements Implementation

      1. Customer Management ✅
      2. Parking Spot Management ✅
      3. Reservation System ✅
      4. Parking Sessions ✅
      5. Payment Processing ✅


# Domain Driven Design

## PHASE 1: STRATEGIC DDD  ✅

### 1. BOUNDED CONTEXTS IDENTIFICATION

    ✅ CUSTOMER MANAGEMENT - Responsibility: User accounts and vehicles
      - Customer registration and profiles
      - Vehicle management (multiple vehicles per customer)
      - Preferences and settings
      - Authentication and authorization
         
    ✅ PARKING MANAGEMENT - Responsibility: Physical parking infrastructure
      - Parking lots and spots management
      - Real-time availability
      - Spot types and restrictions
      - Occupancy tracking
    
    ✅ RESERVATION & SESSIONS - Responsibility: Booking and actual parking
      - Reservation lifecycle
      - Time slot management
      - Session tracking (check-in/check-out)
      - Conflict prevention
      
    ✅ PAYMENT & PRICING - Responsibility: Money matters
      - Dynamic pricing strategies
      - Invoice generation
      - Payment processing
      - Receipts and billing

### 2. UBIQUITOUS LANGUAGE DEFINITION
      Customer Context: 
            - Customer, Vehicle, LicensePlate, VehicleType
      Parking Context: 
            - ParkingLot, ParkingSpot, ParkingSpotType, Occupancy
      Reservation Context: 
            - Reservation, TimeSlot, ParkingSession, CheckIn, CheckOut
      Payment Context: 
            - PricingStrategy, Invoice, Payment, Receipt

### 3. CONTEXT MAPPING & INTEGRATION

<img width="400" height="500" alt="image" src="https://github.com/user-attachments/assets/78111907-8bc9-4d91-93b4-f3cd8b3eaf09" />


      Customer ↔ Reservation: Partnership
      Parking → Reservation: Customer-Supplier  
      Payment → Reservation: Customer-Supplier
      Communication: REST APIs + Kafka Events


### 4. EVENT STORMING

      Commands: MakeReservation, StartParkingSession, ProcessPayment
      Events: ReservationCreated, CheckInCompleted, PaymentProcessed

### 5. Business Flow:
      1. Customer selects time slot (start/end datetime)
      2. System checks:
         - Spot availability for that exact period
         - No conflicts with existing reservations
         - Customer has valid payment method
         - Vehicle type compatibility
      3. System calculates price based on:
         - Base hourly rate
         - Vehicle type multiplier
         - Loyalty discounts
      4. Customer confirms reservation
      5. System holds payment

      Business Rules:
      - Minimum 15-minute advance booking
      - Maximum 30-day advance booking
      - 15-minute grace period for arrival
      - Auto-cancel if payment fails
      - No double-booking allowed for same spot/time

<img width="600" height="879" alt="image" src="https://github.com/user-attachments/assets/eb219378-35d3-404f-a5f8-13d38835e5b9" />
<img width="600" height="879" alt="image" src="https://github.com/user-attachments/assets/7494bcdb-e5d5-4891-aedd-8033f5a115b1" />




