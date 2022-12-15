package com.example.SpringBootAppWithRedisCache.service;

import com.example.SpringBootAppWithRedisCache.entity.CustomerEntity;
import com.example.SpringBootAppWithRedisCache.model.CustomerCreateUpdateDTO;

import java.util.List;

public interface CustomerService {

    CustomerEntity createCustomer(CustomerCreateUpdateDTO dto);
    CustomerEntity updateCustomer(Long id, CustomerCreateUpdateDTO dto);
    void deleteCustomer(Long id);
    CustomerEntity getCustomer(Long id);
    List<CustomerEntity> getAllCustomers();

}