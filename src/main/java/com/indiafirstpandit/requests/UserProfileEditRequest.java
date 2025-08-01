package com.indiafirstpandit.requests;

import com.indiafirstpandit.model.User;
import lombok.*;

@Data
@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileEditRequest {
    public UserProfileEditRequest(User user)
    {
        this.name = user.getName();
        this.email= user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
//        this.ordersPlaced = user.getOrders().size();
//        this.addresses = user.getAddresses();
        this.isVerified = user.isVerified();
    }
    private String name;
    private String email;
    private String phoneNumber;
    //    private Integer ordersPlaced;
//    private List<Address> addresses;
    private boolean isVerified;


}
