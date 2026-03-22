import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request (First-Come-First-Served)
 * Goal: Handle multiple requests fairly using a Queue (FIFO).
 * Version: 5.0
 */

// 1. Domain Class representing a Guest's intent to book
class ReservationRequest {
    private String guestName;
    private String roomType;

    public ReservationRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Booking Request [Guest: " + guestName + ", Room: " + roomType + "]";
    }
}

// 2. The Booking Queue Manager
class BookingQueue {
    // Queue follows FIFO: First-In, First-Out
    private Queue<ReservationRequest> requestQueue;

    public BookingQueue() {
        this.requestQueue = new LinkedList<>();
    }

    // Add request to the back of the line
    public void enqueueRequest(ReservationRequest request) {
        requestQueue.add(request);
        System.out.println("Enqueued: " + request);
    }

    // Display the current waiting line
    public void displayQueue() {
        System.out.println("\n--- Current Booking Request Queue ---");
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            for (ReservationRequest req : requestQueue) {
                System.out.println(req);
            }
        }
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - Version 5.0           ");
        System.out.println("*********************************************");

        // Initialize the Queue System
        BookingQueue bookingSystem = new BookingQueue();

        // Simulate guests submitting requests in order
        System.out.println("Receiving incoming requests...");
        bookingSystem.enqueueRequest(new ReservationRequest("Siddharth", "Suite Room"));
        bookingSystem.enqueueRequest(new ReservationRequest("John", "Single Room"));
        bookingSystem.enqueueRequest(new ReservationRequest("Alice", "Double Room"));

        // Display the queue to show order is preserved
        bookingSystem.displayQueue();

        System.out.println("\nStatus: Requests stored safely. No inventory changed.");
        System.out.println("*********************************************");
    }
}