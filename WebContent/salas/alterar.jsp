<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterando sala</title>
<link rel="stylesheet" type="text/css" href="../estilo.css">

</head>
<body>
<div class="topo">
	<h2><a  class="titulo" href="${pageContext.request.contextPath}">Trabalho Final - JAVA</a></h2>
</div>

	<form method="GET" action="">
		Número: <input type="text" name="Numero" value="<c:out value='${sala.numero}'/>"><br>
		Capacidade: <input type="text" name="Capacidade" value="<c:out value='${sala.capacidade}'/>"><br>
		<input type="submit" value="Alterar"/>
		<input type="hidden" name="control" value="Salas" />
		<input type="hidden" name="action" value="Altera" />
		<input type="hidden" name="Id" value="${sala.id}" />
	</form>
</body>
</html>