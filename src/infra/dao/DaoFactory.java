package infra.dao;

import core.dao.IDepartamentDao;
import core.dao.ISellerDao;

public class DaoFactory {
    public static ISellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }

    public static IDepartamentDao createDepartmentDao(){
        return new DepartmentDaoJDBC();
    }

}
