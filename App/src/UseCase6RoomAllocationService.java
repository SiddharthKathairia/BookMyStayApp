import java.util.*;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 * Goal: Assign rooms safely using a Set to prevent double-booking.
 * Version: 6.0
 */

class AllocationService {
    // Inventory: Room Type -> Count
    private Map<String, Integer> inventory;
    // Allocated Rooms: Room Type -> Set of unique Room IDs
    private Map<String, Set<String>> allocatedRooms;

    public AllocationService() {
        this.inventory = new HashMap<>();
        this.allocatedRooms = new HashMap<>();

        // Initialize inventory
        inventory.put("Single Room", 2);
        inventory.put("Suite Room", 1);

        // Initialize Sets for each room type
        allocatedRooms.put("Single Room", new HashSet<>());
        allocatedRooms.put("Suite Room", new HashSet<>());
    }

    public void processBooking(String guestName, String roomType) {
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            // Generate a unique Room ID (e.g., SR-101)
            String roomId = roomType.substring(0, 1) + "R-" + (100 + available);

            // Set Check: Ensures uniqueness
            if (!allocatedRooms.get(roomType).contains(roomId)) {
                allocatedRooms.get(roomType).add(roomId); // Add to Set
                inventory.put(roomType, available - 1);   // Update HashMap

                System.out.println("CONFIRMED: " + guestName + " assigned to " + roomId);
            }
        } else {
            System.out.println("REJECTED: No " + roomType + "s available for " + guestName);
        }
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - Final Allocation      ");
        System.out.println("*********************************************");

        AllocationService service = new AllocationService();

        // Simulate the Queue (FIFO)
        Queue<String[]> queue = new LinkedList<>();
        queue.add(new String[]{"Siddharth", "Suite Room"});
        queue.add(new String[]{"John", "Suite Room"}); // This should fail (only 1 Suite)
        queue.add(new String[]{"Alice", "Single Room"});

        // Process the Queue
        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            service.processBooking(request[0], request[1]);
        }

        System.out.println("*********************************************");
    }
}
