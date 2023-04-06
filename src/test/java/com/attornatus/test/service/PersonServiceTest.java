package com.attornatus.test.service;


import com.attornatus.test.api.request.person.PersonRequest;
import com.attornatus.test.api.response.person.PersonResponse;
import com.attornatus.test.domain.service.person.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Test
    public void ShouldReturnAListOfPersons(){
        PersonRequest request = new PersonRequest();
        request.setBirthday("1995-09-19");
        request.setName("Sergio");

        PersonResponse personResponse = new PersonResponse(1L,"Sergio","1995-09-19", List.of());
        when(personService.getPersons()).thenReturn(List.of(personResponse));
    }

    @Test
    public void ShouldCreateAnPerson(){
        PersonRequest request = new PersonRequest();
        request.setBirthday("1995-09-19");
        request.setName("Sergio");

        PersonResponse personResponse = new PersonResponse(1L,"Sergio","1995-09-19", List.of());
        when(personService.createPerson(request)).thenReturn(personResponse);
        assertThat(personResponse.getName()).isSameAs(request.getName());
    }

    @Test
    public void ShouldEditAPerson(){
        PersonRequest request = new PersonRequest();

        request.setBirthday("1995-09-19");
        request.setName("Sergio");

        PersonResponse personResponse = new PersonResponse(1L,"Sergio Holovati","1995-09-19", List.of());
        when(personService.editPerson(request,1L)).thenReturn(personResponse);
        assertThat(personResponse.getName()).isNotEqualTo(request.getName());
    }

}
