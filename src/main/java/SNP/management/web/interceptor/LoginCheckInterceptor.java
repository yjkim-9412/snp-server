package SNP.management.web.interceptor;

import SNP.management.domain.exceptionlist.SessionException;
import SNP.management.web.resolver.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("로그인 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_TEACHER) == null) {
            log.info("로그인 미인증 사용자");

            //로그인 페이지로 리턴
            throw new SessionException("세션 미인증");

        }

        return true;
    }
}
