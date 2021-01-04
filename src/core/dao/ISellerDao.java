package core.dao;

import core.entities.Department;
import core.entities.Seller;
import core.interfaces.IBaseDao;

import java.util.List;

public interface ISellerDao extends IBaseDao<Seller, Integer> {
    public List<Seller> findByDepartment(Department department);
}
