package com.example.service;

import com.example.entity.Address;
import com.example.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Address getAddressById(Long id){
        return addressRepository.getAddressById(id);
    }
}
