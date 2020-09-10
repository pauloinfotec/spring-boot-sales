package br.com.prss.sales.rest;

import br.com.prss.sales.model.RelatorioVendas;
import br.com.prss.sales.service.VendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Importante: não realize alterações que modifiquem os caminhos dos endpoints ou o HttpMethod que cada um dele utiliza
 */
@RestController
@RequestMapping("/api/relatorios")
public class RelatorioRestController {

	@Autowired
	private VendaService vendaService;

	@GetMapping("/vendas/{ano}")
	public ResponseEntity<Map<Integer, RelatorioVendas>> buscarPorAno(@PathVariable("ano") int ano) {
		return ResponseEntity.ok(vendaService.calcularRelatorioAno(ano));
	}

}
