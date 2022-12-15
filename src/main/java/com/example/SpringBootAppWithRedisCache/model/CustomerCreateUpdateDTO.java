package com.example.SpringBootAppWithRedisCache.model;

import com.example.SpringBootAppWithRedisCache.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateUpdateDTO {

    private String fullName;
    private String email;

    public CustomerEntity toEntity() {
        return CustomerEntity.builder()
                .fullName(this.fullName)
                .email(this.email)
                .build();
    }

}