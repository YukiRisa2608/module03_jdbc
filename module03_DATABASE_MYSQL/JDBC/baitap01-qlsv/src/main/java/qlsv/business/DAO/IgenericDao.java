package qlsv.business.DAO;

import java.util.List;

public interface IgenericDao <T, E> {
    void create(T entity);
    T findById(E id);
    List<T> findAll();
    void update(T entity);
    void delete(E id);
}
