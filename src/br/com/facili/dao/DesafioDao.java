package br.com.facili.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.facili.mb.Desafio;

public class DesafioDao extends SQLiteOpenHelper {
	private static final String TABELA = "desafio";
	private static final String BASE_DE_DADOS = "enforcado";
	private static final int VERSAO = 1;
	private static final  String[] COLUNAS = {"id" , "pergunta" , "resposta" , "dica" , "idioma" , "nivel" , "status"};

	public DesafioDao(Context context) {
		super(context, BASE_DE_DADOS, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase dataBase) {
		String ddlPortugues = "CREATE TABLE " + TABELA + 
				" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				" pergunta TEXT NOT NULL, " +
				" resposta TEXT NOT NULL," +
				" dica TEXT NOT NULL," +
				" idioma INTEGER NOT NULL," +
				" nivel INTEGER NOT NULL," +
				" status INTEGER NOT NULL);";
		
		dataBase.execSQL(ddlPortugues);
		populaBase(dataBase);
		
	}
	
	public void insere(Desafio desafio){
		ContentValues valores = new ContentValues();
		valores.put("pergunta", desafio.getPergunta());
		valores.put("resposta", desafio.getResposta());
		valores.put("dica", desafio.getDica());
		valores.put("idioma", desafio.getIdioma());
		valores.put("nivel", desafio.getNivel());
		valores.put("status", desafio.getStatus());
		getWritableDatabase().insert(TABELA, null, valores);
	}
	
	public Desafio buscaDesafio(Integer idioma , Integer nivel , Integer status){
		
		String ddlBusca = "SELECT * FROM "+ TABELA +" WHERE idioma="+idioma+" AND nivel="+ nivel+" AND status="+status+" ORDER BY RANDOM() LIMIT 1";
		Cursor c = getWritableDatabase().rawQuery(ddlBusca, null);
		Desafio desafio = new Desafio();
		
		while (c.moveToNext()) {
			desafio.setId(c.getInt(0));
			desafio.setPergunta(c.getString(1));
			desafio.setResposta(c.getString(2));
			desafio.setDica(c.getString(3));
			desafio.setIdioma(c.getInt(4));
			desafio.setNivel(c.getInt(5));
			desafio.setStatus(c.getInt(6));
		}
		
		c.close();
		return desafio;
	}

	@Override
	public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
		/*String ddlDelete = "DROP TABLE IF EXISTS " + TABELA;
		dataBase.execSQL(ddlDelete);
		onCreate(dataBase);*/

	}
	
	public void updateDesafio(Desafio desafio){
		String ddlUpdate = "UPDATE " + TABELA + " SET status=2 WHERE id=" + desafio.getId();
		getWritableDatabase().execSQL(ddlUpdate);
	}
	
	public void populaBase(SQLiteDatabase dataBase){
		String ddlInsere = "";
		
		//perguntas portugues nivel básico
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null , 'Quem estava no grupo do Brasil na copa do mundo de 2006?', 'croacia' , 'País europeu limitado. Banhado a oeste pelo mar Adriático e possui uma fronteira marítima com a Itália, no golfo de Trieste' , 1, 1 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Cidade onde ocorrerá um dos jogos da copa do mundo de 2014?', 'manaus' , 'Cidade situada no pulmão do mundo, uma das mais belas cidades brasileiras, capial do estado do Amazonas' , 1 , 1 , 1);";
		dataBase.execSQL(ddlInsere);  
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Ex-jogador de futebol conhecido como fenómeno?', 'ronaldo' , 'Um ex-futebolista brasileiro que atuava como atacante. É considerado por especialistas e fãs como um dos maiores jogadores de futebol de todos os tempos' , 1 , 1 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Qual país sediou a copa do mundo em 1970?', 'mexico' , 'Uma república constitucional federal localizada na América do Norte. O país é limitado a norte pelos Estados Unidos, ao sul e oeste pelo Oceano Pacífico' , 1 , 1 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Qual país foi o campeão da copa do mundo de 1962?', 'brasil' , 'É o maior país da América do Sul e da região da América Latina, sendo o quinto maior do mundo em área territorial (equivalente a 47% do território sul-americano)' , 1 , 1 , 1);";
		dataBase.execSQL(ddlInsere);
		
		//perguntas portugues nivel médio
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Apelido dado a bola da Copa do mundo de 2010?', 'jabulani' , 'A bola possui 11 cores diferentes, cada uma representando os dialetos e etnias diferentes da �?frica do Sul. O nome da bola significa Celebrar, em isiZulu' , 1 , 2 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Qual foi o maior artilheiro da Copa do Mundo de 2002?', 'ronaldo' , 'Ele foi considerado por especialistas e fãs como um dos maiores jogadores de futebol de todos os tempos' , 1 , 2 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Em qual país a taça Jules Rimet foi furtada?', 'inglaterra' , 'O páis iria sediar o mundial de futebol de 1966. A taça Jules Rimet foi então colocada em exposição no Center Hall de Westminster' , 1 , 2 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Capitão da seleção brasileira da Copa do Mundo de 2002?', 'cafu' , 'É um ex-futebolista brasileiro. Atuava principalmente na lateral direita, Ele foi apontado em uma lista feita por Pelé como um dos cento e vinte e cinco maiores jogadores de futebol vivos em 2004' , 1 , 1 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'No jogo entre Brasil x França, quem fez o gol francês?', 'henry' , 'Obteve grande sucesso e o auge de sua carreira como o jogador do Arsenal. Após uma chegada sem grande alvoroço, em agosto de 1999' , 1 , 2 , 1);";
		dataBase.execSQL(ddlInsere);
		
		//perguntas portugues nivel dificil
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, '1º gol da brasileiro em copas do Mundo foi contra qual seleção?', 'mexico' , 'Desculpe, sem dica' , 1 , 3 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'A final de Copa do Mundo com maior público da história ocorreu onde?', 'brasil' , 'Desculpe, sem dica' , 1 , 3 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Ele Fez o último gol da final da Copa de 1970', 'pele' , 'Desculpe, sem dica' , 1 , 3 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Qual país com maior número de aparições em finais de Copas do Mundo?' , ' italia'  , 'Desculpe, sem dica' , 1 , 3 , 1);";
		dataBase.execSQL(ddlInsere);
		ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Qual seleção que foi líder na sua chave que não tomou nenhum gol sequer?', 'suica' , 'Desculpe, sem dica' , 1 , 3 , 1);";
		dataBase.execSQL(ddlInsere);
		
		
		
		
				//perguntas english nivel básico
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null , 'Who was in the group at Brazil World Cup 2006?', 'croatia' , 'Plated west by the Adriatic Sea and has a sea border with Italy in the Gulf of Trieste' , 2, 1 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'City where there will be one of the games of the World Cup 2014?', 'manaus' , 'City located in the lungs of the world, one of the most beautiful Brazilian cities, capial the state of Amazonas' , 2 , 1 , 1);";
				dataBase.execSQL(ddlInsere);  
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Former football player phenomenon known as?', 'ronaldo' , 'A former brazilian footballer who played as a striker' , 2 , 1 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Which country hosted the World Cup in 1970?', 'mexico' , 'Sorry, no hint' , 2 , 1 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Which country was the champion of the World Cup 1962?', 'brazil' ,'Country in South America and the Latin American region, being the fifth largest in the world by land area (equivalent to 47% of the South American territory)' , 2 , 1 , 1);";
				dataBase.execSQL(ddlInsere);
				
				//perguntas english nivel médio
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Nickname given the ball of the 2010 World Cup?', 'jabulani' , 'The ball has 11 different colors, each representing different ethnicities and dialects of South Africa The name of the ball means celebrate in isiZulu' , 2 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'What was the top scorer of the World Cup 2002?', 'ronaldo' , 'He was considered by experts and fans as one of the  greatest football players of all time' , 2 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'In which country the Jules Rimet trophy was stolen?', 'england' , 'The country would host the World Cup 1966. The Jules Rimet trophy was then placed on display in the Center Hall Westminster' , 2 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Captain of the Brazilian team in the World Cup 2002?', 'cafu' , 'It is a former Brazilian footballer. Worked mainly on the right side, he was appointed from a list made ​​by Pelé as one hundred twenty-five largest players in live football in 2004' , 2 , 1 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'In the match between Brazil vs France, who made the French goal?', 'henry' , 'Achieved great success and the pinnacle of his career as an Arsenal player. After an arrival without uproar in August 1999' , 2 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				
				//perguntas english nivel dificil
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, '1st Goal of Brazil in World Cups was against which team?', 'mexico' , 'Sorry, no hint' , 2 , 3 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Where was the world cup final most watched in history?', 'brazil' , 'Sorry, no hint' , 2 , 3 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'He did the last goal in the final of the 1970 World Cup', 'pele' , 'Sorry, no hint' , 2 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'The country that participated in the World Cup final?' , ' Italy'  , 'Sorry, no hint' , 2 , 3 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Which team has been a leader in its key that took no single goal?', 'switzerland' , 'Sorry, no hint' , 2 , 3 , 1);";
				dataBase.execSQL(ddlInsere);
		

				
				
				
				
				
				//perguntas espanish nivel básico
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null , 'Quién estaba en el grupo en el Mundial de Brasil 2006?', 'croacia' , 'País europeo limitado. Chapado oeste por el Mar Adriático y tiene una frontera marítima con Italia en el Golfo de Trieste' , 3, 1 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Ciudad donde se produce uno de los juegos de la Copa del Mundo de 2014?', 'manaus' , 'Ciudad situada en el pulmón del mundo, una de las ciudades más bellas de Brasil, Capial el estado de Amazonas' , 3 , 1 , 1);";
				dataBase.execSQL(ddlInsere);  
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'El ex jugador de fútbol fenómeno conocido como?', 'ronaldo' , 'Un ex futbolista brasileño que juega como delantero. Es considerado por los expertos y los aficionados como uno de los mejores jugadores de fútbol de todos los tiempos' , 3 , 1 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Qué país fue sede de la Copa del Mundo en 1970?', 'mexico' , 'Una república constitucional federal ubicado en América del Norte. El país limita al norte con los Estados Unidos al sur y al oeste con el Océano Pacífico' , 3 , 1 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Qué país fue el campeón de la Copa Mundial de 1962?', 'brasil' , 'Es el país más grande de América del Sur y la región de América Latina, siendo el quinto más grande del mundo por superficie (equivalente al 47% del territorio de América del Sur)' , 3 , 1 , 1);";
				dataBase.execSQL(ddlInsere);
				
				//perguntas espanish nivel médio
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Apodo da el balón de la Copa Mundial de 2010?', 'jabulani' , 'La pelota tiene 11 colores diferentes, cada uno en representación de los diferentes grupos étnicos y dialectos de Sudáfrica El nombre de la pelota significa celebrar en isiZulu' , 3 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Cuál fue el máximo goleador de la Copa Mundial de 2002?', 'ronaldo' , 'Fue considerado por los expertos y los aficionados como uno de los mejores jugadores de fútbol de todos los tiempos' , 3 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'En qué país el trofeo Jules Rimet fue robado?', 'inglaterra' , 'El país anfitrión de la Copa Mundial de 1966. A continuación, el trofeo Jules Rimet fue puesto en exhibición en el Centro de Salón Westminster' , 3 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'El capitán de la selección brasileña en la Copa Mundial de 2002?', 'cafu' , 'Es un ex futbolista brasileño. Trabajó sobre todo en el lado derecho, fue nombrado a partir de una lista hecha por Pelé como ciento veinticinco jugadores más grandes en el fútbol en directo 2004' , 3 , 1 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null,'Qué seleçao nunca jugó partidos contra Brasil en los doseles?', 'suíca' , 'Lo sentimos, ningún indicio' , 3 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				
				//perguntas espanish nivel dificil
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, '1º Gol de Brasil en las Copas del Mundo fue contra qué equipo?', 'mexico' , 'Lo sentimos, ningún indicio' , 3 , 3 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Quién fue el subcampeón de la Copa Mundial de 2010?', 'holanda' , 'Lo sentimos, ningún indicio' , 3 , 3 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Lo hizo el último gol en la final de la Copa del Mundo 1970', 'pele' , 'Lo sentimos, ningún indicio' , 3 , 2 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Qué selección que ganó el Mundial de 2006?' , ' italia'  , 'Lo sentimos, ningún indicio' , 3 , 3 , 1);";
				dataBase.execSQL(ddlInsere);
				ddlInsere = "INSERT INTO " + TABELA +" VALUES(null, 'Quién ganó el tercer lugar en el Mundial de 2006?', 'alemanha' , 'Lo sentimos, ningún indicio' , 3 , 3 , 1);";
				dataBase.execSQL(ddlInsere);
		
	}

}
