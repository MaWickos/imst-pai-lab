package pl.pollub.mw.pai_lab01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CalcServlet", urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {
    
    // global variable
    final String COOKIE_NAME = "UserWasThereInfo";
    final int MAX_AGE = 60*60*24*7;
    final String SESSION_ATRIBUTE_NAME = "equationHistory";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        // Get params
        String firstParamNumber = request.getParameter("firstNumber");
        String secondParamNumber = request.getParameter("secondNumber");
        String operation = request.getParameter("operation");
        String clearSession = request.getParameter("sessionClear");
        String resultString = "Session is clear! <br/>";
        
        float firstNumber, secondNumber, result;
        
        // Clear session
        if(isParamNotNull(clearSession, false)){
            deleteSession(request);
        } else {
            // Check params and make equation
            if(isParamNotNull(firstParamNumber, true) && isParamNotNull(secondParamNumber, true) & isParamNotNull(operation, false)){
                firstNumber = Float.parseFloat(firstParamNumber);
                secondNumber = Float.parseFloat(secondParamNumber);

                if(operation.equals("/") && secondNumber == 0) {
                    resultString = buildEquationString(firstParamNumber, operation, secondParamNumber, "Division by zero!"); ;
                } else {
                    result = doMath(firstNumber, secondNumber, operation);
                    resultString = buildEquationString(firstParamNumber, operation, secondParamNumber, Float.toString(result));
                }

            } else {
                resultString = "Wrong data type or empty parameters! <br/>";
            }
        }
       
        // Check cookies
        String sayHello = sayHelloWithCookies(request, response);
        
        // Update and get history of operation
        addEquationToSession(request, resultString, SESSION_ATRIBUTE_NAME);
        String equationHistory = writeSesionAtributeValues(request, SESSION_ATRIBUTE_NAME);
      
        
        // Write HTML
        try {
            out.println("<h1>Lab01 - Kalkulator</h1>");
            
            out.println(sayHello);
            
            out.println("<h3> Wynik obliczeń: </h3>");
            out.println(resultString);
            
            out.println("<h3> Historia obliczeń: </h3>");
            out.println(equationHistory);
            
            out.println("<a href=\"calcForm.jsp\"> Back to calc </a> | ");
            out.println("<a href=\"?sessionClear=true\"> Clear session </a> <br/>");
        } finally {
            out.close();
        }
    }

   
    // FORM
    // Function to make math operation
    protected float doMath(float firstNumber, float secondNumber, String operation){
        
        switch(operation){
            case "+":
                return (firstNumber + secondNumber);
            case "-":
                return (firstNumber - secondNumber);
            case "/":
                return (firstNumber / secondNumber);
            case "*":
                return (firstNumber * secondNumber);
            default:
                return -1;
        }
    }

    // Validate method
    protected boolean isParamNotNull(String paramValue, boolean isNumber){
        
        if(paramValue == null || paramValue.trim().equals("")){
            return false;
        } 
        
        if(isNumber){
            try{
                float number = Float.parseFloat(paramValue);
            } catch(NumberFormatException e){
                return false;
            }   
            return true;
        }
        
        return true;
    }
            
    // Build string euation to display result and history
    protected String buildEquationString(String a, String operation, String b, String result){
        return a + " " + operation + " " + b + " = " + result + "</br>"; 
    }

    // COOKIES
    // Hello with cookies or without
    protected String sayHelloWithCookies(HttpServletRequest request, HttpServletResponse response){
        if(isCookieExisting(request, COOKIE_NAME)){
            return "<h1> Welcome back! </h1>";
        } else {
            createCookies(response, COOKIE_NAME, MAX_AGE);
            return "<h1> First time? Welcome! </h1>";
        }
    }
    
    // Create cookies
    protected void createCookies(HttpServletResponse response, String cookieName, int maxAge){
        Cookie newCookie = new Cookie(cookieName, "true");
        newCookie.setMaxAge(maxAge);
        response.addCookie(newCookie);  
    }
    
    // Check cookie exsit
    protected boolean isCookieExisting(HttpServletRequest request, String cookieName){
        Cookie [] cookies = request.getCookies();
        if(cookies != null){
            for(int i = 0; i < cookies.length; i++){
                Cookie singleCookie = cookies[i];
                if(cookieName.equals(singleCookie.getName()))
                    return true;
            }
        }
        return false;
    
    }
    
    // SESSION
    // Get atribute or create new
    protected ArrayList<String> getSessionAttributeArrayStringList(HttpSession session, String atributeName){
        ArrayList<String> equationHistory = (ArrayList<String>) session.getAttribute(atributeName);
        
        if(equationHistory == null){
            equationHistory = new ArrayList<String>();
        }
        
        return equationHistory;
    }
    
    // Add record to session
    protected void addEquationToSession(HttpServletRequest request, String equation, String atributeName){
        HttpSession session = request.getSession(true);
        ArrayList<String> equationHistory = getSessionAttributeArrayStringList(session, atributeName);
        equationHistory.add(equation);
        session.setAttribute(atributeName, equationHistory);
    }
    
    // Write all equation in session
    protected String writeSesionAtributeValues(HttpServletRequest request, String atributeName){
       HttpSession session = request.getSession(true);
       ArrayList<String> equationHistory = getSessionAttributeArrayStringList(session, atributeName);
       String result = "";
       
       if(equationHistory != null){
            for(String equation:equationHistory){
                result += equation;
            }
       } else {
           result = "No operation in history!";
       }

       return result;
    }
    
    // Delete session
    protected void deleteSession(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.invalidate();
    }
   
    
    //OVERRIDED METHODS
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
