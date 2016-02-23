<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserindo nova Turma</title>
</head>
<body>
	<form method="GET" action="" id="formturma">
		Disciplina: <select name="Disciplina" form="formturma">
			<c:forEach items="${disciplinas}" var="valor">
			   <option value="<c:out value="${valor.codigo}"/>"><c:out value="${valor.codigo}"/></option>
			</c:forEach>
		</select><br>
		Professor: <select name="Professor" form="formturma">
		  	<c:forEach items="${professores}" var="valor">
			   <option value="<c:out value="${valor.nome}"/>"><c:out value="${valor.nome}"/></option>
			</c:forEach>
		</select><br>
		Sala: <select name="Sala" form="formturma">
		  	<c:forEach items="${salas}" var="valor">
			   <option value="<c:out value="${valor.numero}"/>"><c:out value="${valor.numero}"/></option>
			</c:forEach>
		</select><br>
		Horário: <input type="text" name="Horario"><br>
		<input type="submit" value="Cadastrar"/>
		<input type="hidden" name="control" value="Turmas" />
		<input type="hidden" name="action" value="Insere" />
	</form>
</body>
</html>