package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A service class that allow to work with a collection of customers
 *
 * @author CORP\anoshkoaleksei
 */
public class CustomerService {

    private final Map<String, Customer> customers;

    private static final CustomerService SINGLETON = new CustomerService();

    /**
     * Default constructor for Customer Service class
     */
    private CustomerService() {
        customers = new HashMap<>();
    }

    /**
     * Creates singleton implementation of CustomerService class
     *
     * @return instance of CustomerService class
     */
     public static CustomerService getSingleton() {
        return SINGLETON;
    }

    /**
     * Creates a customer using parameters and add it to the collection
     *
     * @param email customer's email
     * @param firstName customer's first name
     * @param lastName customer's last name
     */
    public void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        customers.put(email, customer);
    }

    /**
     * Returns a customer with required email
     *
     * @param customerEmail customer's email
     * @return customer with required email
     */
    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    /**
     * Return collection of all customers
     *
     * @return collection of customers
     */
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

}