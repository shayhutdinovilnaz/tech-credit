package com.technical.credit.webapp.filter;

import com.technical.credit.obligationservice.model.LanguageModel;
import com.technical.credit.obligationservice.service.LanguageService;
import com.technical.credit.obligationservice.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestLanguageFilter implements Filter {
    private static final String REQUEST_HEADER_LANGUAGE_PARAM_NAME = "language";
    private final LanguageService languageService;
    private final RequestService requestService;

    @Override
    // todo
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final LanguageModel requestLanguage = languageService.getByIsoCode(request.getHeader(REQUEST_HEADER_LANGUAGE_PARAM_NAME));
        requestService.setCurrentLanguage(requestLanguage);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
