package br.com.prss.sales.model;

public class RelatorioVendas {

	// soma do valor de todos os itens das vendas no mês
	private double valorTotalVendas;
	// número de vendas realizadas no mês
	private int numeroVendas;

	public RelatorioVendas() {
	}
	
	public RelatorioVendas(int numeroVendas, double valorTotalVendas) {
		this.numeroVendas = numeroVendas;
		this.valorTotalVendas = valorTotalVendas;
	}
	
	public int getNumeroVendas() {
		return numeroVendas;
	}
	
	public double getValorTotalVendas() {
		return valorTotalVendas;
	}
}
