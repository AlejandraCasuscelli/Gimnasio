<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">

<title>Stamina Gimnasios</title>



<link
	href="https://fonts.googleapis.com/css?family=Work+Sans:300,400,500,700,800"
	rel="stylesheet">


<!-- Bootstrap  -->
<link href="<c:url value="/css/bootstrap4.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">

<!-- Theme style  -->
<link href="<c:url value="/css/style.css" />" rel="stylesheet">


<link href="<c:url value="/css/fontawesome-all.css" />" rel="stylesheet">
<link href="<c:url value="/css/estilos.css" />" rel="stylesheet">

</head>
<body>

	
	<div id="page">
		<%@include file="menuSinLogin.jsp"%>
		<div class="container mt-5">
			<div class="justify-content-center mx-5 my-3" id="mapa" style="height:500px;"></div>
		</div>
		
		<%@include file="footer.jsp"%>
		
	</div>

	

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	
	
	<!-- countTo -->
	<script src="js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script>
	<script src="https://maps.google.com/maps/api/js?key=AIzaSyAYLHM4JlbbKxTvJTHS295NyCFsv5MmW1g"></script>
	<script src="js/mapa.js"></script>

</body>
</html>

