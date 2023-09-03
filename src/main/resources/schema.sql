Drop TABLE IF EXISTS pages;

CREATE TABLE pages (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(255),
                       content TEXT,
                       parentId INT,
                       FOREIGN KEY (parentId) REFERENCES pages(id)
);
