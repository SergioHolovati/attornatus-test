package com.attornatus.test.domain.model.address;

import com.attornatus.test.domain.model.person.PersonEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NotNull
    private Long personId;

    @Column
    @NotBlank
    private String addressName;

    @Column
    @NotNull
    private Boolean isPrincipal;

    @Column
    @NotBlank
    private String district;

    @Column
    @NotBlank
    private String postalCode;

    @Column
    @NotBlank
    private String street;

    @Column
    @NotNull
    private Integer number;

    @Column
    @NotBlank
    private String city;

}
