<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!-- TAGLIB do JSTL -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página inicial</title>

<!-- referencia dos arquivos CSS do projeto -->
<link rel="stylesheet" href="resources/css/bootstrap.css" />

<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css"/>
<script type="text/javascript">
function pegaID(){
	var id = prompt("Digite o ID do profissional: ")
	window.location.href ="edicaoprofissional?id=" + id;
}
</script>
</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp"></jsp:include>

	<div class="container mt-4 mb-5">
		<h5>Consulta de Profissional</h5>
		<br/>
		
		<table id="consulta" class="table table-hover">
			<thead>
				<tr>
					<th>Nome do profissional</th>
					<th>Email</th>
					<th>CPF</th>
					<th>Telefone</th>
					<th>Observações</th>
					<th>Operações</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${profissionais}" var="p">
					<tr>
						<td>${p.nome}</td>
						<td>${p.email}</td>
						<td>${p.cpf}</td>
						<td>${p.telefone}</td>
						<td>${p.observacoes}</td>
						<td>
							<a href="/projetoSpringMVC01/edicaoprofissional?id=${p.idProfissional}" 
							   	class="btn btn-primary btn-sm">
								Alterar
							</a>
							
							<a href="/projetoSpringMVC01/excluirprofissional?id=${p.idProfissional}" 
								class="btn btn-danger btn-sm"
								onclick="return confirm('Deseja realmente excluir o profissional ${p.nome}?')">
								Excluir
							</a>
						</td>
					</tr>
				</c:forEach>			
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						Quantidade de profissionais: ${profissionais.size()}
					</td>
				</tr>
			</tfoot>
		</table>
		
	</div>

	<!-- referencia de arquivos javascript -->
	<script src="resources/js/bootstrap.bundle.js"></script>
	
	<script src="resources/js/jquery-3.6.0.min.js"></script>
	<script src="resources/js/jquery.dataTables.min.js"></script>

	<script>
	
		$(document).ready(function(){
			
			$('#consulta').DataTable({
				language : {
					url : '//cdn.datatables.net/plug-ins/1.10.25/i18n/Portuguese-Brasil.json'
				}
			});
			
		})
	
	</script>

</body>
</html>








