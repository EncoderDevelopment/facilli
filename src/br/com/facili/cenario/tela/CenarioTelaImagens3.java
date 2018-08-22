package br.com.facili.cenario.tela;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

import br.com.facili.cenario.menu.CenarioMenuApresentacao;
import br.com.facili.cenario.menu.CenarioMenuImagens;
import br.com.facili.cenario.menu.CenarioMenuImagens2;
import br.com.facili.cenario.menu.CenarioMenuImagens3;

public class CenarioTelaImagens3 extends  CCLayer{
	
	/**
	 * Configura o cenario na tela de inicial, tela de apresentação do jogo
	 */
	public CenarioTelaImagens3() {
		//Adiciona ao cenario inicial de apresentacao
		this.addChild(new CenarioMenuImagens3());
		
	}

	
	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelaImagens3());
		return cena;
	}
	
}
