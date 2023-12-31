package sg.edu.nus.iss.paf_day21wsredo.repository;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import sg.edu.nus.iss.paf_day21wsredo.models.Customers;
import sg.edu.nus.iss.paf_day21wsredo.models.Orders;

@Repository
public class CustomersRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String findAllLimitOffsetSQL = "select * from customers limit ? offset ?";
    private final String findByIdSQL = "select * from customers where id = ?";
    private final String checkIfCustExists = "select * from customers where id = ?";
    private final String findOrdersByCustId = "select * from orders where customer_id = ?";

    public List<Customers> getAllCustomersLimitOffset(Integer limit, Integer offset) {
    List<Customers> customers = jdbcTemplate.query(findAllLimitOffsetSQL, BeanPropertyRowMapper.newInstance(Customers.class), limit, offset);

    return customers;

    }

    public Customers findCustomerById(int id) {
        List<Customers> customers = jdbcTemplate.query(findByIdSQL, BeanPropertyRowMapper.newInstance(Customers.class),
                id);

        if (customers.isEmpty()) {
            throw new NoSuchElementException("Customer with ID " + id + " does not exist!");
        }        

        return customers.get(0);
    }

    public List<Orders> findOrdersByCustomerID(int id) {

    List<Customers> customer = jdbcTemplate.query(checkIfCustExists, BeanPropertyRowMapper.newInstance(Customers.class), id);

    if (customer.isEmpty()) {
        throw new NoSuchElementException("Customer with ID " + id + " does not exist!");
    }

    List<Orders> order = jdbcTemplate.query(findOrdersByCustId, BeanPropertyRowMapper.newInstance(Orders.class), id);
    
    return order;
}

}








