package infra.dao;

import core.dao.ISellerDao;
import core.entities.Department;
import core.entities.Seller;
import infra.database.DbContext;
import infra.database.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements ISellerDao {

    protected Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void store(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer key) {

    }

    @Override
    public Seller findById(Integer key) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT s.*, d.Name as DepName " +
                            "FROM seller AS s INNER JOIN department as d  " +
                            "ON s.DepartmentId = d.Id " +
                            "WHERE s.Id = ?;"
            );
            ps.setInt(1, key);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));

                Seller seller = new Seller();
                seller.setId(rs.getInt("Id"));
                seller.setName(rs.getString("Name"));
                seller.setEmail(rs.getString("Email"));
                seller.setBaseSalary(rs.getDouble("BaseSalary"));
                seller.setBirthDate(rs.getDate("BirthDate"));
                seller.setDepartment(dep);

                return seller;
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        }finally {
            DbContext.closeResultSet(rs);
            DbContext.closeStatement(ps);
        }

        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
