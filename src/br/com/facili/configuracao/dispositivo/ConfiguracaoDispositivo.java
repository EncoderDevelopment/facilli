package br.com.facili.configuracao.dispositivo;


import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class ConfiguracaoDispositivo {
	
	/**
	 * CGPoint recebe a configuração da resolução do aparelho
	 * @param cgPoint
	 * @return
	 */
	public static CGPoint resolucao(CGPoint cgPoint) {
		return cgPoint;
	}

	/**
	 * Captura a largura da tela do dispositivo
	 * @return
	 */
	public static float larguraDaCena() {
		return tamanho().width;
	}

	/**
	 * Captura a altura da tela do dispositivo
	 * @return
	 */
	public static float alturaDaCena() {
		return tamanho().height;
	}

	/**
	 * Retorna os parametros de configuração da largura e altura do dispositivo
	 * @return
	 */
	public static CGSize tamanho() {
		return CCDirector.sharedDirector().winSize();
	}
	
}
