<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserindo nova disciplina</title>
<link rel="stylesheet" type="text/css" href="../estilo.css">
</head>
<body>
<body>
<div class="topo">
	<h2><a  class="titulo" href="${pageContext.request.contextPath}">Trabalho Final - JAVA</a></h2>
</div>

	<form method="GET" action="">
		Código: <input type="text" name="Codigo"/><br>
		Nome: <input type="text" name="Nome"><br>
		Ementa: <input type="text" name="Ementa"><br>
		<input type="submit" value="Cadastrar"/>
		<input type="hidden" name="control" value="Disciplinas" />
		<input type="hidden" name="action" value="Insere" />
	</form>
</body>
</html>