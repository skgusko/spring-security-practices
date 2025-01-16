package config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigEx05 {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web
                    .ignoring()
                    .requestMatchers(new AntPathRequestMatcher("/assets/**"));
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    	.formLogin((formLogin) -> {
    		formLogin.loginPage("/user/login");
    	})
    	.authorizeHttpRequests((authorizeRequests) -> {
    		/* ACL */
    		authorizeRequests
    			// ^/board/?(write|delete|modify|reply).*$ :아예 없어도 되고, 저 넷 중 하나가 오는 경우. 뒤에는 와도 되고 안 와도되고  
    			.requestMatchers(new RegexRequestMatcher("^/board/?(write|delete|modify|reply).*$", null))//null : 메서드 상관 x
				.authenticated()
    			
    			.anyRequest()
				.permitAll(); //로그인 여부 판단 (로그인 안 되어있으면 login 페이지로 리다이렉트 시킴)
    		
    	});
    	
    	return http.build();
    }
}
