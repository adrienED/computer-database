<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/dashboard"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${nbOfComputer} Computer found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="DashboardServlet" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="/addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
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
						<th>Computer Name
                            <a href="dashboard?OrderBy=name&nbOfComputerByPage=10&page=1">&#9650;</a>
                            <a href="dashboard?OrderBy=nameDESC&nbOfComputerByPage=10&page=1">&#9660;</a>
                            </th>
						<th>Introduced date
						<a href="dashboard?OrderBy=introduced&nbOfComputerByPage=10&page=1">&#9650;</a>
                        <a href="dashboard?OrderBy=introducedDESC&nbOfComputerByPage=10&page=1">&#9660;</a></th>
			
						<th>Discontinued date
						<a href="dashboard?OrderBy=discontinued&nbOfComputerByPage=10&page=1">&#9650;</a>
                        <a href="dashboard?OrderBy=discontinuedDESC&nbOfComputerByPage=10&page=1">&#9660;</a>
						</th>
						
						<th>Company</a>
						<a href="dashboard?OrderBy=company&nbOfComputerByPage=10&page=1">&#9650;</a>
                        <a href="dashboard?OrderBy=companyDESC&nbOfComputerByPage=10&page=1">&#9660;</a>
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
					<li><a href="?page=${page-1}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<li><a href="?page=${page}">${page}</a></li>
				<c:if test="${page+1<=lastPage}">
					<li><a href="?page=${page+1}&orderBy=${OrderBy}">${page+1}</a></li>
				</c:if>
				<c:if test="${page+2<=lastPage}">
					<li><a href="?page=${page+2}&orderBy=${OrderBy}">${page+2}</a></li>
				</c:if>
				<c:if test="${page+3<=lastPage}">
					<li><a href="?page=${page+3}&orderBy=${OrderBy}">${page+3}</a></li>
				</c:if>
				<c:if test="${page+4<=lastPage}">
					<li><a href="?page=${page+4}&orderBy=${OrderBy}">${page+4}</a></li>
				</c:if>
				<c:if test="${page+1<=lastPage}">
					<li><a href="?page=${page+1}" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
			<li><a href="dashboard?nbOfComputerByPage=10&page=1">10</a></li>
        	<li><a href="dashboard?nbByPage=50&page=1">50</a></li>
			<li><a href="dashboard?nbByPage=100&page=1">100</a></li>
			</div>
		</div>
	</footer>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/dashboard.js"></script>

</body>
</html>
