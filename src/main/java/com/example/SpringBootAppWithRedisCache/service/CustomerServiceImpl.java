package com.example.SpringBootAppWithRedisCache.service;

import com.example.SpringBootAppWithRedisCache.entity.CustomerEntity;
import com.example.SpringBootAppWithRedisCache.exception.CustomerNotFoundException;
import com.example.SpringBootAppWithRedisCache.model.CustomerCreateUpdateDTO;
import com.example.SpringBootAppWithRedisCache.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Caching(
            put = {@CachePut(cacheNames = "customer", key = "#result.id")},
            evict = {@CacheEvict(cacheNames = "customers", allEntries = true)}
    )
    public CustomerEntity createCustomer(CustomerCreateUpdateDTO dto) {
        return customerRepository.save(dto.toEntity());
    }

    @Override
    @Cacheable(cacheNames = "customer", key="#id")
    public CustomerEntity getCustomer(Long id) {
        System.out.println("Reading from DB: customer with id = " + id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
    }

    @Override
    @Cacheable(cacheNames = "customers")
    public List<CustomerEntity> getAllCustomers() {
        System.out.println("Reading from DB: all customers ");
        return customerRepository.findAll();
    }

    @Override
    @Caching(
            put = {@CachePut(cacheNames = "customer", key = "#result.id")},
            evict = {@CacheEvict(cacheNames = "customers", allEntries = true)}
    )
    public CustomerEntity updateCustomer(Long id, CustomerCreateUpdateDTO dto) {
        CustomerEntity dbCustomerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        dbCustomerEntity.setFullName(dto.getFullName());
        dbCustomerEntity.setEmail(dto.getEmail());
        return customerRepository.save(dbCustomerEntity);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "customer", key = "#id"),
                    @CacheEvict(cacheNames = "customers", allEntries = true)
            }
    )
    public void deleteCustomer(Long id) {
        CustomerEntity dbCustomerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        customerRepository.delete(dbCustomerEntity);
    }

}