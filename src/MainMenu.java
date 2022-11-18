import api.HotelResource;
import model.Reservation;

import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main Menu CLI
 *
 * @author CORP\anoshkoaleksei
 */
public class MainMenu {

    public static final HotelResource hotelResource = HotelResource.getSingleton();

    public static void run() {

        String line;
        Scanner scanner = new Scanner(System.in);

        displayMainMenu();

        try {
            do {
                line = scanner.nextLine();

                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1':
                            findAndReserveARoom();
                            break;
                        case '2':
                            displayReservations();
                            break;
                        case '3':
                            createAnAccount();
                            break;
                        case '4':
                            //TODO implement admin menu
                            //AdminMenu.run();
                            break;
                        case '5':
                            System.out.println("Exit");
                            break;
                        default:
                            System.out.println("Unknown action\n");
                            break;
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
         System.out.println("""
                 Welcome to the Hotel Reservation Application
                 --------------------------------------------
                 1. Find and reserve a room
                 2. See my reservations
                 3. Create an Account
                 4. Admin
                 5. Exit
                 --------------------------------------------
                 Please select a number for the menu option:
                 """);
    }

    /**
     * Display menu that allows to find and reserve a room
     */
    //TODO implement the method
    private static void findAndReserveARoom() {

        System.out.println("Enter Check-In Date mm/dd/yyyy example 02/01/2020");
        //Date checkIn = get

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
    }

    /**
     * Display menu that allows to create an account
     */
    private static void createAnAccount() {

        System.out.print("Enter Email format: name@domain.com");

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

        System.out.println("Account created successfully!");
        displayMainMenu();

    }

}
