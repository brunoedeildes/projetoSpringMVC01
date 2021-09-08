<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!-- TAGLIB do Spring MVC -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- TAGLIB do JAVA (JSTL) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>P�gina inicial</title>

<!-- referencia dos arquivos CSS do projeto -->
<link rel="stylesheet" href="resources/css/bootstrap.css" />

<!-- folhas de estilo -->
<style>

	/* formata��o para as mensagens de erro de valida��o */
	label.error {
		color: #d9534f;
	}
	
	/* formta��o para o campo que contem erro de valida��o */
	input.error, textarea.error, select.error {
		border: 1px solid #d9534f;
	}

</style>

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp"></jsp:include>
	
	<div class="container mt-4">
		
		<h5>Cadastro de Servi�o</h5>
		<br/>
		
		<form id="formcadastro" action="cadastrarservico" method="post">
		
			<div class="row">
			
				<div class="col-md-6">
				
					<label>Nome do Servi�o:</label>
					<form:input path="dto.nome" type="text" class="form-control" id="nome" name="nome"/>
				
				</div>
				
				<div class="col-md-3">
				
					<label>Pre�o:</label>
					<form:input path="dto.preco" type="text" class="form-control" id="preco" name="preco"/>
				
				</div>
				
				<div class="col-md-3">
				
					<label>Tempo de Atendimento em minutos:</label>
					<form:input path="dto.tempoAtendimento" type="number" min="0" class="form-control" id="tempoatendimento" name="tempoatendimento"/>
				
				</div>
			
			</div>
			
			<br/>
			
			<div class="row">
			
				<div class="col-md-6">
				
					<label>Descri��o do servi�o</label>
					<form:textarea path="dto.descricao" class="form-control" id="descricao" name="descricao"></form:textarea>
				
				</div>
				
				<div class="col-md-6">
				
					<label>Selecione o profissional:</label>
					<form:select path="dto.profissional" class="form-select" id="profissional" name="profissional">
						<option value="">Escolha um Profissional para o servi�o</option>
						
						<c:forEach items="${profissionais}" var="item">
						
							<option value="${item.idProfissional}">${item.nome} (CPF: ${item.cpf}) (Tel: ${item.telefone})</option>
						
						</c:forEach>
						
					</form:select>
				
				</div>
			
			</div>
			
			<br/>
			
			<input type="submit" class="btn btn-success"
				value="Cadastrar Servi�o"/>
				
			<input type="reset" class="btn btn-light"
				value="Cancelar"/>
		
		</form>	
			
	</div>

	<!-- referencia de arquivos javascript -->
	<script src="resources/js/bootstrap.bundle.js"></script>
	
	<!-- referencia de arquivos javascript -->
	<script src="resources/js/jquery-3.6.0.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>
	
	<!-- bloco de c�digo javascript -->
	<script>
	
		//inicializando o jquery..
		$(document).ready(function(){
			
			//criando a valida��o do formul�rio
			$("#formcadastro").validate({
				rules : {
					"nome" : {
						required : true,
						minlength : 6,
						maxlength : 150
					},
					"preco" : {
						required : true
					},
					"tempoatendimento" : {
						required : true
					},
					"descricao" : {
						required : true
					},
					"profissional" : {
						required : true
					}
				}
			})
			
		})
	
	</script>

</body>
</html>






