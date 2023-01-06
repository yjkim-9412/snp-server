package SNP.management.entity.student;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;

    private String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }
}
