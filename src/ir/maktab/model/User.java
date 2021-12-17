package ir.maktab.model;


import ir.maktab.enums.UserType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String name;
    private String family;
    private String nationalCode;
    @CreationTimestamp
   private Date createDate;
    @UpdateTimestamp
   private Date lastUpdate;
   @Enumerated(EnumType.STRING)
   private UserType userType;
   @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
   private List<Account> accounts=new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
  private List<Update>  updates=new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                ", userType=" + userType +
                ", accounts=" + accounts +
                ", updates=" + updates +
                '}';
    }
}
