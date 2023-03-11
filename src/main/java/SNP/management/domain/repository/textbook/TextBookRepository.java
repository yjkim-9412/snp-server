package SNP.management.domain.repository.textbook;

import SNP.management.domain.DTO.QTextBookDTO;
import SNP.management.domain.DTO.TextBookDTO;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static SNP.management.domain.entity.textbook.QTextBook.*;

@Repository
public class TextBookRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public TextBookRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    public List<TextBookDTO> findAllDTOType() {
        return queryFactory.select(new QTextBookDTO(textBook.id, textBook.code, textBook.textBookType, textBook.name,
                        textBook.numberOfCharacters, textBook.questionCount, textBook.category.id, textBook.category.name))
                .from(textBook)
                .fetch();
    }


}
