package br.com.facili.contrato;

import br.com.facili.componente.ComponenteBotao;

/**
 * Contrato de implementação do botão, garante que os objetos passados serão somente do tipo Button
 * @author andrade
 *
 */
public interface ContratoBotaoMenu {
	
	/**
	 * Recebe um button para ser analizado e processado
	 * @param botao
	 */
	public void clickBotao(ComponenteBotao botao);
	
}
