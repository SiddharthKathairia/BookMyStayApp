import java.io.*;
import java.util.*;

/**
 * Use Case 12: Data Persistence & System Recovery
 * Goal: Use Serialization to save/load system state from a file.
 * Version: 12.0
 */

// 1. Data Model must implement Serializable to be saved to disk
class HotelState implements Serializable {
    private static final long serialVersionUID = 1L;
    Map<String, Integer> inventory;
    List<String> bookingHistory;

    public HotelState(Map<String, Integer> inventory, List<String> history) {
        this.inventory = inventory;
        this.bookingHistory = history;
    }
}

public class UseCase12DataPersistenceRecovery {
    private static final String FILE_NAME = "hotel_state.ser";

    public static void saveSystemState(HotelState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("SYSTEM: State serialized and saved to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("ERROR: Could not save state: " + e.getMessage());
        }
    }

    public static HotelState loadSystemState() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("SYSTEM: No saved state found. Starting fresh.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("SYSTEM: Restoring state from " + FILE_NAME + "...");
            return (HotelState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Recovery failed: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - System Recovery       ");
        System.out.println("*********************************************");

        // 1. Attempt to Recovery
        HotelState currentState = loadSystemState();

        if (currentState == null) {
            // Initializing fresh state if no file exists
            Map<String, Integer> initialInventory = new HashMap<>();
            initialInventory.put("Suite Room", 5);
            List<String> initialHistory = new ArrayList<>();
            initialHistory.add("Initial System Startup");
            currentState = new HotelState(initialInventory, initialHistory);
        }

        // 2. Simulate an update
        System.out.println("Current Inventory: " + currentState.inventory);
        currentState.inventory.put("Suite Room", currentState.inventory.get("Suite Room") - 1);
        currentState.bookingHistory.add("Booking at " + new Date());

        // 3. Persist before shutdown
        saveSystemState(currentState);

        System.out.println("*********************************************");
        System.out.println("Final History Size: " + currentState.bookingHistory.size());
        System.out.println("Check your folder for 'hotel_state.ser'!");
    }
}