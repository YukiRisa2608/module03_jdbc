package ra.demospringmvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class}; // gọi đầu tiên
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0]; // gọi thứ 2
    }

    @Override
    protected String[] getServletMappings() { // gọi thức 3
        return new String[]{"/"};
    }

    @Override  // cấu hình sử dụng utf-8
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("utf-8");
        return new Filter[]{filter};
    }
}
