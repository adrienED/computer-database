<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
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
			<a class="navbar-brand" href="dashboard"> <spring:message code="app.lang.subtitle" /> </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>
						<spring:message code="app.lang.editComputer" />
					</h1>

					<form action="editComputer?id=${computer.id}" method="POST">
						<input type="hidden" value="0" id="id" />
						<!-- TODO: Change this value with the computer id -->
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message
										code="app.lang.computer" /></label> <input type="text"
									class="form-control" id="computerName"
									placeholder="Name computer " name="computerName"
									value=${computer.name}>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message
										code="app.lang.introDate" /></label> <input type="date"
									class="form-control" id="introduced"
									placeholder="Introduced date" name="Introduced"
									pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))"
									value=${computer.introduced} >
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message
										code="app.lang.discDate" /></label> <input type="date"
									class="form-control" id="discontinued"
									placeholder="Discontinued date" name="Discontinued"
									pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))"
									value=${computer.discontinued}>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="app.lang.company" /></label> <select class="form-control"
									id="companyId" name="companyName">
									<option selected="selected" value="${computer.companyName}">
										<c:out value="${computer.companyName}">
										</c:out></option>
									<c:forEach var="company" items="${listCompanies}">
										<option value="${company.name}">
											<c:out value="${company.name}">
											</c:out></option>
									</c:forEach>

								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="app.lang.edit" />" class="btn btn-primary">
							or <a href="dashboard.html" class="btn btn-default"><spring:message code="app.lang.cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>