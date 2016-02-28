<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterando professor</title>
<link rel="stylesheet" type="text/css" href="../estilo.css">

</head>
<body>
<div class="topo">
	<h2><a  class="titulo" href="${pageContext.request.contextPath}">Trabalho Final - JAVA</a></h2>
</div>

	<form method="GET" action="">
		Nome: <input type="text" name="Nome" value="<c:out value='${professor.nome}'/>"><br>
		Email: <input type="text" name="Email" value="<c:out value='${professor.email}'/>"><br>
		<input type="submit" value="Alterar"/>
		<input type="hidden" name="control" value="Professores" />
		<input type="hidden" name="action" value="Altera" />
		<input type="hidden" name="Id" value="${professor.id}" />
	</form>
</body>
</html>