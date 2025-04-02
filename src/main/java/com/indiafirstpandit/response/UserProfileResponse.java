package com.indiafirstpandit.response;

import com.indiafirstpandit.model.Address;
import com.indiafirstpandit.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
//@Builder
public class UserProfileResponse {
    public UserProfileResponse(User user)
    {
        this.name = user.getName();
        this.email= user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.ordersPlaced = user.getOrders().size();
        this.addresses = user.getAddresses();
        this.isVerified = user.isVerified();
    }
    private String name;
    private String email;
    private String phoneNumber;
    private Integer ordersPlaced;
    private List<Address> addresses;
    private boolean isVerified;


}
