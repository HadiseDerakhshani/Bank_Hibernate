package ir.maktab.model;


import ir.maktab.enums.AccountType;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Account {
    private  int id;
    private  int numAccount;
    private  int numCard;
    private  int cvv2;
    private  double balance;
    private AccountType accountType;
    private Date openingDate;
    private Date expirationDate;
    private List<Update> updates=new ArrayList<>();
}
