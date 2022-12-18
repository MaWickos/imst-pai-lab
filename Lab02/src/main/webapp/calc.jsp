<%@page import="java.lang.Math"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAI Lab03 (17.10.2022)</title>
    </head>
    <body>
        <h1>Lab03</h1>
        <h2> Zadanie 2.1 </h2>
        <%= new Date() %> <br/>
        <%
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String formattedDate = dateFormat.format(currentDate);
            out.println(formattedDate);
        %>
        <%= formattedDate %>
        <br/>
        
        <h2> Zadanie 2.2. </h2>
        <form method='POST' action='?calc-installments=true' id='installments-form'>
            <label for="quota_credit"> Credit amount: </label> <br/>
            <input type="number" step="0.01" name="quota_credit"> <br/>
            
            <label for="yearly_percent"> Yearly percent </label> <br/>
            <input type="number" step="0.01" name="yearly_percent""> <br/>
                
            <label for="installments_number"> Number of installments </label> <br/>
            <input type="number" step="1" name="installments_number"> <br/>
        
            <input type="submit" name="calc-installments" value="Calc">
        </form>
        
                    
        <%
            if(request.getParameter("calc-installments") != null){
                Double quota, percentYearly, percentMonthly, installment;
                Integer installmentsNumber;
                try{
                    quota = Double.parseDouble(request.getParameter("quota_credit"));
                    percentYearly = Double.parseDouble(request.getParameter("yearly_percent"));
                    installmentsNumber = Integer.parseInt(request.getParameter("installments_number"));
                    percentMonthly = percentYearly/12;
                    installment = (quota * percentMonthly)/(1-(1/Math.pow(1+percentMonthly, installmentsNumber)));
                    out.println("Monthly installmens: " + Double.toString(installment) + " zł");
                } catch(Exception e) {
                    System.out.println(e);
                    out.println("Invalid input data!");
                }
            }
        %>
    </body>
</html>
