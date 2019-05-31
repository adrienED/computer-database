<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
<title><spring:message code="app.lang.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<a class="navbar-brand" href="dashboard"> 
				<spring:message code="app.lang.subtitle"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${nbOfComputer} <spring:message code="app.lang.computerFound"/> </h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder=<spring:message code="app.lang.searchName"/> /> <input
							type="submit" id="searchsubmit" value="<spring:message code="app.lang.filter"/>"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="app.lang.add"/>
						<spring:message code="app.lang.computer"/></a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="app.lang.edit"/></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="deleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="app.lang.computer"/>
                            <a href="dashboard?orderBy=name&ComputerByPage=10&page=1">&#9650;</a>
                            <a href="dashboard?orderBy=name&AS=DESC&nbOfComputerByPage=10&page=1">&#9660;</a>
                            </th>
						<th><spring:message code="app.lang.introDate"/>
						<a href="dashboard?orderBy=introduced&ComputerByPage=10&page=1">&#9650;</a>
                        <a href="dashboard?orderBy=introduced&AS=DESC&nbOfComputerByPage=10&page=1">&#9660;</a></th>
			
						<th><spring:message code="app.lang.discDate"/>
						<a href="dashboard?orderBy=discontinued&ComputerByPage=10&page=1">&#9650;</a>
                        <a href="dashboard?orderBy=discontinued&AS=DESC&nbOfComputerByPage=10&page=1">&#9660;</a>
						</th>
						
						<th><spring:message code="app.lang.company"/></a>
						<a href="dashboard?orderBy=company&ComputerByPage=10&page=1">&#9650;</a>
                        <a href="dashboard?orderBy=company&AS=DESC&nbOfComputerByPage=10&page=1">&#9660;</a>
                        </th>

					</tr>
				</thead>
				<tbody id="results">
					<tr>

						<c:forEach var="computer" items="${ListComputer}">
							<td class="editMode"><input type="checkbox"
								name="deleteComputer" class="cb" value="${computer.id}">
							</td>
							<td><a href="editComputer?id=${computer.id}"
								onclick=""><c:out value="${computer.name}" /></a></td>
							<td><c:out value="${computer.introduced}" /></td>
							<td><c:out value="${computer.discontinued}" /></td>
							<td><c:out value="${computer.companyName }" /></td>
					</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${(page-1) > 0}">
					<li><a href="?page=${page-1}&&orderBy=${OrderBy}&nbOfComputerByPage=${nbOfComputerByPage }" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<li><a href="?page=${page}">${page}</a></li>
				<c:if test="${page+1<=lastPage}">
					<li><a href="?page=${page+1}&orderBy=${OrderBy}&nbOfComputerByPage=${nbOfComputerByPage }">${page+1}</a></li>
				</c:if>
				<c:if test="${page+2<=lastPage}">
					<li><a href="?page=${page+2}&orderBy=${OrderBy}&nbOfComputerByPage=${nbOfComputerByPage }">${page+2}</a></li>
				</c:if>
				<c:if test="${page+3<=lastPage}">
					<li><a href="?page=${page+3}&orderBy=${OrderBy}&nbOfComputerByPage=${nbOfComputerByPage }">${page+3}</a></li>
				</c:if>
				<c:if test="${page+4<=lastPage}">
					<li><a href="?page=${page+4}&orderBy=${OrderBy}&nbOfComputerByPage=${nbOfComputerByPage }">${page+4}</a></li>
				</c:if>
				<c:if test="${page+1<=lastPage}">
					<li><a href="?page=${page+1}&orderBy=${OrderBy}&nbOfComputerByPage=${nbOfComputerByPage }" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>
			
			<div class="btn-group btn-group-sm pull-right" role="group" >
            <button type="button" class="btn btn-default" onclick="window.location.href ='dashboard?nbOfComputerByPage=10&page=1&orderBy=${OrderBy}'">10</button>
            <button type="button" class="btn btn-default" onclick="window.location.href ='dashboard?nbOfComputerByPage=50&page=1&orderBy=${OrderBy}'">50</button>
            <button type="button" class="btn btn-default" onclick="window.location.href ='dashboard?nbOfComputerByPage=100&page=1&orderBy=${OrderBy}'">100</button>
        </div>
		

		</div>
	</footer>
	
	
	<spring:url value="/static/js/jquery.min.js" var="jqueryJs" />
	<spring:url value="/static/js/bootstrap.min.js" var="bootJs" />
	<spring:url value="static/js/dashboard.js" var="dashboardJs" />

	<script src="${jqueryJs}"></script>
	<script src="${bootJs}"></script>
	<script src="${dashboardJs}"></script>

</body>
</html>
