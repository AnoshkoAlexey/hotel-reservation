package model;

import java.util.regex.Pattern;

/**
 * A class represents customer
 *
 * @author CORP\anoshkoaleksei
 */
public class Customer {

    private String firstName;
    private String lastName;
    private String email;

    /**
     * Constructor for the Customer class that accepts 3 arguments
     *
     * @param firstName first name of the customer
     * @param lastName last name of the customer
     * @param email email of the customer
     */
    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        checkEmail(email);
        this.email = email;
    }

    /**
     * Constructor for the Customer without arguments
     */
    public Customer() {
    }

    /**
     * Return string presentation of the customer
     *
     * @return string presentation of the customer
     */
    @Override
    public String toString() {
        return "First Name: " + firstName +
                " Last Name: " + lastName + " Email: " + email;
    }

    /**
     * Set customer's first name
     *
     * @param firstName customer's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get customer's first name
     *
     * @return customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set customer's last name
     *
     * @param lastName customer's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get customer's last name
     *
     * @return customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get customer's email
     *
     * @return customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set customer's email
     *
     * @param email customer's email
     * @throws IllegalArgumentException in case if email is not valid
     */
    public void setEmail(String email) throws IllegalArgumentException {
        checkEmail(email);
        this.email = email;
    }

    /**
     * Check if the first parameter is valid email
     *
     * @param email string representation of email
     * @throws IllegalArgumentException In case if the email isn't valid
     */
    private void checkEmail(String email) throws IllegalArgumentException{

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if  (!Pattern.compile(regexPattern).matcher(email).matches())
            throw new IllegalArgumentException("The email address is not valid it " +
                                               "should match format: name@domain.com");
    }
}
