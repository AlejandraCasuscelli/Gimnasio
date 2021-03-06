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


<link href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,700,800"
	rel="stylesheet">

<!-- Bootstrap  -->
<link href="<c:url value="/css/bootstrap4.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">

<link href="<c:url value="/css/datatables.css" />" rel="stylesheet">

<!-- Theme style  -->
<link href="<c:url value="/css/style.css" />" rel="stylesheet">


<link href="<c:url value="/css/fontawesome-all.css" />" rel="stylesheet">
<link href="<c:url value="/css/estilos.css" />" rel="stylesheet">

</head>
<body>

	<div class="fh5co-loader"></div>

	<div id="page">
		<%@include file="menuAdministrador.jsp"%>

		<div class="container mt-5">
			<table id="socios" class="table table-striped table-bordered"
				style="width: 100%">
				<thead>
					<tr>
						<td>Nombre</td>
						<td>Apellido</td>
						<td>DNI</td>
						<td>Telefono</td>
						<td>Email</td>
						<td>Localidad</td>
						<td>Calle</td>
						<td>Numero</td>
						<td>Depto</td>
						<td>Pase</td>
					</tr>

				</thead>
				<tbody>
					<c:forEach items="${listaSocios}" var="socio">
						<tr>
							<td>${socio.nombre}</td>
							<td>${socio.apellido}</td>
							<td>${socio.dni}</td>
							<td>${socio.telefono}</td>
							<td>${socio.mail}</td>
							<td>${socio.ciudad.nombre}</td>
							<td>${socio.domicilioCalle}</td>
							<td>${socio.domicilioNumero}</td>
							<td>${socio.domicilioDepto}</td>
							<td>${socio.pase.nombre}</td>
							
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

	<script src="<c:url value="/js/datatables.js" />"></script>>

	<!-- Main -->
	<script src="<c:url value="/js/main.js" />"></script>
	<script src="<c:url value="/js/socios.js" />"></script>

</body>

</html>
