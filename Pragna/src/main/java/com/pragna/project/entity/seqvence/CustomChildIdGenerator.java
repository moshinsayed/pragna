package com.pragna.project.entity.seqvence;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomChildIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Connection connection = session.connection();
        try {
            Statement statement = connection.createStatement();

            // Execute a query to get the current maximum user ID
            ResultSet rs = statement.executeQuery("SELECT MAX(child_id) FROM tbl_childrens");

            if (rs.next()) {
                String currentMaxUserId = rs.getString(1);
                if (currentMaxUserId != null && currentMaxUserId.startsWith("child-")) {
                    // Extract the current number and increment it
                    int currentNumber = Integer.parseInt(currentMaxUserId.replace("child-", ""));
                    currentNumber++;
                    return "child-" + currentNumber;
                }
            }

            return "child-1"; // If no existing records, start with "parent-1"
        } catch (SQLException e) {
            throw new HibernateException("Unable to generate user ID", e);
        }
    }
}

