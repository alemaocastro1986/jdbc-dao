package infra.dao;

import core.dao.IDepartamentDao;
import core.entities.Department;
import infra.database.DbContext;
import infra.database.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements IDepartamentDao {
    protected Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void store(Department department) {
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement(
                    "INSERT INTO department" +
                            "(Name)" +
                            "VALUES(?);"
                    , Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, department.getName());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    department.setId(rs.getInt(1));
                }
                DbContext.closeResultSet(rs);
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DbContext.closeStatement(ps);
        }

    }

    @Override
    public void update(Department department) {
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement(
                    "UPDATE department " +
                            "SET Name=? " +
                            "WHERE Id=?; ");

            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());

            int rows = ps.executeUpdate();

            if (rows <= 0) {
                throw new DbException("Nothing rows affected");
            }


        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DbContext.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer key) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM department WHERE Id=?;");

            // Where
            ps.setInt(1, key);

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DbContext.closeStatement(ps);
        }
    }

    @Override
    public Department findById(Integer key) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
            ps.setInt(1, key);

            rs = ps.executeQuery();

            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                return dep;
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DbContext.closeResultSet(rs);
            DbContext.closeStatement(ps);
        }

        return null;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Department> departments = new ArrayList<>();
        try {
            ps = conn.prepareStatement("SELECT * FROM department");

            rs = ps.executeQuery();

            while (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("Id"));
                dep.setName(rs.getString("Name"));
                departments.add(dep);
            }
        } catch (SQLException ex) {
            throw new DbException(ex.getMessage());
        } finally {
            DbContext.closeResultSet(rs);
            DbContext.closeStatement(ps);
        }

        return departments;
    }
}
