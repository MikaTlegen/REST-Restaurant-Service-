package Tlegen.com.service;

import java.util.List;

public interface Service<T> {

    T create(T t);

    List<T> read();

    T readId(int id);

    T update(int id, String k);

    boolean delete(int id);


}
