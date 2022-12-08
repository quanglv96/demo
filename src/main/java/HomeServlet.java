import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    private EmployDAO employDAO = new EmployDAO();
    private DepartDAO departDAO = new DepartDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
            switch (action) {
                default:
                    listEmployment(request, response);
                    break;
            }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "formEdit":
                    formEdit(request, response);
                    break;
                case "deleteEmploy":
                    deleteEmploy(request,response);
                    break;
                case "search":
                    searchEmploy(request, response);
                    break;
                case "formAddEmploy":
                    formAddEmploy(request, response);
                    break;
                case "saveAdd":
                    addEmployment(request,response);
                    break;
                case "saveEdit":
                    saveForm(request, response);
                    break;
                case "delete":
                    deleteEmploy(request,response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void deleteEmploy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id= Integer.parseInt(request.getParameter("idEmploy"));
        employDAO.deleteEmploy(id);
        response.sendRedirect("http://localhost:8081/home");
    }
    private void searchEmploy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String text = request.getParameter("search");
        List<Employ> listEmploy = employDAO.selectEmployByName(text);
        request.setAttribute("listEmployment", listEmploy);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
        private void addEmployment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
       String name=request.getParameter("name");
       String email=request.getParameter("email");
       String address=request.getParameter("address");
       String phone=request.getParameter("phone");
       Double salary= Double.valueOf(request.getParameter("salary"));
       int depart= Integer.parseInt(request.getParameter("depart"));
       Employ employ=new Employ(name,address,email,phone,salary,departDAO.selectDepartmentById(depart));
       employDAO.insertEmploy(employ);
       response.sendRedirect("http://localhost:8081/home");
    }
    private void formAddEmploy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Depart> departList = departDAO.selectAllDepartment();
        request.setAttribute("listDepartment", departList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("createEmploy.jsp");
        requestDispatcher.forward(request, response);
    }
        private void saveForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id= Integer.parseInt(request.getParameter("idEmploy"));
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String phone=request.getParameter("phone");
        Double salary= Double.valueOf(request.getParameter("salary"));
        int depart= Integer.parseInt(request.getParameter("depart"));
        Employ employ=new Employ(id,name,address,email,phone,salary,departDAO.selectDepartmentById(depart));
        employDAO.UpdateEmploy(employ);
        response.sendRedirect("http://localhost:8081/home");
    }

    private void listEmployment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employ> employList = employDAO.selectAllEmployment();
        request.setAttribute("listEmployment", employList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void formEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int idEmployment = Integer.parseInt(request.getParameter("idEmploy"));
        Employ employ = employDAO.selectEmploymentById(idEmployment);
        List<Depart> departList = departDAO.selectAllDepartment();
        request.setAttribute("id",idEmployment);
        request.setAttribute("nameEmploy", employ.getName_employment());
        request.setAttribute("emailEmploy", employ.getEmail());
        request.setAttribute("addressEmploy", employ.getAddress());
        request.setAttribute("phoneEmploy", employ.getPhone());
        request.setAttribute("salaryEmploy", employ.getSalary());
        request.setAttribute("listDepartment", departList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editEmployment.jsp");
        requestDispatcher.forward(request, response);
    }
}
