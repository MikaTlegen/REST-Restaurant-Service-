package Tlegen.com.service.impl;

import Tlegen.com.DAO.OrderDetailDao;
import Tlegen.com.entity.OrderDetail;
import Tlegen.com.service.OrderDetailService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;



@ApplicationScoped
public class OrderDetailServiceImpl implements OrderDetailService {

    @Inject
    private OrderDetailDao orderDetailDao;

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        try {
            orderDetailDao.create(orderDetail);
            return orderDetail;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при создании OrderDetail: " + e.getMessage(), e);
        }
    }

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailDao.read();
    }

    @Override
    public OrderDetail get(int id) {
        OrderDetail detail = orderDetailDao.readById(id);
        if (detail == null) {
            throw new IllegalArgumentException("Информация о заказе не найдена");
        }
        return detail;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        try {
            return orderDetailDao.update(orderDetail);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при обновлении OrderDetail", e);
        }
    }

    @Override
    public boolean delete(int id) {
        return orderDetailDao.delete(id);
    }
}