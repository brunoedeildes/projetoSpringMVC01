<!-- Declarando as TAGLIBS do JSTL -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Sistema de Controle de Serviços</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/projetoSpringMVC01/paginainicial">Página inicial</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Gerenciar Profissionais </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/projetoSpringMVC01/cadastroprofissional">Cadastrar
									Profissionais</a></li>
							<li><a class="dropdown-item" href="/projetoSpringMVC01/consultaprofissional">Consultar
									Profissionais</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/projetoSpringMVC01/relatorioprofissional">Relatório de
									Profissionais</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Gerenciar Serviços </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/projetoSpringMVC01/cadastroservico">Cadastrar Serviços</a></li>
							<li><a class="dropdown-item" href="/projetoSpringMVC01/consultaservico">Consultar Serviços</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/projetoSpringMVC01/relatorioservico">Relatório de
									Serviços</a></li>
						</ul></li>
				</ul>
				<form class="d-flex">
					<span class="text-white mt-3" style="margin-right: 20px;">
						<strong>${usuario_autenticado.nome}</strong> (${usuario_autenticado.email})
					</span>
					<a href="/projetoSpringMVC01/logout" class="btn btn-outline-danger btn-sm mt-3"
						onclick="return confirm('Deseja realmente sair do sistema?')">
						Sair do Sistema
					</a>
				</form>
				
			</div>
		</div>
	</nav>
	
	<c:if test="${not empty mensagem}">
	
		<!-- local para exibir a mensagem -->
		<div class="alert alert-secondary alert-dismissible fade show" role="alert">
  			<strong>${mensagem}</strong>
  			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	
	</c:if>