package com.attornatus.test.domain.service.person;

import com.attornatus.test.api.request.address.AddressRequest;
import com.attornatus.test.api.request.person.PersonRequest;
import com.attornatus.test.api.request.person.PersonWithAddressRequest;
import com.attornatus.test.api.response.address.AddressResponse;
import com.attornatus.test.api.response.person.PersonResponse;
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
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private GenericMapper mapper;

    public List<PersonResponse> getPersons() {

        try {
            List<PersonEntity> personEntity = personRepository.findAll();

            List<PersonResponse> personsToReturn = mapper.converterCollection(personEntity, PersonResponse.class);
            personsToReturn.forEach(it -> {
                it.setAddresses(mapper.converterCollection(addressRepository.findByPersonId(it.getId()), AddressResponse.class));
            });
            return personsToReturn;
        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }
    }

    public PersonResponse getPersonById(Long id) {
        try {
            Optional<PersonEntity> personEntity = personRepository.findById(id);
            PersonResponse response = mapper.converter(personEntity.get(), PersonResponse.class);
            List<AddressEntity> addressEntity = addressRepository.findByPersonId(response.getId().longValue());
            List<AddressResponse> addressResponses = mapper.converterCollection(addressEntity, AddressResponse.class);
            response.setAddresses(addressResponses);

            return response;
        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }

    }

    public PersonResponse createPerson(PersonRequest person) {
        try {
            PersonEntity personEntity = personRepository.save(mapper.converter(person, PersonEntity.class));
            return mapper.converter(personEntity, PersonResponse.class);
        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }
    }

    public PersonResponse createPersonWithAddress(PersonWithAddressRequest person) {
        try {

            PersonEntity personEntity = this.savePersonWithAddress(person);
            AddressEntity addressEntity = this.saveAddress(personEntity, person);
            PersonResponse personResponse = mapper.converter(personEntity, PersonResponse.class);
            personResponse.setAddresses(List.of(mapper.converter(addressEntity, AddressResponse.class)));
            return personResponse;

        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }
    }

    public PersonResponse editPerson(PersonRequest person, Long id) {
        try {
            if (person.getName().isEmpty() || person.getBirthday().isEmpty()) {
                throw new HandlerException("Field is Empty");
            }

            Optional<PersonEntity> personEntity = personRepository.findById(id);
            if (!personEntity.isEmpty()) {
                personEntity.get().setName(person.getName());
                personEntity.get().setBirthday(person.getBirthday());
                return mapper.converter(personRepository.save(personEntity.get()), PersonResponse.class);
            }
            return mapper.converter(personEntity, PersonResponse.class);
        } catch (Exception e) {
            throw new HandlerException(e.getMessage());
        }
    }

    private PersonEntity savePersonWithAddress(PersonWithAddressRequest person) {
        PersonEntity personToSave = mapper.converter(person, PersonEntity.class);
        return personRepository.save(personToSave);
    }

    private AddressEntity saveAddress(PersonEntity personEntity, PersonWithAddressRequest person) {
        AddressRequest addressRequest = AddressRequest.builder()
                .personId(personEntity.getId().longValue())
                .addressName(person.getAddressName())
                .street(person.getStreet())
                .district(person.getDistrict())
                .postalCode(person.getPostalCode())
                .city(person.getCity())
                .isPrincipal(person.getIsPrincipal())
                .number(person.getNumber())
                .build();

        AddressEntity addressToSave = mapper.converter(addressRequest, AddressEntity.class);
        return addressRepository.save(addressToSave);

    }
}
