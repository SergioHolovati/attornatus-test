package com.attornatus.test.api.response.person;

import com.attornatus.test.api.response.address.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {

    private Long id;
    private String name;
    private String birthday;
    private List<AddressResponse> addresses;
}
