package com.attornatus.test.api.controller.address;

import com.attornatus.test.api.request.address.AddressRequest;
import com.attornatus.test.api.response.address.AddressResponse;
import com.attornatus.test.domain.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<AddressResponse> getAllAdresses(){
        return addressService.getAddresses();
    }

    @GetMapping("/person/{personId}")
    public List<AddressResponse> getAdressesByPersonId(@PathVariable Long personId){
        return addressService.getAddressesByPersonId(personId);
    }

    @PostMapping("create")
    public AddressResponse saveAddress(@RequestBody AddressRequest addressRequest){
        return addressService.saveAddress(addressRequest);
    }

    @PutMapping("/edit/{id}")
    public AddressResponse editAddress(@RequestBody AddressRequest addressRequest, @PathVariable Long id){
        return addressService.editAddress(addressRequest,id);
    }

}
