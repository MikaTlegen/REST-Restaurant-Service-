package Tlegen.com.controller.DAO;

import java.util.List;

public interface DAO<T, N> {
    T create(T t);

    List<T> readAll();

    T read(int i);

    boolean update(T t, int i);

    boolean delete(int i);

}
