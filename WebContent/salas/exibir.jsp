<%@page import="org.omg.CORBA.Current"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exibindo Salas</title>
</head>
<body>
<h2>Lista de Salas</h2>
<table border="1">
	<tr>
		<td>Número</td>
		<td>Capacidade</td>
	</tr>
	<c:forEach items="${salas}" var="current">
	        <tr>
	          <td><c:out value="${current.numero}" /></td>
	          <td><c:out value="${current.capacidade}" /></td>
	          <td><a href="?control=Salas&action=Deleta&Numero=<c:out value='${current.numero}' />" >Deletar</a></td>
	          <td><a href="?control=Salas&action=Alterar&NumeroAntigo=<c:out value='${current.numero}' />" >Alterar</a></td>
	        </tr>
	</c:forEach>
</table>
<a href="?control=Salas&action=Inserir">Cadastrar nova sala</a>
</body>
</html>