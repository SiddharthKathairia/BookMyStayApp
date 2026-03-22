import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 3: Centralized Room Inventory Management
 * Goal: Replace scattered variables with a HashMap for scalable state management.
 * Version: 3.0
 */

class RoomInventory {
    // HashMap to store Room Type -> Availability Count
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    // Initialize inventory with room types and counts
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Controlled update to availability
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        }
    }

    // Retrieve availability in O(1) time
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void displayInventory() {
        System.out.println("--- Current Hotel Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " rooms available");
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - Version 3.0           ");
        System.out.println("*********************************************");

        // 1. Initialize the Inventory Component
        RoomInventory hotelInventory = new RoomInventory();

        // 2. Register Room Types (Centralizing the state)
        hotelInventory.addRoomType("Single Room", 10);
        hotelInventory.addRoomType("Double Room", 5);
        hotelInventory.addRoomType("Suite Room", 2);

        // 3. Display Initial State
        hotelInventory.displayInventory();

        // 4. Demonstrate a Controlled Update (e.g., after a booking)
        System.out.println("\n[System Update]: One Suite Room Booked.");
        hotelInventory.updateAvailability("Suite Room", 1);

        // 5. Final State Display
        System.out.println("\nUpdated Inventory State:");
        hotelInventory.displayInventory();
        System.out.println("*********************************************");
    }
}