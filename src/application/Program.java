package application;

import core.dao.ISellerDao;
import core.entities.Department;
import core.entities.Seller;
import infra.dao.DaoFactory;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        ISellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=".repeat(5) + " Test 1 - Seller find byId " + "=".repeat(5));
        Seller seller = sellerDao.findById(1);

        System.out.println(seller);

        System.out.println("=".repeat(5) + " Test 2 - Seller find byDepartment " + "=".repeat(5));
        Department department = new Department(2, null);
        List<Seller> sellers = sellerDao.findByDepartment(department);

        for (Seller obj : sellers) {
            System.out.println(obj);
        }

    }
}
