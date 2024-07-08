package Tlegen.com.service;

import java.util.List;

public interface Service<T> {

    T create(T t);

    List<T> getAll();

    T get(int id);

    T update(T t);

    boolean delete(int id);


}
