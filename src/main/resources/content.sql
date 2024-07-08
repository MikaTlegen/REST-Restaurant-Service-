INSERT INTO ProductCategory (id, name, type)
VALUES (1, 'Холодные', 'Напитки'),
       (2, 'Горячие', 'Напитки'),
       (3, 'Алкогольные', 'Напитки'),
       (4, 'Безалкогольные', 'Напитки');

INSERT INTO Product (id, name, price, quantity, available)
VALUES (1, 'Чай Зеленый', 300, 1, TRUE),
       (2, 'Освежающий коктейль', 400, 1, TRUE),
       (3, 'Кофе', 300, 1, TRUE),
       (4, 'Коктейль Зажигалка', 300, 1, TRUE),
       (5, 'Коктейль Яркость', 400, 1, TRUE);

INSERT INTO Product_ProductCategory (productId, categoryId)
VALUES (1, 1),/* -- Чай Зеленый -> Холодные */
       (2, 1),/*-- Освежающий коктейль -> Холодные */
       (3, 2),/* -- Кофе -> Горячие */
       (4, 2),/* -- Коктейль Зажигалка -> Горячие */
       (5, 2),/* -- Коктейль Яркость -> Горячие */
       (4, 3),/* -- Коктейль Зажигалка -> Алкогольные */
       (5, 3),/* -- Коктейль Яркость -> Алкогольные */
       (1, 4),/* -- Чай Зеленый -> Безалкогольные */
       (2, 4),/* -- Освежающий коктейль -> Безалкогольные */
       (3, 4),/* -- Кофе -> Безалкогольные */
       (4, 4),/* -- Коктейль Зажигалка -> Безалкогольные */
       (5, 4);/* -- Коктейль Яркость -> Безалкогольные */


INSERT INTO OrderDetail (id, orderStatus, totalAmount)
VALUES (1, 'Новый', 1500.00),
       (2, 'Отмена', 2000.00);

INSERT INTO OrderDetail_Product (orderDetailId, productId)
VALUES (1, 1),/* -- Чай Зеленый -> Заказ 1 */
       (1, 3),/* -- Кофе -> Заказ 1 */
       (2, 2),/* -- Освежающий коктейль -> Заказ 2 */
       (2, 4);/* -- Коктейль Зажигалка -> Заказ 2 */

