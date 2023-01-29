package SNP.management.domain.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity{

    @Id @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private String author;
    private String publisher;
    private Integer level;
    private Integer step;
    private Integer graduate;

}
