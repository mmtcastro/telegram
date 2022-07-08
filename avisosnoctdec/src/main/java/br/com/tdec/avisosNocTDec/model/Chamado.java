package br.com.tdec.avisosNocTDec.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public class Chamado {
	private String id;
	private String codigo;
	private String status;
	private String descricao;

	@Autowired
	private WebClient.Builder webClientBuilder;

	public static List<Chamado> getChamadosPendentes(String codigoContrato) {
		List<Chamado> chamados = new ArrayList<>();

		WebClient webClient = WebClient.create("https://intra.tdec.com.br"); // 1
		UriSpec<RequestBodySpec> uriSpec = webClient.post(); // 2
		RequestBodySpec bodySpec = uriSpec.uri("/api/aviso/mensagem"); // 3
		RequestHeadersSpec<?> headerSpec = bodySpec.bodyValue("data"); //
		Mono<String> response1 = headerSpec.exchangeToMono(response -> { //
			if (response.statusCode().equals(HttpStatus.OK)) {
				return response.bodyToMono(String.class);//
			} else if (response.statusCode().is4xxClientError()) { //
				return Mono.just("Error response"); //
			} else { //
				return response.createException().flatMap(Mono::error);//
			}
		});

		return chamados;
	}

	public void estudo() {
		// WebClient.Builder builder = new Builder;
		Aviso aviso = webClientBuilder.build().get().uri("localhost:8080/api/avisos").retrieve().bodyToMono(Aviso.class)
				.block();

		WebClient client = WebClient.builder().defaultHeaders(header -> header.setBasicAuth("mcastro", "Hodge$404"))
				.build();

	}

	public static List<String> getChamados()  {
		List<Chamado> chamados = new ArrayList<>();
		WebClient webClient = WebClient.create("https://intra.tdec.com.br");
		Mono<Object[]> response = webClient.get()//
				.uri("/rest.nsf/servicos_chamados_contrato.xsp/api?keys=CITROSUCOSP-25/B")//
				.header("Authorization","Basic " + "bWNhc3RybzpIb2RnZSQ0MDQ=")//
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()//
				.bodyToMono(Object[].class);//
			
		// Extraindo o Mono para uma array de objetos
		Object[] objects = response.block();
		
		// Este ObjectMapper é interessante
		ObjectMapper mapper = new ObjectMapper();
		
		List<String> codigos =  Arrays.stream(objects)
				  .map(object -> mapper.convertValue(object, Chamado.class))
				  .map(Chamado::getCodigo)
				  .collect(Collectors.toList());
		
		System.out.println("Resultado do webCliente eh " + codigos);
		
		
		return codigos;
	}
}
