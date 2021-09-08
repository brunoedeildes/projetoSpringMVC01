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

import br.com.cotiinformatica.dtos.ServicoCadastroDTO;
import br.com.cotiinformatica.dtos.ServicoEdicaoDTO;
import br.com.cotiinformatica.dtos.ServicoRelatorioDTO;
import br.com.cotiinformatica.entities.Profissional;
import br.com.cotiinformatica.entities.Servico;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.interfaces.IProfissionalRepository;
import br.com.cotiinformatica.interfaces.IServicoRepository;
import br.com.cotiinformatica.reports.ServicoReportPDF;

@Controller
public class ServicoController {
	
	@Autowired
	private IServicoRepository servicoRepository;
	
	@Autowired
	private IProfissionalRepository profissionalRepository;

	@RequestMapping(value = "/cadastroservico") // URL (link)
	public ModelAndView cadastro(HttpServletResponse response) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("cadastro-servico"); // nome da página .jsp (arquivo)
		
		//criando um objeto DTO par fazer a captura dos dados do formulário
		modelAndView.addObject("dto", new ServicoCadastroDTO());
		
		//enviando para a página uma consulta de profissionais
		try {
			modelAndView.addObject("profissionais", profissionalRepository.consultar());
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		//abrir a página
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrarservico", method = RequestMethod.POST) // SUBMIT do formulário
	public ModelAndView cadastrarservico(ServicoCadastroDTO dto) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("cadastro-servico"); // nome da página .jsp (arquivo)
		
		//criando um objeto DTO par fazer a captura dos dados do formulário
		modelAndView.addObject("dto", new ServicoCadastroDTO());
		
		//enviando para a página uma consulta de profissionais
		try {
			
			//capturar os dados recebidos pelo DTO
			Servico servico = new Servico();
			servico.setProfissional(new Profissional());
			
			servico.setNome(dto.getNome());
			servico.setPreco(Double.parseDouble(dto.getPreco()));
			servico.setTempoAtendimento(Integer.parseInt(dto.getTempoAtendimento()));
			servico.setDescricao(dto.getDescricao());
			servico.getProfissional().setIdProfissional(Integer.parseInt(dto.getProfissional()));
			
			//gravar no banco de dados
			servicoRepository.inserir(servico);
			
			modelAndView.addObject("mensagem", "Serviço " + servico.getNome() + ", cadastrado com sucesso.");
			
			modelAndView.addObject("profissionais", profissionalRepository.consultar());
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		//abrir a página
		return modelAndView;
	}

	@RequestMapping(value = "/consultaservico") // URL (link)
	public ModelAndView consulta(HttpServletResponse response) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("consulta-servico"); // nome da página .jsp (arquivo)
		
		try {
			
			//consultar todos os serviços do banco de dados
			modelAndView.addObject("servicos", servicoRepository.consultar());
			
		}
		catch(Exception e) {
			//exibir mensagem de erro na página
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/excluirservico") //URL (link)
	public ModelAndView excluir(HttpServletResponse response, Integer id) throws IOException{
		
		ModelAndView modelAndView = new ModelAndView("consulta-servico");
		
		try {
			
			//excluindo o serviço
			Servico servico = new Servico();
			servico.setIdServico(id);
			
			servicoRepository.excluir(servico); //excluindo..
			
			modelAndView.addObject("mensagem", "Serviço excluído com sucesso.");
			modelAndView.addObject("servicos", servicoRepository.consultar());
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		return modelAndView; //nome da página .jsp (arquivo)
	}	
	
	@RequestMapping(value = "/edicaoservico") //URL (link)
	public ModelAndView edicao(Integer id) throws Exception {
		
		//define o nome da página /WEB-NF/views/edicao-profissional.jsp
		ModelAndView modelAndView = new ModelAndView("edicao-servico");
		
		try {
			
			modelAndView.addObject("profissionais", profissionalRepository.consultar());
			
			//consultar no banco de dados o serviço atraves do ID..
			Servico servico = servicoRepository.obterPorId(id);
			
			//transferir os dados do serviço para o DTO..
			ServicoEdicaoDTO dto = new ServicoEdicaoDTO();
			dto.setIdServico(servico.getIdServico());
			dto.setNome(servico.getNome());
			dto.setPreco(servico.getPreco().toString());
			dto.setTempoAtendimento(servico.getTempoAtendimento().toString());
			dto.setDescricao(servico.getDescricao());
			dto.setProfissional(servico.getProfissional().getIdProfissional().toString());
			
			//disponibilizar o conteudo do DTO para ser exibido na página
			modelAndView.addObject("dto", dto);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/atualizarservico", method = RequestMethod.POST)
	public ModelAndView atualizarServico(ServicoEdicaoDTO dto) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("edicao-servico");
		
		try {
			
			///transferir os dados do DTO para o objeto Servico (JavaBean)
			Servico servico = new Servico();
			servico.setProfissional(new Profissional());
			
			servico.setIdServico(dto.getIdServico());
			servico.setNome(dto.getNome());
			servico.setPreco(Double.parseDouble(dto.getPreco()));
			servico.setTempoAtendimento(Integer.parseInt(dto.getTempoAtendimento()));
			servico.setDescricao(dto.getDescricao());
			servico.getProfissional().setIdProfissional(Integer.parseInt(dto.getProfissional()));
			
			servicoRepository.alterar(servico);
			
			modelAndView.addObject("dto", dto);
			modelAndView.addObject("profissionais", profissionalRepository.consultar());
			
			modelAndView.addObject("mensagem", "Serviço atualizado com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", "Erro: " + e.getMessage());
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/relatorioservico") // URL (link)
	public ModelAndView relatorio(HttpServletResponse response) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("relatorio-servico");
		
		modelAndView.addObject("dto", new ServicoRelatorioDTO());
		
		return modelAndView;		
	}
	
	@RequestMapping(value = "gerarrelatorioservico", method = RequestMethod.POST)
	public ModelAndView gerarRelatorio(ServicoRelatorioDTO dto, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("relatorio-servico");
		modelAndView.addObject("dto", new ServicoRelatorioDTO());	
		
		try {
			
			//ler os dados do usuario gravado em sessão (autenticado)
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//consultar os serviços pelo nome no banco de dados..
			List<Servico> servicos = servicoRepository.consultarPorNome(dto.getNome());
			
			ServicoReportPDF report = new ServicoReportPDF();
			
			//gerar o relatorio
			ByteArrayInputStream pdf = report.create(servicos, usuario);
			
			//download do relatorio PDF
			response.setContentType("application/pdf");
			response.addHeader("Content-disposition", "attachment; filename=servicos.pdf");
			
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

}


