package unit.model;

import model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void setFirstName() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        assertSame(customer.getFirstName(), "John");
    }

    @Test
    void getFirstName() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        assertSame(customer.getFirstName(), "John");
    }

    @Test
    void setLastName() {
        Customer customer = new Customer();
        customer.setLastName("Galt");
        assertSame(customer.getLastName(), "Galt");
    }

    @Test
    void getLastName() {
        Customer customer = new Customer();
        customer.setLastName("Galt");
        assertSame(customer.getLastName(), "Galt");
    }

    @Test
    void setEmail() {
        Customer customer = new Customer();
        customer.setEmail("abs@email.com");
        assertSame(customer.getEmail(), "abs@email.com");
    }

    @Test
    void getEmail() {
        Customer customer = new Customer();
        customer.setEmail("abs@email.com");
        assertSame(customer.getEmail(), "abs@email.com");

    }

    @Test
    void setWrongEmail() {
        Customer customer = new Customer();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> customer.setEmail("dfgdgd"));
        String expectedMessage = "The email address is not valid it should match format: name@domain.com";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void customerConstructorNoArgs() {
        Customer customer = new Customer();
        assertNull(customer.getEmail());
        assertNull(customer.getFirstName());
        assertNull(customer.getLastName());
    }

    @Test
    void customerConstructor3Args() {
        String firstName = "John";
        String lastName = "Galt";
        String email = "JohnGalt@gmail.com";
        Customer customer = new Customer(firstName, lastName, email);
        assertEquals(customer.getFirstName(), firstName);
        assertEquals(customer.getLastName(), lastName);
        assertEquals(customer.getEmail(), email);
    }

    @Test
    void testToStringEmpty() {
        Customer customer = new Customer();
        String expected = "First Name: null Last Name: null Email: null";
        assertEquals(customer.toString(), expected);
    }

    @Test
    void testToStringWithFilled() {
        Customer customer = new Customer("John", "Galt", "johngalt@gmail.com");
        String expected = "First Name: John Last Name: Galt Email: johngalt@gmail.com";
        assertEquals(customer.toString(), expected);
    }
}