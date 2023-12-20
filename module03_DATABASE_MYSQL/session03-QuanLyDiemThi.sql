CREATE DATABASE IF NOT EXISTS `QuanLyDiemThi`;
USE `QuanLyDiemThi`;

CREATE TABLE IF NOT EXISTS `Student`(
studentId VARCHAR(4) PRIMARY KEY,
studentName VARCHAR(100) NOT NULL,
birthday DATE NOT NULL,
gender BIT(1),
address TEXT NOT NULL,
phoneNumber VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS `Subject`(
subjectId VARCHAR(4) PRIMARY KEY,
subjectName VARCHAR(45) NOT NULL,
priority INT(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Mark` (
subjectId VARCHAR(4),
studentId VARCHAR(4),
`point` DOUBLE NOT NULL,
FOREIGN KEY (subjectId) REFERENCES `Subject`(subjectId),
FOREIGN KEY (studentId) REFERENCES `Student`(studentId)
);

# 1.Insert data
INSERT INTO Student (studentId, studentName, birthday, gender, address, phoneNumber)
VALUES
('S001', 'Nguyen Van A', '2000-01-15', 1, 'Ha Noi', '1234567890'),
('S002', 'Nguyen Van B', '2001-03-22', 0, 'Can Giang', '9876543210'),
('S003', 'Nguyen Van C', '1999-07-10', 1, 'HCM', '5551234567'),
('S004', 'Nguyen Van D', '2000-11-05', 0, 'Hai Phong', '4447890123'),
('S005', 'Nguyen Van E', '2002-05-18', 1, 'Hai Duong', '6789012345');

INSERT INTO Subject (subjectId, subjectName, priority)
VALUES
('SUB1', 'Math', 1),
('SUB2', 'Physics', 2),
('SUB3', 'English', 1),
('SUB4', 'History', 2),
('SUB5', 'Biology', 1);

INSERT INTO Mark (subjectId, studentId, `point`)
VALUES
('SUB1', 'S001', 8.5),
('SUB2', 'S002', 9.0),
('SUB3', 'S003', 2.5),
('SUB4', 'S004', 7.0),
('SUB5', 'S005', 5.5);

#2. UPDATE
-- Cập nhật địa chỉ và số điện thoại cho sinh viên có studentId là 'S001'
UPDATE Student
SET address = 'Can Tho', phoneNumber = '1112223333'
WHERE studentId = 'S001';

-- Cập nhật tên môn học và he so cho môn học có subjectId là 'SUB1'
UPDATE Subject
SET subjectName = 'Art', priority = 3
WHERE subjectId = 'SUB1';

-- Cập nhật điểm cho môn học 'SUB2' của sinh viên 'S002'
UPDATE Mark
SET point = 10
WHERE subjectId = 'SUB2' AND studentId = 'S002';

# 3. Truy van du lieu
-- 1. Lấy ra tất cả thông tin của sinh viên trong bảng Student . [4 điểm]
SELECT * FROM Student;

-- 2. Hiển thị tên và mã môn học của những môn có hệ số bằng 1. [4 điểm]
SELECT subjectName, subjectId
FROM `Subject`
WHERE priority = 1;

-- 3. Hiển thị thông tin học sinh bào gồm: mã học sinh, tên học sinh, tuổi (bằng năm hiện tại trừ
-- năm sinh) , giới tính (hiển thị nam hoặc nữ) và quê quán của tất cả học sinh. [4 điểm]
SELECT studentId,
       studentName,
       YEAR(CURDATE()) - YEAR(birthday) AS age,
       CASE WHEN gender = 1 THEN 'Nam' ELSE 'Nữ' END AS gender,
       address
FROM Student;

-- 4. Hiển thị thông tin bao gồm: tên học sinh, tên môn học , điểm thi của tất cả học sinh của môn
-- Toán và sắp xếp theo điểm giảm dần. [4 điểm]
SELECT s.studentName, sub.subjectName, m.`point`
FROM Mark m
JOIN Student s ON m.studentId = s.studentId
JOIN Subject sub ON m.subjectId = sub.subjectId
WHERE sub.subjectName = 'Toán'
ORDER BY m.point DESC;

-- 5. Thống kê số lượng học sinh theo giới tính ở trong bảng (Gồm 2 cột: giới tính và số lượng).
SELECT CASE WHEN gender = 1 THEN 'Nam' ELSE 'Nữ' END AS gender,
       COUNT(*) AS studentCount
FROM Student
GROUP BY gender;

-- 6. Tính tổng điểm và điểm trung bình của các môn học theo từng học sinh (yêu cầu sử dụng hàm
-- để tính toán) , bảng gồm mã học sinh, tên hoc sinh, tổng điểm và điểm trung bình. [5 điểm]
SELECT m.studentId,
       s.studentName,
       SUM(m.point) AS totalMark,
       AVG(m.point) AS averageMark
FROM Mark m
JOIN Student s ON m.studentId = s.studentId
GROUP BY m.studentId, s.studentName;

# Bài 4: Tạo View, Index, Procedure [20 điểm]:
# 1. Tạo VIEW có tên STUDENT_VIEW lấy thông tin sinh viên bao gồm : mã học sinh, tên học
#  sinh, giới tính , quê quán . [3 điểm]

CREATE VIEW STUDENT_VIEW AS 
SELECT studentId, studentName, gender, address
FROM student;

# 2. Tạo VIEW có tên AVERAGE_MARK_VIEW lấy thông tin gồm:mã học sinh, tên học sinh,
# điểm trung bình các môn học . [3 điểm]

CREATE VIEW AVERAGE_MARK_VIEW AS
SELECT s.studentId, s.studentName, AVG(m.point) AS AVG
FROM student s JOIN mark m
ON s.studentId = m.studentId
GROUP BY s.studentId, s.studentName;

# 3. Đánh Index cho trường `phoneNumber` của bảng STUDENT. [2 điểm]

CREATE INDEX index_phoneNumber ON student(phoneNumber);
SHOW INDEX FROM student WHERE Column_name  = 'phoneNumber';

# 4. Tạo các PROCEDURE sau:
# - Tạo PROC_INSERTSTUDENT dùng để thêm mới 1 học sinh bao gồm tất cả thông tin học sinh đó. [4 điểm]

DELIMITER //
CREATE PROCEDURE PROC_INSERTSTUDENT (
IN new_studentId VARCHAR(4),
IN new_studentName VARCHAR(100),
IN new_birthday DATE,
IN new_gender BIT(1),
IN new_address TEXT,
IN new_phoneNumber VARCHAR(45)
)
BEGIN 
	INSERT INTO student(studentId, studentName, birthday, gender, address, phoneNumber)
	VALUES (new_studentId, new_studentName, new_birthday, new_gender, new_address, new_phoneNumber);
END;
//

CALL PROC_INSERTSTUDENT ('a1', 'Nham', '2000-08-08', 1, 'VN', '0123456789');

# - Tạo PROC_UPDATESUBJECT dùng để cập nhật tên môn học theo mã môn học.
DELIMITER //
CREATE PROCEDURE PROC_UPDATESUBJECT (
IN in_subjectId VARCHAR(4),
IN new_subjectName VARCHAR(45)
)
BEGIN 
	UPDATE subject 
    SET subjectName = new_subjectName
    WHERE subjectId = in_subjectId;
END;
//

CALL PROC_UPDATESUBJECT('SUB1', 'Art1');

# - Tạo PROC_DELETEMARK dùng để xoá toàn bộ điểm các môn học theo mã học sinh và trả về số bản ghi đã xóa. [4 điểm]
DELIMITER //
CREATE PROCEDURE PROC_DELETEMARK (
IN in_studentId VARCHAR(4),
OUT out_deleted_record_numbers INT
)
BEGIN
	DECLARE countDeleteRecord INT DEFAULT 0;
	DELETE FROM mark
    WHERE studentId = in_studentId;
    SELECT row_count() INTO countDeleteRecord;
    SET out_deleted_record_numbers = countDeleteRecord;
END;
//

CALL PROC_DELETEMARK ('S001', @countDeleteRecord);
SELECT @countDeleteRecord AS NumOfDeleteRecord;
