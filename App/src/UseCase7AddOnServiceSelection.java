import java.util.*;

/**
 * Use Case 7: Add-On Service Selection
 * Goal: Use Map<String, List<Service>> to handle one-to-many relationships.
 * Version: 7.0
 */

// 1. Domain Class for the Service
class AddOnService {
    String name;
    double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " (Rs." + cost + ")";
    }
}

// 2. The Add-On Manager
class ServiceManager {
    // Mapping Reservation ID -> List of selected services
    private Map<String, List<AddOnService>> reservationServices;

    public ServiceManager() {
        this.reservationServices = new HashMap<>();
    }

    // Attach a service to a specific reservation
    public void addService(String resId, AddOnService service) {
        // computeIfAbsent creates the List if it doesn't exist yet
        reservationServices.computeIfAbsent(resId, k -> new ArrayList<>()).add(service);
        System.out.println("Added " + service.name + " to Reservation: " + resId);
    }

    // Calculate total extra cost for a reservation
    public double calculateTotalExtra(String resId) {
        double total = 0;
        List<AddOnService> services = reservationServices.get(resId);
        if (services != null) {
            for (AddOnService s : services) {
                total += s.cost;
            }
        }
        return total;
    }

    public void displayServices(String resId) {
        System.out.println("Services for " + resId + ": " + reservationServices.getOrDefault(resId, new ArrayList<>()));
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("    Book My Stay App - Add-On Services       ");
        System.out.println("*********************************************");

        ServiceManager manager = new ServiceManager();
        String myResId = "RES-101";

        // Define available services
        AddOnService breakfast = new AddOnService("Buffet Breakfast", 500.0);
        AddOnService wifi = new AddOnService("Premium WiFi", 200.0);
        AddOnService spa = new AddOnService("Spa Treatment", 1500.0);

        // Simulate Guest selecting multiple services
        manager.addService(myResId, breakfast);
        manager.addService(myResId, wifi);
        manager.addService(myResId, spa);

        // Results
        System.out.println("\n--- Final Billing Detail ---");
        manager.displayServices(myResId);
        System.out.println("Total Add-On Cost: Rs." + manager.calculateTotalExtra(myResId));
        System.out.println("*********************************************");
    }
}