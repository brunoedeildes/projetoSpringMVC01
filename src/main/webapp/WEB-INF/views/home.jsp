<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página inicial</title>

<!-- referencia dos arquivos CSS do projeto -->
<link rel="stylesheet" href="resources/css/bootstrap.css" />

<script type="text/javascript">
function pegaID(){
	var id = prompt("Digite o ID do profissional: ")
	window.location.href ="edicaoprofissional?id=" + id;
}
</script>

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp"></jsp:include>

	<div class="container mt-4">
		<h5>Seja bem vindo ao projeto!</h5>
		Aplicação desenvolvida em Spring MVC com JDBC e MySQL
	</div>

	<!-- referencia de arquivos javascript -->
	<script src="resources/js/bootstrap.bundle.js"></script>

</body>
</html>
