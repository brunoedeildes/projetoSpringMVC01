<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!-- Declarando a TAGLIB do SPRING MVC -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- Declarando as TAGLIBS do JSTL -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página inicial</title>

<!-- referencia dos arquivos CSS do projeto -->
<link rel="stylesheet" href="resources/css/bootstrap.css" />

<!-- folhas de estilo -->
<style>

	/* formatação para as mensagens de erro de validação */
	label.error {
		color: #d9534f;
	}
	
	/* formtação para o campo que contem erro de validação */
	input.error, textarea.error {
		border: 1px solid #d9534f;
	}

</style>
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
		<h5>Edição de Profissional</h5>
		<br/>
		
		<form id="formedicao" action="atualizarprofissional" method="post">
		
			<!-- campo oculto -->
			<form:input path="dto.idProfissional" type="hidden"/>
		
			<div class="row">
				<div class="col-md-6">				
					<label>Nome do profissional:</label>
					<form:input path="dto.nome" type="text" id="nome" name="nome" class="form-control"/>				
				</div>
				<div class="col-md-6">				
					<label>Email:</label>
					<form:input path="dto.email" type="text" id="email" name="email" class="form-control"/>				
				</div>
			</div>
			
			<br/>
			
			<div class="row">
				<div class="col-md-3">				
					<label>CPF:</label>
					<form:input path="dto.cpf" type="text" id="cpf" name="cpf" class="form-control"/>				
				</div>
				<div class="col-md-3">				
					<label>Telefone:</label>
					<form:input path="dto.telefone" type="text" id="telefone" name="telefone" class="form-control"/>				
				</div>
				<div class="col-md-6">				
					<label>Observações:</label>
					<form:textarea path="dto.observacoes" id="observacoes" name="observacoes" class="form-control"></form:textarea>		
				</div>
			</div>
			
			<input type="submit" class="btn btn-primary"
				value="Salvar Alterações"/>
				
			<a href="/projetoSpringMVC01/consultaprofissional" class="btn btn-light">Cancelar</a>
		
		</form>
				
	</div>

	<!-- referencia de arquivos javascript -->
	<script src="resources/js/bootstrap.bundle.js"></script>
	
	<!-- referencia de arquivos javascript -->
	<script src="resources/js/jquery-3.6.0.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/jquery.maskedinput.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>
	
	<!-- bloco de código javascript -->
	<script>
	
		//inicializando o jquery..
		$(document).ready(function(){
			
			//formatando o campo CPF com máscara
			$("#cpf").mask("999.999.999-99");
			
			//formatando o campo TELEFONE com máscara
			$("#telefone").mask("(99) 99999-9999");
			
			//criando a validação do formulário
			$("#formedicao").validate({
				rules : {
					"nome" : {
						required : true,
						minlength : 6,
						maxlength : 150
					},
					"email" : {
						required : true,
						email : true
					},
					"cpf" : {
						required : true
					},
					"telefone" : {
						required : true
					},
					"observacoes" : {
						required : true,
						minlength : 6,
						maxlength : 250
					}
				}
			})
			
		})
	
	</script>

</body>
</html>

