package ra.song.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // là một annotation của Spring, đánh dấu một lớp là lớp cấu hình. cho phép định nghĩa các bean và cấu hình ứng dụng Spring.
@EnableWebMvc // là một annotation khác của Spring, được sử dụng để kích hoạt các chức năng và cấu hình mặc định của Spring MVC. như việc định tuyến yêu cầu, chuyển đổi dữ liệu, xử lý ngoại lệ...
@ComponentScan(basePackages = "ra.song")
//basePackages = "ra.song" chỉ định Spring quét các package bắt đầu từ "ra.song" để tìm kiếm các component.
//để tự động tạo ra các đối tượng Bean-một đối tượng được quản lý bởi Spring container) từ các lớp trong package ".
public class WebConfig implements WebMvcConfigurer {
    @Bean
    //ViewResolver, một thành phần quan trọng trong Spring MVC dùng để xác định cách view được tìm kiếm và render.
    public ViewResolver viewResolver(){
        //InternalResourceViewResolver là một triển khai của ViewResolver dùng trong các ứng dụng JSP.
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //resolver.setPrefix("/WEB-INF/views/") và resolver.setSuffix(".jsp") cấu hình đường dẫn cho các file view,
        // với các file JSP được đặt trong thư mục /WEB-INF/views/.
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    //""""""""""" Lý thuyết"""""""""""
    //Component là một cách để định nghĩa rằng một lớp nào đó sẽ được Spring xem xét để tạo ra một bean tương ứng.
    //Những lớp này thường được đánh dấu bằng các annotation như @Component, @Service, @Repository, và @Controller.
    //@Component: Đây là annotation cơ bản nhất, chỉ ra rằng lớp đó là một component. Nó thường dùng cho các đối tượng mà không rơi vào các loại cụ thể khác như @Service hay @Controller.
    //@Service: Đánh dấu một lớp là "Service", thường chứa logic nghiệp vụ.
    //@Controller: Chỉ ra rằng lớp đó là một Controller trong mô hình MVC, nơi xử lý các yêu cầu HTTP.
    //@Repository: Dùng cho các lớp truy cập dữ liệu, chẳng hạn như truy cập database.
    //Một bean là thể hiện cụ thể của một đối tượng mà Spring container tạo và quản lý. Mỗi bean là một instance của một component.
    //component như là "mẫu" để tạo ra các bean, và bean là các đối tượng thực tế mà Spring container tạo ra và quản lý.
    //Component chỉ ra cái gì sẽ trở thành một bean, trong khi bean là kết quả cuối cùng của quá trình này
}
