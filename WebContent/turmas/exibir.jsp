<%@page import="org.omg.CORBA.Current"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exibindo Turmas</title>
</head>
<body>
<h2>Lista de Turmas</h2>
<table border="1">
	<tr>
		<td>Disciplina</td>
		<td>Professor</td>
		<td>Sala</td>
		<td>Horário</td>
	</tr>
	<c:forEach items="${turmas}" var="current">
	        <tr>
	          <td><c:out value="${current.disciplina}" /></td>
	          <td><c:out value="${current.professor}" /></td>
	          <td><c:out value="${current.sala}" /></td>
	          <td><c:out value="${current.horario}" /></td>
	          <td><a href="?control=Turmas&action=Deleta&Disciplina=<c:out value='${current.disciplina}' />&Professor=<c:out value='${current.professor}' />" >Deletar</a></td>
	          <td><a href="?control=Turmas&action=Alterar&DiscAntigo=<c:out value='${current.disciplina}' />&ProfAntigo=<c:out value='${current.professor}' />" >Alterar</a></td>
	        </tr>
	</c:forEach>
</table>
<a href="?control=Turmas&action=Inserir">Cadastrar nova turma</a>
</body>
</html>