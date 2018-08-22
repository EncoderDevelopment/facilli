package br.com.facili.configuracao.dispositivo;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.view.Gravity;
import android.widget.Toast;
import br.com.facili.cenario.tela.CenarioTelaImagens;
import br.com.facili.cenario.tela.CenarioTelaImagens2;
import br.com.facili.cenario.tela.CenarioTelaImagens3;
import br.com.facili.componente.ComponenteMenssagem;

public class ConfiguracaoPreferencias {
	public static int CENA_CORRENTE;

		
		public static boolean ATUALIZACAO;
		public static boolean SOM;		
		public static boolean COMER;
		public static boolean DORMIR;
		public static boolean SEDE;
		public static Context CONTEXTO;
		
		public static void configuracaoAtualizacao(boolean a){
			ATUALIZACAO =  a;
		}
		
		public static void configuracaoSom(boolean som){
			SOM = som;
		}
		
		public static void configuraComer(boolean c){
			COMER = c;
		}
		
		public static void configuraDormir(boolean d){
			DORMIR = d;
		}
		
		public static void configuraSede(boolean s){
			SEDE = s;
		}

		public static void delegaContexto(Context contexto){
			CONTEXTO = contexto;
		}
		
		
		
		public static void salvaPreferencias(){
			SharedPreferences configuracoes = ConfiguracaoPreferencias.CONTEXTO.getSharedPreferences("preferencias" , Context.MODE_PRIVATE);
			SharedPreferences.Editor  editor = configuracoes.edit();
			editor.putBoolean("atualizacao", ConfiguracaoPreferencias.ATUALIZACAO);
			editor.putBoolean("som", ConfiguracaoPreferencias.SOM);
			editor.putBoolean("comer", ConfiguracaoPreferencias.COMER);
			editor.putBoolean("dormir", ConfiguracaoPreferencias.DORMIR);
			editor.putBoolean("sede", ConfiguracaoPreferencias.SEDE);
			editor.commit();
			
		}
		
		public static void caminho(){
			//comer
			if (CENA_CORRENTE==3){			
				if (ConfiguracaoPreferencias.DORMIR)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens2.criaCenario()));		
				else if (ConfiguracaoPreferencias.SEDE)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens3.criaCenario()));
			}else if (CENA_CORRENTE==8){			
				if (ConfiguracaoPreferencias.SEDE)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens3.criaCenario()));					
				else if (ConfiguracaoPreferencias.COMER)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens.criaCenario()));
			}else if (CENA_CORRENTE==7){			
				if (ConfiguracaoPreferencias.COMER)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens.criaCenario()));					
				else if (ConfiguracaoPreferencias.DORMIR)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens2.criaCenario()));
			}else if (CENA_CORRENTE==2){			
				if (ConfiguracaoPreferencias.COMER)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens.criaCenario()));
				else if (ConfiguracaoPreferencias.DORMIR)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens2.criaCenario()));
				else if (ConfiguracaoPreferencias.SEDE)
					CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaImagens3.criaCenario()));
				else
					ComponenteMenssagem.menssagem("Nenhum contexto ativado!" ,  Gravity.CLIP_VERTICAL , Toast.LENGTH_SHORT , 2);
			}	
		}
				
		
		// verifica a cena corrente nas cenas do criadas com cocos2d
		public static void identificaCenaCorrente(int cenaCorrente) {
			CENA_CORRENTE = cenaCorrente;
		}

}

