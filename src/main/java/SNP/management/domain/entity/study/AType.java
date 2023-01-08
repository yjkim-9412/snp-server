package SNP.management.domain.entity.study;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@DiscriminatorValue("A_CLASS")
public class AType extends Study{



}
