<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form:form action="lala" method="GET" modelAttribute="formulario">
  	<form:select path="idSucursalActividad">
 		<option value=""> Seleccione actividad..
 		<c:forEach items= "${listaActividades}" var="listaActividades">
 			<option value="${listaActividades.idSucursalActividad}">${listaActividades.actividad.getNombre()}	
 		</c:forEach>
  	</form:select>
  	
  	<br><br>Prueba<br>  <form:input path="prueba" type="text" />
 	
  	<br><br><button type="submit">enviar</button> 
</form:form>
	
</body>
</html>