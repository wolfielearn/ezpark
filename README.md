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
      1.  Customer Registers → Adds Vehicles
      2.  Requests Reservation → System checks availability
      3.  Availability Confirmed → Payment authorized (hold funds)
      4.  Payment Authorized → Spot reserved
      5.  Spot Reserved → Reservation confirmed
      6.  Customer checks in → Parking session starts
      7.  Customer checks out → Parking session ends
      8.  System generates invoice → Processes payment
      9.  Payment processed → Spot released
      🎮 Commands & events
            RegisterCustomer	--> CustomerRegistered
            RequestReservation-->	ReservationRequested
            CheckAvailability-->	AvailabilityConfirmed
            AuthorizePayment	System policy	PaymentAuthorized
            ReserveSpot	System policy	SpotReserved
            ConfirmReservation	System policy	ReservationConfirmed
            CheckInVehicle	User action	CheckInCompleted
            CheckOutVehicle	User action	CheckOutCompleted
            GenerateInvoice	System policy	InvoiceGenerated
            ProcessPayment	System policy	PaymentProcessed
            ReleaseSpot	System policy	SpotReleased
      📋 Policies (Business Rules)
            When [Event] happens, then [Action] should be taken:
            When Reservation Requested → Then Check Availability
            When Availability Confirmed → Then Authorize Payment
            When Payment Authorized → Then Reserve Spot
            When Spot Reserved → Then Confirm Reservation
            When Check Out Completed → Then Generate Invoice
            When Payment Processed → Then Release Spot
            When Spot Released → Then Send Receipt
### 5.Business workflow 
<img width="900" height="1500" alt="Smart Parking System - Complete Flow" src="https://github.com/user-attachments/assets/8f018891-841d-45e5-b432-466802fa4041" />




