package br.com.facili.cenario.menu;

import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor3B;

import br.com.facili.cenario.tela.CenarioTelaApresentacao;
import br.com.facili.cenario.tela.CenarioTelaConfiguracoes;
import br.com.facili.cenario.tela.CenarioTelaImagens;
import br.com.facili.cenario.tela.CenarioTelaImagens2;
import br.com.facili.cenario.tela.CenarioTelaImagens3;
import br.com.facili.cenario.tela.CenarioTelaInicio;
import br.com.facili.cenario.tela.CenarioTelaSair;
import br.com.facili.componente.ComponenteBotao;
import br.com.facili.componente.ComponenteCampoTexto;
import br.com.facili.componente.ComponenteDialogoPequeno;
import br.com.facili.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.facili.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.facili.contrato.ContratoBotaoMenu;


public class CenarioMenuInicio extends CCLayer implements ContratoBotaoMenu{
	
	private static ComponenteBotao botaoComunicacaoImagens;
	private static ComponenteBotao botaoContextoImagens;
	private static ComponenteBotao botaoSair;
	
	private final float scaleX = 0.3f;
	private final float scaleY = 0.35f;
	private boolean isClicado1 = true;
	private boolean isClicado2 = true;
	private CCScene sair;
	
	public CenarioMenuInicio() {
		this.setIsTouchEnabled(true);
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		delegaComponentes();
		setButtonspPosition();
		adicionaComponentesNaTela();
		ConfiguracaoPreferencias.identificaCenaCorrente(2);
		
		
	}

	private void adicionaComponentesNaTela() {
		
		
		addChild(botaoComunicacaoImagens);
		addChild(botaoContextoImagens);
		addChild(botaoSair);
		//sair = CenarioTelaSair.criaCenario();
		//addChild(sair);
		//sair.setVisible(false);				
		
		
	}
	
	
	private void delegaComponentes() {
		botaoComunicacaoImagens.setDelegate(this);
		botaoContextoImagens.setDelegate(this);
		botaoSair.setDelegate(this);
		
	
	}

	private void criaComponentes() {
		botaoComunicacaoImagens = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_COMUNICACAO, scaleX ,  scaleY);
		botaoContextoImagens = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_CONTEXTO, scaleX ,  scaleY);
		botaoSair = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_SAIR, scaleX ,  scaleY);		
		
	
	}

	/**
	 * Configura a posição dos botões
	 */
	
	private void setButtonspPosition() {
		botaoComunicacaoImagens.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2) - 110 , alturaDaCena() - 80 )));
		botaoContextoImagens.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2) - 110 , alturaDaCena()/2 )));
		botaoSair.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2) - 110 , alturaDaCena() - 240 )));
	}	
	
	/**
 	* Adiciona os eventos de click do menu
 	*/
	@Override
	public void clickBotao(ComponenteBotao sender) {
		
			if (sender.equals(botaoComunicacaoImagens)) {
				botaoComunicacaoImagens.adicionaBotaoComEfeitoDePulo(2);
				ConfiguracaoPreferencias.caminho();
				
			}
			
			if (sender.equals(botaoContextoImagens)) {
				botaoContextoImagens.adicionaBotaoComEfeitoDePulo(2);
				CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaConfiguracoes.criaCenario()));
				
			}
			
			if (sender.equals(botaoSair)) {								
				sair = CenarioTelaSair.criaCenario();
				//addChild(sair);
				CCDirector.sharedDirector().getRunningScene().addChild(sair);
				desabilitaToqueBotoes(false);				
				CenarioMenuSair.desabilitaToqueBotoes(true);		
				
				
			}
			
	}
	
	
	
	public static void desabilitaToqueBotoes(boolean habilitaDesabilita) {
		botaoComunicacaoImagens.setIsTouchEnabled(habilitaDesabilita);
		botaoContextoImagens.setIsTouchEnabled(habilitaDesabilita);
		botaoSair.setIsTouchEnabled(habilitaDesabilita);
		//CenarioMenuSair.desabilitaToqueBotoes(true);
	}

}
