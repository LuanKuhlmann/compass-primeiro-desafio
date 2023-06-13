package ecommerce.dao.impl;

import db.DB;
import db.DbException;
import ecommerce.dao.ProductDao;
import ecommerce.entities.Product;

import java.sql.*;
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
                    + "(ProductID, ProductName, ProductPrice, Quantity) "
                    + "VALUES "
                    + "(?, ? ,? ,?)" ,
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());
            st.setDouble(3, obj.getPrice());
            st.setInt(4, obj.getQuantity());

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

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }


}
