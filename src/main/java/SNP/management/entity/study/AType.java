package SNP.management.entity.study;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Data
@DiscriminatorValue("A_CLASS")
public class AType extends Study{



}
