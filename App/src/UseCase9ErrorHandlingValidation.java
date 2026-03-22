import java.util.*;

/**
 * Use Case 9: Error Handling & Validation
 * Goal: Use Custom Exceptions and Fail-Fast design to protect system state.
 * Version: 9.0
 */

// 1. Custom Exception for Booking Failures
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// 2. The Validated Booking Service
class ValidatedBookingService {
    private Map<String, Integer> inventory;

    public ValidatedBookingService() {
        this.inventory = new HashMap<>();
        inventory.put("Single Room", 1); // Only 1 room for testing
    }

    // Fail-Fast Validation Method
    public void processBooking(String roomType) throws InvalidBookingException {
        System.out.println("Validating request for: " + roomType + "...");

        // Constraint 1: Check if Room Type exists (Case Sensitive)
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("ERROR: Room type '" + roomType + "' does not exist.");
        }

        // Constraint 2: Check Availability
        int count = inventory.get(roomType);
        if (count <= 0) {
            throw new InvalidBookingException("ERROR: No availability for '" + roomType + "'.");
        }

        // If all checks pass, update state
        inventory.put(roomType, count - 1);
        System.out.println("SUCCESS: Booking confirmed for " + roomType);
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - System Validation     ");
        System.out.println("*********************************************");

        ValidatedBookingService service = new ValidatedBookingService();

        // Testing different scenarios
        String[] testInputs = {"Single Room", "single room", "Penthouse Suite", "Single Room"};

        for (String input : testInputs) {
            try {
                service.processBooking(input);
            } catch (InvalidBookingException e) {
                // Graceful failure handling
                System.err.println(e.getMessage());
            }
            System.out.println("---------------------------------------------");
        }

        System.out.println("System remains stable after processing all inputs.");
        System.out.println("*********************************************");
    }
}