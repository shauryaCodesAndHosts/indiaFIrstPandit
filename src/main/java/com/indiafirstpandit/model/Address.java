package com.indiafirstpandit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indiafirstpandit.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String fullName;
    private String mobileNumber;
    private String firstLine ; //house number, building
    private String SecondLine; //Area,street,sector,village
    private String landmark;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String pinCode;
    @Column
    private AddressType addressType; // e.g., "Home", "Work", etc.
    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
    private User user;
//    @Override
//    public String toString() {
//        return "Address{" +
//                "addressType='" + addressType + '\'' +
//                ", landmark='" + landmark + '\'' +
//                ", postalCode='" + pinCode + '\'' +
//                ", city='" + city + '\'' +
//                ", id=" + id +
//                ", street='" + street + '\'' +
//                ", state='" + state + '\'' +
//                '}';
//    }
//    @Override
//    public String toString() {
//        return "Address{" +
//                "id=" + id +
//                ", fullName='" + fullName + '\'' +
//                ", mobileNumber='" + mobileNumber + '\'' +
//                ", firstLine='" + firstLine + '\'' +
//                ", secondLine='" + SecondLine + '\'' +
//                ", landmark='" + landmark + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", pinCode='" + pinCode + '\'' +
//                ", addressType=" + addressType +
//                '}';
//    }
}
