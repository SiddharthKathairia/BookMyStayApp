import java.util.*;

/**
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 * Goal: Use 'synchronized' to prevent race conditions during simultaneous bookings.
 * Version: 11.0
 */

class ConcurrentInventory {
    private int availableRooms = 1; // Only one room for the race!

    // The 'synchronized' keyword ensures only one thread enters this method at a time
    public synchronized boolean bookRoom(String guestName) {
        System.out.println(guestName + " is checking availability...");

        if (availableRooms > 0) {
            // Simulate processing time to increase chance of a race condition
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            availableRooms--;
            System.out.println("SUCCESS: Room allocated to " + guestName);
            return true;
        } else {
            System.out.println("FAILED: No rooms left for " + guestName);
            return false;
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - Concurrency Test      ");
        System.out.println("*********************************************");

        ConcurrentInventory sharedInventory = new ConcurrentInventory();

        // Create multiple threads (Guests) attempting to book the SAME room
        Thread guest1 = new Thread(() -> sharedInventory.bookRoom("Siddharth"));
        Thread guest2 = new Thread(() -> sharedInventory.bookRoom("John"));
        Thread guest3 = new Thread(() -> sharedInventory.bookRoom("Alice"));

        // Start all guests simultaneously
        guest1.start();
        guest2.start();
        guest3.start();

        // Wait for all threads to finish
        try {
            guest1.join();
            guest2.join();
            guest3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("*********************************************");
        System.out.println("Final System State: Consistent.");
    }
}