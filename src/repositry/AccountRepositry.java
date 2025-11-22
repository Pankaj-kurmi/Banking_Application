package repositry;

import domain.Account;
import domain.Customer;

import java.util.*;

public class AccountRepositry {
    private  final Map<String , Account> accountsByNumber = new HashMap<>();
    public void save(Account account){
      accountsByNumber.put(account.getAccountnumber(), account);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accountsByNumber.values());
    }

    public Optional<Account> findByNumber(String accountnumber) {
        return Optional.ofNullable(accountsByNumber.get(accountnumber));
    }

    public List<Account> findByCustomerId(String customerId) {
        List<Account> result = new ArrayList<>();
        for (Account a : accountsByNumber.values()){
            if (a.getCustomerid().toLowerCase().contains(customerId))
                result.add(a);
        }
        return result;

    }
}
