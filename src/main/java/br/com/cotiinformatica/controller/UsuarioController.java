package br.com.cotiinformatica.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.UsuarioLoginDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@RequestMapping(value = "/") // URL (página inicial do projeto)
	public ModelAndView login(HttpServletResponse response) throws IOException {

		ModelAndView modelAndView = new ModelAndView("login");
		
		//enviando para a página um DTO inicializado (instanciado)
		modelAndView.addObject("dto", new UsuarioLoginDTO());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "autenticarusuario") // URL (página inicial do projeto)
	public ModelAndView autenticar(UsuarioLoginDTO dto, HttpServletRequest request) throws IOException {

		ModelAndView modelAndView = new ModelAndView("login");
		
		try {
			
			//consultar o usuario no banco de dados atraves do email e da senha
			Usuario usuario = usuarioRepository.obter(dto.getEmail(), dto.getSenha());
			
			//verificar se o usuario foi encontrado..
			if(usuario != null) {
				
				//armazenar os dados do usuario em uma sessão..
				request.getSession().setAttribute("usuario_autenticado", usuario);
				
				//redirecionar o usuario
				modelAndView.setViewName("redirect:paginainicial"); //redirecionamento
			}
			else {
				modelAndView.addObject("mensagem", "Acesso negado. Usuário inválido.");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		
		//enviando para a página um DTO inicializado (instanciado)
		modelAndView.addObject("dto", new UsuarioLoginDTO());
		
		return modelAndView;
	}
	@RequestMapping(value = "logout") 
	public ModelAndView logout( HttpServletRequest request) throws IOException {
		
		//apagar os dados gravados em sessão..
		request.getSession().removeAttribute("usuario_autenticado");
		request.getSession().invalidate();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/"); //redirecionamento
		
		return modelAndView;
	}


}


