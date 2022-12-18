package pl.pollub.mw.pai_lab04;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListServlet", urlPatterns = {"/ListServlet"})
public class ListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        response.setContentType("text/html;charset=UTF-8");
//      Zadanie 3.2
//        PrintWriter out = response.getWriter();
//        out.println("<h1> Informacje o krajach </h1>");
//        
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC", "root", "");
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM country WHERE Continent = 'Europe'";
//            ResultSet resultSet = statement.executeQuery(query);
//            
//            String name, code;
//            Integer population;
//
//            out.println("<ol>");
//            while(resultSet.next()){
//                name = resultSet.getString("name");
//                code = resultSet.getString("code");
//                population = resultSet.getInt("population");
//                out.println("<li> " + name + " (" + code + ")" + " - " + population.toString() + "</li>");
//            }
//            out.println("</ol>");
//            
//        } catch(ClassNotFoundException | SQLException exception){
//            out.println("<p> ClassNotFoundException or SqlException error! </p>");
//            out.println(exception.toString());
//        } finally{
//            out.close();
//        }

//      Zadanie 3.3.
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?serverTimezone=UTC", "root", "");
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM country WHERE Continent = 'Europe'";
        ResultSet resultSet = statement.executeQuery(query);
        
        HttpSession httpSession = request.getSession(true);
        CountryBean countryBean;
        ArrayList<CountryBean> countryBeanList = new ArrayList<CountryBean>();
        
        while(resultSet.next()){
            countryBean = new CountryBean();
            countryBean.setName(resultSet.getString("name"));
            countryBean.setCode(resultSet.getString("code"));
            countryBean.setPopulation(resultSet.getInt("population"));
            countryBean.setContinent(resultSet.getString("continent"));
            countryBean.setRegion(resultSet.getString("region"));
            countryBean.setSurfacaArea(resultSet.getFloat("SurfaceArea"));
            countryBean.setIndepYear(resultSet.getInt("IndepYear"));
            countryBean.setGovernmentForm(resultSet.getString("GovernmentForm"));
            countryBean.setHeadOfState(resultSet.getString("HeadOfState"));
            countryBeanList.add(countryBean);
        }
        httpSession.setAttribute("countryList", countryBeanList);
        response.sendRedirect("countryList.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
