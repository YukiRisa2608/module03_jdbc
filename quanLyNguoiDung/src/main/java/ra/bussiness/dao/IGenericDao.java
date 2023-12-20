package ra.bussiness.dao;

import java.util.List;

public interface IGenericDao<U, T, E> {
    List<U> findAll();
    T findById(E id);
    void save(T t);  // Thêm mới hoặc cập nhật
    void delete(E id);
}
