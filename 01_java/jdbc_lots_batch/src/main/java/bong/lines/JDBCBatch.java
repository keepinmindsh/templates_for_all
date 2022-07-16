package bong.lines;

import oracle.jdbc.datasource.OracleDataSource;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCBatch {

    public static void main(String[] args) {
        writeInABatchWithCompiledQuery();
    }

    public static void writeInABatchWithCompiledQuery() {

        int records = 100;

        PreparedStatement preparedStatement;

        try {
            Connection connection = getDatabaseConnection();
            connection.setAutoCommit(true);

            String compiledQuery = "INSERT INTO TESTDB.EMPLOYEE(EMPNO, EMPNM, DEPT, RANK, USERNAME) VALUES" + "(?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(compiledQuery);

            for(int index = 1; index <= records; index++) {
                preparedStatement.setInt(1, index);
                preparedStatement.setString(2, "empo number-"+index);
                preparedStatement.setInt(3, index+100);
                preparedStatement.setInt(4, index+200);
                preparedStatement.setString(5, "usernames");
                preparedStatement.addBatch();
            }

            long start = System.currentTimeMillis();
            int[] inserted = preparedStatement.executeBatch();
            long end = System.currentTimeMillis();

            System.out.println("total time taken to insert the batch = " + (end - start) + " ms");
            System.out.println("total time taken = " + (end - start)/records + " s");

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            System.err.println("SQLException information");
            while (ex != null) {
                System.err.println("Error msg: " + ex.getMessage());
                ex = ex.getNextException();
            }
            throw new RuntimeException("Error");
        }catch (Exception exception){
            System.err.println(exception.getMessage());
        }
    }

    private static Connection getDatabaseConnection() throws SQLException {

        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost");
        basicDataSource.setUsername("111");
        basicDataSource.setPassword("111");

        basicDataSource.setInitialSize(2);
        basicDataSource.setMaxTotal(20);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMinIdle(20);

        basicDataSource.setDefaultReadOnly(true);

        return basicDataSource.getConnection();
    }
}
