package com.attornatus.test.service;

import com.attornatus.test.api.request.address.AddressRequest;
import com.attornatus.test.api.request.person.PersonRequest;
import com.attornatus.test.api.response.address.AddressResponse;
import com.attornatus.test.api.response.person.PersonResponse;
import com.attornatus.test.domain.repository.address.AddressRepository;
import com.attornatus.test.domain.service.address.AddressService;
import com.attornatus.test.domain.service.person.PersonService;
import com.attornatus.test.infrastructure.mapper.GenericMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private GenericMapper mapper;

    @Test
    public void ShouldAListOfAddress(){
        AddressResponse addressResponse =
                new AddressResponse(1l,2L,"trabalho",true,"jardins","13346-250","soldado",440,"São Paulo");
        when(addressService.getAddresses()).thenReturn(List.of(addressResponse));
    }

    @Test
    public void ShouldReturnAListOfAddressByPersonId(){
        AddressResponse addressResponse =
                new AddressResponse(1l,2L,"trabalho",true,"jardins","13346-250","soldado",440,"São Paulo");
        when(addressService.getAddressesByPersonId(2L)).thenReturn(List.of(addressResponse));
    }

    @Test
    public void ShouldSaveAnAddress(){
        AddressRequest addressRequest = AddressRequest.builder()
                .personId(2L)
                .addressName("trabalho")
                .isPrincipal(true)
                .district("jardins")
                .postalCode("13346-250")
                .street("soldado")
                .city("são paulo")
                .build();
        AddressResponse addressResponse =
                new AddressResponse(1l,2L,"trabalho",true,"jardins","13346-250","soldado",440,"São Paulo");
        when(addressService.saveAddress(addressRequest)).thenReturn(addressResponse);
    }

    @Test
    public void ShouldEditAnAddress(){
        AddressRequest addressRequest = AddressRequest.builder()
                .personId(2L)
                .addressName("casa")
                .isPrincipal(true)
                .district("jardins")
                .postalCode("13346-250")
                .street("soldado")
                .city("são paulo")
                .build();
        AddressResponse addressResponse =
                new AddressResponse(1l,2L,"casa",true,"jardins","13346-250","soldado",440,"São Paulo");
        when(addressService.editAddress(addressRequest,1l)).thenReturn(addressResponse);
    }

}
