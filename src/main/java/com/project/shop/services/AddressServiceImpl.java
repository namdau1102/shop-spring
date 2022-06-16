package com.project.shop.services;

import com.project.shop.models.Address;
import com.project.shop.repositories.AddressRepository;
import com.project.shop.services.declarations.AddressService;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;


    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public void update(Address address) {
        addressRepository.save(address);
    }
}
