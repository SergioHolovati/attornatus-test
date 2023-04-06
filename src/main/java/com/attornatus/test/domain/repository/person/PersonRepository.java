package com.attornatus.test.domain.repository.person;

import com.attornatus.test.domain.model.person.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {
}
