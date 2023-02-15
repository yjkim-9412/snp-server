package SNP.management.domain.repository.student;

import SNP.management.domain.DTO.chart.*;
import SNP.management.domain.entity.QCategory;
import SNP.management.domain.entity.student.QQuestionLog;
import SNP.management.domain.entity.textbook.QQuestion;
import SNP.management.domain.entity.textbook.QTextBook;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static SNP.management.domain.entity.QCategory.*;
import static SNP.management.domain.entity.student.QQuestionLog.*;
import static SNP.management.domain.entity.textbook.QQuestion.*;
import static SNP.management.domain.entity.textbook.QTextBook.*;

@Repository
public class QuestionLogRepository {


    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public QuestionLogRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    public List<QuestionTypeChartDTO> findQuestionTypeAvgByStudentIdGroupByQuestionType(Long studentId) {

        List<QuestionTypeChartDTO> resultList = queryFactory.select(new QQuestionTypeChartDTO(
                        questionLog.score.avg().multiply(10).castToNum(Integer.class),
                        questionLog.id.count().castToNum(Integer.class), questionLog.score.sum(), question.questionType))
                .from(questionLog)
                .join(questionLog.question, question)
                .where(questionLog.student.id.eq(studentId))
                .groupBy(question.questionType)
                .fetch();

        for (QuestionTypeChartDTO questionTypeChartDTO : resultList) {
            questionTypeChartDTO.setQuestionTypeString();
        }
        return resultList;
    }

    public List<CategoryChartDTO> findCategoryQuestionAvgByStudentIdGroupByCategoryName(Long studentId) {
        return queryFactory.select(new QCategoryChartDTO(questionLog.score.avg().multiply(10).castToNum(Integer.class),
                        questionLog.id.count().castToNum(Integer.class), questionLog.score.sum(), category.name))
                .from(questionLog)
                .join(questionLog.question, question)
                .join(question.textBook, textBook)
                .join(textBook.category, category)
                .where(questionLog.student.id.eq(studentId))
                .groupBy(category.name)
                .fetch();
    }




}
