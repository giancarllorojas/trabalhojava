<%@page import="org.omg.CORBA.Current"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exibindo Disciplinas</title>
</head>
<body>
<h2>Lista de professores</h2>
<table border="1">
	<tr>
		<td>Nome</td>
		<td>Email</td>
	</tr>
	<c:forEach items="${profs}" var="current">
	        <tr>
	          <td><c:out value="${current.nome}" /></td>
	          <td><c:out value="${current.email}" /></td>
	          <td><a href="?control=Professores&action=Deleta&Nome=<c:out value='${current.nome}' />&Email=<c:out value='${current.email}' />" >Deletar</a></td>
	          <td><a href="?control=Professores&action=Alterar&NomeAntigo=<c:out value='${current.nome}' />&Nome=<c:out value='${current.nome}' />&Email=<c:out value='${current.email}' />" >Alterar</a></td>
	        </tr>
	</c:forEach>
</table>
<a href="?control=Professores&action=Inserir">Cadastrar novo professor</a>
</body>
</html>