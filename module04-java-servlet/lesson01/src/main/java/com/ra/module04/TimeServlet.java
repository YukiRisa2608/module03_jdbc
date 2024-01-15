package com.ra.module04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@WebServlet(name = "TimeServlet",value = "/time-servlet")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // nhận các yêu cầu gửi theo phương thức get : form:get , thẻ a , gõ trực tiếp trên đường dẫn
//         gửi giá trị sang jsp
//        req.setAttribute("date",new Date());
//        req.setAttribute("name","Hồ Xuân Hùng");
//        req.setAttribute("age",24);
        // lấy ra tham số trong request gửi lên
        String action = req.getParameter("action");
        if (action!=null){
            switch (action){
                case "time":
                    req.setAttribute("list", Arrays.asList(1,2,4,5,7,8,3,6));
                    req.setAttribute("date", LocalTime.now());
                    break;
                case "day":
                    req.setAttribute("date", LocalDate.now());
                    break;
            }
        }
        // gửi đi đâu
        req.getRequestDispatcher("/views/home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // nhận cac yêu cầu gửi theo phương thức post : form:post
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action!=null){
            switch (action){
                case "show":
                    String id = req.getParameter("id");
                    String name = req.getParameter("name");
                    int age = Integer.parseInt(req.getParameter("age"));
                    req.setAttribute("person",new Person(id,name,age));
                    req.getRequestDispatcher("/views/profile.jsp").forward(req,resp);
                    break;
            }
        }
    }
}
