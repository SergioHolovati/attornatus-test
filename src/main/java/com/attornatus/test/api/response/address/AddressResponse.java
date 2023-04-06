package com.attornatus.test.api.response.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Long id;
    private Long personId;
    private String addressName;
    private Boolean isPrincipal;
    private String district;
    private String postalCode;
    private String street;
    private Integer number;
    private String city;

}
