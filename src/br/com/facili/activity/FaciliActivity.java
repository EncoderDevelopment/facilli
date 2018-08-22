package br.com.facili.activity;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.transitions.CCFadeTransition;

import br.com.facili.R;
import br.com.facili.cenario.menu.CenarioMenuApresentacao;
import br.com.facili.cenario.menu.CenarioMenuImagens;
import br.com.facili.cenario.menu.CenarioMenuImagens2;
import br.com.facili.cenario.menu.CenarioMenuInicio;
import br.com.facili.cenario.menu.CenarioMenuSair;
import br.com.facili.cenario.tela.CenarioTelaApresentacao;
import br.com.facili.cenario.tela.CenarioTelaImagens;
import br.com.facili.cenario.tela.CenarioTelaInicio;
import br.com.facili.cenario.tela.CenarioTelaSair;
import br.com.facili.componente.ComponenteMenssagem;
import br.com.facili.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.facili.configuracao.dispositivo.ConfiguracaoTextoApp;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class FaciliActivity extends Activity {
	private CCGLSurfaceView configuracaoTela;
	private CCScene cenarioSair;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 ConfiguracaoPreferencias.delegaContexto(FaciliActivity.this);
		 
		//verifica o arquivo de configurações
		SharedPreferences configuracoeSalva = getSharedPreferences("preferencias" , Context.MODE_PRIVATE);
		
		//busca e adiciona o parametro salvo no arquivo de configuração, cria uma nova com a opção defaut caso não exista
		ConfiguracaoPreferencias.configuracaoAtualizacao(configuracoeSalva.getBoolean("atualizacao", true));
		ConfiguracaoPreferencias.configuracaoSom(configuracoeSalva.getBoolean("som", true));
		ConfiguracaoPreferencias.configuraComer(configuracoeSalva.getBoolean("comer", true));
		ConfiguracaoPreferencias.configuraDormir(configuracoeSalva.getBoolean("dormir", true));
		ConfiguracaoPreferencias.configuraSede(configuracoeSalva.getBoolean("sede", true));
		
				
		 
		// definindo orientação como landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
      	//deixa a tela do dispositivo em modo FULL SCREEN
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //configura a tela
       configuracaoTela = new CCGLSurfaceView(this);
       setContentView(configuracaoTela);
       CCDirector.sharedDirector().attachInView(configuracaoTela);
       
       //configura CCDirector
       CCDirector.sharedDirector().setScreenSize(480, 320);
       
       //abre tela principal
       CCDirector.sharedDirector().runWithScene(CenarioTelaApresentacao.criaCenario());
       
       SoundEngine.sharedEngine().preloadEffect(this, R.raw.fome);
       SoundEngine.sharedEngine().preloadEffect(this, R.raw.sede);
       SoundEngine.sharedEngine().preloadEffect(this, R.raw.sono);
       
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		 if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
			 
			 if (ConfiguracaoPreferencias.CENA_CORRENTE == 1) {
				 	//cenarioSair = CenarioTelaSair.criaCenario();     
					//CenarioMenuApresentacao.desabilitaToqueBotoes(false);
					//CenarioMenuSair.desabilitaToqueBotoes(true);
					//CCDirector.sharedDirector().getRunningScene().addChild(cenarioSair);
				 ComponenteMenssagem.menssagem("Utilize o botão fechar para sair da aplicação" ,  Gravity.CLIP_VERTICAL , Toast.LENGTH_SHORT , 1);
				} else if (ConfiguracaoPreferencias.CENA_CORRENTE == 2) {
					//cenarioSair = CenarioTelaSair.criaCenario();     
					//CenarioMenuInicio.desabilitaToqueBotoes(false);					
					//CenarioMenuSair.desabilitaToqueBotoes(true);
					//CCDirector.sharedDirector().getRunningScene().addChild(cenarioSair);		
					ComponenteMenssagem.menssagem("Utilize o botão fechar para sair da aplicação" ,  Gravity.CLIP_VERTICAL , Toast.LENGTH_SHORT , 1);
					
					
				} else if (ConfiguracaoPreferencias.CENA_CORRENTE == 3) {
					//CenarioMenuImagens.desabilitaToqueBotoes(false);
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaInicio.criaCenario()));
				} else if (ConfiguracaoPreferencias.CENA_CORRENTE == 4) {
					//CenarioMenuImagens2.desabilitaToqueBotoes(false);
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaInicio.criaCenario()));					
				}else if (ConfiguracaoPreferencias.CENA_CORRENTE == 5) {
					//CenarioMenuImagens2.desabilitaToqueBotoes(false);
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaInicio.criaCenario()));
				}else if (ConfiguracaoPreferencias.CENA_CORRENTE == 6) {
					//CenarioMenuImagens2.desabilitaToqueBotoes(false);
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaInicio.criaCenario())); 
				}else if (ConfiguracaoPreferencias.CENA_CORRENTE == 7) {
					//CenarioMenuImagens2.desabilitaToqueBotoes(false);
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaInicio.criaCenario())); 
				}else if (ConfiguracaoPreferencias.CENA_CORRENTE == 8) {
					//CenarioMenuImagens2.desabilitaToqueBotoes(false);
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaInicio.criaCenario())); 
				}
			 
			    return true;
			  }
		 
		return super.onKeyDown(keyCode, event);
	}
	
}
