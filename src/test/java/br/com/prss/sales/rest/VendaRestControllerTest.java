package br.com.prss.sales.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import br.com.prss.sales.model.Item;
import br.com.prss.sales.model.Venda;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VendaRestControllerTest extends BaseRestControllerTest {

	private static final String VENDA_API_PATH = "/api/vendas";

	@Test
	public void test_cadastrarNova() {
		Venda venda = new Venda();
		venda.setCodigo("venda1");
		venda.setDataHora(LocalDateTime.now());

		Item item = new Item();
		item.setCodigo("item1");
		item.setValor(123.45);
		venda.addItem(item);

		cadastrarNova(venda);
	}

	private void cadastrarNova(Venda venda) {
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

	@Test
	public void test_cadastrarNova_buscarPorCodigo() {
		String codigoVenda = "venda2";

		Venda venda = new Venda();
		venda.setCodigo(codigoVenda);
		venda.setDataHora(LocalDateTime.now());

		Item item1 = new Item();
		item1.setCodigo("item1");
		item1.setValor(123.45);
		venda.addItem(item1);

		Item item2 = new Item();
		item2.setCodigo("item2");
		item2.setValor(99.99);
		venda.addItem(item2);

		cadastrarNova(venda);

		Venda vendaRetorno = buscarPorCodigo(codigoVenda);

		assertBuscaValida(venda, vendaRetorno);
	}

	private void assertBuscaValida(Venda vendaPreCadastro, Venda vendaPosCadastro) {
		assertThat(vendaPosCadastro.getCodigo(), is(vendaPreCadastro.getCodigo()));
		assertThat(vendaPosCadastro.getDataHora(), is(vendaPreCadastro.getDataHora()));
		assertThat(vendaPosCadastro
				.getItens()
				.size(), is(vendaPreCadastro
				.getItens()
				.size()));

		double valorVendaPre = vendaPreCadastro
				.getItens()
				.stream()
				.mapToDouble(Item::getValor)
				.sum();

		double valorVendaPos = vendaPosCadastro
				.getItens()
				.stream()
				.mapToDouble(Item::getValor)
				.sum();

		assertThat(valorVendaPos, is(valorVendaPre));
	}

	private Venda buscarPorCodigo(String codigoVenda) {
		return given()
				.when()
				.contentType("application/json")
				.get(VENDA_API_PATH + "/" + codigoVenda)
				.then()
				.statusCode(is(HttpStatus.SC_OK))
				.assertThat()
				.contentType(ContentType.JSON)
				.extract()
				.as(Venda.class);
	}
}
