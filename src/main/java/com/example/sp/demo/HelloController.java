package com.example.sp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/satya")
    public List<Record> index(@RequestParam(value ="id",defaultValue = "0") int id,
                             @RequestParam(value = "name") String name) {
        List<Record> list=new ArrayList<Record>();
        for(int i=0;i<5;i++)
        {
            list.add(Record.builder().id(id).name(name).build());
        }
        return list;
    }
    @GetMapping("/allusers")
    public  List<User> getAllusers()
    {
        List<User> list =new ArrayList<User>();
       List<Customer> dblist= null;
               customerRepository.findAll().forEach(customer -> {
                   User u=User.builder()
                           .id(customer.getId())
                           .lastName(customer.getLastName())
                           .firstName(customer.getFirstName())
                           .build();
                   list.add(u);
               } );

return list;
    }
    @GetMapping("/user")
    public User getUser(@RequestParam(value = "id") int id)
    {
        System.out.println("calling users endpoint");
        Customer customer= customerRepository.findById(id);
        return User.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();

    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user)
    {
        System.out.println("calling saveuser endpoint"+user);
        Customer customer=new Customer(user.getId(),user.getFirstName(),user.getLastName());
       // Customer customer= new Customer(1l,"satya","kola");
               Customer newCustomer1 =customerRepository.save(customer);
        return User.builder()
                .id(newCustomer1.getId())
                .firstName(newCustomer1.getFirstName())
                .lastName(newCustomer1.getLastName())
                .build();

    }

    @PostMapping("/hellopost")
    public String hello()
    {
        return "this is my rest hello";
    }

}
