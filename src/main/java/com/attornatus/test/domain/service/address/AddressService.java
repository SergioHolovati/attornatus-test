package com.attornatus.test.domain.service.address;

import com.attornatus.test.api.request.address.AddressRequest;
import com.attornatus.test.api.response.address.AddressResponse;
import com.attornatus.test.domain.model.address.AddressEntity;
import com.attornatus.test.domain.model.person.PersonEntity;
import com.attornatus.test.domain.repository.address.AddressRepository;
import com.attornatus.test.domain.repository.person.PersonRepository;
import com.attornatus.test.infrastructure.exception.HandlerException;
import com.attornatus.test.infrastructure.mapper.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GenericMapper mapper;

    public List<AddressResponse> getAddresses() {
        try{
            return mapper.converterCollection(addressRepository.findAll(), AddressResponse.class);

        }catch (Exception e){
            throw new HandlerException(e.getMessage());
        }
    }

    public List<AddressResponse> getAddressesByPersonId(Long personId) {
        try{
            return mapper.converterCollection(addressRepository.findByPersonId(personId), AddressResponse.class);

        }catch (Exception e){
           throw new HandlerException(e.getMessage());
        }
     }

    public AddressResponse saveAddress(AddressRequest addressRequest) {
        try {
            if(personRepository.findById(addressRequest.getPersonId()).stream().count() < 1){
                throw new HandlerException("Person from id "+addressRequest.getPersonId()+" not exists");
            }
            AddressEntity addressEntity = mapper.converter(addressRequest, AddressEntity.class);
            AddressEntity addressToReturn = addressRepository.save(addressEntity);
            return mapper.converter(addressToReturn, AddressResponse.class);
        } catch (HandlerException e) {

            throw new HandlerException(e.getMessage());
        }
    }

    public AddressResponse editAddress(AddressRequest addressRequest, Long id) {
        try {
            Optional<AddressEntity> addressEntity = addressRepository.findById(id);
            if (!addressEntity.isEmpty()) {
                addressEntity.get().setAddressName(addressRequest.getAddressName());
                addressEntity.get().setStreet(addressRequest.getStreet());
                addressEntity.get().setDistrict(addressRequest.getDistrict());
                addressEntity.get().setPostalCode(addressRequest.getPostalCode());
                addressEntity.get().setIsPrincipal(addressRequest.getIsPrincipal());
                addressEntity.get().setCity(addressRequest.getCity());
                addressEntity.get().setPersonId(addressRequest.getPersonId());
                return mapper.converter(addressRepository.save(addressEntity.get()), AddressResponse.class);
            }
            return mapper.converter(addressEntity, AddressResponse.class);
        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }
    }

}
