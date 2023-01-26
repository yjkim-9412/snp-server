package SNP.management;

import SNP.management.web.exception.SessionException;
import SNP.management.web.resolver.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class ManagementApplication {
	@Autowired HttpServletRequest request;

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
	@Bean
	public AuditorAware<String> auditorProvider() {

		return () -> Optional.of(request.getSession(false).getAttribute(SessionConst.LOGIN_TEACHER).toString());
	}

}
