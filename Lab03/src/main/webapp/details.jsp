<%-- 
    Document   : details
    Created on : Nov 6, 2022, 12:38:11 PM
    Author     : Maciek
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="pl.pollub.mw.pai_lab04.CountryBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details of country</title>
    </head>
    <body>
        <% 
            if(
                session.getAttribute("countryList") == null || session.getAttribute("countryList") == "" ||
                request.getParameter("i") == null || request.getParameter("i") == ""
            ){
                out.println("<p> No data to display </p>");
            } else {

                Integer index = Integer.parseInt(request.getParameter("i"));
                ArrayList<CountryBean> countryBeanList =(ArrayList<CountryBean>)session.getAttribute("countryList"); 
                CountryBean choosenCountry = countryBeanList.get(index);
        %>
        <h1>Details of <%= choosenCountry.getName() %> </h1>
        <table>
            <thead>
                <tr>
                    <td> Parameter </td>
                    <td> Value </td>
                </tr>
            </thead>
              <tbody>   
                <tr> <td> Country code </td> <td> <%= choosenCountry.getCode() %> </td> </tr>
                <tr> <td> Population </td> <td> <%= Integer.toString(choosenCountry.getPopulation()) %> </td> </tr>
                <tr> <td> Continent </td> <td> <%= choosenCountry.getContinent() %> </td> </tr>
                <tr> <td> Region </td> <td> <%= choosenCountry.getRegion() %> </td> </tr>
                <tr> <td> Surface area </td> <td> <%= Float.toString(choosenCountry.getSurfacaArea()) %> </td> </tr>
                <tr> <td> Independent year </td> <td> <%= Integer.toString(choosenCountry.getIndepYear()) %> </td> </tr>
                <tr> <td> Government form </td> <td> <%= choosenCountry.getGovernmentForm() %> </td> </tr>
                <tr> <td> Head of State  </td> <td> <%= choosenCountry.getHeadOfState() %> </td> </tr>
            </tbody>
        </table>
        <%
            }
        %>
        <a href="countryList.jsp"> Country lists </a>
    </body>
</html>
