-- Initialize EZPark Database Schemas
-- This runs automatically when PostgreSQL starts

-- creation of schema is delegated to resources/schema.sql
-- Create schemas for each bounded context
CREATE SCHEMA IF NOT EXISTS customer;
CREATE SCHEMA IF NOT EXISTS reservation;
CREATE SCHEMA IF NOT EXISTS parking;
CREATE SCHEMA IF NOT EXISTS payment;

-- Create application user with permissions
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'ezpark_user') THEN
        CREATE USER ezpark_user WITH PASSWORD 'ezpark123';
    END IF;
END
$$;

-- Grant permissions to schemas
GRANT ALL ON SCHEMA customer TO ezpark_user;
GRANT ALL ON SCHEMA reservation TO ezpark_user;
GRANT ALL ON SCHEMA parking TO ezpark_user;
GRANT ALL ON SCHEMA payment TO ezpark_user;

-- Grant usage on public schema (for Hibernate, etc.)
GRANT USAGE ON SCHEMA public TO ezpark_user;

-- Set default search path (optional)
ALTER ROLE ezpark_user SET search_path TO customer,reservation,parking,payment,public;

-- Log successful initialization
DO $$
BEGIN
    RAISE NOTICE 'EZPark database schemas initialized successfully';
END
$$;