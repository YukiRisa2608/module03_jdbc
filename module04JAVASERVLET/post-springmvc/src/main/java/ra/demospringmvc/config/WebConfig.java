package ra.demospringmvc.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ra.demospringmvc.dao.IPostDao;
import ra.demospringmvc.dao.impl.PostDaoImpl;

@Configuration //là lơp cấu hình
@EnableWebMvc // cho phép  sử dụng cấu hình mvc
@ComponentScan(basePackages = "ra.demospringmvc")
// quét và tự động phát hiện các component (@Component
// ,@Service, @Controller, @Repository)
public class WebConfig implements WebMvcConfigurer {
    // cấu hinh beans  ViewResolver
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }

}
