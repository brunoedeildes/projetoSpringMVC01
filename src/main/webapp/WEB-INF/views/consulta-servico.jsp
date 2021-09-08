<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Biblioteca de TAGS do JAVA (JSTL) TagLib -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página inicial</title>

<!-- referencia dos arquivos CSS do projeto -->
<link rel="stylesheet" href="resources/css/bootstrap.css" />

<link rel="stylesheet" href="resources/css/jquery.dataTables.min.css"/>

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp"></jsp:include>

	<div class="container mt-4">
		<h5>Consulta de Serviço</h5>
		<br/>
		
		<table id="consulta" class="table table-hover">
			<thead>
				<tr>
					<th>Nome do serviço</th>
					<th>Descrição</th>
					<th>Tempo</th>
					<th>Preço</th>
					<th>Profissional with="200"</th>
					<th with="140">Operações</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${servicos}" var="s">
					<tr>
						<td>${s.nome}</td>
						<td>${s.descricao}</td>
						<td>${s.tempoAtendimento} minutos</td>
						<td>${s.preco}</td>
						<td>
						
							<div>
								${s.profissional.nome}
							</div>
							
							<small>Telefone: ${s.profissional.telefone}</small>
							<br/>
							
							<small>Email: ${s.profissional.email}</small>
							<br/>
							
							<small>CPF: ${s.profissional.cpf}</small>
							<br/>
						
						</td>
						<td>
							<a href="/projetoSpringMVC01/edicaoservico?id=${s.idServico}" 
							   	class="btn btn-primary btn-sm">
								Alterar
							</a>
							
							<a href="/projetoSpringMVC01/excluirservico?id=${s.idServico}" 
								class="btn btn-danger btn-sm"
								onclick="return confirm('Deseja realmente excluir o serviço ${s.nome}?')">
								Excluir
							</a>
						</td>
					</tr>
				</c:forEach>	
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						Quantidade de serviços: ${servicos.size()}
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


