package Lab_Final;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// User Class
class User {
    private String userID;
    private String name;
    private String email;

    public User(String userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    public void requestTrip(SchedulingSystem schedulingSystem, String origin, String destination, LocalDateTime time) {
        Trip trip = schedulingSystem.createTrip(this, origin, destination, time);
        if (trip != null) {
            System.out.println("Trip scheduled successfully: " + trip.getTripID());
        } else {
            System.out.println("Failed to schedule trip.");
        }
    }
}

// Trip Class
class Trip {
    private String tripID;
    private String origin;
    private String destination;
    private LocalDateTime scheduleTime;
    private String status;
    private Vehicle assignedVehicle;

    public Trip(String tripID, String origin, String destination, LocalDateTime scheduleTime) {
        this.tripID = tripID;
        this.origin = origin;
        this.destination = destination;
        this.scheduleTime = scheduleTime;
        this.status = "Pending";
    }

    public String getTripID() {
        return tripID;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.assignedVehicle = vehicle;
        this.status = "Scheduled";
    }
}

// Vehicle Class
class Vehicle {
    private String vehicleID;
    private String type;
    private int capacity;
    private boolean availability;

    public Vehicle(String vehicleID, String type, int capacity, boolean availability) {
        this.vehicleID = vehicleID;
        this.type = type;
        this.capacity = capacity;
        this.availability = availability;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

// SchedulingSystem Class
class SchedulingSystem {
    private Database database;

    public SchedulingSystem(Database database) {
        this.database = database;
    }

    public Trip createTrip(User user, String origin, String destination, LocalDateTime time) {
        String tripID = "TRIP" + System.currentTimeMillis(); // Generate a unique Trip ID
        Trip trip = new Trip(tripID, origin, destination, time);
        Vehicle vehicle = findAvailableVehicle(trip);

        if (vehicle != null) {
            assignVehicleToTrip(trip, vehicle);
            database.saveTrip(trip);
            return trip;
        } else {
            System.out.println("No available vehicles for the trip.");
            return null;
        }
    }

    public Vehicle findAvailableVehicle(Trip trip) {
        List<Vehicle> availableVehicles = database.getAvailableVehicles();
        for (Vehicle vehicle : availableVehicles) {
            if (vehicle.isAvailable()) {
                return vehicle;
            }
        }
        return null;
    }

    public void assignVehicleToTrip(Trip trip, Vehicle vehicle) {
        vehicle.setAvailability(false); // Mark vehicle as unavailable
        trip.assignVehicle(vehicle);
        System.out.println("Vehicle " + vehicle.getVehicleID() + " assigned to trip " + trip.getTripID());
    }
}

// Database Class
class Database {
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();

    public Database() {
        // Initialize with some sample vehicles
        vehicles.add(new Vehicle("V1", "Car", 4, true));
        vehicles.add(new Vehicle("V2", "Van", 8, true));
        vehicles.add(new Vehicle("V3", "Bus", 20, true));
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public void saveTrip(Trip trip) {
        trips.add(trip);
        System.out.println("Trip " + trip.getTripID() + " saved to database.");
    }
}

// Main Class to Test the System
public class SmartTravellingSystem {
    public static void main(String[] args) {
        Database database = new Database();
        SchedulingSystem schedulingSystem = new SchedulingSystem(database);

        User user = new User("U1", "Alice", "alice@example.com");
        user.requestTrip(schedulingSystem, "Location A", "Location B", LocalDateTime.now().plusHours(1));
    }
}
