package br.com.facili.cenario.menu;

import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeDownTransition;
import org.cocos2d.transitions.CCFadeTRTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor3B;

import br.com.facili.cenario.tela.CenarioTelaApresentacao;
import br.com.facili.cenario.tela.CenarioTelaImagens;
import br.com.facili.cenario.tela.CenarioTelaImagens2;
import br.com.facili.cenario.tela.CenarioTelaInicio;
import br.com.facili.componente.ComponenteBotao;
import br.com.facili.componente.ComponenteCampoTexto;
import br.com.facili.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.facili.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.facili.contrato.ContratoBotaoMenu;

public class CenarioMenuSair extends CCLayer implements ContratoBotaoMenu{
	
	private static ComponenteBotao botaoSim;
	private static ComponenteBotao botaoNao;
	private ComponenteCampoTexto textoConfirmacao1;
	private ComponenteCampoTexto textoConfirmacao2;
	
	private final float scaleX = 0.4f;
	private final float scaleY = 0.5f;
	
	public CenarioMenuSair() {
		this.setIsTouchEnabled(true);
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		// coloca botões na posição correta
		setPosition();
		adicionaComponentesNaTela();
		desabilitaToqueBotoes(false);
	}

	private void adicionaComponentesNaTela() {
		textoConfirmacao1.adicionaComEfeitoDeBulo();
		addChild(textoConfirmacao1);
		
		textoConfirmacao2.adicionaComEfeitoDeBulo();
		addChild(textoConfirmacao2);
		
		botaoSim.adicionaBotaoETextoComEfeitoDePulo();
		addChild(botaoSim);
		
		botaoNao.adicionaBotaoETextoComEfeitoDePulo();
		addChild(botaoNao);
	}

	private void criaComponentes() {
		textoConfirmacao1 = new ComponenteCampoTexto(ConfiguracaoTextoApp.TEXTO_CONFIRMACAO_SAIR1, ConfiguracaoFontCaminho.FONT_SENSATION_REGULAR, ccColor3B.ccBLACK, 18);
		textoConfirmacao2 = new ComponenteCampoTexto(ConfiguracaoTextoApp.TEXTO_CONFIRMACAO_SAIR2, ConfiguracaoFontCaminho.FONT_SENSATION_REGULAR, ccColor3B.ccBLACK, 18);
		
		botaoSim = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_GENERICO , new ComponenteCampoTexto(ConfiguracaoTextoApp.TEXTO_BOTAO_CABECALHO_SIM, ConfiguracaoFontCaminho.FONT_YAHOO, ccColor3B.ccWHITE, 18), scaleX , scaleY);
		botaoNao = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_GENERICO , new ComponenteCampoTexto(ConfiguracaoTextoApp.TEXTO_BOTAO_CABECALHO_NAO, ConfiguracaoFontCaminho.FONT_YAHOO, ccColor3B.ccWHITE, 18), scaleX , scaleY);
		
		delegaComportamento();
	}

	private void delegaComportamento() {
		botaoSim.setDelegate(this);
		botaoNao.setDelegate(this);
	}

	/**
	 * Configura a posição dos botões
	 */
	private void setPosition() {
		textoConfirmacao1.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2 , (alturaDaCena() /  2))));
		textoConfirmacao2.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2 , (alturaDaCena() /  2) - 20)));
		
		botaoSim.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2) - 60 , (alturaDaCena() /2) - 90 )));
		botaoNao.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2) + 60 , (alturaDaCena() /2) - 90 )));
		
	}
	
	
	/**
 	* Adiciona os eventos de click do menu
 	*/
	@Override
	public void clickBotao(ComponenteBotao sender) {
		
		
		if (sender.equals(botaoSim)) {
			System.exit(0);
		}
		
		if (sender.equals(botaoNao)) {			
			 
			CCDirector.sharedDirector().replaceScene( CCFadeTRTransition.transition(0.1f , CenarioTelaInicio.criaCenario()));			
			CenarioMenuInicio.desabilitaToqueBotoes(true);
		}
		
		
	}
	
	public static void desabilitaToqueBotoes(boolean h){
		botaoSim.setIsTouchEnabled(h);
		botaoNao.setIsTouchEnabled(h);
		
	}

}
