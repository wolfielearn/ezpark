# ezpark
Smart Parking System 🅿️

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
   <img width="700" height="700" alt="image" src="https://github.com/user-attachments/assets/5eec46be-b5d7-4386-93f9-e1f49bc55589" />

    ✅ CUSTOMER MANAGEMENT - User accounts, vehicles, profiles 
    ✅ PARKING MANAGEMENT - Lots, spots, real-time availability  
    ✅ RESERVATION & SESSIONS - Booking lifecycle, session tracking
    ✅ PAYMENT & PRICING - Dynamic pricing, invoices, payments

### 2. UBIQUITOUS LANGUAGE DEFINITION
      Customer Context: Customer, Vehicle, LicensePlate, VehicleType
      Parking Context: ParkingLot, ParkingSpot, ParkingSpotType, Occupancy
      Reservation Context: Reservation, TimeSlot, ParkingSession, CheckIn, CheckOut
      Payment Context: PricingStrategy, Invoice, Payment, Receipt

### 3. CONTEXT MAPPING & INTEGRATION

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
         - Peak/off-peak pricing
         - Special event surcharges
         - Loyalty discounts
      4. Customer confirms reservation
      5. System holds payment authorization

      Business Rules:
      - Minimum 15-minute advance booking
      - Maximum 30-day advance booking
      - 15-minute grace period for arrival
      - Auto-cancel if payment fails
      - No double-booking allowed for same spot/time

