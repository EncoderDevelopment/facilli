package br.com.facili.cenario.menu;

import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import br.com.facili.R;
import br.com.facili.cenario.tela.CenarioTelaImagens;
import br.com.facili.cenario.tela.CenarioTelaImagens2;
import br.com.facili.cenario.tela.CenarioTelaImagens3;
import br.com.facili.cenario.tela.CenarioTelaInicio;
import br.com.facili.componente.ComponenteBotao;
import br.com.facili.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.facili.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.facili.contrato.ContratoBotaoMenu;

public class CenarioMenuImagens3 extends CCLayer implements ContratoBotaoMenu{
	
	private static ComponenteBotao fundo;
	
	private static ComponenteBotao direito;
	private static ComponenteBotao esquerdo;	
	private static ComponenteBotao sede;
	
	public CenarioMenuImagens3() {
		this.setIsTouchEnabled(true);
		
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		delegaComponentes();
		setButtonspPosition();
		adicionaComponentesNaTela();
		direito.setScaleX(0.3f);
		direito.setScaleY(0.3f);
		esquerdo.setScaleX(0.3f);
		esquerdo.setScaleY(0.3f);		
		ConfiguracaoPreferencias.identificaCenaCorrente(7);
		
	}

	private void adicionaComponentesNaTela() {
		
		addChild(fundo);		
		addChild(direito);
		addChild(esquerdo);		
		addChild(sede);
		
	}
	
	
	private void delegaComponentes() {
		fundo.setDelegate(this);
		direito.setDelegate(this);
		esquerdo.setDelegate(this);
		sede.setDelegate(this);
	}

	private void criaComponentes() {
		fundo = new ComponenteBotao(ConfiguracaoImagemCaminho.FUNDO_IMAGEM3);
		direito = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_DIREITO);
		esquerdo = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_ESQUERDO);
		sede = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_SEDE);
		
	}

	/**
	 * Configura a posição dos botões
	 */
	private void setButtonspPosition() {
		fundo.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2.f, alturaDaCena() / 2.f)));		
		direito.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)+40, (alturaDaCena() / 2.f)-115)));
		esquerdo.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)-375, (alturaDaCena() / 2.f)-115)));	
		sede.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f), (alturaDaCena() / 2.f)+120)));
	}
	
	
	/**
 	* Adiciona os eventos de click do menu
 	*/
	@Override
	public void clickBotao(ComponenteBotao sender) {
		if (sender.equals(direito)) {
			ConfiguracaoPreferencias.caminho();
			
		}
		
		if (sender.equals(esquerdo)) {
			ConfiguracaoPreferencias.caminho();
			
		}
		
		if (sender.equals(sede)) {
			if (ConfiguracaoPreferencias.SOM)
				SoundEngine.sharedEngine().playEffect(ConfiguracaoPreferencias.CONTEXTO, R.raw.sede);				
		}
		
		/*if (sender.equals(fundo)) {
			oundEngine.sharedEngine().playEffect(ConfiguracaoPreferencias.CONTEXTO, R.raw.sede);				
		}*/
		
		
	}
	
	
	
	
	public static void desabilitaToqueBotoes(boolean habilitaDesabilita) {
		fundo.setIsTouchEnabled(habilitaDesabilita);
		direito.setIsTouchEnabled(habilitaDesabilita);
		esquerdo.setIsTouchEnabled(habilitaDesabilita);		
		sede.setIsTouchEnabled(habilitaDesabilita);
	}
	
}
