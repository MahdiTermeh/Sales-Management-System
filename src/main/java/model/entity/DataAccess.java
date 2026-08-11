package model.entity;

import java.util.List;

public interface DataAccess <I,T> {

    I save(I i) throws Exception;
    boolean update(I i) throws Exception;
    boolean delete(T t) throws Exception;
    List<I> findAll() throws Exception;
    I findById(T t) throws Exception;
}
