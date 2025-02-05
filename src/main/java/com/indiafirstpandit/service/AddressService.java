package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.AddressDto;
import com.indiafirstpandit.model.Address;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address getAddress(UUID addressId)
    {
        return addressRepository.findById(addressId).get();
    }

    public List<Address> getAddresses(User user)
    {
//        return user.getAddresses();
        return addressRepository.findByUserId(user.getId());
    }

    public List<Address> addAddress(User user, AddressDto addressDto) {
        Address newAddress = new Address();
        newAddress.setFullName(addressDto.getFullName());
        newAddress.setMobileNumber(addressDto.getMobileNumber());
        newAddress.setFirstLine(addressDto.getFirstLine());
        newAddress.setSecondLine(addressDto.getSecondLine());
        newAddress.setLandmark(addressDto.getLandmark());
        newAddress.setCity(addressDto.getCity());
        newAddress.setState(addressDto.getState());
        newAddress.setPinCode(addressDto.getPinCode());
        newAddress.setAddressType(addressDto.getAddressType());
        newAddress.setUser(user);  // Associate address with user

        addressRepository.save(newAddress);

        return addressRepository.findByUserId(user.getId());
    }
}
