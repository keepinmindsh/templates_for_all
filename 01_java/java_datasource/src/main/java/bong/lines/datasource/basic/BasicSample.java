package bong.lines.datasource.basic;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class BasicSample {

    public static void main(String[] args) {
        String sqlSelectAllPersons = "SELECT SEQ_NO, TITLE FROM TB_IDEA_MST";
        String connectionUrl = "jdbc:mariadb://61.79.202.243:6371/1line?useUnicode=true&characterEncoding=utf-8";

        try (Connection conn = DriverManager.getConnection(connectionUrl, "1line", "1line0000");
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("SEQ_NO");
                String name = rs.getString("TITLE");

                log.info("ID, Name : {} ,{}", id, name);
                System.out.println(id);
                System.out.println(name);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            // handle the exception
        }
    }
}
