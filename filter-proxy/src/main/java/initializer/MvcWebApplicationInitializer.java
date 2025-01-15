package initializer;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import config.AppConfig;
import config.WebConfig;
import jakarta.servlet.Filter;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @NonNull
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

	@Override
	protected Filter[] getServletFilters() { // DelegatingFilterProxy 서블릿 필터(Servlet Filter)로 등록
		return new Filter[] { new DelegatingFilterProxy("realFilter") }; //DelegatingFilterProxy : Spring 컨텍스트에 있는 특정 필터 빈(realFilter)을 호출하기 위한 서블릿 컨테이너의 프록시 필터
	}

    
    
}