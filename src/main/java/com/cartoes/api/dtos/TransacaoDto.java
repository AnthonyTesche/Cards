package com.cartoes.api.dtos;
import javax.validation.constraints.NotEmpty;

import com.cartoes.api.entities.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Length;
 
public class TransacaoDto {
 
   	private String id;
   	
	@NotEmpty(message = "Cnpj não pode ser vazio.")
	@Length(min = 14, max = 14,
	message = "CNPJ deve conter 14 caracteres.")
   	private String cnpj;
   	
	@NotEmpty(message = "Valor não pode ser vazio.")
	@Length(max = 10,
	message = "Valor deve ser no máximo 10 caracteres.")
   	private String value;
   	
	@NotEmpty(message = "qdtParcelas não pode ser vazio.")
	@Length(max = 2,
	message = "Numero de parcelas maxima é de 99")
   	private String qdtParcelas;
   	
	@NotEmpty(message = "Juros não pode ser vazio.")
	@Length(max = 4,
	message = "Valor maximo de juros é de 9999.")
   	private String juros;

   	@JsonProperty("cartao")
   	private Cartao cartao;
   	
   	public String getId() {
     	return id;
   	}
   	
   	public void setId(String id) {
     	this.id = id;
   	}
   	
   	public String getCnpj() {
     	return cnpj;
   	}
   	
   	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
   	}
   	
   	public void setValue(String value) {
     	this.value = value;
   	}
   	
   	public String getValue() {
     	return value;
	}
   	
   	public String getJuros() {
     	return juros;
	}
	
	public void setJuros(String juros) {
     	this.juros = juros;
	}
   	
   	public String getQdtParcelas() {
 		return qdtParcelas;
   	}
   	
   	public void setQdtParcelas(String qdtParcelas) {
     	this.qdtParcelas = qdtParcelas;
   	}
   	
   	public Cartao getCartao() {
     	return cartao;
   	}
   	
   	public void setCartao(Cartao cartao) {
     	this.cartao= cartao;
   	}
         	
   	@Override
   	public String toString() {
     	return "Transacao[id=" + id + ","
            + "cnpj=" + cnpj + ","
            + "value=" + value + ","
            + "qtdParcelas=" + qdtParcelas + ","
            + "juros=" + juros + ","
            + "numero=" + cartao.getNumero() + "]";
   	}
}