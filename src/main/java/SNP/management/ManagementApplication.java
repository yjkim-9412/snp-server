package SNP.management;

import SNP.management.web.resolver.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class ManagementApplication {

	private final HttpServletRequest request;

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
	@Bean
	public AuditorAware<String> auditorProvider() {

		return () -> Optional.of(request.getSession(false).getAttribute(SessionConst.LOGIN_TEACHER).toString());
	}

}
