<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error Page</h1>
        <p> <b> Failed URL: </b> ${url} <br/>
            <b> Exception: </b> ${exception.message} <br/>
            <c:forEach items="${exception.stackTrace}" var="ste">
                ${ste}
            </c:forEach>
        </p>
        <div>
            <a href="/pai_spring">Pokaż listę pracowników</a>
        </div>
    </body>
</html>