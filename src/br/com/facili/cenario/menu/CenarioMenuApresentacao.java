package br.com.facili.cenario.menu;

import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import br.com.facili.cenario.tela.CenarioTelaInicio;
import br.com.facili.componente.ComponenteBotao;
import br.com.facili.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.facili.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.facili.contrato.ContratoBotaoMenu;

public class CenarioMenuApresentacao extends CCLayer implements ContratoBotaoMenu{
	
	private static ComponenteBotao botaoLogoApresentacao;
	
	public CenarioMenuApresentacao() {
		this.setIsTouchEnabled(true);
		
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		delegaComponentes();
		setButtonspPosition();
		adicionaComponentesNaTela();
		ConfiguracaoPreferencias.identificaCenaCorrente(1);
		
	}

	private void adicionaComponentesNaTela() {
		
		//botaoLogoApresentacao.adicionaBotaoComEfeitoDePulo();
		addChild(botaoLogoApresentacao);
		
	}
	
	
	private void delegaComponentes() {
		botaoLogoApresentacao.setDelegate(this);
	}

	private void criaComponentes() {
		botaoLogoApresentacao = new ComponenteBotao(ConfiguracaoImagemCaminho.FUNDO_CENARIO);
	}

	/**
	 * Configura a posição dos botões
	 */
	private void setButtonspPosition() {
		botaoLogoApresentacao.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2.f, alturaDaCena() / 2.f)));
	}
	
	
	/**
 	* Adiciona os eventos de click do menu
 	*/
	@Override
	public void clickBotao(ComponenteBotao sender) {
		//Executa o som ao clicar no botão
		//ConfiguracaoPreferencias.vibrarCelular(30);
		
		CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaInicio.criaCenario()));			
		
	}
	
	
	public static void desabilitaToqueBotoes(boolean habilitaDesabilita) {
		botaoLogoApresentacao.setIsTouchEnabled(habilitaDesabilita);
	}
	
}
