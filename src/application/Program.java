package application;

import core.dao.IDepartamentDao;
import core.dao.ISellerDao;
import core.entities.Department;
import core.entities.Seller;
import infra.dao.DaoFactory;
import utils.TestConsole;

import java.util.Date;
import java.util.List;

public class Program {


    public static void main(String[] args) {
        ISellerDao sellerDao = DaoFactory.createSellerDao();

        TestConsole.describe(" Test Seller Methods ");

        TestConsole.it("Should return a seller by id");
        Seller seller = sellerDao.findById(1);

        TestConsole.printResult(seller.toString());

        TestConsole.it("Should return a seller by department id to equal 2");
        Department department = new Department(2, null);
        List<Seller> sellers = sellerDao.findByDepartment(department);

        for (Seller obj : sellers) {
            TestConsole.printResult(obj.toString());
        }

        TestConsole.it("Should return all sellers");
        sellers = sellerDao.findAll();

        for (Seller obj : sellers) {
            TestConsole.printResult(obj.toString());
        }

        TestConsole.it("Should create a new Seller.");
        Seller newSeller = new Seller(null,
                "John Doe",
                "john-doe@gmail.com",
                new Date(),
                3000.00,
                department);
        sellerDao.store(newSeller);
        TestConsole.printResult("Inserted new Id =" + newSeller.getId());

        TestConsole.it("Should update a seller");
        seller = sellerDao.findById(9);
        seller.setName("John doe doe");
        sellerDao.update(seller);
        TestConsole.printResult("Updated completed");

        TestConsole.it("Should remove a seller by id.");
        seller = sellerDao.findById(13);

        sellerDao.deleteById(newSeller.getId());
        TestConsole.printResult("Deleted completed");


        TestConsole.describe(" Test Department Methods ");

        IDepartamentDao departamentDao = DaoFactory.createDepartmentDao();

        TestConsole.it("should update a department");
        Department dep = new Department(null, "Logistics");
        departamentDao.store(dep);
        TestConsole.printResult(dep.toString());

        TestConsole.it("should update a department");
        dep.setName("Logistic");
        departamentDao.update(dep);
        TestConsole.printResult(dep.toString());

        TestConsole.it("should remove a department by id");
        int id = dep.getId();
        departamentDao.deleteById(id);
        TestConsole.printResult("Deleted by id: " + id);

        TestConsole.it("should return a department by id");
        dep = departamentDao.findById(2);
        TestConsole.printResult(dep.toString());


        TestConsole.it("Should return list of Departments:");
        List<Department> departments = departamentDao.findAll();

        for (Department depart : departments) {
            TestConsole.printResult(depart.toString());
        }

    }
}
