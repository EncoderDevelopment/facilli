package br.com.facili.cenario.tela;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

import br.com.facili.cenario.menu.CenarioMenuApresentacao;
import br.com.facili.cenario.menu.CenarioMenuImagens;

public class CenarioTelaImagens extends  CCLayer{
	
	/**
	 * Configura o cenario na tela de inicial, tela de apresentação do jogo
	 */
	public CenarioTelaImagens() {
		//Adiciona ao cenario inicial de apresentacao
		this.addChild(new CenarioMenuImagens());
		
	}

	
	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelaImagens());
		return cena;
	}
	
}
