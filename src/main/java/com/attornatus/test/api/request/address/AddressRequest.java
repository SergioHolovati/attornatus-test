package com.attornatus.test.api.request.address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {

    private Long personId;
    private String addressName;
    private Boolean isPrincipal;
    private String district;
    private String postalCode;
    private String street;
    private String city;
    private Integer number;

}
