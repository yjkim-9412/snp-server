package SNP.management.entity.student;

import SNP.management.domain.StudentDTO;
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

    public Address setAddress(StudentDTO studentDTO) {
        this.city = studentDTO.getCity();
        this.street = studentDTO.getStreet();
        return this;
    }
}
