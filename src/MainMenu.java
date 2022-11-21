import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main Menu CLI
 *
 * @author CORP\anoshkoaleksei
 */
public class MainMenu {

    public static final HotelResource hotelResource = HotelResource.getSingleton();
    private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

    public static void run() {

        String line;
        Scanner scanner = new Scanner(System.in);

        displayMainMenu();

        try {
            do {
                line = scanner.nextLine();

                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1' -> findAndReserveARoom();
                        case '2' -> displayReservations();
                        case '3' -> createAnAccount();
                        case '4' -> AdminMenu.run();
                        case '5' -> System.out.println("Exit");
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
     * Displays main menu
     */
    public static void displayMainMenu() {
         System.out.print("""
                 Welcome to the Hotel Reservation Application
                 
                 --------------------------------------------
                 1. Find and reserve a room
                 2. See my reservations
                 3. Create an Account
                 4. Admin
                 5. Exit
                 --------------------------------------------
                 Please select a number for the menu option
                 """);
    }

    /**
     * Display menu that allows to find and reserve a room
     */
    private static void findAndReserveARoom() {

        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");

        Scanner scanner = new Scanner(System.in);
        Date checkIn = getInputDate();

        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Date checkOut = getInputDate();

        if (checkIn != null && checkOut != null) {
            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                System.out.println("No rooms found.");
            } else {
                printRooms(availableRooms);
                reserveRoom(scanner, checkIn, checkOut, availableRooms);
            }
        }
    }

    private static Date getInputDate() {
        Scanner scanner = new Scanner(System.in);
        try {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.");
            findAndReserveRoom();
        }

        return null;
    }

    private static void findAndReserveRoom() {
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");
        Date checkIn = getInputDate();

        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Date checkOut = getInputDate();

        if (checkIn != null && checkOut != null) {
            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);

            if (availableRooms.isEmpty()) {
                    System.out.println("No rooms found.");
            } else {
                printRooms(availableRooms);
                reserveRoom(scanner, checkIn, checkOut, availableRooms);
            }
        }
    }

    /**
     * Display reservations
     */
    private static void displayReservations() {

        System.out.println("Enter your Email format: name@domain.com");

        Scanner scanner = new Scanner(System.in);
        String customerEmail = scanner.nextLine();

        Collection< Reservation> reservations = hotelResource.getCustomerReservations(customerEmail);
        if (Objects.isNull(reservations) || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }

        displayMainMenu();
    }

    /**
     * Display menu that allows to create an account
     */
    private static void createAnAccount() {

        System.out.println("Enter Email format: name@domain.com");

        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();

        System.out.println("First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Last Name:");
        String lastName = scanner.nextLine();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            createAnAccount();
        }

        displayMainMenu();
    }

    private static void reserveRoom(final Scanner scanner, final Date checkInDate,
                                    final Date checkOutDate, final Collection<IRoom> rooms) {
        System.out.println("Would you like to book? y/n");
        final String bookRoom = scanner.nextLine();

        if ("y".equals(bookRoom)) {
            System.out.println("Do you have an account with us? y/n");
            final String haveAccount = scanner.nextLine();

            if ("y".equals(haveAccount)) {
                System.out.println("Enter Email format: name@domain.com");
                final String customerEmail = scanner.nextLine();

                if (hotelResource.getCustomer(customerEmail) == null) {
                    System.out.println("Customer not found.\nYou may need to create a new account.");
                } else {
                    System.out.println("What room number would you like to reserve?");
                    final String roomNumber = scanner.nextLine();

                    if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        final IRoom room = hotelResource.getRoom(roomNumber);

                        final Reservation reservation = hotelResource
                                .bookARoom(customerEmail, room, checkInDate, checkOutDate);
                        System.out.println("Reservation created successfully!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Error: room number not available.\nStart reservation again.");
                    }
                }

                displayMainMenu();
            } else {
                System.out.println("Please, create an account.");
                displayMainMenu();
            }
        } else if ("n".equals(bookRoom)){
            displayMainMenu();
        } else {
            reserveRoom(scanner, checkInDate, checkOutDate, rooms);
        }
    }

    private static void printRooms(Collection<IRoom> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

}
