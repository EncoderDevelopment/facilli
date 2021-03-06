package br.com.facili.componente;

import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.facili.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;

import br.com.facili.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.facili.configuracao.dispositivo.ConfiguracaoImagemCaminho;

public class ComponenteDialogoPequeno extends CCLayer {

	public ComponenteDialogoPequeno(String textoCabecalho) {
		addChild(CCColorLayer.node(ccColor4B.ccc4(0, 0, 0, 100) , larguraDaCena(), alturaDaCena()));
		
		ComponenteImagem fundoDialogo = new ComponenteImagem(ConfiguracaoImagemCaminho.FUNDO_DIALOGO_PEQUENO);
		
		fundoDialogo.setScale(0.5f);
		fundoDialogo.setPosition( resolucao(CGPoint.ccp( (larguraDaCena() / 2.0f) - 120, (alturaDaCena() / 2.0f ) -  80) ) );
		fundoDialogo.adicionaComEfeitoDeBulo(5);
		addChild(fundoDialogo);
		
		ComponenteCampoTexto rotuloTextoCabecalho = new ComponenteCampoTexto(textoCabecalho , ConfiguracaoFontCaminho.FONT_SENSATION_NEGRITO, ccColor3B.ccc3(56, 176, 222), 26);
		rotuloTextoCabecalho.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2 , (alturaDaCena() /  2) + 90)));
		rotuloTextoCabecalho.adicionaComEfeitoDeBulo();
		addChild(rotuloTextoCabecalho);
	}
}
