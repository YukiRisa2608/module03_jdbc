package ra.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping({"", "/dashboard"})
    public String dashboard(){
        return "admin/index";
    }
    @RequestMapping( "/user")
    public String user(){
        return "admin/user";
    }
    @RequestMapping( "/category")
    public String category(){
        return "admin/category";
    }
    @RequestMapping("/student")
    public String student(){
        return "admin/student";
    }
}
