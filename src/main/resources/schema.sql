CREATE TABLE page (
    page_id bigint AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) not null,
    content text,
    parent_id bigint DEFAULT NULL
);