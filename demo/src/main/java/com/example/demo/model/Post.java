package com.example.demo.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    private Integer id;
    private String author,title,content,imageUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
}
