package com.example.tvapp;

import java.util.HashMap;

import android.app.Activity;

public class Base extends Activity {
	public String baseURL = "http://api-tv-program.herokuapp.com/";
	public String channelJson = "{\"programs\":[{\"time\":\"00:00\",\"name\":\"Barter Kings\","
			+ "\"link\":\"uf=1&action=programa&programa=0000322745&evento=000000536241919&operadora=14&canal=MDO&gds=&hgh=\"},"
			+ "{\"time\":\"00:30\",\"name\":\"Barter Kings\","
			+ "\"link\":\"uf=1&action=programa&programa=0000322745&evento=000000536241919&operadora=14&canal=MDO&gds=&hgh=\"},"
			+ "{\"time\":\"01:00\",\"name\":\"Eby Tapetes\","
			+ "\"link\":\"uf=1&action=programa&programa=0000322745&evento=000000536241919&operadora=14&canal=MDO&gds=&hgh=\"},"
			+ "{\"time\":\"03:00\",\"name\":\"Barter Kings\","
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
	
	public String[] channelNames = new String[] {
			"A&E Mundo","Animal Planet","Band News","Band Sports","Bandeirantes",
			"Bandeirantes Sao Paulo","BBC World","Bem Simples","Bloomberg","Boomerang",
			"Canal Brasil","Canal Futura","Canal Rural","Canção Nova","Cartoon Network",
			"Cinemax","Clima Tempo","CNN Espanhol","CNN International","CNT","Comedy Central",
			"Deutsche Welle","Discovery Channel","Discovery HD Theater","Discovery Home & Health",
			"Discovery Kids","Discovery Travel & Living","Discovery Turbo","Disney Channel",
			"Disney HD","Disney Jr.","E! Entertainment","ESPN Brasil","ESPN International",
			"Esporte Interativo","EuroChannel","Fashion TV","FEA - Film & Arts","For Man",
			"Fox","Fox + NatGeo HD","Fox Life","Fox News","Fox Sports","FX","Glitz","Globo News",
			"Globosat HD","GNT","Hallmark","HBO","HBO e","HBO Family","HBO HD","HBO Plus",
			"HBO Plus e","HBO Signature","HBO 2","Investigação Discovery","i-SAT","Management TV",
			"Jetix","Max","Max HD","Max Prime","Max Prime e","Megapix","MGM","MTV Brasil",
			"MTV Hits","Multishow","Multishow HD","Nat Geo Wild HD","National Geographic",
			"NBR","NHK","Nick Jr","Nickelodeon","Off","People+Arts","Playboy TV",
			"Premiere Combate","PR3 Premiere Futebol Clube","RAI (italiano)","RBS TV",
			"Record","Record News","Rede TV","Rede Vida","SBT","Sesc TV","Sexy Hot","Shoptime",
			"SIC Internacional","Sony Entertainment Television","Sony Spin","Space","Space HD",
			"Speed Channel","SPORTV","SPORTV 2","SPORTV 3","SyFy","TBS muitodivertido","TCM",
			"Telecine HD","Telecine Action","Telecine Action HD","Telecine Cult","Telecine Fun",
			"Telecine Light","Telecine Pipoca","Telecine Pipoca HD","Telecine Premium",
			"Telecine Touch","Terra Viva","The Golf Channel","The History Channel","TLC",
			"TLC HD","TNT","TNT HD","Tooncast","TruTV","Turner Classic Movies","TV Brasil",
			"TV Câmara","TV Canção Nova","TV Cultura","TV Escola","TV Justiça","TV Rá Tim Bum",
			"TV Senado","TV5Monde","TV Terra Viva","TVE Brasil","TVE Internacional","TV5MONDE",
			"Universal Channel","VH1","VH1 HD","VH1 Mega Hits","Viva","Warner","WooHoo"
	};
	
	public static final HashMap<String, String> acronymHash = new HashMap<String, String>(){{
		put("MDO","A&E Mundo");
		put("APL","Animal Planet");
		put("NEW","Band News");
		put("BSP","Band Sports");
		put("BAN","Bandeirantes");
		put("BPO","Bandeirantes Sao Paulo");
		put("BBC","BBC World");
		put("BEM","Bem Simples");
		put("BIT","Bloomberg");
		put("BMG","Boomerang");
		put("CBR","Canal Brasil");
		put("FUT","Canal Futura");
		put("RUR","Canal Rural");
		put("CNV","Canção Nova");
		put("CAR","Cartoon Network");
		put("MNX","Cinemax");
		put("CLI","Clima Tempo");
		put("CNE","CNN Espanhol");
		put("CNN","CNN International");
		put("CNT","CNT");
		put("CCE","Comedy Central");
		put("DWL","Deutsche Welle");
		put("DIS","Discovery Channel");
		put("DHD","Discovery HD Theater");
		put("HEA","Discovery Home & Health");
		put("DIK","Discovery Kids");
		put("TRV","Discovery Travel & Living");
		put("DTU","Discovery Turbo");
		put("DNY","Disney Channel");
		put("DNH","Disney HD");
		put("PHD","Disney Jr.");
		put("EET","E! Entertainment");
		put("ESB","ESPN Brasil");
		put("ESP","ESPN International");
		put("SPI","Esporte Interativo");
		put("EUR","EuroChannel");
		put("FAS","Fashion TV");
		put("BRA","FEA - Film & Arts");
		put("GLS","For Man");
		put("FOX","Fox");
		put("FHD","Fox + NatGeo HD");
		put("FLI","Fox Life");
		put("FNE","Fox News");
		put("FSP","Fox Sports");
		put("CFX","FX");
		put("FAS","Glitz");
		put("GLN","Globo News");
		put("GHD","Globosat HD");
		put("GNT","GNT");
		put("HAL","Hallmark");
		put("HBO","HBO");
		put("HBE","HBO e");
		put("HFA","HBO Family");
		put("HBH","HBO HD");
		put("HPL","HBO Plus");
		put("HPE","HBO Plus e");
		put("HFE","HBO Signature");
		put("HB2","HBO 2");
		put("LIV","Investigação Discovery");
		put("SAT","i-SAT");
		put("MNG","Management TV");
		put("KID","Jetix");
		put("MXE","Max");
		put("MAX","Max HD");
		put("MAP","Max Prime");
		put("MPE","Max Prime e");
		put("MPX","Megapix");
		put("MGM","MGM");
		put("MTV","MTV Brasil");
		put("MTH","MTV Hits");
		put("MSW","Multishow");
		put("MSH","Multishow HD");
		put("NGH","Nat Geo Wild HD");
		put("SUP","National Geographic");
		put("NBR","NBR");
		put("NHK","NHK");
		put("NJR","Nick Jr");
		put("NIC","Nickelodeon");
		put("OFF","Off");
		put("TRA","People+Arts");
		put("PLA","Playboy TV");
		put("135","Premiere Combate");
		put("121","PR3 Premiere Futebol Clube");
		put("RAI","RAI (italiano)");
		put("POA","RBS TV");
		put("REC","Record");
		put("RCN","Record News");
		put("RTV","Rede TV");
		put("VDA","Rede Vida");
		put("SCC","SBT");
		put("NAC","Sesc TV");
		put("HOT","Sexy Hot");
		put("SHO","Shoptime");
		put("SIC","SIC Internacional");
		put("SET","Sony Entertainment Television");
		put("ANX","Sony Spin");
		put("SPA","Space");
		put("SPH","Space HD");
		put("SPE","Speed Channel");
		put("SPO","SPORTV");
		put("SP2","SPORTV 2");
		put("SP3","SPORTV 3");
		put("SCI","SyFy");
		put("TBS","TBS muitodivertido");
		put("TCM","TCM");
		put("TCH","Telecine HD");
		put("TC2","Telecine Action");
		put("T2H","Telecine Action HD");
		put("TC5","Telecine Cult");
		put("TC6","Telecine Fun");
		put("TC3","Telecine Light");
		put("TC4","Telecine Pipoca");
		put("T4H","Telecine Pipoca HD");
		put("TC1","Telecine Premium");
		put("TC3","Telecine Touch");
		put("TVV","Terra Viva");
		put("TGC","The Golf Channel");
		put("HIS","The History Channel");
		put("TRV","TLC");
		put("TLH","TLC HD");
		put("TNT","TNT");
		put("TNH","TNT HD");
		put("TOC","Tooncast");
		put("TRU","TruTV");
		put("TCM","Turner Classic Movies");
		put("TED","TV Brasil");
		put("CAM","TV Câmara");
		put("CNV","TV Canção Nova");
		put("CUL","TV Cultura");
		put("ESC","TV Escola");
		put("JUS","TV Justiça");
		put("RTB","TV Rá Tim Bum");
		put("SEN","TV Senado");
		put("TV5","TV5Monde");
		put("TVV","TV Terra Viva");
		put("TED","TVE Brasil");
		put("TVE","TVE Internacional");
		put("TV5","TV5MONDE");
		put("USA","Universal Channel");
		put("VH1","VH1");
		put("VHD","VH1 HD");
		put("VHS","VH1 Mega Hits");
		put("VIV","Viva");
		put("WBT","Warner");
		put("WOO","WooHoo");
	}};
}