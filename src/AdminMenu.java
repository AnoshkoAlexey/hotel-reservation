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



        try {
            do {
                displayAdminMenu();
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
        enterAnotherRoom();
    }

    /**
     * Dialog that check correctness of entered price
     *
     * @return a price of a room per night
     */
    private static double enterPrice() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exp) {
            System.out.println("""
                    Invalid value of the room price! Please, enter a valid double number.
                    (use a point (.) to separate fractional part)""");
            return enterPrice();
        }
    }

    /**
     * Display dialogue that request input value and check if it is a valid room's type
     * @return the enumeration value of room's type
     */
    private static RoomType enterType() {

        Scanner scanner = new Scanner(System.in);
        String roomType = scanner.nextLine();

        try {
            return RoomType.valueOfLabel(roomType);
        } catch (IllegalArgumentException e) {
            System.out.println("""
                    |Invalid room type!
                    Please, choose 1 for a single bed or 2 for double bed""");
            return enterType();
        }
    }

    /**
     * Accept input value and check if another room should be created
     */
    private static void enterAnotherRoom() {

        System.out.println("Would you like to add another room? y/n");

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
            System.out.println("No rooms found");
        } else {
            System.out.println("Rooms");
            rooms.forEach(System.out::println);
        }
    }

    /**
     * Display all customers
     */
    private static void displayAllCustomers() {

        Collection<Customer> customers = adminResource.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("No customers found");
        } else {
            System.out.println("Customers");
            customers.forEach(System.out::println);
        }

    }

    /**
     * Display all reservations
     */
    private static void displayAllReservations() {
        adminResource.displayAllReservations();
    }
}