package hayk.abajyan.apps.am.config;

import hayk.abajyan.apps.am.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    @Autowired
    public WebMvcConfig(LogInterceptor logInterceptor) {
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }
}
