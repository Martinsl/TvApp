package com.example.tvapp;

import java.util.HashMap;

import android.app.Activity;

public class Base extends Activity {
	public String baseURL = "http://api-tv-program.herokuapp.com/";
	public String channelJson = "{\"programs\":[{\"time\":\"00:00\",\"name\":\"Barter Kings\","
			+ "\"link\":\"uf=1&action=programa&programa=0000322745&evento=000000536241919&operadora=14&canal=MDO&gds=&hgh=\"},"
			+ "{\"time\":\"00:30\",\"name\":\"Barter Kings2\","
			+ "\"link\":\"uf=1&action=programa&programa=0000322745&evento=000000536241919&operadora=14&canal=MDO&gds=&hgh=\"},"
			+ "{\"time\":\"01:00\",\"name\":\"Eby Tapetes\","
			+ "\"link\":\"uf=1&action=programa&programa=0000322745&evento=000000536241919&operadora=14&canal=MDO&gds=&hgh=\"},"
			+ "{\"time\":\"03:00\",\"name\":\"Barter Kings3\","
			+ "\"link\":\"uf=1&action=programa&programa=0000322745&evento=000000536241919&operadora=14&canal=MDO&gds=&hgh=\"}]}";
	
	public String[] channelAcron = new String[] {
			"MDO","APL","NEW","BSP","BAN","BPO","BBC","BEM","BIT","BMG","CBR","FUT","RUR",
			"CNV","CAR","MNX","CLI","CNE","CNN","CNT","CCE","DWL","DIS","DHD","HEA","DIK",
			"TRV","DTU","DNY","DNH","PHD","EET","ESB","ESP","SPI","EUR","FAS","BRA","GLS",
			"FOX","FHD","FLI","FNE","FSP","CFX","FAS","GLN","GHD","GNT","HAL","HBO","HBE",
			"HFA","HBH","HPL","HPE","HFE","HB2","LIV","SAT","MNG","KID","MXE","MAX","MAP",
			"MPE","MPX","MGM","MTV","MTH","MSW","MSH","NGH","SUP","NBR","NHK","NJR","NIC",
			"OFF","TRA","PLA","135","121","RAI","POA","REC","RCN","RTV","VDA","SCC","NAC",
			"HOT","SHO","SIC","SET","ANX","SPA","SPH","SPE","SPO","SP2","SP3","SCI","TBS",
			"TCM","TCH","TC2","T2H","TC5","TC6","TC3","TC4","T4H","TC1","TC3","TVV","TGC",
			"HIS","TRV","TLH","TNT","TNH","TOC","TRU","TCM","TED","CAM","CNV","CUL","ESC",
			"JUS","RTB","SEN","TV5","TVV","TED","TVE","TV5","USA","VH1","VHD","VHS","VIV",
			"WBT","WOO"	
	};
	
	public static final HashMap<String, String> acronymHash = new HashMap<String, String>(){{
		put("A\u0026E Mundo","MDO");
		put("Animal Planet","APL");
		put("Band News","NEW");
		put("Band Sports","BSP");
		put("Bandeirantes","BAN");
		put("Bandeirantes Sao Paulo","BPO");
		put("BBC World","BBC");
		put("Bem Simples","BEM");
		put("Bloomberg","BIT");
		put("Boomerang","BMG");
		put("Canal Brasil","CBR");
		put("Canal Futura","FUT");
		put("Canal Rural","RUR");
		put("Canção Nova","CNV");
		put("Cartoon Network","CAR");
		put("Cinemax","MNX");
		put("Clima Tempo","CLI");
		put("CNN Espanhol","CNE");
		put("CNN International","CNN");
		put("CNT","CNT");
		put("Comedy Central","CCE");
		put("Deutsche Welle","DWL");
		put("Discovery Channel","DIS");
		put("Discovery HD Theater","DHD");
		put("Discovery Home & Health","HEA");
		put("Discovery Kids","DIK");
		put("Discovery Travel & Living","TRV");
		put("Discovery Turbo","DTU");
		put("Disney Channel","DNY");
		put("Disney HD","DNH");
		put("Disney Jr.","PHD");
		put("E! Entertainment","EET");
		put("ESPN Brasil","ESB");
		put("ESPN International","ESP");
		put("Esporte Interativo","SPI");
		put("EuroChannel","EUR");
		put("Fashion TV","FAS");
		put("FEA - Film & Arts","BRA");
		put("For Man","GLS");
		put("Fox","FOX");
		put("Fox + NatGeo HD","FHD");
		put("Fox Life","FLI");
		put("Fox News","FNE");
		put("Fox Sports","FSP");
		put("FX","CFX");
		put("Glitz","FAS");
		put("Globo News","GLN");
		put("Globosat HD","GHD");
		put("GNT","GNT");
		put("Hallmark","HAL");
		put("HBO","HBO");
		put("HBO e","HBE");
		put("HBO Family","HFA");
		put("HBO HD","HBH");
		put("HBO Plus","HPL");
		put("HBO Plus e","HPE");
		put("HBO Signature","HFE");
		put("HBO 2","HB2");
		put("Investigação Discovery","LIV");
		put("i-SAT","SAT");
		put("Management TV","MNG");
		put("Jetix","KID");
		put("Max","MXE");
		put("Max HD","MAX");
		put("Max Prime","MAP");
		put("Max Prime e","MPE");
		put("Megapix","MPX");
		put("MGM","MGM");
		put("MTV Brasil","MTV");
		put("MTV Hits","MTH");
		put("Multishow","MSW");
		put("Multishow HD","MSH");
		put("Nat Geo Wild HD","NGH");
		put("National Geographic","SUP");
		put("NBR","NBR");
		put("NHK","NHK");
		put("Nick Jr","NJR");
		put("Nickelodeon","NIC");
		put("Off","OFF");
		put("People+Arts","TRA");
		put("Playboy TV","PLA");
		put("Premiere Combate","135");
		put("PR3 Premiere Futebol Clube","121");
		put("RAI (italiano)","RAI");
		put("RBS TV","POA");
		put("Record","REC");
		put("Record News","RCN");
		put("Rede TV","RTV");
		put("Rede Vida","VDA");
		put("SBT","SCC");
		put("Sesc TV","NAC");
		put("Sexy Hot","HOT");
		put("Shoptime","SHO");
		put("SIC Internacional","SIC");
		put("Sony Entertainment Television","SET");
		put("Sony Spin","ANX");
		put("Space","SPA");
		put("Space HD","SPH");
		put("Speed Channel","SPE");
		put("SPORTV","SPO");
		put("SPORTV 2","SP2");
		put("SPORTV 3","SP3");
		put("SyFy","SCI");
		put("TBS muitodivertido","TBS");
		put("TCM","TCM");
		put("Telecine HD","TCH");
		put("Telecine Action","TC2");
		put("Telecine Action HD","T2H");
		put("Telecine Cult","TC5");
		put("Telecine Fun","TC6");
		put("Telecine Light","TC3");
		put("Telecine Pipoca","TC4");
		put("Telecine Pipoca HD","T4H");
		put("Telecine Premium","TC1");
		put("Telecine Touch","TC3");
		put("Terra Viva","TVV");
		put("The Golf Channel","TGC");
		put("The History Channel","HIS");
		put("TLC","TRV");
		put("TLC HD","TLH");
		put("TNT","TNT");
		put("TNT HD","TNH");
		put("Tooncast","TOC");
		put("TruTV","TRU");
		put("Turner Classic Movies","TCM");
		put("TV Brasil","TED");
		put("TV Câmara","CAM");
		put("TV Canção Nova","CNV");
		put("TV Cultura","CUL");
		put("TV Escola","ESC");
		put("TV Justiça","JUS");
		put("TV Rá Tim Bum","RTB");
		put("TV Senado","SEN");
		put("TV5Monde","TV5");
		put("TV Terra Viva","TVV");
		put("TVE Brasil","TED");
		put("TVE Internacional","TVE");
		put("TV5MONDE","TV5");
		put("Universal Channel","USA");
		put("VH1","VH1");
		put("VH1 HD","VHD");
		put("VH1 Mega Hits","VHS");
		put("Viva","VIV");
		put("Warner","WBT");
		put("WooHoo","WOO");
	}};
}