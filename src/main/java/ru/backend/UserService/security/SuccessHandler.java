package ru.backend.UserService.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        var user = (AppUserDetails)appUserDetailsService.loadUserByUsername(authentication.getName());
        var userId = user.getAppUser().getId();

        // Получаем роли пользователя
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Определяем куда перенаправлять пользователя
        String targetUrl = "";
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            targetUrl = "/admin/users";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            targetUrl = "/users/" + userId;
        }

        // Перенаправляем на нужную страницу
        response.sendRedirect(targetUrl);
    }
}
