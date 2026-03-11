CREATE TABLE users
(
    username   VARCHAR(20) PRIMARY KEY,
    password   VARCHAR(300) NOT NULL,
    role       VARCHAR(10)  NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP    NULL
);

CREATE TABLE categories
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(30) NOT NULL
);

CREATE TABLE products
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id    BIGINT       NOT NULL,
    thumbnail_url  VARCHAR(500) NOT NULL,
    name           VARCHAR(50)  NOT NULL,
    price          INT          NOT NULL,
    stock_quantity INT          NOT NULL,
    created_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at     TIMESTAMP    NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE product_detail_images
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT       NOT NULL,
    image_url  VARCHAR(500) NOT NULL,
    sort_order INT          NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE cart_items
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(20) NOT NULL,
    product_id BIGINT      NOT NULL,
    quantity   INT         NOT NULL DEFAULT 1,
    UNIQUE KEY uk_user_product (username, product_id),
    CONSTRAINT fk_cart_user FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE,
    CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(20) NOT NULL,
    status      VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    total_price INT         NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at  TIMESTAMP   NULL,
    FOREIGN KEY (username) REFERENCES users (username),
    INDEX idx_orders_deleted_at (deleted_at)
);

CREATE TABLE order_items
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    price      INT    NOT NULL,
    quantity   INT    NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);