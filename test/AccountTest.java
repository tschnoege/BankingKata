import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public void addAmount() throws ParseException {
        final String pattern = "dd.MM.yyyy";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date valuta = simpleDateFormat.parse("01.03.2020");

        final Account account = new Account();
        account.addAmount(valuta, 500);

        List<AccountMovement> movements = account.getMovements(valuta);
        assertEquals(1, movements.size());

        AccountMovement movement = movements.get(0);
        assertEquals(500, movement.getAmount());
        assertEquals(valuta, movement.getValuta());

        account.deposit(100);
        assertEquals(500, movement.getAmount());
        assertEquals(valuta, movement.getValuta());
    }

    @Test
    public void balance() {
        final Account account = new Account();

        assertEquals(0, account.getBalance());

        account.deposit(100);
        assertEquals(100, account.getBalance());

        account.withdraw(30);
        assertEquals(70, account.getBalance());

        account.withdraw(80);
        assertEquals(-10, account.getBalance());
    }

    @Test
    public void withdraw()
    {
        final Account account = new Account();

        account.deposit(500);
        account.withdraw(100);
        assertEquals(400, account.getBalance());

        account.withdraw(50);
        assertEquals(350, account.getBalance());
    }

    @Test
    public void getMovements() throws ParseException {
        final String pattern = "dd.MM.yyyy";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        final Date valuta1 = simpleDateFormat.parse("24.12.2015");
        final Date valuta2 = simpleDateFormat.parse("16.07.2016");

        final Account account = new Account();
        AccountMovement movement;

        account.addAmount(valuta1, 500);
        account.addAmount(valuta1, -100);
        account.addAmount(valuta2, 30);

        List<AccountMovement> movements = account.getAllMovements();
        assertEquals(3, movements.size());

        movements = account.getMovements(valuta1);
        assertEquals(2, movements.size());
        movement = movements.get(0);
        assertEquals(500, movement.getAmount());
        assertEquals(valuta1, movement.getValuta());
        movement = movements.get(1);
        assertEquals(-100, movement.getAmount());
        assertEquals(valuta1, movement.getValuta());

        movements = account.getMovements(valuta2);
        assertEquals(1, movements.size());
        movement = movements.get(0);
        assertEquals(30, movement.getAmount());
    }

    @Test
    public void printStatement() throws ParseException {
        final Account account = new Account();
        final String pattern = "dd.MM.yyyy";
        final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        final Date valuta1 = dateFormat.parse("24.12.2015");
        final Date valuta2 = dateFormat.parse("16.07.2016");

        account.addAmount(valuta1, 500);
        account.addAmount(valuta2, 100);

        String statement = "Date\t\tAmount\tBalance\n" + dateFormat.format(valuta1) +
                "\t" +
                500 +
                "\t\t" +
                500 +
                "\n" +
                dateFormat.format(valuta2) +
                "\t" +
                100 +
                "\t\t" +
                600 +
                "\n";

        assertEquals(statement, account.printStatement());
    }
}
