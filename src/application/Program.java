package application;

import core.dao.ISellerDao;
import core.entities.Department;
import core.entities.Seller;
import infra.dao.DaoFactory;

import java.util.Date;
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

        System.out.println("=".repeat(5) + " Test 3 - Seller find All " + "=".repeat(5));
        sellers = sellerDao.findAll();

        for (Seller obj : sellers) {
            System.out.println(obj);
        }

        System.out.println("=".repeat(5) + " Test 4 - Seller Create " + "=".repeat(5));
        Seller newSeller = new Seller(null,
                "John Doe",
                "john-doe@gmail.com",
                new Date(),
                3000.00,
                department);
        sellerDao.store(newSeller);
        System.out.println("Inserted new Id =" + newSeller.getId());

        System.out.println("=".repeat(5) + " Test 5 - Seller Update " + "=".repeat(5));
        seller = sellerDao.findById(9);
        seller.setName("John doe doe");
        sellerDao.update(seller);
        System.out.println("Updated completed");

        System.out.println("=".repeat(5) + " Test 6 - Seller Update " + "=".repeat(5));
        seller = sellerDao.findById(10);

        sellerDao.deleteById(seller.getId());
        System.out.println("Deleted completed");


    }
}
