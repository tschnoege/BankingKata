import java.util.Date;

public class AccountMovement {
    Date valuta;
    int amount;

    public AccountMovement(Date valuta, int amount)
    {
        this.valuta = valuta;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Date getValuta() {
        return valuta;
    }
}
