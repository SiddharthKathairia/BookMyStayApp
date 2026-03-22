import java.util.*;

/**
 * Use Case 10: Booking Cancellation & Inventory Rollback
 * Goal: Use a Stack (LIFO) to safely reverse system state and restore inventory.
 * Version: 10.0
 */

class CancellationService {
    private Map<String, Integer> inventory;
    private Stack<String> allocationHistory; // LIFO for Rollback

    public CancellationService() {
        this.inventory = new HashMap<>();
        this.allocationHistory = new Stack<>();

        // Initial State
        inventory.put("Suite Room", 1);
    }

    // Simulate a successful booking
    public void bookRoom(String roomType) {
        int count = inventory.get(roomType);
        if (count > 0) {
            inventory.put(roomType, count - 1);
            String bookingId = roomType + "-RES-" + System.currentTimeMillis() % 1000;
            allocationHistory.push(bookingId); // Push to stack
            System.out.println("BOOKED: " + bookingId + ". Inventory: " + (count - 1));
        }
    }

    // The Rollback (Cancellation) Logic
    public void cancelLastBooking(String roomType) {
        System.out.println("\nInitiating Cancellation Request...");

        // Validation: Can't cancel if no bookings exist
        if (!allocationHistory.isEmpty()) {
            String lastBooking = allocationHistory.pop(); // LIFO: Remove the last one

            // State Reversal: Increment Inventory
            int currentCount = inventory.get(roomType);
            inventory.put(roomType, currentCount + 1);

            System.out.println("CANCELLED: " + lastBooking);
            System.out.println("ROLLBACK SUCCESS: " + roomType + " inventory restored to " + (currentCount + 1));
        } else {
            System.out.println("ERROR: No active bookings found to cancel.");
        }
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - State Rollback        ");
        System.out.println("*********************************************");

        CancellationService service = new CancellationService();

        // 1. Successful Booking
        service.bookRoom("Suite Room");

        // 2. Perform Cancellation (Rollback)
        service.cancelLastBooking("Suite Room");

        // 3. Attempt Invalid Cancellation
        service.cancelLastBooking("Suite Room");

        System.out.println("*********************************************");
    }
}