<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
</html><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trabalho JAVA</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<div class="topo">
		<h2><a  class="titulo" href="${request.getRequestURL()}">Trabalho Final - JAVA</a></h2>
	</div>
	<a href="${request.getRequestURL()}go/?control=Disciplinas&action=Exibir">Disciplinas</a> - 
	<a href="${request.getRequestURL()}go/?control=Professores&action=Exibir">Professores</a> - 
	<a href="${request.getRequestURL()}go/?control=Salas&action=Exibir">Salas</a> - 
	<a href="${request.getRequestURL()}go/?control=Turmas&action=Exibir">Turmas</a>
</body>
</html>