import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/exammodul3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678";
    private static final String SELECT_DEPARTMENT_BY_ID = "select id_department,name_department from department where id_department=?";
    private static final String SELECT_DEPARTMENT = "select * from department";

    public DepartDAO() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public Depart selectDepartmentById(int id) {
        Depart department = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name_department");
                department = new Depart(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return department;
    }
    public List<Depart> selectAllDepartment() {
        List<Depart> listDepart = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name_department");
                int id_department = rs.getInt("id_department");
               listDepart.add(new Depart(id_department,name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listDepart;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
