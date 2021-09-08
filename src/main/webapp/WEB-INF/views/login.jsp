<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!-- TAGLIB do Spring -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<!-- referencia dos arquivos CSS do projeto -->
<link rel="stylesheet" href="resources/css/bootstrap.css" />

</head>
<body style="background-color: #eee;">

	<div class="row">
		<div class="col-md-4 offset-md-4">
		
			<div class="card mt-5">
				<div class="card-body">
				
					<div class="text-center">
						<img src="https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png"/>
						<h5>Acesso ao sistema</h5>
					</div>
					
					<form action="autenticarusuario" method="post">
					
						<label>Email de acesso:</label>
						<form:input path="dto.email" type="text" class="form-control"/>
						<br/>
	
						<label>Senha de acesso:</label>
						<form:input path="dto.senha" type="password" class="form-control"/>
						<br/>
						
						<div class="d-grid">
							<input type="submit" value="Acessar Sistema" class="btn btn-success"/>
						</div>
						
						<div class="text-center">
							<h5 class="text-danger">${mensagem}</h5>
						</div>
					
					</form>
					
					<div class="d-grid mt-2">
						<a href="/recuperarsenha" class="btn btn-light">Esqueci minha senha?</a>
					</div>
				
				</div>			
			</div>
		
		</div>
	</div>


	<!-- referencia de arquivos javascript -->
	<script src="resources/js/bootstrap.bundle.js"></script>

</body>
</html>
