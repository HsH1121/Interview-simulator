// GlobalModelAttributes.java
package com.example.Interview.simulator.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("_csrf")
    public CsrfToken csrfToken(CsrfToken token) {
        return token; // 뷰에서 {{_csrf.parameterName}}, {{_csrf.token}} 사용 가능
    }
}
