package SNP.management.domain.repository.textbook;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TextBookRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;


    public TextBookRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }


}
