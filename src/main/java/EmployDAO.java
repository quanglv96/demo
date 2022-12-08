import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/exammodul3?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678";
    private static final String INSERT_EMPLOY_SQL = "INSERT INTO employment (name_employment,address,email,phone,salary,id_department) VALUES ( ?, ?,?,?, ?,?);";
    private static final String SELECT_ALL_EMPLOY = "select  *from employment;";
    private static final String DELETE_EMPLOY_SQL = "delete from employment where id_employment = ?;";
    private static final String UPDATE_EMPLOY_SQL = "update employment set name_employment= ?,address= ?,email= ?,phone= ?,salary= ?,id_department= ? where id_employment = ?;";
    private static final String SELECT_EMPLOY_BY_ID = "select * from employment where id_employment=?";
    private final String SELECT_BY_LIKE="select * from employment where name_employment like ?;";
    private DepartDAO departDAO;

    public EmployDAO() {
        departDAO = new DepartDAO();
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

    public void UpdateEmploy(Employ employment) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOY_SQL)) {
            preparedStatement.setString(1, employment.getName_employment());
            preparedStatement.setString(2, employment.getAddress());
            preparedStatement.setString(3, employment.getEmail());
            preparedStatement.setString(4, employment.getPhone());
            preparedStatement.setDouble(5, employment.getSalary());
            preparedStatement.setInt(6, employment.getDepartment().getId_depart());
            preparedStatement.setInt(7, employment.getId_employment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void deleteEmploy(int idEmploy) throws SQLException {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOY_SQL)) {
            preparedStatement.setInt(1, idEmploy);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void insertEmploy(Employ employment) throws SQLException {
        System.out.println(INSERT_EMPLOY_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOY_SQL)) {
            preparedStatement.setString(1, employment.getName_employment());
            preparedStatement.setString(2, employment.getAddress());
            preparedStatement.setString(3, employment.getEmail());
            preparedStatement.setString(4, employment.getPhone());
            preparedStatement.setDouble(5, employment.getSalary());
            preparedStatement.setInt(6, employment.getDepartment().getId_depart());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Employ selectEmploymentById(int id) throws SQLException {
        Employ employment = null;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOY_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            int stt = 0;
            while ((rs.next())) {
                stt++;
                String name = rs.getString("name_employment");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Double salary = rs.getDouble("salary");
                int id_department = rs.getInt("id_department");
                employment = new Employ(stt, id_department, name, address, email, phone, salary, departDAO.selectDepartmentById(id_department));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employment;
    }

    public List<Employ> selectAllEmployment() {
        List<Employ> listEmployment = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOY);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            int stt = 0;
            while (rs.next()) {
                stt++;
                int id = rs.getInt("id_employment");
                String name = rs.getString("name_employment");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Double salary = rs.getDouble("salary");
                int id_department = rs.getInt("id_department");
                listEmployment.add(new Employ(stt, id, name, address, email, phone, salary, departDAO.selectDepartmentById(id_department)));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listEmployment;
    }
public List<Employ> selectEmployByName(String text){
    List<Employ> listEmployment = new ArrayList<>();
    try (Connection connection = getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LIKE) ){
        String text1='%'+text+'%';
        preparedStatement.setString(1, text1);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        int stt = 0;
        while (rs.next()) {
            stt++;
            int id = rs.getInt("id_employment");
            String name = rs.getString("name_employment");
            String address = rs.getString("address");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            Double salary = rs.getDouble("salary");
            int id_department = rs.getInt("id_department");
            listEmployment.add(new Employ(stt, id, name, address, email, phone, salary, departDAO.selectDepartmentById(id_department)));
        }
    } catch (SQLException e) {
        printSQLException(e);
    }
    return listEmployment;
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
