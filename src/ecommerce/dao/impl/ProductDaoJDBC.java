package ecommerce.dao.impl;

import db.DB;
import db.DbException;
import ecommerce.dao.ProductDao;
import ecommerce.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {
    private Connection conn;

    public ProductDaoJDBC(Connection conn) { //Força injeção de dependência
        this.conn = conn;
    }

    @Override
    public void insert(Product obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO product "
                    + "(ProductName, ProductPrice, Quantity) "
                    + "VALUES "
                    + "(?, ? ,?)" ,
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setDouble(2, obj.getPrice());
            st.setInt(3, obj.getQuantity());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No tows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Product obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE product "
                            + "SET ProductID = ?, ProductName = ?, ProductPrice = ?, Quantity = ?  "
                            + "WHERE ProductID = ?");

            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());
            st.setDouble(3, obj.getPrice());
            st.setInt(4, obj.getQuantity());
            st.setInt(5, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM product WHERE ProductID = ?");
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void updateProductQuantity(Integer id, Integer quantity) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE product SET Quantity = ? WHERE ProductID = ?");
            st.setInt(1, quantity);
            st.setInt(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Product findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM product WHERE ProductID = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Product prod = new Product(rs);
                prod.setId(rs.getInt("ProductID"));
                prod.setName(rs.getString("ProductName"));
                prod.setPrice(rs.getDouble("ProductPrice"));
                prod.setQuantity(rs.getInt("Quantity"));
                return prod;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }


    @Override
    public List<Product> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM product ORDER BY ProductID");
            rs = st.executeQuery();

            List<Product> list = new ArrayList<>();

            while (rs.next()) {
                Product prod = new Product(rs);
                prod.setId(rs.getInt("ProductID"));
                prod.setName(rs.getString("ProductName"));
                prod.setPrice(rs.getDouble("ProductPrice"));
                prod.setQuantity(rs.getInt("Quantity"));
                list.add(prod);
            }

            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }


}
