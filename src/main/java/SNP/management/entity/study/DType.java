package SNP.management.entity.study;

import javax.persistence.*;

@Entity
@DiscriminatorValue("D_CLASS")
public class DType extends Study{

}
