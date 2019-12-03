CREATE TABLE book(
idbook INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(70) NOT NULL,
image VARCHAR(30) NOT NULL,
description VARCHAR(70) NOT NULL,
author VARCHAR(50) NOT NULL,
isActive TINYINT(1) DEFAULT 1,
price DECIMAL(7,2) NOT NULL
);

INSERT INTO book (title,image,author,description, price) 
VALUES 
('Toi thay hoa vang tren co xanh', 'toithayhoavangtrencoxanh.jpg', 'Nguyen Nhat Anh', 'Lorem ipsum dolor sit amet', 100),
('Nha gia kim', 'nhagiakim.jpg', 'Paulo Coelho', 'Lorem ipsum dolor sit amet', 80),
('Hai so phan', 'haisophan.jpg', 'Jeffrey Archer', 'Lorem ipsum dolor sit amet', 120);

CREATE TABLE user(
userId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
userName VARCHAR(70) NOT NULL,
password VARCHAR(50) NOT NULL,
role VARCHAR(10) NOT NULL DEFAULT 'User'
);

INSERT INTO user (userName, password, role) 
VALUES 
('admin', 'admin', 'Admin'),
('user', 'user', 'User');
