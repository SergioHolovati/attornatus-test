package com.attornatus.test.api.request.person;

import lombok.Data;

@Data
public class PersonWithAddressRequest {
    String name;
    String birthday;
    String addressName;
    String street;
    String district;
    String postalCode;
    Integer number;
    String city;
    Boolean isPrincipal;

}
