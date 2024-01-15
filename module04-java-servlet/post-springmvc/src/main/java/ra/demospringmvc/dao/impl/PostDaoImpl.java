package ra.demospringmvc.dao.impl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ra.demospringmvc.dao.IPostDao;
import ra.demospringmvc.model.Post;
import ra.demospringmvc.util.ConnectDB;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
//@Component
public class PostDaoImpl implements IPostDao {
    @Override
    public List<Post> findAllPost() {
        List<Post> list = new ArrayList<>();
        // mở kết nối
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select * from post");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Post p = new Post();
                p.setId(rs.getInt("id"));
                p.setTitle(rs.getString("title"));
                p.setAuthor(rs.getString("author"));
                p.setContent(rs.getString("content"));
                p.setImageUrl(rs.getString("imageUrl"));
                p.setCreatedDate(rs.getTimestamp("createdAt"));
                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }
    @Override
    public List<Post> findAllPostByTitleOrContent(String search) {
        List<Post> list = new ArrayList<>();
        // mở kết nối
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select * from post where title like ? or content like ?");
            callSt.setString(1,"%"+search+"%");
            callSt.setString(2,"%"+search+"%");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Post p = new Post();
                p.setId(rs.getInt("id"));
                p.setTitle(rs.getString("title"));
                p.setAuthor(rs.getString("author"));
                p.setContent(rs.getString("content"));
                p.setImageUrl(rs.getString("imageUrl"));
                p.setCreatedDate(rs.getTimestamp("createdAt"));

                list.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void addPost(Post post) {
        if (post == null) {
            // In ra giá trị của createdDate để kiểm tra
//            System.out.println("Created Date: " + post.getCreatedDate());

            throw new IllegalArgumentException("Post cannot be null");
        }
        // mở kết nối
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("INSERT INTO post (title, author, content, imageUrl,createdAt) VALUES (?, ?, ?, ?,?)");

            callSt.setString(1, post.getTitle());
            callSt.setString(2, post.getAuthor());
            callSt.setString(3, post.getContent());
            callSt.setString(4, post.getImageUrl());
            callSt.setTimestamp(5, new Timestamp(new java.util.Date().getTime()));

            callSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void editPost(Post post) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("UPDATE post SET title=?, author=?, content=?, imageUrl=? WHERE id=?");
            callSt.setString(1, post.getTitle());
            callSt.setString(2, post.getAuthor());
            callSt.setString(3, post.getContent());
            callSt.setString(4, post.getImageUrl());
            callSt.setInt(5, post.getId());
            callSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void deletePost(int postId) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement callSt = conn.prepareCall("DELETE FROM post WHERE id=?");
            callSt.setInt(1, postId);
            callSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }
}