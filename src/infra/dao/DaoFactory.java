package infra.dao;

import core.dao.IDepartamentDao;
import core.dao.ISellerDao;
import infra.database.DbContext;

public class DaoFactory {

    public static ISellerDao createSellerDao(){
        return new SellerDaoJDBC(DbContext.getConnection());
    }

    public static IDepartamentDao createDepartmentDao(){
        return new DepartmentDaoJDBC();
    }

}
