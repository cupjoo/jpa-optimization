package jpabook.jpashop.jpaoptimization.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Builder
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
