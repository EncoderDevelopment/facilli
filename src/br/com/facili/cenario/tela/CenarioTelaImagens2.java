package br.com.facili.cenario.tela;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

import br.com.facili.cenario.menu.CenarioMenuApresentacao;
import br.com.facili.cenario.menu.CenarioMenuImagens;
import br.com.facili.cenario.menu.CenarioMenuImagens2;

public class CenarioTelaImagens2 extends  CCLayer{
	
	/**
	 * Configura o cenario na tela de inicial, tela de apresentação do jogo
	 */
	public CenarioTelaImagens2() {
		//Adiciona ao cenario inicial de apresentacao
		this.addChild(new CenarioMenuImagens2());
		
	}

	
	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelaImagens2());
		return cena;
	}
	
}
