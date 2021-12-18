package ir.maktab.service;

import ir.maktab.dao.AccountDao;
import ir.maktab.dao.UpdateDao;
import ir.maktab.dao.UserDao;
import ir.maktab.enums.AccountType;
import ir.maktab.enums.Transaction;
import ir.maktab.enums.UserType;
import ir.maktab.model.Account;
import ir.maktab.model.Update;
import ir.maktab.model.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class BankService {
    AccountDao accountDao = new AccountDao();
    UpdateDao updateDao = new UpdateDao();
    UserDao userDao=new UserDao();
    double balance;


    public void createUser(String info){
        String[] split = info.split(",");
        User.UserBuilder userBuilder = User.UserBuilder.anUser();
        User user = userBuilder.withName(split[0]).withFamily(split[1])
                .withNationalCode(split[2]).withPhoneNumber(split[3])
                .withUserType(UserType.NONE).build();
        userDao.save(user);
    }
    public void deposit(int number, double amount) {
        List<Account> byNumCard = accountDao.findByNumCard(number);
        for (Account acc : byNumCard) {
            balance = acc.getBalance();
            int deposit = accountDao.deposit(acc.getNumCard(), amount);
            if (deposit == 0) {
                Update balanceUpdate = createUpdate(amount, "balance", balance, Transaction.DEPOSIT);
                updateDao.save(balanceUpdate);
                acc.getUpdates().remove(0);
                acc.getUpdates().add(balanceUpdate);
            }
        }


    }

    public Update createUpdate(double amount, String fieldName, double balance, Transaction transaction) {
        Update update = new Update();
        update.setFieldNme(fieldName);
        update.setBeforeUpdate(String.valueOf(balance));
        update.setAfterUpdate(String.valueOf(amount));
        update.setDate(new Date());
        update.setTransaction(transaction);
        return update;
    }

    public void withdraw(int number, double amount) {

        List<Account> byNumCard = accountDao.findByNumCard(number);
        for (Account acc : byNumCard) {
            if (checkExpirationDateOfCard(acc)) {
                balance = acc.getBalance();
                if (balance >= amount - Account.baseBalance) {
                    int deposit = accountDao.deposit(acc.getNumCard(), amount);
                    if (deposit == 0) {
                        Update balanceUpdate = createUpdate(amount, "balance", balance, Transaction.WITHDRAW);
                        updateDao.save(balanceUpdate);
                        if (acc.getUpdates().size() >= 3)
                            acc.getUpdates().remove(0);

                        acc.getUpdates().add(balanceUpdate);
                    }
                } else
                    System.out.println("not enough money");
            } else
                System.out.println("expiration is card ended");
        }
    }

    public boolean checkExpirationDateOfCard(Account account) {
        boolean check = false;
        Date date = new Date();
        if (date != account.getExpirationDate())
            check = true;
        return check;

    }

    public Account createAccount(AccountType type, double balance) {
        Account account = new Account();
        int number = accountDao.maxNumAccount();
        if (number != 0) {
            account.setNumAccount(number + 1);
        } else account.setNumAccount(10000);
        number = accountDao.maxNumCard();
        if (number != 0)
            account.setNumCard(number + 1);
        else
            account.setNumCard(123456);
        number = accountDao.maxCvv2();
        if (number != 0)
            account.setCvv2(number + 1);
        else
            account.setCvv2(1111);
        account.setBalance(balance);
        account.setAccountType(type);
        Calendar cal = Calendar.getInstance();
        cal.setTime(account.getOpeningDate());
        cal.add(Calendar.MONTH, 6);
        account.setExpirationDate(cal.getTime());

        return account;
    }
    public  void search(int select,String bySearch){
        switch (select){
            case 1-> userDao.findByName(bySearch);
            case 2-> userDao.findByFamily(bySearch);
            case 3-> userDao.findByNationalCode(bySearch);
        }
    }
}
