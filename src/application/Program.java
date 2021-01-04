package application;

import core.dao.ISellerDao;
import core.entities.Seller;
import infra.dao.DaoFactory;

public class Program {
    public static void main(String[] args) {
        ISellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=".repeat(5) + " Test 1 - Seller find byId " + "=".repeat(5));
        Seller seller = sellerDao.findById(1);

        System.out.println(seller);
    }
}
