package br.com.facili.componente;

import org.cocos2d.actions.ease.CCEaseInOut;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;
import br.com.facili.contrato.ContratoBotaoMenu;


/**
 * Adiciona um botão na tela do dispositivo
 * @author andrade
 *
 */
public class ComponenteBotao extends CCLayer{
	private CCSprite botao;
	private ComponenteCampoTexto textoBotao;
	private ContratoBotaoMenu delegate;
	
	/**
	 * Cria um botão baseado em uma imagem
	 * @param imagem
	 */
	public ComponenteBotao(String imagem) {
		this.setIsTouchEnabled(true);
		this.botao = CCSprite.sprite(imagem);
		this.addChild(this.botao);
		
	}
	
	public ComponenteBotao(String imagem , ComponenteCampoTexto textoBotao  , float scala) {
		this.setIsTouchEnabled(true);
		this.botao = CCSprite.sprite(imagem);
		textoBotao.setPosition(botao.getPosition());
		this.botao.setScale(scala);
		this.addChild(this.botao);
		this.textoBotao = textoBotao;
		this.addChild(this.textoBotao);

	}
	
	public ComponenteBotao(String imagem , ComponenteCampoTexto textoBotao  , float scala , int opacidade) {
		this.setIsTouchEnabled(true);
		this.botao = CCSprite.sprite(imagem);
		this.botao.setOpacity(opacidade);
		textoBotao.setPosition(botao.getPosition());
		this.botao.setScale(scala);
		this.addChild(this.botao);
		this.textoBotao = textoBotao;
		this.addChild(this.textoBotao);

	}
	
	public ComponenteBotao(String imagem , ComponenteCampoTexto textoBotao  , float scalaX , float scalaY) {
		this.setIsTouchEnabled(true);
		this.botao = CCSprite.sprite(imagem);
		textoBotao.setPosition(botao.getPosition());
		this.botao.setScaleX(scalaX);
		this.botao.setScaleY(scalaY);
		this.addChild(this.botao);
		this.textoBotao = textoBotao;
		this.addChild(this.textoBotao);

	}
	
	public ComponenteBotao(String imagem, float scalaX, float scalaY) {
		// TODO Auto-generated constructor stub
		this.setIsTouchEnabled(true);
		this.botao = CCSprite.sprite(imagem);
		this.botao.setScaleX(scalaX);
		this.botao.setScaleY(scalaY);
		this.addChild(this.botao);
	}

	/**
	 * Deixa o proprio botão invocar o método clickBotao(Button sender);  
	 * @param sender
	 */
	public void setDelegate(ContratoBotaoMenu sender) {
		this.delegate = sender;
	}
	
	/**
	 * Faz com que o delegate seja avisado quando esse botão (que é um CCLayer) for tocado.
	 */
	@Override
	protected void registerWithTouchDispatcher() {
		CCTouchDispatcher.sharedDispatcher().addTargetedDelegate(this, 0, false);
	}
	
	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		CGPoint touchLocation = CGPoint.make(event.getX(), event.getY());
		touchLocation = CCDirector.sharedDirector().convertToGL(touchLocation);
		touchLocation = this.convertToNodeSpace(touchLocation);
		
		//Verifica toque no botão
		if (CGRect.containsPoint( this.botao.getBoundingBox(), touchLocation)) {
				delegate.clickBotao(this);
		}
		
		return true;
	}
	
	public void removeComEfeitoDesvaneceEscala(){
		float dt = 0.2f;
		botao.runAction(CCSpawn.actions(CCScaleBy.action(dt, 0.5f), CCFadeOut.action(dt)));
		textoBotao.runAction(CCSpawn.actions(CCScaleBy.action(dt, 0.5f), CCFadeOut.action(dt)));
	}
	
	public void adicionaBotaoETextoComEfeitoDePulo(){
		float dt = 0.8f;
		botao.runAction(CCSpawn.actions(CCJumpBy.action(dt, botao.getPosition(), 30f, 5), CCFadeIn.action(dt)));
	    textoBotao.adicionaComEfeitoDeBulo();
	}
	
	public void adicionaBotaoComEfeitoDePulo(){
		float dt = 0.8f;
		botao.runAction(CCSpawn.actions(CCJumpBy.action(dt, botao.getPosition(), 30f, 5), CCFadeIn.action(dt)));
	}
	
	
	public void adicionaBotaoComEfeitoDePulo(int pulos){
		float dt = 0.3f;
		botao.runAction(CCJumpBy.action(dt, botao.getPosition(), 15f, pulos));
	}
	
	public void adicionaBotaoComEfeitoScala(float dt){
		CCScaleBy s = CCScaleBy.action(dt, dt);
		s.setDuration(0.1f);
		botao.runAction(s);
	}
	
}
