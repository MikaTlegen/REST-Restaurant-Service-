
DROP TABLE IF EXISTS productCategory CASCADE;
DROP TABLE IF EXISTS OrderDetail CASCADE;
DROP TABLE IF EXISTS Product CASCADE;
DROP TABLE IF EXISTS OrderDetail_Product CASCADE;
DROP TABLE IF EXISTS Product_ProductCategory;

CREATE TYPE CategoryType AS ENUM ('Напитки', 'Первое', 'Второе');

CREATE TABLE IF NOT EXISTS ProductCategory
(
    id INT PRIMARY KEY NOT NULL ,
    name VARCHAR(255) NOT NULL UNIQUE,
    type CategoryType
);

CREATE TABLE IF NOT EXISTS OrderDetail
(
    id INT PRIMARY KEY NOT NULL ,
    orderStatus VARCHAR(255),
    totalAmount DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS Product
(
    id INT PRIMARY KEY NOT NULL ,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    quantity INT,
    available BOOLEAN
);

CREATE TABLE IF NOT EXISTS OrderDetail_Product
(
    orderDetail_ProductId BIGSERIAL PRIMARY KEY NOT NULL ,
    orderDetailId INT,
    productId INT,
    FOREIGN KEY (productId) REFERENCES Product(id),
    FOREIGN KEY (orderDetailId) REFERENCES OrderDetail(id)
);


CREATE TABLE IF NOT EXISTS Product_ProductCategory
(
    product_productCategoryId BIGSERIAL PRIMARY KEY NOT NULL ,
    productId INT,
    categoryId INT,
    FOREIGN KEY (productId) REFERENCES Product(id),
    FOREIGN KEY (categoryId) REFERENCES ProductCategory(id)
);

