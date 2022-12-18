<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
           prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pracownicy</title>
    </head>
    <body>
        <div>
            <h1>Edycja danych istniejącego użytkownika</h1>   
            <form:form method="post" action="editsave">
                <form:hidden path="id" />
                    <table>
                        <tr>
                            <td>Nazwisko : </td>
                            <td> <form:input path="nazwisko" /> </td>
                        </tr>
                        <tr>
                            <td>Pensja :</td>
                            <td> <form:input path="pensja" /> </td>
                        </tr>
                        <tr>
                            <td>Firma :</td>
                            <td> <form:input path="firma" /> </td>
                        </tr>
                        <tr>
                            <td> </td>
                            <td> <input type="submit" value="Zapisz" /> </td>
                        </tr>
                    </table>
                </form:form>  
        </div>
    </body>
</html>