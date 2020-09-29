package online.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import online.shop.dao.OrderDao;
import online.shop.exception.DataProcessingException;
import online.shop.lib.Dao;
import online.shop.model.Order;
import online.shop.model.Product;
import online.shop.util.ConnectionUtil;

@Dao
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet, connection));
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order by user id : " + userId, e);
        }
    }

    @Override
    public Order create(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO orders(user_id) values (?)";
            PreparedStatement statement =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
            addProductsToOrder(order, connection);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create the order : " + order, e);
        }
    }

    @Override
    public Optional<Order> getById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE order_id  = ? AND deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getOrderFromResultSet(resultSet, connection));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order by id : " + id, e);
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM orders WHERE deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet, connection));
            }
            return orders;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all orders!", e);
        }
    }

    @Override
    public Order update(Order order) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "DELETE FROM orders_products WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            addProductsToOrder(order, connection);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order: " + order, e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE orders SET deleted = true "
                    + "WHERE order_id = ? AND deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete order by id: " + id, e);
        }
    }

    private Order getOrderFromResultSet(ResultSet resultSet, Connection connection)
            throws SQLException {
        Long orderId = resultSet.getLong("order_id");
        Long userId = resultSet.getLong("user_id");
        Order order = new Order(userId);
        order.setId(orderId);
        order.setProducts(getOrderProduct(orderId, connection));
        return order;
    }

    private List<Product> getOrderProduct(Long id, Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products p "
                + "INNER JOIN orders_products op "
                + "ON op.product_id = p.product_id "
                + "WHERE op.order_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
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

    private void addProductsToOrder(Order order, Connection connection) throws SQLException {
        String query = "INSERT INTO orders_products(order_id, product_id) values(?,?)";
        for (Product product : order.getProducts()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, order.getId());
            statement.setLong(2, product.getId());
            statement.executeUpdate();
        }
    }
}
