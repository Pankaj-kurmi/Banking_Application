package repositry;

import domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositry {
    private static final Map<String , Customer> customerbyId = new HashMap<>();




    public List<Customer> findAll() {
        return new ArrayList<>(customerbyId.values());
    }
    public static void save(Customer c) {
        customerbyId.put(c.getId(),c);
    }

}
