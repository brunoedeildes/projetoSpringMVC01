package br.com.cotiinformatica.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.cotiinformatica.interfaces.IProfissionalRepository;
import br.com.cotiinformatica.interfaces.IServicoRepository;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import br.com.cotiinformatica.repositories.ProfissionalRepository;
import br.com.cotiinformatica.repositories.ServicoRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Configuration
@ComponentScan(basePackages="br.com.cotiinformatica")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean //método de configuração do Spring para MVC
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean //método de configuração do Spring para o DataSource 
	//Parametros para conexão com o banco de dados do projeto
	public DataSource getDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/projetospringmvc01?useTimezone=true&serverTimezone=UTC&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("bruno");
		
		return dataSource;
	}
	
	/*
	 * Mapeamento de cada repositorio do projeto
	 */
	@Bean
	public IProfissionalRepository getProfissionalRepository() {
		return new ProfissionalRepository(getDataSource());
	}
	
	@Bean
	public IServicoRepository getServicoRepository() {
		return new ServicoRepository(getDataSource());
	}
	
	@Bean
	public IUsuarioRepository getUsuarioRepository() {
		return new UsuarioRepository(getDataSource());
	}
}


