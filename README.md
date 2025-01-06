# SDA-LAB-Final

# Smart Travelling System - Scheduling System Module

This project implements a Smart Travelling System with a focus on the Scheduling System use case. The architecture is based on a Service-Oriented Architecture (SOA) design, ensuring modularity and separation of concerns. This document provides an overview of the architecture and instructions for executing the code.

# Architecture Design

# Architecture Type

The project follows a Service-Oriented Architecture (SOA) design pattern. Below are the key components:

Service Consumers: Handles user interactions. This includes the User class, which interacts with the SchedulingSystem service.

Service Provider: The SchedulingSystem acts as the core service providing scheduling functionalities.

Service Components: Additional components like Trip, Vehicle, and Database support the main service by encapsulating specific functionalities.

Service Contracts: The methods in SchedulingSystem define the operations provided by the service.

# Key Components

User Class: Represents an individual using the system to request trips.

SchedulingSystem Class: Core service managing the trip scheduling process.

Trip Class: Represents the details of a trip (origin, destination, time, status).

Vehicle Class: Represents available vehicles for trips.

Database Class: Handles data retrieval and persistence.


# How to Execute the Code

1. Clone the Project

Copy the provided Java code into your local system, ensuring the directory structure matches the following:

SmartTravellingSystem/
 ├─ src/
     ├─ main/
         ├─ java/
             ├─ SmartTravellingSystem.java
             ├─ SchedulingSystem.java
             ├─ User.java
             ├─ Trip.java
             ├─ Vehicle.java
             ├─ Database.java

# 2. Compile and Run

# Option 1: Run from Command Line

Navigate to the project directory:

cd SmartTravellingSystem/src/main/java

Compile all Java files:

javac *.java

Execute the main class:

java SmartTravellingSystem

# Option 2: Run in an IDE

Open the project in your preferred IDE (e.g., NetBeans, IntelliJ IDEA, or Eclipse).

Ensure that the SmartTravellingSystem class contains the main method.

Run the project using the IDE's run configuration.

Execution Flow

User Interaction:

The User class initiates a trip request by calling requestTrip on the SchedulingSystem.

Trip Creation:

The SchedulingSystem creates a Trip object and checks for available vehicles via the Database.

Vehicle Assignment:

If an available vehicle is found, it is assigned to the trip. The Database saves the trip.

# Output:

The system outputs the trip details, including the assigned vehicle ID and confirmation of the scheduled trip.

