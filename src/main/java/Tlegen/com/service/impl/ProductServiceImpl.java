package Tlegen.com.service.impl;

import Tlegen.com.DAO.ProductDao;
import Tlegen.com.entity.Product;
import Tlegen.com.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.print.PrintService;
import java.util.List;


@ApplicationScoped
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        try {
            productDao.create(product);
            return product;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании OrderDetail: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> read() {
        return productDao.read();
    }

    @Override
    public Product readId(int id) {
        Product detail = productDao.readById(id);
        if (detail == null) {
            throw new IllegalArgumentException("Информация о заказе не найдена");
        }
        return detail;
    }

    @Override
    public Product update(int id, String name) {
        try {
            return productDao.update(id, name);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при обновлении OrderDetail", e);
        }
    }

    @Override
    public boolean delete(int id) {
        return productDao.delete(id);
    }

}
