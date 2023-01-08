package SNP.management.domain.entity.study;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C_CLASS")
@Getter
@Setter
public class CType extends Study{
}
