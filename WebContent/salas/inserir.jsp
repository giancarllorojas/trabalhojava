<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserindo nova Sala</title>
</head>
<body>
	<form method="GET" action="">
		Número: <input type="text" name="Numero"><br>
		Capacidade: <input type="text" name="Capacidade"><br>
		<input type="submit" value="Cadastrar"/>
		<input type="hidden" name="control" value="Salas" />
		<input type="hidden" name="action" value="Insere" />
	</form>
</body>
</html>