package br.com.prss.sales.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prss.sales.model.RelatorioVendas;
import br.com.prss.sales.model.Venda;
import br.com.prss.sales.repository.VendaRepository;
import br.com.prss.sales.utils.DateUtils;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository vendaRepository;

	public Venda cadastrarNova(Venda venda) {
		venda.getItens().stream().forEach(item -> {
			item.setVenda(venda);
		});
		return vendaRepository.save(venda);
	}

	public Venda buscarPorCodigo(String codigoVenda) {
		return vendaRepository.findByCodigo(codigoVenda);
	}

	/**
	 * Retorna uma estrutura de dados (Map) onde a key é o mês da venda (1 a 12)
	 * e o value é um objeto contendo o valor e o número total de vendas relativos ao ano passado por parâmetro
	 *
	 * @param ano ano da venda (ex: 2019)
	 * @return estrutura de agrupamento de RelatorioVendas por mês do ano
	 */
	public Map<Integer, RelatorioVendas> calcularRelatorioAno(int ano) {
		
		Map<Integer, RelatorioVendas> relatorioVendas = new HashMap<>();
		
		// busca vendas do ano
		List<Venda> vendasAno = vendaRepository.findAllByDataHoraBetween
				(DateUtils.firstSecondYear(ano), DateUtils.lastSecondYear(ano));
		
		// agrupa vendas por mes
		Map<Integer, List<Venda>> vendasByMonth =
				  vendasAno.stream().collect(Collectors.groupingBy(v -> v.getDataHora().getMonthValue()));

		// cria estrutura com numero de vendas e valor total de vendas por mes
		vendasByMonth.forEach((mes, vendas) -> {
			relatorioVendas.put(mes, 
					new RelatorioVendas(vendas.size(), 
							vendas.stream().flatMap(v -> v.getItens().stream())
								.mapToDouble(i -> i.getValor()).sum()));
		});
		
		return relatorioVendas;
	}
}
