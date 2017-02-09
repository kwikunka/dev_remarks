<%--
  Created by IntelliJ IDEA.
  User: kwik
  Date: 06.02.2017
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>View ToDo</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h1>View&Edit:</h1>


<c:url var="editAction" value="/to_do/edit"/>

<form:form action="${editAction}" commandName="todo">
<table class="view">
    <tr>
        <th width="30">ID</th>
        <th width="120">Name</th>
        <th width="60">Complete</th>
    </tr>
    <tr>
        <td>
            <form:label path="idTODO">
            </form:label>
            <form:input path="idTODO" readonly="true" size="3" />
        </td>
        <td>
            <form:label path="nameTODO">
            </form:label>
            <form:input path="nameTODO" readonly="true"/>
        </td>
        <td>
            <form:label path="completed">
            </form:label>
            <form:checkbox path="completed"/>
        </td>
    </tr>
</table>

<input type="submit" value="<spring:message text="Edit"/>"/>
</form:form>

<a href="../to_do">Return to List ToDo</a>

</body>
</html>
