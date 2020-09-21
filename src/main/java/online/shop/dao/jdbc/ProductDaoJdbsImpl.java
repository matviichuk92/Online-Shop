package online.shop.dao.jdbc;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import online.shop.dao.ProductDao;
import online.shop.exception.DataProcessingException;
import online.shop.lib.Dao;
import online.shop.model.Product;
import online.shop.util.ConnectionUtil;

@Dao
public class ProductDaoJdbsImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO product_table (name, price) VALUES (?, ?);";
            PreparedStatement statement =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            }
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't add item: " + product, e);
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM product_table WHERE product_id = ? AND deleted=false;";
            PreparedStatement statement =
                    connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getProductFromResultSet(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find product by id: " + id, e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM product_table;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = getProductFromResultSet(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all products!", e);
        }
    }

    @Override
    public Product update(Product product) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE product_table "
                    + "SET name = ?, price = ? "
                    + "WHERE product_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.execute();
            return product;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update product by id: " + product.getId(), e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE product_table SET deleted=true "
                    + "WHERE product_id = ? AND deleted=false;";
            PreparedStatement statement =
                    connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete product by id: " + id, e);
        }
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        Product product = new Product(name, price);
        product.setId(id);
        return product;
    }
}
