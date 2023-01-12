package SNP.management.web.controller;

import SNP.management.web.form.teacher.TeacherLoginForm;
import SNP.management.web.resolver.BindingResolver;
import SNP.management.web.resolver.Login;
import SNP.management.web.resolver.SessionConst;
import SNP.management.domain.DTO.TeacherDTO;
import SNP.management.domain.repository.teacher.TeacherRepository;
import SNP.management.domain.service.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final BindingResolver bindingResolver;
    private final TeacherRepository teacherRepository;

    @GetMapping("/login")
    public Object loginForm() {


        return "/login";
    }
    @PostMapping("/login/teacher")
    public Object loginTeacher(@RequestBody @Validated @Login TeacherLoginForm loginTeacher, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()){
            return bindingResolver.bindingAPI(bindingResult);

            }
        if (loginTeacher == null) {
            return "/";
        }

        teacherService.findByLogin(new TeacherDTO().login(loginTeacher));
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_TEACHER, loginTeacher);

        return "/main";
    }




}
