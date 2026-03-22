/**
 * Use Case 2: Basic Room Types & Static Availability
 * Goal: Demonstrate Inheritance and Abstraction.
 * Version: 2.0
 */

// 1. Abstract Class representing a generalized concept
abstract class Room {
    protected String type;
    protected double price;
    protected int capacity;

    public Room(String type, double price, int capacity) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
    }

    // Abstract method to be implemented by sub-classes (Polymorphism)
    public abstract void displayRoomInfo();
}

// 2. Concrete Classes extending the abstract Room class
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1200.0, 1);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println("Room Type: " + type + " | Price: " + price + " | Capacity: " + capacity);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2200.0, 2);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println("Room Type: " + type + " | Price: " + price + " | Capacity: " + capacity);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 5000.0, 4);
    }

    @Override
    public void displayRoomInfo() {
        System.out.println("Room Type: " + type + " | Price: " + price + " | Capacity: " + capacity);
    }
}

// 3. Application Entry Point
public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("    Book My Stay App - Room Initialization   ");
        System.out.println("=============================================");

        // Polymorphism: Using the Room type to reference different objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability representation (Simple variables as required)
        int singleAvailable = 10;
        int doubleAvailable = 5;
        int suiteAvailable = 2;

        // Displaying Details
        single.displayRoomInfo();
        System.out.println("Current Availability: " + singleAvailable);
        System.out.println("---------------------------------------------");

        doubleRoom.displayRoomInfo();
        System.out.println("Current Availability: " + doubleAvailable);
        System.out.println("---------------------------------------------");

        suite.displayRoomInfo();
        System.out.println("Current Availability: " + suiteAvailable);
        System.out.println("=============================================");
    }
}