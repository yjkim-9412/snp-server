package SNP.management.domain.repository;

import SNP.management.domain.DTO.StudyDTO;
import SNP.management.domain.entity.study.Study;
import SNP.management.domain.enumlist.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudyDataJpa extends JpaRepository<Study, Long> {

    @Query("SELECT st FROM Study st " +
            "WHERE st.detail = :detail AND st.studyType = :studyType " +
            "AND st.numberOfDays >= :count")
    Optional<Study> findByDetailAndStudyTypeAndNumberAndCount(@Param("detail") String detail,
            @Param("studyType")  StudyType studyType,@Param("count")  Integer count);

    @Query("SELECT st FROM Study st " +
            "WHERE st.detail = :detail AND st.studyType = :studyType " +
            "AND st.numberOfDays > :count")
    Optional<Study> findByDetailAndStudyTypeAndNumberAndLessCount(@Param("detail") String detail,
                                                              @Param("studyType")  StudyType studyType,@Param("count")  Integer count);

    Optional<Study> findByStep(Integer step);

    Optional<Study> findByStudyTypeAndStep(StudyType studyType, Integer step);

    @Query("SELECT s.detail FROM Study s WHERE s.studyType = :studyType")
    List<String> findAllByStudyType(@Param("studyType") StudyType studyType);
}
