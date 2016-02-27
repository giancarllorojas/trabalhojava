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
<h2>Lista de disciplinas</h2>
<table border="1">
	<tr>
		<td>Código</td>
		<td>Nome</td>
		<td>Ementa</td>
	</tr>
	<c:forEach items="${discs}" var="current">
	        <tr>
	          <td><c:out value="${current.codigo}" /></td>
	          <td><c:out value="${current.nome}" /></td>
	          <td><c:out value="${current.ementa}" /></td>
	          <td><a href="?control=Disciplinas&action=Deleta&Codigo=<c:out value='${current.codigo}' />&Nome=<c:out value='${current.nome}' />&Ementa=<c:out value='${current.ementa}' />" >Deletar</a></td>
	          <td><a href="?control=Disciplinas&action=Alterar&CodAntigo=<c:out value='${current.codigo}' />&Codigo=<c:out value='${current.codigo}' />&Nome=<c:out value='${current.nome}' />&Ementa=<c:out value='${current.ementa}' />" >Alterar</a></td>
	        </tr>
	</c:forEach>
</table>
<a href="?control=Disciplinas&action=Inserir">Cadastrar nova disciplina</a>
</body>
</html>