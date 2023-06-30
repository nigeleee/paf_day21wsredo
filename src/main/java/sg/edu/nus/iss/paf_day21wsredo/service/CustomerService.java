package sg.edu.nus.iss.paf_day21wsredo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day21wsredo.models.Customers;
import sg.edu.nus.iss.paf_day21wsredo.models.Orders;
import sg.edu.nus.iss.paf_day21wsredo.repository.CustomersRepo;

@Service
public class CustomerService {
    @Autowired
    CustomersRepo custRepo;

    // public List<Customers> getAllCustomers() {
    //     return custRepo.getAllCustomers();
    // }

    public List<Customers> getAllCustomers(Integer limit, Integer offset) {
  
        return custRepo.getAllCustomersLimitOffset(limit, offset);
    }

    public Customers findCustomerById(Integer id) {
    
        return custRepo.findCustomerById(id);
    }


    public List<Orders> findOrdersByCustomerID(int id) {

        return custRepo.findOrdersByCustomerID(id);
    }
}
