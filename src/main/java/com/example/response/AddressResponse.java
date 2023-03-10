package com.example.response;

import com.example.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private Long id;
    private String city;
    private String street;

    public AddressResponse(Address address){
        this.id = address.getId();
        this.city = address.getCity();
        this.street = address.getStreet();
    }
}
