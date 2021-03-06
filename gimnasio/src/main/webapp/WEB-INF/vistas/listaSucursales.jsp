<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Stamina Gimnasios</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="freehtml5.co" />


<link
	href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,700,800"
	rel="stylesheet">


<!-- Bootstrap  -->
<link href="<c:url value="/css/bootstrap4.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">

<!-- Theme style  -->
<link href="<c:url value="/css/style.css" />" rel="stylesheet">

<link href="<c:url value="/css/datatables.css" />" rel="stylesheet">
<link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<c:url value="/css/MonthPicker.min.css" />" rel="stylesheet" type="text/css" />

<link href="<c:url value="/css/fontawesome-all.css" />" rel="stylesheet">
<link href="<c:url value="/css/estilos.css" />" rel="stylesheet">

</head>
<body>


	<div class="fh5co-loader"></div>

	<div id="page">
		<%@include file="menuAdministrador.jsp"%>

		<div class="container mt-5">
			<table id="sucursales" class="table table-striped table-bordered" style="width:100%">
				<thead>
					<tr>
						<td>Nombre</td>
						<td>Localidad</td>
						<td>Direccion</td>
						<td>Codigo Postal</td>
						<td>Responsable</td>
						<td>Modificar</td>
						<td>Eliminar</td>
					</tr>
				
				</thead>
				<tbody>
					<c:forEach items="${listaSucursales}" var="sucursal">
						<tr>
							<td>${sucursal.nombre}</td>
							<td>${sucursal.ciudad.nombre}</td>
							<td>${sucursal.calle} ${sucursal.numcalle}</td>
							<td>${sucursal.codPostal}</td>
							<td>${sucursal.operador.nombre} ${sucursal.operador.apellido}</td>
							<td><a href="<c:url value="/sucursal/${sucursal.id}/modificar" />" 
							class="btn btn-primary btn-outline btn-sm">
							Modificar<i class="icon-arrow-right"></i></a></td>
							<td><a href="<c:url value="/sucursal/${sucursal.id}/eliminar" />" 
							class="btn btn-primary btn-outline btn-sm">
							Eliminar<i class="icon-arrow-right"></i></a></td>
						</tr>
					</c:forEach>
				
				</tbody>
			
			</table>
		</div>	
		<div class="container mt-5">
		<a href="<c:url value="/sucursales" />" 
		class="btn btn-primary btn-outline btn-sm">
		Ver mapa<i class="icon-arrow-right"></i></a>
		</div>
		<div class="container mt-5">
		<a href="<c:url value="/sucursal/agregarNuevaSucursal" />" 
		class="btn btn-primary btn-outline btn-sm">
		Nueva Sucursal<i class="icon-arrow-right"></i></a>
		</div>
	</div>


	<!-- jQuery -->

	<script src="<c:url value="/js/jquery.min.js" />"></script>
	<!-- jQuery Easing -->

	<script src="<c:url value="/js/jquery.easing.1.3.js" />"></script>
	<!-- Bootstrap -->

	<script src="<c:url value="/js/bootstrap.min.js" />"></script>
	<!-- Waypoints -->

	<script src="<c:url value="/js/jquery.waypoints.min.js" />"></script>
	<!-- Stellar Parallax -->

	<script src="<c:url value="/js/jquery.stellar.min.js" />"></script>
	<!-- Carousel -->

	<script src="<c:url value="/js/owl.carousel.min.js" />"></script>
	<!-- countTo -->

	<script src="<c:url value="/js/jquery.countTo.js" />"></script>

	

	<script src="<c:url value="/js/datatables.js" />"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
    <script src="https://cdn.rawgit.com/digitalBush/jquery.maskedinput/1.4.1/dist/jquery.maskedinput.min.js"></script>
	<script src="<c:url value="/js/MonthPicker.min.js" />"></script>
	
<!-- Main -->
	<script src="<c:url value="/js/main.js" />"></script>
	<script src="<c:url value="/js/home.js" />"></script>
	<script src="<c:url value="/js/ganancias.js" />"></script>

</body>

</html>
