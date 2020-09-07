package com.cartoes.api.services;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cartoes.api.entities.Cartao;
import com.cartoes.api.entities.Transacao;
import com.cartoes.api.repositories.CartaoRepository;
import com.cartoes.api.repositories.TransacaoRepository;
import com.cartoes.api.utils.ConsistenciaException;
 
@Service
public class TransacaoService {
 
   	private static final Logger log = LoggerFactory.getLogger(CartaoService.class);
 
   	@Autowired
   	private CartaoRepository cartaoRepository;
   	
   	@Autowired
   	private CartaoService cartaoService;
   	
   	@Autowired
   	private TransacaoRepository transacaoRepository;
 
   	public List<Transacao> searchByCard(String nCard) throws ConsistenciaException {
         	log.info("Service: buscando transações do cartão: {}", nCard);
         	Optional<List<Transacao>> tr = transacaoRepository.findByCartaoNumero(nCard);
         	if (!tr.isPresent() || tr.get().size() < 1) {
                log.info("Service: Nenhuma transação foi encontrado pelo cartao {}", nCard);
                throw new ConsistenciaException("Nenhuma transação foi encontrado pelo cartao {}", nCard);
            }
         	return tr.get();
   	}
 
   	public Transacao salvar(Transacao tr) throws ConsistenciaException, ParseException {
   			log.info("Service: salvando transação: {}", tr);
   			if(tr.getCartao().getId() > 0) {
            	throw new ConsistenciaException("Transações não podem ser alteradas.");
   			}
   			Optional<Cartao> cardService = cartaoService.searchByNumber(tr.getCartao().getNumero());
         	Cartao card = cardService.get();
         	if(card.getBloqueado()) {
         		log.info("Service: {}", getMsgCon("bloqueado"));
            	throw new ConsistenciaException(getMsgCon("bloqueado"));
			}
			SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			Date dataValidade = card.getDataValidade();

			/* 
			 * Validar Posteriorimente
			*/
			tr.setDataTransacao(dateFormat.parse(dateFormat.format(new Date())));
			tr.setCartao(card);
			if(dataValidade.before(tr.getDataTransacao())) {
				System.out.println(dataValidade);
				System.out.println(dateFormat.format(tr.getDataTransacao()));
         		log.info("Service: {}", getMsgCon("vencido"));
            	throw new ConsistenciaException(getMsgCon("vencido"));
			}
			/* 
			 * Validar Posteriorimente
			*/

			try {
                return transacaoRepository.save(tr);
         	} catch (DataIntegrityViolationException e) {
                log.info("{}", e.getMessage());
                throw new ConsistenciaException("{}", e.getMessage());
         	}
   	}
   	
   	protected String getMsgCon(String motivo) {
   		return "Não foi possivel adicionar transações para o cartão informado, pois o mesmo encontra-se " + motivo;
   	}
 
}