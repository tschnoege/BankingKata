import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    @Test
    public void deposit()
    {
        final Account account = new Account();

        account.deposit(500);
        assertEquals(500, account.getBalance());

        account.deposit(100);
        assertEquals(600, account.getBalance());
    }

    @Test
    public void withdraw()
    {
        final Account account = new Account();

        account.deposit(500);
        account.withdraw(100);
        assertEquals(400, account.getBalance());

    }
}
