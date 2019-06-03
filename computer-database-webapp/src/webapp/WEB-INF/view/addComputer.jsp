<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<a class="navbar-brand" href="/computer-database/dashboard"> <spring:message
					code="app.lang.subtitle" />
			</a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="app.lang.addComputer" />
					</h1>
					<form:form method="POST" action="addComputer"
						modelAttribute="computerDTO">
						<fieldset>
						
							<div class="form-group">

								<form:label path="name" class="form-control">
									<spring:message code="app.lang.computer" />
								</form:label>
								<form:input path="name" placeholder="Computer name"
									class="form-control" required="required"/>
							</div>


							<div class="form-group">

								<form:label path="introduced" class="form-control">
									<spring:message code="app.lang.introDate" />
								</form:label>
								<form:input path="introduced" placeholder="YYYY-MM-dd"
									class="form-control"
									pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" />


							</div>
							<div class="form-group">
								<form:label path="discontinued" class="form-control">
									<spring:message code="app.lang.discDate" />
								</form:label>
								<form:input path="discontinued" class="form-control"
									placeholder="YYYY-MM-dd"
									pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" />
							</div>
							<div class="form-group">
								<label for="company_id" class="form-control"><spring:message
										code="app.lang.company" /></label> <select class="form-control"
									id="companyId" name="company_id">

									<c:forEach var="company" items="${ListCompanies}">
										<option value="${company.id}">
											<c:out value="${company.name}">
											</c:out></option>
											
											
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit"
								value="<spring:message code="app.lang.add"/>"
								class="btn btn-primary"> or <a
								href="/computer-database/dashboard" class="btn btn-default"><spring:message
									code="app.lang.cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>