package com.indiafirstpandit.dto;

import com.indiafirstpandit.enums.AddressType;
import com.indiafirstpandit.model.Address;
import com.indiafirstpandit.model.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private UUID id;
    private String fullName;
    private String mobileNumber;
    private String firstLine ; //house number, building
    private String secondLine; //Area,street,sector,village
    private String landmark;
    private String city;
    private String state;
    private String pinCode;
    private AddressType addressType;
    private UUID userId;
    public AddressDto(Address address)
    {
        this.id = address.getId();
        this.fullName = address.getFullName();
        this.mobileNumber = address.getMobileNumber();
        this.firstLine = address.getFirstLine();
        this.secondLine = address.getSecondLine();
        this.landmark = address.getLandmark();
        this.city = address.getCity();
        this.state = address.getState();
        this.pinCode = address.getPinCode();
        this.addressType = address.getAddressType();
        this.userId = address.getUser().getId();
    }

}
