package Tlegen.com.DAO;

import java.util.List;

public interface DAO<T, N> {
    T create(T t);

    List<T> read();

    T readById(int id);

    T update(T t);

    boolean delete(int n);

}
