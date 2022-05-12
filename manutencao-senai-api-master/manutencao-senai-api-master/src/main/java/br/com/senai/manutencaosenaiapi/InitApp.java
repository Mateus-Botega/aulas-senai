package br.com.senai.manutencaosenaiapi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import br.com.senai.manutencaosenaiapi.entity.Cliente;
import br.com.senai.manutencaosenaiapi.entity.OrdemDeServico;
import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.Tecnico;
import br.com.senai.manutencaosenaiapi.enums.Sexo;
import br.com.senai.manutencaosenaiapi.repository.PecasRepository;
import br.com.senai.manutencaosenaiapi.repository.TecnicosRepository;
import br.com.senai.manutencaosenaiapi.service.ClienteService;
import br.com.senai.manutencaosenaiapi.service.OrdemDeServicoService;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import br.com.senai.manutencaosenaiapi.service.TecnicoService;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
		
	}
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PecaService pecaService;
	
	@Autowired
	private OrdemDeServicoService ordemService;	
	
	@Bean	
	public CommandLineRunner commandLineRunner(ApplicationContext ac) {
		return args -> {
			try {
				/*List<Cliente> clientes = clienteService.listarPor("j");
				Cliente clienteSelecionado = clientes.get(0);
				
				List<Tecnico> tecnicos = tecnicoService.listarPor("b");
				Tecnico tecnicoSelecionado = tecnicos.get(0);
				
				List<Peca> pecas = pecaService.listarPor("p");
				List<Peca> pecasDoReparo = new ArrayList<>();
				pecasDoReparo.add(pecas.get(0));
				pecasDoReparo.add(pecas.get(1));
				
				OrdemDeServico novaOrdem = new OrdemDeServico();
				novaOrdem.setCliente(clienteSelecionado);
				novaOrdem.setTecnico(tecnicoSelecionado);
				novaOrdem.setDataDeAbertura(LocalDate.of(2022, 5, 6));
				novaOrdem.setDescricaoDoProblema("Micro não liga");
				novaOrdem.setPecasDoReparo(pecasDoReparo);
				
				this.ordemService.inserir(novaOrdem);*/
				
				/*List<Cliente> clientes = clienteService.listarPor("j");
				OrdemDeServico ordemSalva = ordemService.buscarPor(6);
				ordemSalva.setCliente(clientes.get(2));
				ordemService.alterar(ordemSalva);
				System.out.println(ordemSalva);*/
				
				OrdemDeServico ordemSalva = ordemService.buscarPor(6);
				ordemSalva.getPecasDoReparo()
						.add(ordemSalva.getPecasDoReparo().get(0));
				ordemSalva.setDescricaoDoReparo("Poeira");
				ordemSalva.setDataDeEncerramento(LocalDate.of(2022, 5, 6));
				this.ordemService.fechar(ordemSalva);
				
			}catch (Exception e) {				
				System.out.println(e.getMessage());
			}
			
		};
	}	

}
