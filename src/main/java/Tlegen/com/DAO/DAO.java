package Tlegen.com.DAO;

import Tlegen.com.entity.OrderDetail;

import java.util.List;

public interface DAO<T, N> {
    T create(T t);

    List<T> read();

    T readById(int id);

    T update(int n, String m);

    boolean delete(int n);

}
