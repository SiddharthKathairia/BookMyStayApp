import java.util.*;

/**
 * Use Case 8: Booking History & Reporting
 * Goal: Use a List to maintain a chronological audit trail of bookings.
 * Version: 8.0
 */

// 1. Data Model for a Confirmed Booking Record
class BookingRecord {
    String reservationId;
    String guestName;
    String roomType;
    double totalCost;

    public BookingRecord(String id, String name, String type, double cost) {
        this.reservationId = id;
        this.guestName = name;
        this.roomType = type;
        this.totalCost = cost;
    }

    @Override
    public String toString() {
        return String.format("[%s] Guest: %-10s | Room: %-12s | Total: Rs.%.2f",
                reservationId, guestName, roomType, totalCost);
    }
}

// 2. History and Reporting Service
class BookingHistoryManager {
    // List preserves insertion order (Chronological History)
    private List<BookingRecord> history;

    public BookingHistoryManager() {
        this.history = new ArrayList<>();
    }

    // Add confirmed booking to history
    public void recordBooking(BookingRecord record) {
        history.add(record);
    }

    // Generate a Summary Report (Read-only)
    public void generateReport() {
        System.out.println("\n--- FINAL BOOKING HISTORY REPORT ---");
        double grandTotal = 0;

        for (BookingRecord record : history) {
            System.out.println(record);
            grandTotal += record.totalCost;
        }

        System.out.println("------------------------------------");
        System.out.println("Total Bookings: " + history.size());
        System.out.println("Total Revenue:  Rs." + grandTotal);
        System.out.println("------------------------------------");
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - Administrative View   ");
        System.out.println("*********************************************");

        BookingHistoryManager historyManager = new BookingHistoryManager();

        // Simulate recording several confirmed bookings over time
        historyManager.recordBooking(new BookingRecord("RES-101", "Siddharth", "Suite Room", 5000.0));
        historyManager.recordBooking(new BookingRecord("RES-102", "Alice", "Single Room", 1200.0));
        historyManager.recordBooking(new BookingRecord("RES-103", "John", "Double Room", 2500.0));

        // Admin requests the report
        historyManager.generateReport();

        System.out.println("\nReport Generated Successfully. History preserved.");
        System.out.println("*********************************************");
    }
}