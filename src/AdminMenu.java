import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Admin menu CLI
 *
 * @author CORP\anoshkoaleksei
 */

public class AdminMenu {

    private static final AdminResource adminResource = AdminResource.getSingleton();

    /**
     * Control admin menu
     */
    public static void run() {

        String line;
        Scanner scanner = new Scanner(System.in);

        displayAdminMenu();

        try {
            do {
                line = scanner.nextLine();

                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1' -> displayAllCustomers();
                        case '2' -> displayAllRooms();
                        case '3' -> displayAllReservations();
                        case '4' -> addARoom();
                        case '5' -> MainMenu.displayMainMenu();
                        default -> System.out.println("Unknown action");
                    }
                } else {
                    System.out.println("Error: Invalid action\n");
                }

            } while (line.charAt(0) != '5' || line.length() != 1);
        } catch (Exception ex) {
            System.out.println("Empty input received. Exiting program...");
        }

    }

    /**
     * Displays admin menu
     */
    private static void displayAdminMenu() {
        System.out.print("""
                 \nAdmin Menu
                 --------------------------------------------
                 1. See all Customers
                 2. See all Rooms
                 3. See all Reservations
                 4. Add a Room
                 5. Back to Main Menu
                 --------------------------------------------
                 Please select a number for the menu option
                 """);
    }

    /**
     * Display menu that allow to enter info about a room to add to system
     */
    private static void addARoom() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter room number");
        String roomNumber = scanner.nextLine();

        System.out.println("Enter price per night");
        double price = enterPrice();

        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
        RoomType type = enterType();

        Room room = new Room(roomNumber, price, type);

        adminResource.addRoom(Collections.singletonList(room));

        System.out.println("Would you like to add another room? y/n");
        enterAnotherRoom();
    }

    private static double enterPrice() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exp) {
            System.out.println("""
                    |Invalid room price! Please, enter a valid double number.
                    |Decimals should be separated with a point (.)""");
            return enterPrice();
        }
    }

    private static RoomType enterType() {
        Scanner scanner = new Scanner(System.in);
        String roomType = scanner.nextLine();
        if (roomType.equals("1"))
            return RoomType.SINGLE;
        else if (roomType.equals("2"))
            return RoomType.DOUBLE;
        else {
            System.out.println("Invalid room type! Please, enter 1 for a single bed or 2 for a double bed");
            return enterType();
        }
    }

    private static void enterAnotherRoom() {
        Scanner scanner = new Scanner(System.in);

        try {
            String anotherRoom;

            anotherRoom = scanner.nextLine();

            while ((anotherRoom.charAt(0) != 'y' && anotherRoom.charAt(0) != 'n')
                    || anotherRoom.length() != 1) {
                System.out.println("Please enter y (Yes) or n (No)");
                anotherRoom = scanner.nextLine();
            }

            if (anotherRoom.charAt(0) == 'y') {
                addARoom();
            } else if (anotherRoom.charAt(0) == 'n') {
                displayAdminMenu();
            } else {
                enterAnotherRoom();
            }
        } catch (StringIndexOutOfBoundsException ex) {
            enterAnotherRoom();
        }
    }

    /**
     * Display all rooms
     */
    private static void displayAllRooms() {

        Collection<IRoom> rooms = adminResource.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for (IRoom room: rooms)
                System.out.println(room);
        }

        displayAdminMenu();
    }

    /**
     * Display all customers
     */
    private static void displayAllCustomers() {

        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer: customers) {
                System.out.println(customer);
            }
        }

        displayAdminMenu();
    }

    /**
     * Display all reservations
     */
    private static void displayAllReservations() {
        adminResource.displayAllReservations();
        displayAdminMenu();
    }
}

