package ra.song.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    //AbstractAnnotationConfigDispatcherServletInitializer là một lớp trong Spring MVC
    // dùng để cấu hình ứng dụng web mà không cần tới file cấu hình web.xml.
    @Override
    // gọi đầu tiên
    // cung cấp cấu hình chính (root configuration) cho ứng dụng Spring mvc.
    //trả về một mảng chứa  WebConfig.class (lớp cấu hình định nghĩa các bean, view resolvers, và các cấu hình Spring MVC khác).
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // gọi thứ 2
    //chỉ định cấu hình cho DispatcherServlet.
    // thường dùng để cấu hình các aspect riêng của web layer như controllers, view resolvers, handler mappings, v.v.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    // gọi thứ 3
    //xác định các URL pattern mà DispatcherServlet sẽ xử lý.
    //new String[]{"/"}, nghĩa là DispatcherServlet sẽ xử lý tất cả các yêu cầu HTTP đến ứng dụng
    @Override
    protected String[] getServletMappings() { // gọi thức 3
        return new String[]{"/"};
    }

    //bộ lọc được sử dụng để đảm bảo tất cả
    //các yêu cầu và phản hồi qua ứng dụng được xử lý với bộ mã hóa ký tự UTF-8.
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("utf-8");
        return new Filter[]{filter};
    }
    //Lớp AppInit cung cấp cách cấu hình chính cho ứng dụng Spring MVC,
    // bao gồm việc thiết lập các lớp cấu hình cốt lõi,
    // định nghĩa URL được xử lý bởi DispatcherServlet,
    // và thiết lập bộ lọc mã hóa ký tự UTF-8 để đảm bảo rằng dữ liệu được xử lý đúng cách trên toàn bộ ứng dụng.
    //********************:

    //DispatcherServlet là một thành phần trung tâm trong Spring MVC,
    // đóng vai trò là một front controller. Quản lý dòng chảy của yêu cầu HTTP trong một ứng dụng web sử dụng Spring MVC.
    //Luồng:
    //1. Tiếp Nhận Yêu Cầu: DispatcherServlet nhận tất cả các yêu cầu vào ứng dụng và đóng vai trò là điểm đầu vào cho dòng chảy của yêu cầu.
    //2. Chuyển Tiếp Yêu Cầu: Nó quyết định controller nào sẽ xử lý yêu cầu dựa trên cấu hình URL-to-controller mapping.
    //3. Điều Hướng Quy Trình Xử Lý: Sau khi xác định controller cần thiết, DispatcherServlet chuyển yêu cầu đến controller đó. Controller sau đó xử lý yêu cầu và trả về một model và tên view.
    //4. Xử Lý View: Cuối cùng, DispatcherServlet sẽ chuyển tới View Resolver để tìm view tương ứng với tên view trả về từ controller, sau đó render view đó cùng với model để tạo nên phản hồi HTTP.
}
