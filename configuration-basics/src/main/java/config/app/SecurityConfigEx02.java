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
public class SecurityConfigEx02 {
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return new WebSecurityCustomizer() {
	        @Override
	        public void customize(WebSecurity web) {
	            web
	                .ignoring() // 필터링 체인에서 제외하도록 설정
	                .requestMatchers(new AntPathRequestMatcher("/assets/**"));
	        }
	    };
	}
	
	// @EnableWebSecurity가 있기에 SecurityFilterChain만 만들어주면 됨 
	// HttpSecurity http 는 빌더!
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable {
		return http.build(); // 필터 설정 따로 안 했기 때문에 기본 설정 (필터 10개 생김) 
	}
}
