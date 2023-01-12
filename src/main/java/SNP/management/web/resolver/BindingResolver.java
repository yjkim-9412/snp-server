package SNP.management.web.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class BindingResolver {


    /**
     * API 로 에러 반환
     * @param bindingResult
     * @return Map<ErrorField, ErrorDefaultMessage>
     */
    public Map<String, String> bindingAPI(BindingResult bindingResult) {

        log.info("검증 오류 발생 error={}", bindingResult);

        Map<String, String> mapErrors = new ConcurrentHashMap<>();// 동시성 문제 ConcurrentHashMap

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            mapErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return mapErrors;
    }
}
