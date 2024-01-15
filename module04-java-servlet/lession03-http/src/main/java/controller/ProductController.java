package controller;

import dto.ProductRequest;
import model.Product;
import service.impl.ProductServiceImpl;
import service.interf.IProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="ProductController",value = "/product-controller")
public class ProductController extends HttpServlet {
    private static IProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action =  req.getParameter("action");
        if (action!=null){
            switch (action){
                case "SHOWALL" :
                    showAllProduct(req, resp);
                    break;
                case "ADD":

            }
        }
    }

    protected void showAllProduct (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy ra danh sách cần hiển thị
        List<Product> list = productService.findAll();
        // chuyển nó qua view để hiển thị ra
        req.setAttribute("list",list);
        req.getRequestDispatcher("/views/product.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action =  req.getParameter("action");
        if (action!=null){
            switch (action){
                case "ADD":
                    // lấy ra toàn bộ thông tin của người dùng nhâp
                    String productName = req.getParameter("productName");
                    String description = req.getParameter("description");
                    Double price = Double.valueOf(req.getParameter("price"));
                    Integer stock = Integer.valueOf(req.getParameter("stock"));
                    ProductRequest pro = new ProductRequest(productName,price,description,stock);
                    productService.add(pro);
                    showAllProduct(req, resp);
                    break;
            }
        }
    }
}