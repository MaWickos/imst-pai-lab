<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>PAI Lab02 - Calc Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>LAb01 - Kalkulator</h1>
        <form method="POST" action="CalcServlet" id="calc">
            <fieldset>
                <legend> Podaj dane do operacji: </legend>
                <input type="text" id="fistNumber" name="firstNumber">
                <select name="operation" id="operation">
                    <option value="+"> + </option>
                    <option value="-"> - </option>
                    <option value="/"> / </option>
                    <option value="*"> * </option>
                </select>
                <input type="text" id="secondNumber" name="secondNumber">
                <button type="submit" form="calc" value="=">=</button>
            </fieldset>
        </form>
    </body>
</html>