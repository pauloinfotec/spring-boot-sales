package br.com.prss.sales.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.prss.sales.model.Venda;
import br.com.prss.sales.service.VendaService;

/**
 * Importante: não realize alterações que modifiquem os caminhos dos endpoints ou o HttpMethod que cada um dele utiliza
 */
@RestController
@RequestMapping("/api/vendas")
public class VendaRestController {

	@Autowired
	private VendaService vendaService;

	@PostMapping
	public ResponseEntity<?> cadastrarNova(@RequestBody Venda venda) {
		Venda novaVenda = vendaService.cadastrarNova(venda);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
		         path("/{codigo}").buildAndExpand(novaVenda.getCodigo()).toUri();
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.header("location", uri.toASCIIString())
				.build();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Venda> buscarPorCodigo(@PathVariable("codigo") String codigo) {
		return ResponseEntity.ok(vendaService.buscarPorCodigo(codigo));
	}

}
