# Bai tap

# 1. tao CSDL school
CREATE DATABASE IF NOT EXISTS school;
USE school;

#2. Bảng Class có các trường lần lượt là id, name
CREATE TABLE IF NOT EXISTS class (
id_class INT PRIMARY KEY,
name_class VARCHAR(50) NOT NULL
);

#3. Bảng Teacher có các trường lần lượt là id, name, age, country
CREATE TABLE IF NOT EXISTS teachers (
id_teacher INT PRIMARY KEY,
name_teacher VARCHAR(50) NOT NULL,
age_teacher TINYINT,
country VARCHAR(255)
);

#4. Luyện tập thêm các câu lệnh cơ bản 
# thêm dữ liệu mới vào bảng
#INSERT INTO teachers (id_teacher, name_teacher) VALUES (2, 'Nguyen Van A');
INSERT INTO teachers (id_teacher, name_teacher) VALUES (3, 'Nguyen Van A');
INSERT INTO teachers (id_teacher, name_teacher) VALUES (4, 'Nguyen Van B');
INSERT INTO teachers (id_teacher, name_teacher) VALUES (5, 'Nguyen Van C');
INSERT INTO teachers (id_teacher, name_teacher) VALUES (6, 'Nguyen Van D');

# UPDATE
UPDATE teachers SET age_teacher = 50 WHERE id_teacher = 2;
UPDATE teachers SET name_teacher = 'Nguyen Van B' WHERE id_teacher = 1;

# Thay đổi cấu trúc bảng
ALTER TABLE teachers ADD COLUMN phone TINYINT;

# Xóa dữ liệu từ bảng
DELETE FROM teachers WHERE id_teacher = 4;

# Truy vấn dữ liệu từ bảng
SELECT * FROM  teachers;

# Xóa bảng khỏi CSDL
# DROP TABLE IF EXISTS teachers;

# Xóa CSDL
# DROP DATABASE IF EXISTS school;

