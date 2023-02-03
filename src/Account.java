import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private final List<AccountMovement> movements = new ArrayList<>();

    public void addAmount(Date valuta, int amount) {
        movements.add(new AccountMovement(valuta, amount));
    }

    public void deposit(int amount) {
        this.addAmount(new Date(), amount);
    }

    public void withdraw(int amount) {
        this.addAmount(new Date(), -amount);
    }

    public int getBalance() {
        int balance = 0;

        for (AccountMovement movement : movements) {
            balance += movement.getAmount();
        }

        return balance;
    }

    public List<AccountMovement> getMovements(Date valuta) {
        assert valuta != null;

        return movements.stream()
                .filter(accountMovement -> accountMovement.getValuta().equals(valuta))
                .toList();
    }

    public List<AccountMovement> getAllMovements() {
        return movements.stream()
                .toList();
    }

    public String printStatement() {
        int balance = 0;
        final String pattern = "dd.MM.yyyy";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        final StringBuilder statement = new StringBuilder("Date\t\tAmount\tBalance\n");

        for (AccountMovement movement : movements) {
            final int amount = movement.getAmount();

            balance += amount;

            statement.append(simpleDateFormat.format(movement.getValuta()));
            statement.append("\t");
            statement.append(movement.getAmount());
            statement.append("\t\t");
            statement.append(balance);
            statement.append("\n");
        }

        return statement.toString();
    }
}
