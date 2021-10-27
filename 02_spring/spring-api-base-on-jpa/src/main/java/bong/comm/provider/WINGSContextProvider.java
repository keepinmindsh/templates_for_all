package bong.comm.provider;

import bong.comm.code.ApplicationContextType;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class WINGSContextProvider {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    String driverName = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@192.168.50.170:1527/WPMS07";
    String public_url = "jdbc:oracle:thin:@222.239.73.170:1537/WPMS07";
    String user = "WINGSINTEGRATION";
    String password = "WINGS_4172";

    public WINGSContextProvider(){
        try
        {
            // ① 로드
            Class.forName(driverName);

        }
        catch (ClassNotFoundException e)
        {
            System.out.println("[로드 오류]\n" + e.getStackTrace());
        }
    }

    public void closeDatabase()
    {
        try
        {
            if( connection != null )
            {
                // connection 닫기
                connection.close();
            }

            if( statement != null )
            {
                // statement 닫기
                statement.close();
            }

            if( resultSet != null )
            {
                // resultSet 닫기
                resultSet.close();
            }
        }
        catch (SQLException e)
        {
            System.out.println("[닫기 오류]\n" + e.getStackTrace());
        }
    }

    public Map<ApplicationContextType, Object> getApplicationContextInfo(String systemId, String profileId)
    {
        Map<ApplicationContextType, Object> dataBaseMap = new HashMap<>();
        try
        {
            String queryString = " SELECT A.PROFILE_ID, " +
                                    "    A.SYSTEM_ID, " +
                                    "    A.ROOT_CONTEXT, " +
                                    "    A.USE_YN, " +
                                    "    A.SOURCE_CONTEXT, " +
                                    "    ( SELECT LISTAGG(X.SERVLET_TYPE_CODE, ',') WITHIN GROUP (ORDER BY X.SEQ_NO) " +
                                    "        FROM TB_IS_CONTEXT_MAPPING X " +
                                    "       WHERE X.SYSTEM_ID = A.SYSTEM_ID " +
                                    "         AND X.PROFILE_ID = A.PROFILE_ID ) SERVLET_TYPE_LIST, " +
                                    "    ( SELECT LISTAGG(X.CONTEXT_PATH, ',') WITHIN GROUP (ORDER BY X.SEQ_NO) " +
                                    "        FROM TB_IS_CONTEXT_MAPPING X " +
                                    "       WHERE X.SYSTEM_ID = A.SYSTEM_ID " +
                                    "         AND X.PROFILE_ID = A.PROFILE_ID ) CONTEXT_LIST " +
                                    "  FROM TB_IS_APPLICATION_CONTEXT A " +
                                    " WHERE A.SYSTEM_ID = '" + systemId + "'" +
                                    "   AND A.PROFILE_ID = '" + profileId + "'";


            // ② 연결 [Connection]
            connection = DriverManager.getConnection(profileId.equals("cloud-netsys-japan") ? public_url : url, user, password);
            // ② 연결 [Statement]
            statement = connection.createStatement();
            // ③ 실행 [CRUD]
            resultSet = statement.executeQuery(queryString);

            while (resultSet.next())
            {

                dataBaseMap.put(ApplicationContextType.PROFILE_ID, resultSet.getString("PROFILE_ID"));
                dataBaseMap.put(ApplicationContextType.SYSTEM_ID, resultSet.getString("SYSTEM_ID"));
                dataBaseMap.put(ApplicationContextType.CONTEXT_LIST, resultSet.getString("CONTEXT_LIST"));
                dataBaseMap.put(ApplicationContextType.ROOT_CONTEXT, resultSet.getString("ROOT_CONTEXT"));
                dataBaseMap.put(ApplicationContextType.USE_YN, resultSet.getString("USE_YN"));
                dataBaseMap.put(ApplicationContextType.SERVLET_TYPE_LIST, resultSet.getString("SERVLET_TYPE_LIST"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("[쿼리 오류]\n" + e.getMessage());
        }
        finally
        {
            // ④ 닫기
            closeDatabase();
        }

        return dataBaseMap;
    }

}
