package online.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import online.shop.dao.ShoppingCartDao;
import online.shop.exception.DataProcessingException;
import online.shop.lib.Dao;
import online.shop.model.Product;
import online.shop.model.ShoppingCart;
import online.shop.util.ConnectionUtil;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_cart WHERE user_id = ? AND deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getCartFromResultSet(resultSet, connection));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get shopping cart by id : " + userId, e);
        }
        return Optional.empty();
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO shopping_cart (user_id) values(?)";
            PreparedStatement statement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, shoppingCart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong(1));
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t create shopping cart: " + shoppingCart, e);
        }
    }

    @Override
    public Optional<ShoppingCart> getById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_cart WHERE cart_id = ? AND deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getCartFromResultSet(resultSet, connection));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Cant get shopping cart by id : " + id, e);
        }
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM shopping_cart WHERE deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shoppingCarts.add(getCartFromResultSet(resultSet, connection));
            }
            return shoppingCarts;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t get all shopping carts!", e);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM shopping_carts_products WHERE cart_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, shoppingCart.getId());
            statement.executeUpdate();
            addProductsToCart(shoppingCart, connection);
            return shoppingCart;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update shopping cart : " + shoppingCart, e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE shopping_cart SET deleted = true WHERE cart_id = ? "
                    + "AND deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can`t delete shopping cart by id : " + id, e);
        }
    }

    private ShoppingCart getCartFromResultSet(ResultSet resultSet, Connection connection)
            throws SQLException {
        Long cartId = resultSet.getLong("cart_id");
        Long userId = resultSet.getLong("user_id");
        ShoppingCart shoppingCart = new ShoppingCart(userId);
        shoppingCart.setId(cartId);
        shoppingCart.setProducts(getProductsInCart(cartId, connection));
        return shoppingCart;
    }

    private List<Product> getProductsInCart(Long cartId, Connection connection)
            throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products p INNER JOIN shopping_carts_products s "
                + "ON p.product_id = s.product_id "
                + "WHERE cart_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, cartId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Long productId = resultSet.getLong("product_id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            Product product = new Product(name, price);
            product.setId(productId);
            products.add(product);
        }
        return products;
    }

    private void addProductsToCart(ShoppingCart shoppingCart, Connection connection)
            throws SQLException {
        String query = "INSERT INTO shopping_carts_products (cart_id, product_id) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        for (Product product : shoppingCart.getProducts()) {
            statement.setLong(1, shoppingCart.getId());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
        }
    }
}
