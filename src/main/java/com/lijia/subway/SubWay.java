package com.lijia.subway;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SubWay {
	
	static String[] line1 = {"苹果园","古城","八角游乐园","八宝山","玉泉路","五棵松","万寿路","公主坟","军事博物馆","木樨地","南礼士路","复兴门","西单","天安门西","天安门东","王府井","东单","建国门","永安里","国贸","大望路","四惠","四惠东"};
	static String[] line2 = {"西直门","积水潭","鼓楼大街","安定门","雍和宫","东直门","东四十条","朝阳门","建国门","北京站","崇文门","前门","和平门","宣武门","长椿街","复兴门","阜成门","车公庄"};
	static String[] line4 = {"安河桥北","北宫门","西苑","圆明园","北京大学东门","中关村","海淀黄庄","人民大学","魏公村","国家图书馆","动物园","西直门","新街口","平安里","西四","灵境胡同","西单","宣武门","菜市口","陶然亭","北京南站","马家堡","角门西","公益西桥","新宫","西红门","高米店北","高米店南","枣园","清源路","黄村西大街","黄村火车站","义和庄","生物医药基地","天宫院"};
	static String[] line5 ={"宋家庄","刘家窑","蒲黄榆","天坛东门","磁器口","崇文门","东单","灯市口","东四","张自忠路","北新桥","雍和宫","和平里北街","和平西桥","惠新西街南口","惠新西街北口","大屯路东","北苑路北","立水桥南","立水桥","天通苑南","天通苑","天通苑北"};
	static String[] line6 = {"海淀五路居","慈寿寺","花园桥","白石桥南","车公庄西","车公庄","平安里","北海北","南锣鼓巷","东四","朝阳门","东大桥","呼家楼","金台路","十里堡","青年路","褡裢坡","黄渠","常营","草房","物资学院路","通州北关","通运门","北运河西","北运河东","郝家府","东夏园","潞城"};
	static String[] line7 = {"北京西站","湾子","达官营","广安门内","菜市口","虎坊桥","珠市口","桥湾","磁器口","广渠门内","广渠门外","九龙山","大郊亭","百子湾","化工","南楼梓庄","欢乐谷景区","垡头","双合","焦化厂"};
	static String[] line8 = {"朱辛庄","育知路","平西府","回龙观东大街","霍营","育新","西小口","永泰庄","林萃桥","森林公园南门","奥林匹克公园","奥体中心","北土城","安华桥","鼓楼大街","什刹海","南锣鼓巷"};
	static String[] line9 = {"郭公庄","丰台科技园","科怡路","丰台南路","丰台东大街","七里庄","六里桥","六里桥东","北京西站","军事博物馆","白堆子","白石桥南","国家图书馆"};
	static String[] line10 = {"巴沟","苏州街","海淀黄庄","知春里","知春路","西土城","牡丹园","健德门","北土城","安贞门","惠新西街南口","芍药居","太阳宫","三元桥","亮马桥","农业展览馆","团结湖","呼家楼","金台夕照","国贸","双井","劲松","潘家园","十里河","分钟寺","成寿寺","宋家庄","石榴庄","大红门","角门东","角门西","草桥","纪家庙","首经贸","丰台站","泥洼","西局","六里桥","莲花桥","公主坟","西钓鱼台","慈寿寺","车道沟","长春桥","火器营"};
	static String[] line13 = {"西直门","大钟寺","知春路","五道口","上地","西二旗","龙泽","回龙观","霍营","立水桥","北苑","望京西","芍药居","光熙门","柳芳","东直门"};
	static String[] line14east = {"张郭庄","园博园","大瓦窑","郭庄子","大井","七里庄","西局"};
	static String[] line14west = {"金台路","朝阳公园","枣营","东风北桥","将台","高家园","望京南","阜通","望京","东湖渠","来广营","善各庄"};
	static String[] line15 = {"清华东路西口","六道口","北沙滩","奥林匹克公园","安立路","关庄","望京西","望京","崔各庄","马泉营","孙河","国展","花梨坎","后沙峪","南法信","石门","顺义","俸伯"};
	static String[] lineBaTong = {"四惠","四惠东","高碑店","传媒大学","双桥","管庄","八里桥","通州北苑","果园","九棵树","梨园","临河里","土桥"};
	static String[] lineChangPing = {"南邵","沙河高教园","沙河","巩华城","朱辛庄","生命科学园","西二旗"};
	static String[] lineYiZhuang = {"宋家庄","肖村","小红门","旧宫","亦庄桥","亦庄文化园","万源街","荣京东街","荣昌东街","同济南路","经海路","次渠南","次渠"};
	static String[] lineFangshan = {"郭公庄","大葆台","稻田","长阳","篱笆房","广阳城","良乡大学城北","良乡大学城","良乡大学城西","良乡南关","苏庄"};
	
	static String[][] edges = {{"苹果园","古城","2.606"},{"古城","八角游乐园","1.921"},{"八角游乐园","八宝山","1.953"},{"八宝山","玉泉路","1.479"},{"玉泉路","五棵松","1.81"},{"五棵松","万寿路","1.778"},{"万寿路","公主坟","1.313"},{"公主坟","军事博物馆","1.172"},{"军事博物馆","木樨地","1.166"},{"木樨地","南礼士路","1.291"},{"南礼士路","复兴门","0.424"},{"复兴门","西单","1.59"},{"西单","天安门西","1.217"},{"天安门西","天安门东","0.925"},{"天安门东","王府井","0.852"},{"王府井","东单","0.774"},{"东单","建国门","1.23"},{"建国门","永安里","1.377"},{"永安里","国贸","0.79"},{"国贸","大望路","1.385"},{"大望路","四惠","1.673"},{"四惠","四惠东","1.714"},{"西直门","积水潭","1.899"},{"积水潭","鼓楼大街","1.766"},{"鼓楼大街","安定门","1.237"},{"安定门","雍和宫","0.794"},{"雍和宫","东直门","2.228"},{"东直门","东四十条","0.824"},{"东四十条","朝阳门","1.027"},{"朝阳门","建国门","1.763"},{"建国门","北京站","0.945"},{"北京站","崇文门","1.023"},{"崇文门","前门","1.634"},{"前门","和平门","1.171"},{"和平门","宣武门","0.851"},{"宣武门","长椿街","0.929"},{"长椿街","复兴门","1.234"},{"复兴门","阜成门","1.832"},{"阜成门","车公庄","0.96"},{"车公庄","西直门","0.909"},{"安河桥北","北宫门","1.363"},{"北宫门","西苑","1.251"},{"西苑","圆明园","1.672"},{"圆明园","北京大学东门","1.295"},{"北京大学东门","中关村","0.887"},{"中关村","海淀黄庄","0.9"},{"海淀黄庄","人民大学","1.063"},{"人民大学","魏公村","1.051"},{"魏公村","国家图书馆","1.658"},{"国家图书馆","动物园","1.517"},{"动物园","西直门","1.441"},{"西直门","新街口","1.025"},{"新街口","平安里","1.1"},{"平安里","西四","1.1"},{"西四","灵境胡同","0.869"},{"灵境胡同","西单","1.011"},{"西单","宣武门","0.815"},{"宣武门","菜市口","1.152"},{"菜市口","陶然亭","1.2"},{"陶然亭","北京南站","1.643"},{"北京南站","马家堡","1.48"},{"马家堡","角门西","0.827"},{"角门西","公益西桥","0.989"},{"宋家庄","刘家窑","1.67"},{"刘家窑","蒲黄榆","0.905"},{"蒲黄榆","天坛东门","1.9"},{"天坛东门","磁器口","1.183"},{"磁器口","崇文门","0.876"},{"崇文门","东单","0.821"},{"东单","灯市口","0.945"},{"灯市口","东四","0.848"},{"东四","张自忠路","1.016"},{"张自忠路","北新桥","0.791"},{"北新桥","雍和宫","0.866"},{"雍和宫","和平里北街","1.151"},{"和平里北街","和平西桥","1.059"},{"和平西桥","惠新西街南口","1.025"},{"惠新西街南口","惠新西街北口","1.122"},{"惠新西街北口","大屯路东","1.838"},{"大屯路东","北苑路北","3"},{"北苑路北","立水桥南","1.286"},{"立水桥南","立水桥","1.305"},{"立水桥","天通苑南","1.544"},{"天通苑南","天通苑","0.965"},{"天通苑","天通苑北","0.939"},{"海淀五路居","慈寿寺","1.508"},{"慈寿寺","花园桥","1.431"},{"花园桥","白石桥南","1.166"},{"白石桥南","车公庄西","1.664"},{"车公庄西","车公庄","0.887"},{"车公庄","平安里","1.443"},{"平安里","北海北","1.321"},{"北海北","南锣鼓巷","1.349"},{"南锣鼓巷","东四","1.937"},{"东四","朝阳门","1.399"},{"朝阳门","东大桥","1.668"},{"东大桥","呼家楼","0.845"},{"呼家楼","金台路","1.45"},{"金台路","十里堡","2.036"},{"十里堡","青年路","1.282"},{"青年路","褡裢坡","3.999"},{"褡裢坡","黄渠","1.238"},{"黄渠","常营","1.854"},{"常营","草房","1.405"},{"草房","物资学院路","2.115"},{"物资学院路","通州北关","2.557"},{"通州北关","通运门","1.468"},{"通运门","北运河西","1.543"},{"北运河西","北运河东","1.599"},{"北运河东","郝家府","0.929"},{"郝家府","东夏园","1.346"},{"东夏园","潞城","1.194"},{"北京西站","湾子","0.935"},{"湾子","达官营","0.734"},{"达官营","广安门内","1.874"},{"广安门内","菜市口","1.374"},{"菜市口","虎坊桥","0.885"},{"虎坊桥","珠市口","1.205"},{"珠市口","桥湾","0.869"},{"桥湾","磁器口","1.016"},{"磁器口","广渠门内","1.138"},{"广渠门内","广渠门外","1.332"},{"广渠门外","九龙山","2.552"},{"九龙山","大郊亭","0.781"},{"大郊亭","百子湾","0.865"},{"百子湾","化工","0.903"},{"化工","南楼梓庄","1.464"},{"南楼梓庄","欢乐谷景区","0.906"},{"欢乐谷景区","垡头","1.679"},{"垡头","双合","1.304"},{"双合","焦化厂","1.021"},{"朱辛庄","育知路","2.318"},{"育知路","平西府","1.985"},{"平西府","回龙观东大街","2.056"},{"回龙观东大街","霍营","1.114"},{"霍营","育新","1.894"},{"育新","西小口","1.543"},{"西小口","永泰庄","1.041"},{"永泰庄","林萃桥","2.553"},{"林萃桥","森林公园南门","2.555"},{"森林公园南门","奥林匹克公园","1.016"},{"奥林匹克公园","奥体中心","1.667"},{"奥体中心","北土城","0.9"},{"北土城","安华桥","1.018"},{"鼓楼大街","安华桥","2.357"},{"鼓楼大街","什刹海","1.188"},{"什刹海","南锣鼓巷","0.902"},{"郭公庄","丰台科技园","1.347"},{"丰台科技园","科怡路","0.788"},{"科怡路","丰台南路","0.98"},{"丰台南路","丰台东大街","1.585"},{"丰台东大街","七里庄","1.325"},{"七里庄","六里桥","1.778"},{"六里桥","六里桥东","1.309"},{"六里桥东","北京西站","1.17"},{"北京西站","军事博物馆","1.398"},{"军事博物馆","白堆子","1.912"},{"白堆子","白石桥南","0.943"},{"白石桥南","国家图书馆","1.096"},{"巴沟","苏州街","1.11"},{"苏州街","海淀黄庄","0.95"},{"海淀黄庄","知春里","0.975"},{"知春里","知春路","1.058"},{"知春路","西土城","1.101"},{"西土城","牡丹园","1.33"},{"牡丹园","健德门","0.973"},{"健德门","北土城","1.1"},{"北土城","安贞门","1.02"},{"安贞门","惠新西街南口","0.982"},{"惠新西街南口","芍药居","1.712"},{"芍药居","太阳宫","1.003"},{"太阳宫","三元桥","1.759"},{"三元桥","亮马桥","1.506"},{"亮马桥","农业展览馆","0.914"},{"农业展览馆","团结湖","0.853"},{"团结湖","呼家楼","1.149"},{"呼家楼","金台夕照","0.734"},{"金台夕照","国贸","0.835"},{"国贸","双井","1.759"},{"双井","劲松","1.006"},{"劲松","潘家园","1.021"},{"潘家园","十里河","1.097"},{"十里河","分钟寺","1.804"},{"分钟寺","成寿寺","1.058"},{"成寿寺","宋家庄","1.677"},{"宋家庄","石榴庄","1.269"},{"石榴庄","大红门","1.244"},{"大红门","角门东","1.13"},{"角门东","角门西","1.254"},{"角门西","草桥","1.688"},{"草桥","纪家庙","1.547"},{"纪家庙","首经贸","1.143"},{"首经贸","丰台站","1.717"},{"丰台站","泥洼","0.954"},{"泥洼","西局","0.749"},{"西局","六里桥","1.584"},{"六里桥","莲花桥","2.392"},{"莲花桥","公主坟","1.016"},{"公主坟","西钓鱼台","2.386"},{"西钓鱼台","慈寿寺","1.214"},{"慈寿寺","车道沟","1.59"},{"车道沟","长春桥","1.205"},{"长春桥","火器营","0.961"},{"火器营","巴沟","1.495"},{"西直门","大钟寺","2.839"},{"大钟寺","知春路","1.206"},{"知春路","五道口","1.829"},{"五道口","上地","4.866"},{"上地","西二旗","2.538"},{"西二旗","龙泽","3.623"},{"龙泽","回龙观","1.423"},{"回龙观","霍营","2.11"},{"霍营","立水桥","4.785"},{"立水桥","北苑","2.272"},{"北苑","望京西","6.72"},{"望京西","芍药居","2.152"},{"芍药居","光熙门","1.11"},{"光熙门","柳芳","1.135"},{"柳芳","东直门","1.769"},{"张郭庄","园博园","1.345"},{"园博园","大瓦窑","4.073"},{"大瓦窑","郭庄子","1.236"},{"郭庄子","大井","2.044"},{"大井","七里庄","1.579"},{"七里庄","西局","0.845"},{"金台路","朝阳公园","1.085"},{"朝阳公园","枣营","1.221"},{"枣营","东风北桥","2.173"},{"东风北桥","将台","1.6"},{"将台","高家园","1.171"},{"高家园","望京南","0.676"},{"望京南","阜通","1.168"},{"阜通","望京","0.903"},{"望京","东湖渠","1.283"},{"东湖渠","来广营","1.1"},{"来广营","善各庄","1.364"},{"清华东路西口","六道口","1.144"},{"六道口","北沙滩","1.337"},{"北沙滩","奥林匹克公园","1.999"},{"奥林匹克公园","安立路","1.368"},{"安立路","关庄","2.025"},{"关庄","望京西","2.071"},{"望京西","望京","1.758"},{"望京","崔各庄","3.947"},{"崔各庄","马泉营","2.008"},{"马泉营","孙河","3.309"},{"孙河","国展","3.386"},{"国展","花梨坎","1.615"},{"花梨坎","后沙峪","3.354"},{"后沙峪","南法信","4.576"},{"南法信","石门","2.712"},{"石门","顺义","1.331"},{"顺义","俸伯","2.441"},{"四惠","四惠东","1.715"},{"四惠东","高碑店","1.375"},{"高碑店","传媒大学","2.002"},{"传媒大学","双桥","1.894"},{"双桥","管庄","1.912"},{"管庄","八里桥","1.763"},{"八里桥","通州北苑","1.7"},{"通州北苑","果园","1.465"},{"果园","九棵树","0.99"},{"九棵树","梨园","1.225"},{"梨园","临河里","1.257"},{"临河里","土桥","0.776"},{"南邵","沙河高教园","5.357"},{"沙河高教园","沙河","1.964"},{"沙河","巩华城","2.025"},{"巩华城","朱辛庄","3.799"},{"朱辛庄","生命科学园","2.367"},{"生命科学园","西二旗","5.44"},{"宋家庄","肖村","2.631"},{"肖村","小红门","1.275"},{"小红门","旧宫","2.366"},{"旧宫","亦庄桥","1.982"},{"亦庄桥","亦庄文化园","0.993"},{"亦庄文化园","万源街","1.728"},{"万源街","荣京东街","1.09"},{"荣京东街","荣昌东街","1.355"},{"荣昌东街","同济南路","2.337"},{"同济南路","经海路","2.301"},{"经海路","次渠南","2.055"},{"次渠南","次渠","1.281"},{"郭公庄","大葆台","1.405"},{"大葆台","稻田","6.466"},{"稻田","长阳","4.041"},{"长阳","篱笆房","2.15"},{"篱笆房","广阳城","1.474"},{"广阳城","良乡大学城北","2.003"},{"良乡大学城北","良乡大学城","1.188"},{"良乡大学城","良乡大学城西","1.738"},{"良乡大学城西","良乡南关","1.332"},{"良乡南关","苏庄","1.33"},{"东直门","三元桥","3.022"},{"三元桥","T3航站楼","18.322"},{"T3航站楼","T2航站楼","7.243"},{"公益西桥","新宫","2.798"},{"新宫","西红门","5.102"},{"西红门","高米店北","1.81"},{"高米店北","高米店南","1.128"},{"高米店南","枣园","1.096"},{"枣园","清源路","1.2"},{"清源路","黄村西大街","1.214"},{"黄村西大街","黄村火车站","0.987"},{"黄村火车站","义和庄","2.035"},{"义和庄","生物医药基地","2.918"},{"生物医药基地","天宫院","1.811"}};

	static java.util.LinkedHashMap<String, String[]> all = new LinkedHashMap<String, String[]>();
	static{
		all.put("一号线", line1);
		all.put("二号线", line2);
		all.put("四号线", line4);
		all.put("五号线", line5);
		all.put("六号线", line6);
		all.put("七号线", line7);
		all.put("八号线", line8);
		all.put("九号线", line9);
		all.put("十号线", line10);
		all.put("十三号线", line13);
		all.put("十四号线西段", line14east);
		all.put("十四号线东段", line14west);
		all.put("十五号线", line15);
		all.put("八通线", lineBaTong);
		all.put("昌平线", lineChangPing);
		all.put("亦庄线", lineYiZhuang);
		all.put("房山线", lineFangshan);
		
		
	}
	public static SubWayResult getleastPath(String sourcename, String tergatname){
		Map<String, Vertex> stationMap = new HashMap<String, Vertex>();
		for(String[] stations:all.values()){
			for(String stationName: stations){
				addVertexToMap(stationName,stationMap);
			}
		}
		for(String[] strings:edges){
			String source = strings[0];
			String target = strings[1];
			double weight = Double.parseDouble(strings[2]);
			Vertex sVertex = stationMap.get(source);
			Vertex tVertex = stationMap.get(target);
			if(tVertex== null){
				System.out.println(strings);
				System.out.println(source);
				System.out.println(target);
				System.out.println(weight);
			}
			if("T3航站楼".equals(target) || "T2航站楼".equals(target)){
				continue;
			}
			sVertex.adjacencies.add(new Edge(tVertex, weight));
			tVertex.adjacencies.add(new Edge(sVertex, weight));
		}
		
		Vertex source = stationMap.get(sourcename); 
		Vertex tergat = stationMap.get(tergatname); 
		Dijkstra di = new Dijkstra();
		di.computePaths(source);
		List<String> path = di.getShortestPathTo(tergat);
		System.out.println("Path: " + path);
		System.out.println("Distance to : " + tergat.minDistance);
		
		return new SubWayResult(path,tergat.minDistance);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SubWayResult re = getleastPath("四惠","霍营");
		getleastPath("回龙观东大街","农业展览馆");
//		Map<String, Vertex> stationMap = new HashMap<String, Vertex>();
//		for(String[] stations:all.values()){
//			for(String stationName: stations){
//				addVertexToMap(stationName,stationMap);
//			}
//		}
//		for(String[] strings:edges){
//			String source = strings[0];
//			String target = strings[1];
//			double weight = Double.parseDouble(strings[2]);
//			Vertex sVertex = stationMap.get(source);
//			Vertex tVertex = stationMap.get(target);
//			if(tVertex== null){
//				System.out.println(strings);
//				System.out.println(source);
//				System.out.println(target);
//				System.out.println(weight);
//			}
//			if("T3航站楼".equals(target) || "T2航站楼".equals(target)){
//				continue;
//			}
//			sVertex.adjacencies.add(new Edge(tVertex, weight));
//			tVertex.adjacencies.add(new Edge(sVertex, weight));
//		}
//		
//		Vertex source = stationMap.get("回龙观东大街"); 
//		Vertex tergat = stationMap.get("农业展览馆"); 
//		Dijkstra di = new Dijkstra();
//		di.computePaths(source);
//		List<Vertex> path = di.getShortestPathTo(tergat);
//		System.out.println("Path: " + path);
//		System.out.println("Distance to : " + tergat.minDistance);
//		stationMap.clear();
//		for(String[] stations:all.values()){
//			for(String stationName: stations){
//				addVertexToMap(stationName,stationMap);
//			}
//		}
//		for(String[] strings:edges){
//			String source1 = strings[0];
//			String target1 = strings[1];
//			double weight = Double.parseDouble(strings[2]);
//			Vertex sVertex = stationMap.get(source1);
//			Vertex tVertex = stationMap.get(target1);
//			if(tVertex== null){
//				System.out.println(strings);
//				System.out.println(source1);
//				System.out.println(target1);
//				System.out.println(weight);
//			}
//			if("T3航站楼".equals(target1) || "T2航站楼".equals(target1)){
//				continue;
//			}
//			sVertex.adjacencies.add(new Edge(tVertex, weight));
//			tVertex.adjacencies.add(new Edge(sVertex, weight));
//		}
//		source = stationMap.get("四惠"); 
//		tergat = stationMap.get("霍营"); 
//		di = new Dijkstra();
//		di.computePaths(source);
//		path = di.getShortestPathTo(tergat);
//		System.out.println("Path: " + path);
//		System.out.println("Distance to : " + tergat.minDistance);
	}
	private static void addVertexToMap(String key,
			Map<String, Vertex> map) {
		if(!map.containsKey(key)){
			Vertex v = new Vertex(key);
			map.put(key, v);
		}
		
	}
}
