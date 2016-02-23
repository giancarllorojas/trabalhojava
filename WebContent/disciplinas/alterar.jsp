<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterando disciplina: <c:out value='${disciplina.nome}' /></title>
</head>
<body>
	<form method="GET" action="">
		Código: <input type="text" name="Codigo" value="<c:out value='${disciplina.codigo}'/>"/><br>
		Nome: <input type="text" name="Nome" value="<c:out value='${disciplina.nome}'/>"><br>
		Ementa: <input type="text" name="Ementa" value="<c:out value='${disciplina.ementa}'/>"><br>
		<input type="submit" value="Alterar"/>
		<input type="hidden" name="control" value="Disciplinas" />
		<input type="hidden" name="action" value="Altera" />
		<input type="hidden" name="CodAntigo" value="${disciplina.codigo}" />
	</form>
</body>
</html>