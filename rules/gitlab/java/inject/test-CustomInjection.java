// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=com.amazonaws.aws-java-sdk-simpledb@1.12.187
package inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class CustomInjection {

    public void danger(DataSource dataSource, String input) throws SQLException {
        String sql = "select * from Users where name = " + input;
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
        }
    }

    public void danger2(DataSource dataSource, String input) throws SQLException {
        String value = String.format("%s", input);
        String sql = "select * from Users where name = " + input;
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);
        }
    }

    public void danger3(DataSource dataSource, String input) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from Users where name = " + input);
            System.out.println(resultSet);
        }
    }

    public void danger4(DataSource dataSource, String input) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from Users where name = " + String.format("%s", input));
            System.out.println(resultSet);
        }
    }


    public void danger5(DataSource dataSource, String input) throws SQLException {
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("select * from Users where name = %s", input));
            System.out.println(resultSet);
        }
    }
}
