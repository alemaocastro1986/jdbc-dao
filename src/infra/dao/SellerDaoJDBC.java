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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Department dep = departmentMapper(rs);

                return sellerMapper(rs, dep);
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DbContext.closeResultSet(rs);
            DbContext.closeStatement(ps);
        }

        return null;
    }

    private Seller sellerMapper(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department departmentMapper(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT s.*, d.Name as DepName " +
                            "FROM seller AS s INNER JOIN department as d  " +
                            "ON s.DepartmentId = d.Id " +
                            "ORDER BY s.Name;"
            );

            rs = ps.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();

            while (rs.next()) {
                Department dep = departmentMap.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = departmentMapper(rs);
                    departmentMap.put(rs.getInt("DepartmentId"), dep);
                }

                Seller obj = sellerMapper(rs, dep);
                sellers.add(obj);

            }

            return sellers;
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DbContext.closeResultSet(rs);
            DbContext.closeStatement(ps);
        }

    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT s.*, d.Name as DepName " +
                            "FROM seller AS s INNER JOIN department as d  " +
                            "ON s.DepartmentId = d.Id " +
                            "WHERE d.Id = ? " +
                            "ORDER BY s.Name;"
            );
            ps.setInt(1, department.getId());
            rs = ps.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();

            while (rs.next()) {
                Department dep = departmentMap.get(rs.getInt("DepartmentId"));

                if (dep == null) {
                    dep = departmentMapper(rs);
                    departmentMap.put(rs.getInt("DepartmentId"), dep);
                }

                Seller obj = sellerMapper(rs, dep);
                sellers.add(obj);

            }

            return sellers;
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DbContext.closeResultSet(rs);
            DbContext.closeStatement(ps);
        }

    }
}
