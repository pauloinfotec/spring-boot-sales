package br.com.prss.sales.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venda {

	@Id
	@GeneratedValue
	private Long id;
	// código único da venda
	private String codigo;
	// momento da venda
	private LocalDateTime dataHora;
	// itens que foram adquiridos na venda
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> itens = new ArrayList<>();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void addItem(Item item) {
		this.itens.add(item);
	}
}
