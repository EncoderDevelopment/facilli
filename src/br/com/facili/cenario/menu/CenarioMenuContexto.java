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
import br.com.facili.cenario.tela.CenarioTelaImagens2;
import br.com.facili.cenario.tela.CenarioTelaInicio;
import br.com.facili.componente.ComponenteBotao;
import br.com.facili.componente.ComponenteMenssagem;
import br.com.facili.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.facili.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.facili.contrato.ContratoBotaoMenu;

public class CenarioMenuContexto extends CCLayer implements ContratoBotaoMenu{
	
	private static ComponenteBotao botaoLogoApresentacao;
	private static ComponenteBotao aplicacao;
	private static ComponenteBotao contexto;
	private static ComponenteBotao comer;
	private static ComponenteBotao dormir;
	private static ComponenteBotao sede;
	
	
	
	public CenarioMenuContexto() {
		this.setIsTouchEnabled(true);
		
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		delegaComponentes();
		setButtonspPosition();
		adicionaComponentesNaTela();
		ConfiguracaoPreferencias.identificaCenaCorrente(6);
		
	}

	private void adicionaComponentesNaTela() {
		
		//botaoLogoApresentacao.adicionaBotaoComEfeitoDePulo();
		addChild(botaoLogoApresentacao);
		addChild(aplicacao);
		addChild(contexto);
		addChild(comer);
		addChild(dormir);
		addChild(sede);
		
	}
	
	
	private void delegaComponentes() {
		botaoLogoApresentacao.setDelegate(this);
		aplicacao.setDelegate(this);
		contexto.setDelegate(this);
		comer.setDelegate(this);
		dormir.setDelegate(this);
		sede.setDelegate(this);
	}

	private void criaComponentes() {
		botaoLogoApresentacao = new ComponenteBotao(ConfiguracaoImagemCaminho.FUNDO2_CONF);
		aplicacao = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_APLICACAO);
		contexto = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_CONTETXO_CLICADO);
		
		if (ConfiguracaoPreferencias.COMER)
			comer = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_LIGADO);
		else
			comer = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_DESLIGADO);
		
		if (ConfiguracaoPreferencias.DORMIR)
			dormir = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_LIGADO);
		else
			dormir = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_DESLIGADO);
		
		if (ConfiguracaoPreferencias.SEDE)
			sede = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_LIGADO);
		else
			sede = new ComponenteBotao(ConfiguracaoImagemCaminho.BT_DESLIGADO);
		
	}

	/**
	 * Configura a posição dos botões
	 */
	private void setButtonspPosition() {
		botaoLogoApresentacao.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2.f, alturaDaCena() / 2.f)));
		aplicacao.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f) -157, (alturaDaCena() / 2.f)+45)));
		contexto.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)-150, (alturaDaCena() / 2.f)+5)));
		
		comer.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)+180, (alturaDaCena() / 2.f)+40)));
		dormir.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)+180, (alturaDaCena() / 2.f)-2)));
		sede.setPosition(resolucao(CGPoint.ccp( (larguraDaCena() / 2.f)+180, (alturaDaCena() / 2.f)-45)));
		
	}
	
	
	/**
 	* Adiciona os eventos de click do menu
 	*/
	@Override
	public void clickBotao(ComponenteBotao sender) {

		if (sender.equals(aplicacao)) {
			aplicacao.adicionaBotaoComEfeitoDePulo(2);
			CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaConfiguracoes.criaCenario()));
			
		}
		
		if (sender.equals(comer)) {
			comer = onclick(comer,ConfiguracaoPreferencias.COMER);
			addChild(comer);
			ComponenteMenssagem.menssagem("As alterações no contexto salvas!" ,  Gravity.CLIP_VERTICAL , Toast.LENGTH_SHORT , 1);

			if (ConfiguracaoPreferencias.COMER)
				ConfiguracaoPreferencias.configuraComer(false);
			else
				ConfiguracaoPreferencias.configuraComer(true);
			
			ConfiguracaoPreferencias.salvaPreferencias();	
			
			
		}
		
		if (sender.equals(dormir)) {
			dormir = onclick(dormir,ConfiguracaoPreferencias.DORMIR);
			addChild(dormir);
			ComponenteMenssagem.menssagem("As alterações no contexto salvas!" ,  Gravity.CLIP_VERTICAL , Toast.LENGTH_SHORT , 1);

			if (ConfiguracaoPreferencias.DORMIR)
				ConfiguracaoPreferencias.configuraDormir(false);
			else
				ConfiguracaoPreferencias.configuraDormir(true);
			
			ConfiguracaoPreferencias.salvaPreferencias();	
			
		}
		
		
		if (sender.equals(sede)) {
			sede = onclick(sede,ConfiguracaoPreferencias.SEDE);
			addChild(sede);
			ComponenteMenssagem.menssagem("As alterações no contexto salvas!" ,  Gravity.CLIP_VERTICAL , Toast.LENGTH_SHORT , 1);

			if (ConfiguracaoPreferencias.SEDE)
				ConfiguracaoPreferencias.configuraSede(false);
			else
				ConfiguracaoPreferencias.configuraSede(true);
			
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
