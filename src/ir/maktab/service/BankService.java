package ir.maktab.service;

import ir.maktab.dao.AccountDao;
import ir.maktab.dao.UpdateDao;
import ir.maktab.enums.AccountType;
import ir.maktab.enums.Transaction;
import ir.maktab.model.Account;
import ir.maktab.model.Update;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class BankService {
    AccountDao accountDao=new AccountDao();
    UpdateDao updateDao=new UpdateDao();
    double balance;

    public void deposit(Account account,double amount){
        List<Account> byNumCard = accountDao.findByNumCard(account.getNumCard());
        for (Account acc:byNumCard) {
         balance = acc.getBalance();
            int deposit = accountDao.deposit(acc.getNumCard(), amount);
            if (deposit==0) {
                Update balanceUpdate= createUpdate(amount, "balance", balance, Transaction.DEPOSIT);
                updateDao.save(balanceUpdate);
                account.getUpdates().remove(0);
                account.getUpdates().add(balanceUpdate);
            }
        }


    }
    public Update createUpdate(double amount,String fieldName,double balance,Transaction transaction){
        Update update=new Update();
        update.setFieldNme(fieldName);
        update.setBeforeUpdate(String.valueOf(balance));
        update.setAfterUpdate(String.valueOf(amount));
        update.setDate(new Date());
        update.setTransaction(transaction);
      return update;
    }
    public void withdraw(Account account,double amount){

        List<Account> byNumCard = accountDao.findByNumCard(account.getNumCard());
        for (Account acc:byNumCard) {
            if (checkExpirationDateOfCard(account)) {
                balance = acc.getBalance();
                if (balance >= amount - Account.baseBalance) {
                    int deposit = accountDao.deposit(acc.getNumCard(), amount);
                    if (deposit == 0) {
                        Update balanceUpdate = createUpdate(amount, "balance", balance, Transaction.WITHDRAW);
                        updateDao.save(balanceUpdate);
                        if (account.getUpdates().size() >= 3)
                            account.getUpdates().remove(0);

                        account.getUpdates().add(balanceUpdate);
                    }
                } else
                    System.out.println("not enough money");
            }else
                System.out.println("expiration is card ended");
        }
    }
    public boolean checkExpirationDateOfCard(Account account){
        boolean check=false;
        Date date = new Date();
       if(date!=account.getExpirationDate())
           check=true;
        return check;

    }
    public Account createAccount(AccountType type,double balance){
        Account account=new Account();
        int number=accountDao.maxNumAccount();
        if(number!=0){
         account.setNumAccount(number+1);
        }else account.setNumAccount(10000);
        number=accountDao.maxNumCard();
        if(number!=0)
            account.setNumCard(number+1);
        else
            account.setNumCard(123456);
        number=accountDao.maxCvv2();
        if(number!=0)
            account.setCvv2(number+1);
        else
            account.setCvv2(1111);
        account.setBalance(balance);
        account.setAccountType(type);
        account.setOpeningDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(account.getOpeningDate());
        cal.add(Calendar.MONTH, 6);
        account.setExpirationDate(cal.getTime());

        return account;
    }
}
