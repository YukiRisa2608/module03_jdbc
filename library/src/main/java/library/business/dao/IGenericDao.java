package library.business.dao;

import java.util.List;

public interface IGenericDao<U, T, E> {
    List<U> findAll(int displayOption);
    T findById(E id);
    void save(T t); // vừa thêm mới vừa cập nhật
    void delete(E id);
}


