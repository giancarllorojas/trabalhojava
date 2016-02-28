<%@page import="org.omg.CORBA.Current"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exibindo Turmas</title>
<link rel="stylesheet" type="text/css" href="../estilo.css">

</head>
<body>
<div class="topo">
	<h2><a  class="titulo" href="${pageContext.request.contextPath}">Trabalho Final - JAVA</a></h2>
</div>

<h2>Lista de Turmas</h2>
<table border="1" class="tabela">
	<tr>
		<td>Disciplina</td>
		<td>Professor</td>
		<td>Sala</td>
		<td>Horário</td>
	</tr>
	<c:forEach items="${turmas}" var="i">
	        <tr>
	          <td><c:out value="${i.disciplina}" /></td>
	          <td><c:out value="${i.professor}" /></td>
	          <td><c:out value="${i.sala}" /></td>
	          <td><c:out value="${i.horario}" /></td>
	          <td><a href="?control=Turmas&action=Deleta&Id=<c:out value='${i.id}' />">Deletar</a></td>
	          <td><a href="?control=Turmas&action=Alterar&Id=<c:out value='${i.id}' />" >Alterar</a></td>
	        </tr>
	</c:forEach>
</table>
<a href="?control=Turmas&action=Inserir">Cadastrar nova turma</a>
</body>
</html>