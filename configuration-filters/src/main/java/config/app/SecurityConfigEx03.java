package config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigEx03 {
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
        	.formLogin((formLogin) -> {})
        	.httpBasic((httpBasic) -> {}) //톰캣 로그인 처럼 
        	.authorizeHttpRequests((authorizeRequests) -> {
        		/* Access Control List(ACL) */  //@Auth 에서 했던 작업들을 여기서!
        		authorizeRequests
        			.anyRequest().permitAll(); //다 통과시키기. 젤 마지막에 넣기. 이거 안 넣으면 에러남 
        	});
    	
    	return http.build();
    }
}
