package ir.maktab.model;

import ir.maktab.enums.Transaction;
import jdk.jfr.Timestamp;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
public class Update {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fieldNme;
    private String beforeUpdate;
    private String afterUpdate;
    @Enumerated(EnumType.STRING)
    private Transaction transaction;
    @Timestamp
    private Date date;

    @Override
    public String toString() {
        return "Update{" +
                "id=" + id +
                ", fieldNme='" + fieldNme + '\'' +
                ", beforeUpdate='" + beforeUpdate + '\'' +
                ", afterUpdate='" + afterUpdate + '\'' +
                ", transaction=" + transaction +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Update update = (Update) o;
        return id == update.id && Objects.equals(fieldNme, update.fieldNme) && Objects.equals(beforeUpdate, update.beforeUpdate) && Objects.equals(afterUpdate, update.afterUpdate) && transaction == update.transaction && Objects.equals(date, update.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fieldNme, beforeUpdate, afterUpdate, transaction, date);
    }
}
