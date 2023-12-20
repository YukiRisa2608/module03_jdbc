CREATE DATABASE IF NOT EXISTS ThucTap;
USE ThucTap;

CREATE TABLE IF NOT EXISTS Khoa (
makhoa char(10) PRIMARY KEY, 
tenkhoa char(30), 
dienthoai char(10)
);

CREATE TABLE IF NOT EXISTS GiangVien (
magv int PRIMARY KEY, 
hotengv char(30), 
luong decimal(5,2), 
makhoa char(10),
FOREIGN KEY (makhoa) REFERENCES Khoa(makhoa)
);

CREATE TABLE IF NOT EXISTS SinhVien (
masv int PRIMARY KEY, 
hotensv char(30), 
makhoa char(10), 
namsinh int, 
quequan char(30),
FOREIGN KEY (makhoa) REFERENCES Khoa(makhoa)
);

CREATE TABLE IF NOT EXISTS Detai (
madt char(10) PRIMARY KEY,
tendt char(30),
kinhphi int,
NoiThucTap char(30)
);

CREATE TABLE IF NOT EXISTS HuongDan (
masv int,
madt char(10), 
magv int, 
ketqua decimal(5,2)
);


