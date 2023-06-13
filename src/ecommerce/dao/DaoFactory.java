package ecommerce.dao;

import db.DB;
import ecommerce.dao.impl.ProductDaoJDBC;

public class DaoFactory { //É uma forma de fazer uma injeção de dependência sem explicitar a implementação

    public static ProductDao createProductDao () {
        return new ProductDaoJDBC(DB.getConnection());
    }
}