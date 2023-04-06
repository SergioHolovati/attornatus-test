package com.attornatus.test.domain.repository.address;

import com.attornatus.test.domain.model.address.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {

    List<AddressEntity> findByPersonId(Long id);
}
