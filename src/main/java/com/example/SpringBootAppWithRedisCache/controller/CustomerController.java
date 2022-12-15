package com.example.SpringBootAppWithRedisCache.controller;

import com.example.SpringBootAppWithRedisCache.entity.CustomerEntity;
import com.example.SpringBootAppWithRedisCache.model.CustomerCreateUpdateDTO;
import com.example.SpringBootAppWithRedisCache.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @PostMapping("/save")
    public CustomerEntity createCustomer(@RequestBody CustomerCreateUpdateDTO dto) {
        return customerService.createCustomer(dto);
    }

    @GetMapping("/get/{id}")
    public CustomerEntity getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping("/get/all")
    public List<CustomerEntity> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/update/{id}")
    public CustomerEntity updateCustomer(@PathVariable Long id, @RequestBody CustomerCreateUpdateDTO dto) {
        return customerService.updateCustomer(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer with id: " + id + " deleted";
    }

}