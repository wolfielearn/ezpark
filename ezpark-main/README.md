### This section will be updated later, to describe how you can run the application.
- Implemented :For now only the customer registration, adding vehicles to customer and reservation creation.
  
#### Start the application
    - docker compose up
    - locally :
        - clone project
        - start postgres and kafka from the docker-compose file
        - start the application module - as spring boot

#### Endpoints:

    - Customer :
      - POST localhost:8080/customers
      - payload :
            {
                "name": "donas",
                "email": "donas@mail.com"
              }
      - GET localhost:8080/customers/{id}
  
    - Vehicles :
      - POST localhost:8080/customers/{customerId}/vehicles
      - payload :
          {
            "license":"a123-4556 ",
            "type":"CAR"
          }
          
    - Reservation :
      - POST localhost:8080/api/reservations
      - payload :
            {
            "customerId": "7aff1316-4fe0-452c-bd92-c67f69e04964",
            "spotId": "4",
            "startTime": "2025-12-25T10:30:00Z",
            "endTime": "2025-12-25T11:30:00Z"
          }
