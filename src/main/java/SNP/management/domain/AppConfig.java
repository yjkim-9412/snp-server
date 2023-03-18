package SNP.management.domain;

import SNP.management.domain.enumlist.BasicTestType;
import SNP.management.domain.service.basictest.BasicTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final ApplicationContext context;

    @Bean
    public Map<BasicTestType, BasicTestService> basicTestServiceMap() {
        Map<String, BasicTestService> basicTestServiceBeanMap = context.getBeansOfType(BasicTestService.class);
        Map<BasicTestType, BasicTestService> basicTestServiceMap = new HashMap<>();
        for (String beanName : basicTestServiceBeanMap.keySet()) {
            BasicTestType basicTestType = BasicTestType.findByString(beanName);
            basicTestServiceMap.put(basicTestType, basicTestServiceBeanMap.get(beanName));
        }
        return basicTestServiceMap;
    }

}
