package com.example.nlabservs;

import java.sql.SQLException;

public class DaoException extends RuntimeException {
    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
