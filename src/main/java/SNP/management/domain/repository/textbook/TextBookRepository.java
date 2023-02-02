package SNP.management.domain.repository.textbook;

import SNP.management.domain.entity.textbook.QQuestion;
import SNP.management.domain.entity.textbook.QTextBook;
import SNP.management.domain.entity.textbook.TextBook;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static SNP.management.domain.entity.textbook.QQuestion.*;
import static SNP.management.domain.entity.textbook.QTextBook.*;

@Repository
public class TextBookRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public TextBookRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }





}
