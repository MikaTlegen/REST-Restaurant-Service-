package Tlegen.com.controller.DAO;

import java.util.List;

public interface DAO<T, N> {
    List<T> create(T t);

    List<T> readAll();

    List<T> update(int n, String m);

    boolean delete(int n);

}
