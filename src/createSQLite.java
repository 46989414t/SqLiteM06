/**
 * Created by dremon on 09/11/15.
 */
import java.sql.*;

public class createSQLite {

    public static void main(String[] args) {
        {
            //Connection: gestionan las clases fisicas, rollo ip, etc...
            Connection c = null;

            //Statement: formata instruccuines sql
            Statement stmt = null;
            try {

                //driver

                Class.forName("org.sqlite.JDBC");

                //conection: conecta con la BBDD
                c = DriverManager.getConnection("jdbc:sqlite:test.db");
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                String sql = "CREATE TABLE COMPANY " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " NAME           TEXT    NOT NULL, " +
                        " AGE            INT     NOT NULL, " +
                        " ADDRESS        CHAR(50), " +
                        " SALARY         REAL)";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();// es importantes cerrar pq si no despues no deja abrir una segunda conexion
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Table created successfully");
        }

    }


}
