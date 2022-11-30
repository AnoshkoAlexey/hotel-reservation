import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Main Menu CLI
 *
 * @author CORP\anoshkoaleksei
 */
public class MainMenu {

    public static final HotelResource hotelResource = HotelResource.getSingleton();
    private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

    /**
     * Launch main menu
     */
    public static void run() {

        String line;
        Scanner scanner = new Scanner(System.in);

        try {
            do {
                displayMainMenu();
                line = scanner.nextLine();
                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1' -> findARoom();
                        case '2' -> displayReservations();
                        case '3' -> createAccount();
                        case '4' -> AdminMenu.run();
                        case '5' -> System.out.println("Exit");
                        default -> System.out.println("Invalid input");
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
     * Display main menu
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
     * Display dialogue that allows to fina a room and suggest to reserve it
     *
     */
    private static void findARoom() {

        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");
        Date checkIn = getInputDate();

        System.out.println("Enter Check-Out Date mm/dd/yyyy example 02/21/2020");
        Date checkOut = getInputDate();

        if (!Objects.isNull(checkIn) && !Objects.isNull(checkOut) && checkOut.after(checkIn)) {

            Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);
            displayRooms(availableRooms);

            if (!availableRooms.isEmpty()) {
                reserveRoom(availableRooms, checkIn, checkOut);
            } else {
                Collection<IRoom> alternativeRooms = hotelResource.findAlternativeRooms(checkIn, checkOut);

                if (alternativeRooms.isEmpty()) {
                    System.out.println("No rooms found.");
                } else {
                    final Date alternativeCheckIn = hotelResource.addDays(checkIn);
                    final Date alternativeCheckOut = hotelResource.addDays(checkOut);
                    System.out.println("We've only found rooms on alternative dates:" +
                            "\nCheck-In Date: " + alternativeCheckIn +
                            "\nCheck-out Date:" + alternativeCheckOut);

                    displayRooms(alternativeRooms);
                    reserveRoom(alternativeRooms, alternativeCheckIn, alternativeCheckOut);
                }
            }
        }
    }

    /**
     * Display dialogue that allows to enter a date, and check if it is in a valid format
     *
     * @return the date transormated from String to Date class
     */
    private static Date getInputDate() {
        Scanner scanner = new Scanner(System.in);
        try {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Error: Invalid date.");
            getInputDate();
        }

        return null;
    }

    /**
     * Display dialogue that allows to reserve a room
     *
     * @param checkInDate a first date of the reservation
     * @param checkOutDate a last date of the reservation
     * @param rooms collection of rooms
     */
    private static void reserveRoom(Collection<IRoom> rooms, Date checkInDate, Date checkOutDate) {

        Scanner scanner = new Scanner(System.in);

        if (!doWantToBook()) {
            return;
        }

        if(!haveAccount()) {
            return;
        }

        System.out.println("Enter Email format: name@domain.com");
        String customerEmail = scanner.nextLine();

        if (Objects.isNull(hotelResource.getCustomer(customerEmail))) {
            System.out.println("Customer not found");
        } else {
            System.out.println("What room number would you like to reserve?");
            String roomNumber = scanner.nextLine();

            if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {

                IRoom room = hotelResource.getRoom(roomNumber);
                Reservation reservation = hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
                System.out.println("Reservation created successfully!");
                System.out.println(reservation);
            } else {
                System.out.println("The room is not available. Start reservation again.");
            }
        }

    }

    /**
     * Display menu that ask if a customer wants to book a room
     *
     * @return true if the customer wants to book, false otherwide
     */
    public static boolean doWantToBook() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to book? y/n");
        final String bookRoom = scanner.nextLine();

        if ("y".equals(bookRoom)) {
            return true;
        }

        if ("n".equals(bookRoom)) {
            return false;
        }

        doWantToBook();
        return false;

    }

    /**
     * Display menu that ask if a customer has an account
     *
     * @return true if a customer has account, false otherwise
     */

    public static boolean haveAccount() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you have an account with us? y/n");
        String haveAccount = scanner.nextLine();

        if ("y".equals(haveAccount)) {
            return true;
        }

        if ("n".equals(haveAccount)) {
            System.out.println("Please, create an account.");
            return false;
        }

        haveAccount();
        return false;

    }

    /**
     * Display reservations
     */
    private static void displayReservations() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Email format: name@domain.com");
        String customerEmail = scanner.nextLine();

        Collection< Reservation> reservations = hotelResource.getCustomerReservations(customerEmail);
        if (Objects.isNull(reservations) || reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("Reservations");
            reservations.forEach(System.out::println);
        }
    }

    /**
     * Display menu that allows to create an account
     */
    private static void createAccount() {

        System.out.println("Enter Email format: name@domain.com");

        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();

        System.out.println("First Name:");
        String firstName = scanner.nextLine();

        System.out.println("Last Name:");
        String lastName = scanner.nextLine();

        try {
            hotelResource.createCustomer(email, firstName, lastName);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            createAccount();
        }
    }

    /**
     * Display rooms
     *
     * @param rooms collection of rooms
     */
    private static void displayRooms(Collection<IRoom> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

}
