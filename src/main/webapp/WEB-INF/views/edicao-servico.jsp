<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!-- TAGLIB do Spring MVC -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- TAGLIB do JAVA (JSTL) -->
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
	input.error, textarea.error, select.error {
		border: 1px solid #d9534f;
	}

</style>

</head>
<body>

	<jsp:include page="/WEB-INF/components/menu.jsp"></jsp:include>

	<div class="container mt-4">
		
		<h5>Edição de Serviço</h5>
		<br/>
		
		<form id="formedicao" action="atualizarservico" method="post">
		
			<!-- campo oculto -->
			<form:input path="dto.idServico" type="hidden"/>
		
			<div class="row">
			
				<div class="col-md-6">
				
					<label>Nome do Serviço:</label>
					<form:input path="dto.nome" type="text" class="form-control" id="nome" name="nome"/>
				
				</div>
				
				<div class="col-md-3">
				
					<label>Preço:</label>
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
				
					<label>Descrição do serviço</label>
					<form:textarea path="dto.descricao" class="form-control" id="descricao" name="descricao"></form:textarea>
				
				</div>
				
				<div class="col-md-6">
				
					<label>Selecione o profissional:</label>
					<form:select path="dto.profissional" class="form-select" id="profissional" name="profissional">
						<option value="">Escolha um Profissional para o serviço</option>		
						
						<c:forEach items="${profissionais}" var="item">
						
							<c:if test="${dto.profissional == item.idProfissional}">
							
								<option value="${item.idProfissional}" selected="selected">
									${item.nome} (CPF: ${item.cpf})
								</option>
							
							</c:if>
						
							<c:if test="${dto.profissional != item.idProfissional}">
							
								<option value="${item.idProfissional}">
									${item.nome} (CPF: ${item.cpf})
								</option>
							
							</c:if>
						
						</c:forEach>
																
					</form:select>
				
				</div>
			
			</div>
			
			<br/>
			
			<input type="submit" class="btn btn-success"
				value="Salvar Alterações"/>
							
			<a href="/projetoSpringMVC01/consultaservico" class="btn btn-light">Cancelar</a>
				
		</form>	
			
	</div>

	<!-- referencia de arquivos javascript -->
	<script src="resources/js/bootstrap.bundle.js"></script>
	
	<!-- referencia de arquivos javascript -->
	<script src="resources/js/jquery-3.6.0.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>
	
	<!-- bloco de código javascript -->
	<script>
	
		//inicializando o jquery..
		$(document).ready(function(){
			
			//criando a validação do formulário
			$("#formedicao").validate({
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


