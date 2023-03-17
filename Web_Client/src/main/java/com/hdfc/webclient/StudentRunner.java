package com.hdfc.webclient;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import com.hdfc.webclient.entity.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/webclient")
public class StudentRunner {

	private static final String baseUrl = "http://localhost:8080/api/jdbc";

	
	@GetMapping("/all")
	public Mono<List<Student>> getAll() {

		WebClient webClient = WebClient.create(baseUrl);

		Flux<Student> flux = webClient.get().uri("/getall").retrieve().bodyToFlux(Student.class);

		flux.doOnNext((std) -> {
			System.out.println(std);
		});

		Mono<List<Student>> mono = flux.collectList();

		return mono;

	}
	
	@PostMapping("/add")
	public String addEmployee(@RequestBody  Student std){
		
		

		
		WebClient client = WebClient.create(baseUrl);
		Mono<String>  mono = client.post()
								.uri("/add")
								.body(Mono.just(std), Student.class)
									.retrieve()
									 .bodyToMono(String.class);
									 
	
		
				String msg =		mono.block();
				
			
		return "Record added";
		
		
	}
	
	@PutMapping("/update/{id}")
	public Mono<String> updateProduct(@RequestBody Student std, @PathVariable Integer id){
		WebClient webClient = WebClient.create(baseUrl);
		Mono<String> mono=webClient.put().uri(uriBuilder ->uriBuilder.path("/update/{id}").build(id)).body(Mono.just(std),Student.class).retrieve().bodyToMono(String.class);
		return mono;
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<String> delete(@PathVariable Integer id){
		WebClient webClient = WebClient.create(baseUrl);
		Mono<String> mono=webClient.delete().
				uri(uriBuilder ->uriBuilder.path("/delete/{id}").
				build(id)).
				retrieve().
				bodyToMono(String.class);
		return mono;
	}
}
