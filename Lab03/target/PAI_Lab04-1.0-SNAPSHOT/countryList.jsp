<%@page import="java.util.ArrayList"%>
<%@page import="pl.pollub.mw.pai_lab04.CountryBean"%>
<%@page import="pl.pollub.mw.pai_lab04.CountryBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAI Lab04 - JDBC</title>
    </head>
    <body>
        <h1>Lista krajów:</h1>
            <% 
                if(session.getAttribute("countryList") == null || session.getAttribute("countryList") == ""){
                    out.println("<p> No data to display </p>");
                } else {

                    ArrayList<CountryBean> countryBeanList =(ArrayList<CountryBean>)session.getAttribute("countryList"); 
            %>
            <table>
                <thead>
                    <tr>
                        <td> Name </td>
                        <td> Code </td>
                        <td> Population </td>
                        <td> Details </td>
                    </tr>
                </thead>
                <tbody>
                 <%
                    for(CountryBean country:countryBeanList){
                        String href = "<a href=\"http://localhost:8080/PAI_Lab04/details.jsp?i="+ Integer.toString(countryBeanList.indexOf(country)) +"\" target=\"_blank\"> Details </a>";
                %>
                <tr> 
                    <td> <%= country.getName() %> </td> 
                    <td> <%= country.getCode() %> </td> 
                    <td> <%= country.getPopulation() %> </td> 
                    <td> <%= href %> </td> 
                </tr>
                <%  
                    }
                %>
                </tbody>
            </table>
            <%
                }
            %>

    </body>
</html>
