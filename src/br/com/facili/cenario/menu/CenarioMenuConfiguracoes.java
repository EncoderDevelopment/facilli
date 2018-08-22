package br.com.facili.cenario.menu;

import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import android.view.Gravity;
import android.widget.Toast;
import br.com.facili.cenario.tela.CenarioTelaConfiguracoes;
import br.com.facili.cenario.tela.CenarioTelaContexto;
import br.com.facili.cenario.tela.CenarioTelaImagens2;
import br.com.facili.cenario.tela.CenarioTelaInicio;
import br.com.facili.componente.ComponenteBotao;
import br.com.facili.componente.ComponenteMenssagem;
import br.com.facili.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.facili.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.facili.contrato.ContratoBotaoMenu;

public class CenarioMenuConfiguracoes extends CCLayer implements ContratoBotaoMenu{
	
	private static ComponenteBotao botaoLogoApresentacao;
	private static ComponenteBotao aplicacao;
	private static ComponenteBotao contexto;
	private static ComponenteBotao atualizacao;
	private static ComponenteBotao som;
	
	public CenarioMenuConfiguracoes() {
		this.setIsTouchEnabled(true);
		
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		delegaComponentes();
		setButtonspPosition();
		adicionaComponentesNaTela();
		ConfiguracaoPreferencias.identificaCenaCorrente(5);
		
	}

	private void adicionaComponentesNaTela() {
		
		//botaoLogoApresentacao.adicionaBotaoComEfeitoDePulo();
		addChild(botaoLogoApresentacao);
		addChild(aplicacao);
		addChild(contexto);
		addChild(atualizacao);
		addChild(som);
		
	}
	
	
	private void delegaComponentes() {
		botaoLogoApresentacao.setDelegate(this);
		aplicacao.setDelegate(this);
		contexto.setDelegate(this);
		atualizacao.setDelegate(this);
		som.setDelegate(this);
	}

	private void criaComponentes() {
		botaoLogoApresentacao = new ComponenteBotao(ConfiguracaoImagemCaminho.FUNDO1_CONF);
		aplicacao = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_APLICACAO_CLICADO);
		contexto = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_CONTETXO);
		
		if (ConfiguracaoPreferencias.ATUALIZACAO)
			atualizacao = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_LIGADO);
		else
			atualizacao = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_DESLIGADO);


		if (ConfiguracaoPreferencias.SOM)
			som = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_LIGADO);
		else
			som = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_DESLIGADO);


	}

	/**
	 * Configura a posição dos botões
	 */
	private void setButtonspPosition() {
		botaoLogoApresentacao.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2.f, alturaDaCena() / 2.f)));
		aplicacao.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f) -150, (alturaDaCena() / 2.f)+45)));
		contexto.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)-160, (alturaDaCena() / 2.f)+5)));
		atualizacao.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)+180, (alturaDaCena() / 2.f)+40)));
		som.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)+180, (alturaDaCena() / 2.f)-5)));
		
	}
	
	
	/**
 	* Adiciona os eventos de click do menu
 	*/
	@Override
	public void clickBotao(ComponenteBotao sender) {

		if (sender.equals(contexto)) {
			contexto.adicionaBotaoComEfeitoDePulo(2);
			CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaContexto.criaCenario()));
			
		}
		
		if (sender.equals(atualizacao)) {
			
			atualizacao = onclick(atualizacao, ConfiguracaoPreferencias.ATUALIZACAO);
			addChild(atualizacao);						
			ComponenteMenssagem.menssagem("As alterações na aplicação foram salvas!" ,  Gravity.CLIP_VERTICAL , Toast.LENGTH_SHORT , 1);

			if (ConfiguracaoPreferencias.ATUALIZACAO)
				ConfiguracaoPreferencias.configuracaoAtualizacao(false);
			else
				ConfiguracaoPreferencias.configuracaoAtualizacao(true);
			
			ConfiguracaoPreferencias.salvaPreferencias();				

			
		}
		
		if (sender.equals(som)) {			
							
			som = onclick(som, ConfiguracaoPreferencias.SOM);
			addChild(som);									
			ComponenteMenssagem.menssagem("As alterações no som foram salvas!" ,  Gravity.CLIP_VERTICAL , Toast.LENGTH_SHORT , 1);
			
			if (ConfiguracaoPreferencias.SOM)
					ConfiguracaoPreferencias.configuracaoSom(false);
				else
					ConfiguracaoPreferencias.configuracaoSom(true);
				
			ConfiguracaoPreferencias.salvaPreferencias();				
			
		}
		
	}
	
	
	public static void desabilitaToqueBotoes(boolean habilitaDesabilita) {
		botaoLogoApresentacao.setIsTouchEnabled(habilitaDesabilita);
	}
	
	private ComponenteBotao onclick(ComponenteBotao botaoRemovido, boolean ligado){
		ComponenteBotao novo = null;
		if (ligado){
			novo = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_DESLIGADO);						
		}else{
			novo = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_LIGADO);
		}
		
		novo.setPosition(botaoRemovido.getPosition());
		novo.setDelegate(this);			
		removeChild(botaoRemovido, true);
		novo.adicionaBotaoComEfeitoDePulo(2);
		return novo;
		
	}
}
