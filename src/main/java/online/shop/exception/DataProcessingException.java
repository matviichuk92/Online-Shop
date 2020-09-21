package online.shop.exception;

import java.sql.SQLException;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, SQLException sqlException) {
        super(sqlException);
    }
}
