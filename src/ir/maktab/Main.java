package ir.maktab;

import ir.maktab.enums.AccountType;
import ir.maktab.model.Account;
import ir.maktab.service.BankService;

import java.util.Scanner;

public class Main {
    static    Scanner scanner=new Scanner(System.in);
    static BankService bankService=new BankService();
    public static void main(String[] args) {

      boolean checkCuntinue=true;
      do {
          System.out.println("Select Operation :\n  1.New User\n 2.New Account\n  3.Deposit\n 4.Withdraw\n 5.Search user");
          int input=scanner.nextInt();
          switch (input){
              case 1-> createUser();
              case 2->createAccount();
              case 3->deposit();
              case 4->withdraw();
              case 5->searchUser();
              default -> checkCuntinue=tryAgain();
          }
      }while (checkCuntinue);
    }
    public static void createUser(){
        System.out.println("please enter like sample: name,family,nationalCode,phoneNumber");
        String input=scanner.next();
        bankService.createUser(input);
    }
    public static  void createAccount(){
        System.out.println("please enter nationalCode of User : ");
        String id=scanner.next();
          //search id,
        double balance;
        AccountType accountType=null;
        boolean check=true;
        do{
            System.out.println("please select Type of Account :\n 1.Current\n 2.Short_Account\n" +
                    "3.Long_Account\n 4.Saving_Account");
            int type=scanner.nextInt();
            switch (type){
                case 1 -> accountType=AccountType.CURRENT;
                case 2-> accountType=AccountType.SHORT_ACCOUNT;
                case 3->  accountType=AccountType.LONG_ACCOUNT;
                case 4->accountType=AccountType.SAVING_ACCOUNT;

                default ->check=tryAgain();

            }
        }while (check);
      do {
          System.out.println("please enter balance that more than " + Account.baseBalance + ":");
       balance = Double.parseDouble(scanner.next());
          if(balance<Account.baseBalance){
              check=tryAgain();
          }
      }while (check);
        bankService.createAccount(accountType,balance);
    }
    public  static  void  deposit(){
        System.out.println("please enter number of Account & amount for Deposit (number,amount):");
        int number=scanner.nextInt();
        double amount=Double.parseDouble(scanner.next());
        bankService.deposit(number,amount);
    }
    public static void withdraw(){
        System.out.println("please enter number of Account & amount for Withdraw(number,amount):");
        int number=scanner.nextInt();
        double amount=Double.parseDouble(scanner.next());
        bankService.withdraw(number,amount);
    }
    public static boolean tryAgain(){
        System.out.println("-----entered is not valid ------\n please try again");
        return false;
    }
    public static void searchUser(){
        boolean check=true;
        String input="";
        int chose;
        do {
            System.out.println(" select search : \n 1.By Name\n 2.By Family\n 3.By NationalCode");
           chose =scanner.nextInt();
            switch (chose){
                case 1:
                    System.out.println("Please Enter Name");
                    input=scanner.next();
                    break;
                case 2:
                    System.out.println("Please Enter Family");
                    input=scanner.next();
                    break;
                case 3:
                    System.out.println("Please Enter NationalCode");
                    input=scanner.next();
                    break;
                default:check=tryAgain();
                break;
            }
        } while (check);
        bankService.search(chose,input);
    }

}
