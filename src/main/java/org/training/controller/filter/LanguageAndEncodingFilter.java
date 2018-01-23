package org.training.controller.filter;

import org.training.constant.jsp.RequestAttributes;
import org.training.util.properties.BundleManager;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Language and encoding filter for all pages
 * @see BundleManager
 * @see Locale
 */
@WebFilter(urlPatterns = "/*")
public class LanguageAndEncodingFilter extends AbstractFilter {
    /**
     * Checks language attribute and changes locale depends on language
     * @param request request
     * @param response response
     * @param filterChain filter chain
     * @param role user role
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void filter(HttpServletRequest request,
                          HttpServletResponse response,
                          FilterChain filterChain, String role)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            Locale locale = (Locale) request.getSession().getAttribute(RequestAttributes.LANGUAGE);
            BundleManager.INSTANCE.changeLocale(locale);
            filterChain.doFilter(request, response);
        } catch (ClassCastException e) {
            Locale locale = new Locale((String) request.getSession().getAttribute(RequestAttributes.LANGUAGE));
            BundleManager.INSTANCE.changeLocale(locale);
            filterChain.doFilter(request, response);
        }

    }
}
