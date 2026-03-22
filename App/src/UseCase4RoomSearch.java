import java.util.HashMap;
import java.util.Map;

// Reuse the Room hierarchy from UC2
abstract class Room {
    String type;
    double price;
    public Room(String type, double price) { this.type = type; this.price = price; }
    public abstract String getDetails();
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1200.0); }
    public String getDetails() { return type + " - Rs." + price + " (1 Bed)"; }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2500.0); }
    public String getDetails() { return type + " - Rs." + price + " (2 Beds)"; }
}

// Search Service: The Read-Only Component
class RoomSearchService {
    public void searchAvailableRooms(Map<String, Integer> inventory, Map<String, Room> roomDetails) {
        System.out.println("\n--- Available Rooms Search Results ---");
        boolean found = false;

        for (String type : inventory.keySet()) {
            int count = inventory.get(type);

            // Logic: Only show if availability > 0
            if (count > 0) {
                Room details = roomDetails.get(type);
                System.out.println(details.getDetails() + " | Available: " + count);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry, no rooms are currently available.");
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - Version 4.0           ");
        System.out.println("*********************************************");

        // 1. Setup Data (State)
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // This should be filtered out!

        Map<String, Room> roomMetadata = new HashMap<>();
        roomMetadata.put("Single Room", new SingleRoom());
        roomMetadata.put("Double Room", new DoubleRoom());

        // 2. Perform Search (Read-Only)
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, roomMetadata);

        System.out.println("\nSearch Complete. System state remained unchanged.");
        System.out.println("*********************************************");
    }
}