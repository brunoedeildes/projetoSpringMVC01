<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página inicial</title>

<!-- referencia dos arquivos CSS do projeto -->
<link rel="stylesheet" href="resources/css/bootstrap.css" />

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp"/>

	<div class="container mt-4">
		<h5>Relatório de Profissional</h5>
		<br/>
		
		<form action="gerarrelatorioprofissional" method="post">
		
			<div class="row">
				<div class="col-md-4">
				
					<label>Informe o nome do profissional desejado:</label>
					<form:input path="dto.nome" class="form-control"/>
					<br/>
					
					<input type="submit" value="Gerar Relatório" class="btn btn-success"/>
				
				</div>
			</div>
		
		</form>
		
	</div>

	<!-- referencia de arquivos javascript -->
	<script src="resources/js/bootstrap.bundle.js"></script>

</body>
</html>


