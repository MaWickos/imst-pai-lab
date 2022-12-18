package pl.pollub.mw.pai_lab01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"})
public class HelloServlet extends HttpServlet {

    // Variable for current date
    private Date currentDateTime;   // in script -> date1
    private String formatedCurrentDateTime;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Information from Request Object
            out.println("<h2>Dane serwera</h2>");
            out.println("<p>request.getServerName(): " + request.getServerName() + "</p>");
            out.println("<p>request.getServerPort(): " + request.getServerPort() + "</p>");
            out.println("<p>request.getRemoteHost(): " + request.getRemoteHost() + "</p>");
            out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() + "</p>");
            out.println("<h2>Szczegóły żądania</h2>");
            out.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
            out.println("<p>request.getQueryString(): " + request.getQueryString() + "</p>");
            out.println("<br/>");
            out.println("<h2> Zadanie 1.3 </h2>");
            out.println("<p> Data z init(): " + currentDateTime + "</p>");
            out.println("<p> Data z processRequest(): " + new Date() + "</p>");
            out.println("<p> Data w formacie YYYY-MM-DD: " + formatedCurrentDateTime);
        } finally {
            out.close();
        }
    }
    // Init function
    @Override
    public void init(){
        currentDateTime = new Date();
        
        // Format date to YYYY-MM-DD
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        formatedCurrentDateTime = dateFormat.format(currentDateTime);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
