package infra.dao;

import core.interfaces.IBaseDao;

import java.util.List;

public class BaseDao <TEntity,Tkey> implements IBaseDao<TEntity, Tkey> {
    @Override
    public void store(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void deleteById(Object key) {

    }

    @Override
    public TEntity findById(Object key) {
        return null;
    }

    @Override
    public List<TEntity> findAll() {
        return null;
    }
}
