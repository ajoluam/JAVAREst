package com.montanha.gerenciador.dtos;

/*Este Dto será responsável por transferir as informações vindas da requisição, 
seja um usuário fazendo um cadastro em uma tela ou dados vindos de uma outra 
API, estas informações irão passar pelo DTO, que passará por todas nossas camadas
de Controladores, Serviços e Repositórios.
*/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ViagemDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String localDeDestino;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataPartida;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataRetorno;
	
	private String acompanhante;
	
	public ViagemDto() {
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * As classes ViagemDto e Viagem estão praticamente iguais, dentre as poucas
	 * diferenças encontradas estão algumas validações que podemos encontrar em
	 * nosso Dto como por exemplo o @Lenght e o @NotNull, que estipularão por
	 * exemplo os tamanhos mínimos e máximos de uma certa informação ou indicará a
	 * obrigatoriedade de um certo campo para o cadastro desta Viagem, impedindo que
	 * dados inconsistentes alcancem o banco de dados.
	 */
		
	@NotNull(message = "Local de Destino é uma informação obrigatória")
	@Length(min = 3, max = 40, message = "Local de Destino deve estar entre 3 e 40 caracteres")
	public String getLocalDeDestino() {
		return localDeDestino;
	}

	public void setLocalDeDestino(String localDeDestino) {
		this.localDeDestino = localDeDestino;
	}

	@NotNull(message = "Data da Partida é uma informação obrigatória")
	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public String getAcompanhante() {
		return acompanhante;
	}

	public void setAcompanhante(String acompanhante) {
		this.acompanhante = acompanhante;
	}

	@Override
	public String toString() {
		return "ViagemDto [id=" + id + ", localDeDestino=" + localDeDestino + ", dataPartida=" + dataPartida
				+ ", dataRetorno=" + dataRetorno + ", acompanhante=" + acompanhante + "]";
	}
	
}
