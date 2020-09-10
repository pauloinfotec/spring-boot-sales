package br.com.prss.sales.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prss.sales.model.Item;
import br.com.prss.sales.model.RelatorioVendas;
import br.com.prss.sales.model.Venda;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RelatorioRestControllerTest extends BaseRestControllerTest {

	private static final String RELATORIO_API_PATH = "/api/relatorios";
	private static final String VENDA_API_PATH = "/api/vendas";

	private void cadastrarNovaVenda(Venda venda) {
		String location = given()
				.body(venda)
				.when()
				.contentType("application/json")
				.post(VENDA_API_PATH)
				.then()
				.statusCode(is(HttpStatus.SC_CREATED))
				.assertThat()
				.extract()
				.header("location");

		String expectedLocation = RestAssured.baseURI + ":" + RestAssured.port + VENDA_API_PATH + "/" + venda.getCodigo();
		assertThat(location, is(expectedLocation));
	}
	
	private void adicionarItemVenda(Venda venda, String codigoItem, double valorItem) {
		Item item = new Item();
		item.setCodigo(codigoItem);
		item.setValor(valorItem);
		venda.addItem(item);
	}

	@Test
	public void test_cadastrarNova_calcularRelatorioAno() {
		String codigoVenda = "venda1";

		Venda venda = new Venda();
		venda.setCodigo(codigoVenda);
		venda.setDataHora(LocalDateTime.now());

		adicionarItemVenda(venda, "item1", 100.00);
		adicionarItemVenda(venda, "item2", 200.00);

		cadastrarNovaVenda(venda);

		Map<Integer, RelatorioVendas> relatorio = buscarRelatorioVendasAno(venda.getDataHora().getYear());

		assertCalculoValido(relatorio);
	}

	private void assertCalculoValido(Map<Integer, RelatorioVendas> relatorioMes) {		
		assertThat(relatorioMes.size(), is(1));
		
		relatorioMes.values().stream().forEach(relatorio -> {
			assertThat(relatorio.getNumeroVendas(), is(1));
			assertThat(relatorio.getValorTotalVendas(), is(300.00));
		});
		
	}
	
	private Map<Integer, RelatorioVendas> buscarRelatorioVendasAno(Integer ano) {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.convertValue( 
				 given()
				.when()
				.contentType("application/json")
				.get(RELATORIO_API_PATH + "/vendas/" + ano)
				.then()
				.statusCode(is(HttpStatus.SC_OK))
				.assertThat()
				.contentType(ContentType.JSON)
				.extract()
				.as(JsonNode.class),
				new TypeReference<Map<Integer, RelatorioVendas>>(){});
	}
}
