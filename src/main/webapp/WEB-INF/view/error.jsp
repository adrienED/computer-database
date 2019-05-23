<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Computer Database</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<spring:url value="/static/css/font-awesome.css" var="fontCss" />
	<spring:url value="/static/css/main.css" var="mainCss" />
	<spring:url value="/static/css/bootstrap.min.css" var="bootCss" />
	
	<link href="${fontCss}" rel="stylesheet" />
	<link href="${mainCss}" rel="stylesheet" />
	<link href="${bootCss}" rel="stylesheet" />
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">	
			<div class="alert alert-danger">
				Error 500: An error has occured!
				<br/>
				${errorMessage}
			</div>
		</div>
	</section>

	<spring:url value="/static/js/jquery.min.js" var="jqueryJs" />
	<spring:url value="/static/js/bootstrap.min.js" var="bootJs" />
	<spring:url value="static/js/dashboard.js" var="dashboardJs" />

	<script src="${jqueryJs}"></script>
	<script src="${bootJs}"></script>
	<script src="${dashboardJs}"></script>

</body>
</html>