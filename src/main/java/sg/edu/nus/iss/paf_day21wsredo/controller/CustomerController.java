package sg.edu.nus.iss.paf_day21wsredo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sg.edu.nus.iss.paf_day21wsredo.models.Customers;
import sg.edu.nus.iss.paf_day21wsredo.models.Orders;
import sg.edu.nus.iss.paf_day21wsredo.repository.CustomersRepo;
import sg.edu.nus.iss.paf_day21wsredo.service.CustomerService;

@RestController
@RequestMapping("/api")

public class CustomerController {
    @Autowired
    CustomerService custSvc;

    @GetMapping(path="/customers", produces = "application/json")
    public ResponseEntity<List<Customers>> getAllCustomers(
        @RequestParam(required = false, defaultValue = "0") Integer offset, 
        @RequestParam(required = false, defaultValue = "5") Integer limit) {
        List<Customers> result = custSvc.getAllCustomers(limit, offset);

        if (result != null) {
        return ResponseEntity.ok().body(result);

    } else {
        return ResponseEntity.notFound().build();
    }

    }

    // @GetMapping(path="/customers", produces = "application/json")
    // public ResponseEntity<List<Customers>> getAllCustomers() {
    
    //     List<Customers> result = custSvc.getAllCustomers();
    //     if (result != null) {
        
    //         return ResponseEntity.ok().body(result);

    // } else {
    //     return ResponseEntity.notFound().build();
    // }

    // }


    @GetMapping(path = "/customers/{id}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable Integer id) {
        
        try {
            
            Customers result = custSvc.findCustomerById(id);
            return ResponseEntity.ok().body(result);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }

    }    
    //     Customers result = custSvc.findCustomerById(id);

    // if (result != null) {
    //     return ResponseEntity.ok().body(result);
    // } else {
    //     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + id + " does not exist!");
    // }

    // }

    @GetMapping(path="/customers/{id}/orders", produces = "application/json")
    public ResponseEntity<?> findOrdersByCustomerID(@PathVariable Integer id) {
        try {
          
          List<Orders> result = custSvc.findOrdersByCustomerID(id);
          return ResponseEntity.ok().body(result);

        } catch (NoSuchElementException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");

        }
    //     List<Orders> result = custSvc.findOrdersByCustomerID(id);

    //     if(result != null) {
    //         return ResponseEntity.ok().body(result);
    //     } else {
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + id + " does not exist!");
    //     }
    // }
    }

}
