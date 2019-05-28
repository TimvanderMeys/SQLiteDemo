package dao;

import java.util.ArrayList;

public interface GenericDao<T> {
    public ArrayList<T> findAll();

    public boolean save(T t);

    public boolean update(T t);

    public boolean delete(T t);
}
