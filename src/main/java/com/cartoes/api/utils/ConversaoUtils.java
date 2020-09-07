package com.cartoes.api.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import com.cartoes.api.dtos.CartaoDto;
import com.cartoes.api.dtos.ClienteDto;
import com.cartoes.api.dtos.TransacaoDto;
import com.cartoes.api.entities.Cartao;
import com.cartoes.api.entities.Cliente;
import com.cartoes.api.entities.Transacao;
 
public class ConversaoUtils {
 
   	public static Cartao Converter(CartaoDto cartaoDto) throws ParseException {
		Cartao cartao = new Cartao();
		if (cartaoDto.getId() != null && cartaoDto.getId() != "")
			cartao.setId(Integer.parseInt(cartaoDto.getId()));
		cartao.setNumero(cartaoDto.getNumero());
		cartao.setDataValidade(new SimpleDateFormat("dd/MM/yyyy").parse(cartaoDto.getDataValidade()));
		cartao.setBloqueado(Boolean.parseBoolean(cartaoDto.getBloqueado()));
		Cliente cliente = new Cliente();
		cliente.setId(Integer.parseInt(cartaoDto.getClienteId()));
		cartao.setCliente(cliente);
		return cartao;
   	}
   	
	public static CartaoDto Converter(Cartao cartao) {
		CartaoDto cartaoDto = new CartaoDto();
		cartaoDto.setId(String.valueOf(cartao.getId()));
		cartaoDto.setNumero(cartao.getNumero());
		cartaoDto.setDataValidade(cartao.getDataValidade().toString());
		cartaoDto.setBloqueado(String.valueOf(cartao.getBloqueado()));
		cartaoDto.setClienteId(String.valueOf(cartao.getCliente().getId()));
		return cartaoDto;
   	}
   	
   	public static List<CartaoDto> ConverterLista(List<Cartao> list){
		List<CartaoDto> lst = new ArrayList<CartaoDto>(list.size());
		for (Cartao card : list) {
				lst.add(Converter(card));
		}
		return lst;
   	}
   	
   	public static List<TransacaoDto> ConvertListTrans(List<Transacao> list){
		List<TransacaoDto> lst = new ArrayList<TransacaoDto>(list.size());
		for (Transacao tr : list) {
				lst.add(Converter(tr));
		}
		return lst;
   	}
 
   	public static Cliente Converter(ClienteDto clienteDto) {
		Cliente cliente = new Cliente();
		if (clienteDto.getId() != null && clienteDto.getId() != "")
			cliente.setId(Integer.parseInt(clienteDto.getId()));
		cliente.setNome(clienteDto.getNome());
		cliente.setCpf(clienteDto.getCpf());
		cliente.setUf(clienteDto.getUf());
		return cliente;
   	}
    
   	public static Transacao Converter(TransacaoDto transacaoDto) {
		Transacao transacao = new Transacao();
		if (transacaoDto.getId() != null && transacaoDto.getId() != "")
			transacao.setId(Integer.parseInt(transacaoDto.getId()));
		transacao.setJuros(Double.parseDouble(transacaoDto.getJuros()));
		transacao.setQdtParcelas(Integer.parseInt(transacaoDto.getQdtParcelas()));
		transacao.setValue(Double.parseDouble(transacaoDto.getValue()));
		transacao.setCnpj(transacaoDto.getCnpj());
		transacao.setCartao(transacaoDto.getCartao());
		return transacao;
   	}
 
   	public static TransacaoDto Converter(Transacao transacao) {
   		TransacaoDto transacaoDto = new TransacaoDto();
     	transacaoDto.setId(String.valueOf(transacao.getId()));
     	transacaoDto.setJuros(String.valueOf(transacao.getJuros()));
     	transacaoDto.setQdtParcelas(String.valueOf(transacao.getQdtParcelas()));
     	transacaoDto.setValue(String.valueOf(transacao.getValue()));
     	transacaoDto.setCartao(transacao.getCartao());
     	transacaoDto.setCnpj(transacao.getCnpj());
     	return transacaoDto;
   	}
   	
   	public static ClienteDto Converter(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(String.valueOf(cliente.getId()));
		clienteDto.setNome(cliente.getNome());
		clienteDto.setCpf(cliente.getCpf());
		clienteDto.setUf(cliente.getUf());
		return clienteDto;
   	}
}