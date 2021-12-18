package ir.maktab.model;


import ir.maktab.enums.AccountType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Account {
    @Transient
    final static public int baseBalance = 10000;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numAccount;
    private int numCard;
    private int cvv2;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @CreationTimestamp
    private Date openingDate;
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Update> updates = new ArrayList<>();

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", numAccount=" + numAccount +
                ", numCard=" + numCard +
                ", cvv2=" + cvv2 +
                ", balance=" + balance +
                ", accountType=" + accountType +
                ", openingDate=" + openingDate +
                ", expirationDate=" + expirationDate +
                ", updates=" + updates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && numAccount == account.numAccount && numCard == account.numCard && cvv2 == account.cvv2 && Double.compare(account.balance, balance) == 0 && accountType == account.accountType && Objects.equals(openingDate, account.openingDate) && Objects.equals(expirationDate, account.expirationDate) && Objects.equals(updates, account.updates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numAccount, numCard, cvv2, balance, accountType, openingDate, expirationDate, updates);
    }
}
