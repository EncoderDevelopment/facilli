package br.com.facili.cenario.tela;

import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.facili.cenario.menu.CenarioMenuInicio;
import br.com.facili.componente.ComponenteImagem;
import br.com.facili.configuracao.dispositivo.ConfiguracaoImagemCaminho;

public class CenarioTelaInicio extends  CCLayer{
	public CenarioTelaInicio() {
		ComponenteImagem imagemCenarioFundoTela = new ComponenteImagem(ConfiguracaoImagemCaminho.FUNDO_MENU);
		imagemCenarioFundoTela.setPosition(CGPoint.ccp(larguraDaCena() / 2.f, alturaDaCena() / 2.f));
		addChild(imagemCenarioFundoTela);
			
		addChild(new CenarioMenuInicio());
		
	}

	public CCScene criaCena(){
		
		CCScene scene = CCScene.node();
		scene.addChild(this);

		return scene;
	}

	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelaInicio());
		return cena;
	}
	
}
