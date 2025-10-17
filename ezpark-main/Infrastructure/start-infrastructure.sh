#!/bin/bash

echo "🚀 Starting EZPark Infrastructure..."
echo "======================================"

# Stop and remove existing containers
echo "🛑 Stopping any existing containers..."
docker-compose down

# Start services
echo "🐳 Starting PostgreSQL..."
docker-compose up -d

# Wait for PostgreSQL to be ready
echo "⏳ Waiting for PostgreSQL to be ready..."
until docker exec ezpark-postgres pg_isready -U postgres; do
  sleep 2
done

echo ""
echo " INFRASTRUCTURE READY!"
echo "======================================"
echo "📊 Database: localhost:5432/ezpark_db"
echo "👤 User: postgres / password"
echo "🗂️  Schemas: customer, reservation, parking, payment"
echo ""
echo "💡 Test connection:"
echo "   psql -h localhost -U postgres -d ezpark_db"
echo ""