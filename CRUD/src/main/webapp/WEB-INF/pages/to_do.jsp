<%--
  Created by IntelliJ IDEA.
  User: kwik
  Date: 06.02.2017
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>List ToDo</title>

</head>
<body>

<h1>List ToDo</h1>


<c:if test="${!empty todoList}">
Filter:
<select name="select" onchange="location.href=this.value">
    <option <c:if test="${filt == 2}">selected</c:if> value="/to_do?filt=2">All</option>
    <option <c:if test="${filt == 1}">selected</c:if> value="/to_do?filt=1">Completed</option>
    <option <c:if test="${filt == 0}">selected</c:if> value="/to_do?filt=0">Not completed</option>
</select>



    <table class="view">
        <tr>
            <th width="30">ID</th>
            <th width="240">Name</th>
            <th width="60">Complete</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${todoList}" var="todo">
            <tr>
                <td>${todo.idTODO}</td>
                <td><a href="/view/${todo.idTODO}">${todo.nameTODO}</a></td>
                <td>
                    <c:if test="${todo.completed == true}"><img src="${pageContext.servletContext.contextPath}/img/complete.png" width="25" height="25" /></c:if>
                    <c:if test="${todo.completed == false}"><img src="/img/incomplete.png" width="25" height="25" /></c:if>
                </td>
                <td><a href="<c:url value='/delete/${todo.idTODO}'/>"><img src="/img/delete.png" width="25" height="25" /></a></td>
            </tr>
        </c:forEach>
    </table>



<div class="paging">
    <c:if test="${todoListFull.size() > -1}">
        <c:forEach begin="1" end="${count}" var="val">
            <c:if test="${numpage == val}">
                <span>${val}</span>
            </c:if>
            <c:if test="${numpage != val}">
                <c:url var="numpageURL" value="?numpage=${val}" />
                <a href="/to_do${numpageURL}&filt=${filt}">${val}</a>
            </c:if>

        </c:forEach>
    </c:if>
</div>
</c:if>


<h1>Add new ToDo</h1>

<c:url var="addAction" value="/to_do/add"/>

<form:form action="${addAction}" commandName="todo">

    <form:label path="idTODO"></form:label>
    <form:hidden path="idTODO"/>

    <form:label path="nameTODO">
        <spring:message text="Name:"/>
    </form:label>
    <form:input path="nameTODO" required="required"/>

    <form:label path="completed"></form:label>
    <form:hidden path="completed" />

    <input type="submit" value="<spring:message text="Add"/>"/>
</form:form>

<br>

<a href="../../index.jsp">Return to main menu</a>

</body>
</html>
