CREATE TABLE Page (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    title     VARCHAR(255),
    content   VARCHAR(500),
    parent_id BIGINT
);
