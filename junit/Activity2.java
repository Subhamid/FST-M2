package Activities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Activity2 {
    @Test
    void notEnoughFunds()
    {
        //create bank account
        BankAccount account = new BankAccount(9);
        //Assertion for exception.
        assertThrows(NotEnoughFundsException.class, ()->account.withdraw(10));
    }
    @Test
    void enoughFunds()
    {
        //create bank account.
        BankAccount account = new BankAccount(100);
        //Assertion for no exceptions.
        assertDoesNotThrow(()->account.withdraw(100));
    }
}
