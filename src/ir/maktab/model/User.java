package ir.maktab.model;


import ir.maktab.enums.UserType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private  int id;
    private String name;
    private String family;
    private String nationalCode;
   private Date createDate;
   private Date lastUpdate;
   private UserType userType;
   private List<Account> accounts=new ArrayList<>();
  private List<Update>  updates=new ArrayList<>();

}
