package br.com.prss.sales.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prss.sales.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	Venda findByCodigo(String codigo);
	
	List<Venda> findAllByDataHoraBetween(LocalDateTime dataHoraStart, LocalDateTime dataHoraEnd);
}
