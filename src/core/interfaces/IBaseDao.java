package core.interfaces;

import java.util.List;

public interface IBaseDao<TEntity, Tkey>{
    public void store(TEntity entity);
    public void update(TEntity entity);
    public void deleteById(Tkey key);
    public TEntity findById(Tkey key);
    public List<TEntity> findAll();
}
