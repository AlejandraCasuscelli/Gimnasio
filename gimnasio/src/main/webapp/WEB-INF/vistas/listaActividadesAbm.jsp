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

<!-- Animate.css -->
<link href="<c:url value="/css/animate.css" />" rel="stylesheet">
<!-- Icomoon Icon Fonts-->
<link href="<c:url value="/css/icomoon.css" />" rel="stylesheet">
<!-- Bootstrap  -->
<link href="<c:url value="/css/bootstrap4.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">

<!-- Theme style  -->
<link href="<c:url value="/css/style.css" />" rel="stylesheet">

<!-- Modernizr JS -->
<script src="<c:url value="/js/modernizr-2.6.2.min.js" />"></script>

<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="<c:url value="/js/respond.min.js" />"></script>
	<![endif]-->

<link href="<c:url value="/css/fontawesome-all.css" />" rel="stylesheet">
<link href="<c:url value="/css/estilos.css" />" rel="stylesheet">

</head>
<body>

	<div class="fh5co-loader"></div>

	<div id="page">
		<%@include file="menuAdministrador.jsp"%>
		<label style="display: block;text-align: center;line-height: 150%; font-size: .85em; margin-top: 30px;">
		Actividades </label> 
		<div class="container mt-5">
			<table id="sucursales" class="table table-striped table-bordered" style="width:100%">
				<thead>
					<tr>
						<td>Nombre</td>
						<td>Dia</td>
						<td>Horarios</td>
						<td>Cupo</td>
						<td>Descripcion</td>			
						<td>Modificar</td>
						<td>Eliminar</td>
					</tr>
				
				</thead>
				<tbody>
					<c:forEach items="${listaSucursalActividades}" var="sucursalActividad">
						<tr>
							<td>${sucursalActividad.actividad.nombre}</td>
							<td>${sucursalActividad.dia}</td>
							<td>${sucursalActividad.horaDesde}hs  a   ${sucursalActividad.horaHasta}hs</td>
							<td>${sucursalActividad.cupo} max</td>
							<td>${sucursalActividad.actividad.descripcion}</td>
							<td><a href="<c:url value="/${sucursalActividad.idSucursalActividad}/modificacionActividad" />" class="btn btn-primary btn-outline btn-sm">Modificar</i></a></td>
							<td><a href="<c:url value="/${sucursalActividad.sucursal.id}/bajaActividad?idSucursalActividad=${sucursalActividad.idSucursalActividad}" />" class="btn btn-primary btn-outline btn-sm">Baja</a></td>
						</tr>
					</c:forEach>
				
				</tbody>
			
			</table>
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

	<!-- Main -->
	<script src="<c:url value="/js/main.js" />"></script>

</body>

</html>