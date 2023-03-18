package SNP.management.domain;

import SNP.management.domain.enumlist.BasicTestType;
import SNP.management.domain.repository.basictest.StudyHabitsDataJpa;
import SNP.management.domain.repository.student.StudentDataJpa;
import SNP.management.domain.resolver.BasicTestConst;
import SNP.management.domain.service.basictest.BasicTestService;
import SNP.management.domain.service.basictest.ReadBasicServiceImpl;
import SNP.management.domain.service.basictest.StudyHabitsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class BasicTestConfig {

    private final ApplicationContext context;
    private final StudyHabitsDataJpa studyHabitsDataJpa;
    private final StudentDataJpa studentDataJpa;

    @Bean(name = BasicTestConst.STUDY_HABITS)
    public BasicTestService studyHabitsServiceImpl() {
        return new StudyHabitsServiceImpl(studyHabitsDataJpa, studentDataJpa);
    }
    @Bean(name = BasicTestConst.READ_BASIC)
    public BasicTestService readBasicServiceImpl() {
        return new ReadBasicServiceImpl(studyHabitsDataJpa, studentDataJpa);
    }
    @Bean(name = BasicTestConst.INTELLIGENCE)
    public BasicTestService intelligenceImpl() {
        return new StudyHabitsServiceImpl(studyHabitsDataJpa, studentDataJpa);
    }
    @Bean
    public Map<BasicTestType, BasicTestService> basicTestServiceMap() {
        Map<String, BasicTestService> basicTestServiceBeanMap = context.getBeansOfType(BasicTestService.class);
        Map<BasicTestType, BasicTestService> basicTestServiceMap = new HashMap<>();
        for (String beanName : basicTestServiceBeanMap.keySet()) {
            BasicTestType basicTestType = BasicTestType.findByString(beanName);
            basicTestServiceMap.put(basicTestType, basicTestServiceBeanMap.get(beanName));
        }
        for (Map.Entry<BasicTestType, BasicTestService> et : basicTestServiceMap.entrySet()) {
            System.out.println("et.getKey() = " + et.getKey());
            System.out.println("et.getValue() = " + et.getValue());

        }
        return basicTestServiceMap;
    }

}
