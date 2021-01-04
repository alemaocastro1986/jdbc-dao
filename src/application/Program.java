package application;

import core.dao.ISellerDao;
import core.entities.Seller;
import infra.dao.DaoFactory;

public class Program {
    public static void main(String[] args) {
        ISellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(1);

        System.out.println(seller);
    }
}
