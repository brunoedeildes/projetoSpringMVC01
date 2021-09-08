package br.com.cotiinformatica.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.ProfissionalCadastroDTO;
import br.com.cotiinformatica.dtos.ProfissionalEdicaoDTO;
import br.com.cotiinformatica.dtos.ProfissionalRelatorioDTO;
import br.com.cotiinformatica.entities.Profissional;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.interfaces.IProfissionalRepository;
import br.com.cotiinformatica.reports.ProfissionalReportPDF;

@Controller //estereótipos (artefato)
public class ProfissionalController {
	
	@Autowired //inicialização automática!!
	private IProfissionalRepository profissionalRepository;

	@RequestMapping(value="/cadastroprofissional") //URL (link)
	public ModelAndView cadastro(HttpServletResponse response) throws IOException{
		
		//Criando um objeto do tipo ModelAndView definindo o nome da página que será aberta
		ModelAndView modelAndView = new ModelAndView("cadastro-profissional");
		
		//enviando para a página um instancia da classe ProfissionalCadastroDTO.java
		modelAndView.addObject("dto", new ProfissionalCadastroDTO());
		
		//finalizando o método
		return modelAndView;
	}
	
	@RequestMapping(value = "cadastrarprofissional", method = RequestMethod.POST)
	public ModelAndView cadastrarProfissional(ProfissionalCadastroDTO dto) {
		
		//Criando um objeto do tipo ModelAndView definindo o nome da página que será aberta
		ModelAndView modelAndView = new ModelAndView("cadastro-profissional");
		
		try {
			
			Profissional profissional = new Profissional();
			
			profissional.setNome(dto.getNome());
			profissional.setEmail(dto.getEmail());
			profissional.setCpf(dto.getCpf());
			profissional.setTelefone(dto.getTelefone());
			profissional.setObservacoes(dto.getObservacoes());
			
			profissionalRepository.inserir(profissional);
			
			modelAndView.addObject("mensagem", "Profissional " + profissional.getNome() + ", cadastrado com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
				
		//enviando para a página um instancia da classe ProfissionalCadastroDTO.java
		modelAndView.addObject("dto", new ProfissionalCadastroDTO());
				
		//finalizando o método
		return modelAndView;
		
	}	
	
	@RequestMapping(value="/consultaprofissional") //URL (link)
	public ModelAndView consulta(HttpServletResponse response) throws IOException{
		
		ModelAndView modelAndView = new ModelAndView("consulta-profissional");
		
		try {
			
			//consultar todos os profissionais cadastrados no banco de dados
			//e enviar para a página uma lista contendo estes registros
			modelAndView.addObject("profissionais", profissionalRepository.consultar());
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		return modelAndView; //nome da página .jsp (arquivo)
	}
	
	@RequestMapping(value="/excluirprofissional") //URL (link)
	public ModelAndView excluir(HttpServletResponse response, Integer id) throws IOException{
		
		ModelAndView modelAndView = new ModelAndView("consulta-profissional");
		
		try {
			
			//excluindo o profissional
			Profissional profissional = new Profissional();
			profissional.setIdProfissional(id);
			
			profissionalRepository.excluir(profissional); //excluindo..
			
			modelAndView.addObject("mensagem", "Profissional excluído com sucesso.");
			
			//consultar todos os profissionais cadastrados no banco de dados
			//e enviar para a página uma lista contendo estes registros
			modelAndView.addObject("profissionais", profissionalRepository.consultar());
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		return modelAndView; //nome da página .jsp (arquivo)
	}	
	
	@RequestMapping(value="/relatorioprofissional") //URL (link)
	public ModelAndView relatorio(HttpServletResponse response) throws IOException{
		
		ModelAndView modelAndView = new ModelAndView("relatorio-profissional");
		modelAndView.addObject("dto", new ProfissionalRelatorioDTO());		
		
		return modelAndView; //nome da página .jsp (arquivo)
	}
	
	//método para receber o SUBMIT POST do formulário de relatorio de profissional
	@RequestMapping(value = "gerarrelatorioprofissional", method = RequestMethod.POST)
	public ModelAndView gerarRelatorio(ProfissionalRelatorioDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("relatorio-profissional");
		modelAndView.addObject("dto", new ProfissionalRelatorioDTO());	
		
		try {
			
			//ler os dados do usuario gravado em sessão (autenticado)
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//consultar os profissionais pelo nome no banco de dados..
			List<Profissional> profissionais = profissionalRepository.consultarPorNome(dto.getNome());
			
			ProfissionalReportPDF report = new ProfissionalReportPDF();
			
			//gerar o relatorio
			ByteArrayInputStream pdf = report.create(profissionais, usuario);
			
			//download do relatorio PDF
			response.setContentType("application/pdf");
			response.addHeader("Content-disposition", "attachment; filename=profissionais.pdf");
			
			byte[] dados = pdf.readAllBytes();
			
			OutputStream out = response.getOutputStream();
			out.write(dados, 0, dados.length);
			out.flush();
			out.close();
			
			response.getOutputStream().flush();
			
			return null;
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		return modelAndView; //nome da página .jsp (arquivo)
	}	
	
	
	@RequestMapping(value = "/edicaoprofissional") //URL (link)
	public ModelAndView edicao(Integer id) throws Exception {
		
		//define o nome da página /WEB-NF/views/edicao-profissional.jsp
		ModelAndView modelAndView = new ModelAndView("edicao-profissional");
		
		try {
			
			Profissional profissional = profissionalRepository.obterPorId(id);
			
			//transferir os dados do profissional para o DTO..
			ProfissionalEdicaoDTO dto = new ProfissionalEdicaoDTO();
			dto.setIdProfissional(profissional.getIdProfissional());
			dto.setNome(profissional.getNome());
			dto.setCpf(profissional.getCpf());
			dto.setEmail(profissional.getEmail());
			dto.setTelefone(profissional.getTelefone());
			dto.setObservacoes(profissional.getObservacoes());
			
			modelAndView.addObject("dto", dto);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "atualizarprofissional", method = RequestMethod.POST)
	public ModelAndView atualizarProfissional(ProfissionalEdicaoDTO dto) {
		
		//Criando um objeto do tipo ModelAndView definindo o nome da página que será aberta
		ModelAndView modelAndView = new ModelAndView("edicao-profissional");
		
		try {
			
			Profissional profissional = new Profissional();
			
			profissional.setIdProfissional(dto.getIdProfissional());
			profissional.setNome(dto.getNome());
			profissional.setEmail(dto.getEmail());
			profissional.setCpf(dto.getCpf());
			profissional.setTelefone(dto.getTelefone());
			profissional.setObservacoes(dto.getObservacoes());
			
			profissionalRepository.alterar(profissional);
			
			modelAndView.addObject("mensagem", "Profissional " + profissional.getNome() + ", atualizado com sucesso.");
			modelAndView.addObject("dto", dto);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
								
		//finalizando o método
		return modelAndView;
		
	}	
}

