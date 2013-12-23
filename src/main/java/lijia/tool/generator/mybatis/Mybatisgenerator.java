package lijia.tool.generator.mybatis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * a tool to autogenerat mybatis xml file from java POJO objetc
 * @version 1.0
 * @author lijia (tawalisa@163.com)
 *
 */
public class Mybatisgenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		generator(TdProdline.class);
//		generator(TdCatPub.class);
//		generator(TdProdlineAttr.class);
//		generator(TdStoreShowcaseProductHistory.class);
		
		
	}

	private static void generator(Class class1) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
		sb.append("<mapper namespace=\"").append(class1.getName().replace(".po.", ".dao.")).append("DAO\">\n");
		sb.append("  <cache />\n");
		
		StringBuffer sb1 = new StringBuffer();
		sb1.append("package ").append(class1.getPackage().getName().replace(".po.", ".dao.")).append(";\n\n");
		sb1.append("import ").append(class1.getName()).append(";\n\n");
		sb1.append("public interface ").append(class1.getSimpleName()).append("DAO {\n\n");
		
		List<String> fieldlist = new ArrayList<String>();
		for(Field f:class1.getDeclaredFields()){
			fieldlist.add(f.getName());
		}
		
		addMethod(class1,sb,sb1,fieldlist);
		
		updateMethod(class1,sb,sb1,fieldlist);
		deleteMethod(class1,sb,sb1,fieldlist);
		selectMethod(class1,sb,sb1,fieldlist);
		
		sb.append("</mapper>");
		sb1.append("}");
		System.out.println(sb.toString());
		System.out.println(sb1.toString());
	}

	private static void selectMethod(Class class1, StringBuffer sb,
			StringBuffer sb1, List<String> fieldlist) {
//	    <select id="findBkAmdinByLoginName" resultType="com.dhgate.core.po.bk.BkAmdin">
//	       select * from bk_amdin where loginname=#{loginname}
//	  </select>
		sb.append("	  <select id=\"find").append(class1.getSimpleName()).append("By").append(fieldlist.get(0)).append("\" resultType=\"").append(class1.getName()).append("\">\n");
	       sb.append("	       select * from ").append(handlertablename(class1.getSimpleName())).append(" where ").append(listPKParam(fieldlist)).append("\n");
	       sb.append("	  </select>\n"); 
	       
//	       public BkAmdin findBkAdminByUserid(Long userid);
	       sb1.append("public ").append(class1.getSimpleName()).append(" find").append(class1.getSimpleName()).append("By").append(fieldlist.get(0)).append(" (Long ").append(handlefirstbye(fieldlist.get(0))).append(");\n\n");
	}

	private static void deleteMethod(Class class1, StringBuffer sb,
			StringBuffer sb1, List<String> fieldlist) {
//		  <delete id="deleteBkAdmin" parameterType="long">
//	        delete from bk_amdin where userid=#{userid}
//	  </delete>
		sb.append("	  <delete id=\"delete").append(class1.getSimpleName()).append("\" parameterType=\"long\">\n");
	       sb.append("	       delete from ").append(handlertablename(class1.getSimpleName())).append(" where ").append(listPKParam(fieldlist)).append("\n");
	       sb.append("	  </delete>\n"); 
	       
	       
//	       public int deleteBkAdmin(Long userid);
	       
	       
	       sb1.append("public int  delete").append(class1.getSimpleName()).append(" (Long ").append(handlefirstbye(fieldlist.get(0))).append(");\n\n");
	}

	private static void updateMethod(Class class1, StringBuffer sb,
			StringBuffer sb1, List<String> fieldlist) {
		
//		  <update id="updateBkAdmin" parameterType="com.dhgate.core.po.bk.BkAmdin" >
//	       update bk_amdin set userid=#{userid},loginname=#{loginname},password=#{password},phonenum=#{phonenum},mobilenum=#{mobilenum},email=#{email},status=#{status},createtime=#{createtime},createuser=#{createuser},lastmodify=#{lastmodify},updatetimeuser=#{updatetimeuser},deletetime=#{deletetime},deleteuser=#{deleteuser} where userid=#{userid}
//	  </update>
		sb.append("	  <update id=\"update").append(class1.getSimpleName()).append("\" >\n");
	       sb.append("	       update ").append(handlertablename(class1.getSimpleName())).append(" set ").append(listFieldsetlist(fieldlist)).append(" where ").append(listPKParam(fieldlist)).append("\n");
	       sb.append("	  </update>\n"); 
	       
//	       public int updateBkAdmin (BkAmdin bakadmin);
	       
	       sb1.append("public int  update").append(class1.getSimpleName()).append(" (").append(class1.getSimpleName()).append(" ").append(handlefirstbye(class1.getSimpleName())).append(");\n\n");
	}

	private static Object listPKParam(List<String> fieldlist) {
		return fieldlist.get(0)+"=#{"+fieldlist.get(0)+"}";
	}

	private static Object listFieldsetlist(List<String> fieldlist) {
		StringBuffer sb = new StringBuffer();
		for(String s:fieldlist){
			sb.append(s).append("=#{").append(s).append("},");
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}

	private static void addMethod(Class class1, StringBuffer sb, StringBuffer sb1, List<String> fieldlist) {
		// TODO Auto-generated method stub
		// <insert id="addBkAdmin" parameterType="com.dhgate.core.po.bk.BkAmdin"  keyProperty="userid" useGeneratedKeys="true">
//	       INSERT INTO bk_amdin(userid,loginname,password,phonenum,mobilenum,email,status,createtime,createuser,lastmodify,updatetimeuser,deletetime,deleteuser)VALUES(#{userid},#{loginname},#{password},#{phonenum},#{mobilenum},#{email},#{status},#{createtime},#{createuser},#{lastmodify},#{updatetimeuser},#{deletetime},#{deleteuser})
//	  </insert>
	       
	       sb.append("	  <insert id=\"add").append(class1.getSimpleName()).append("\"  keyProperty=\"").append(fieldlist.get(0)).append("\" useGeneratedKeys=\"true\">\n");
	       sb.append("	       INSERT INTO ").append(handlertablename(class1.getSimpleName())).append("(").append(listFieldlist(fieldlist)).append(")VALUES(").append(listParam(fieldlist)).append(")\n");
	       sb.append("	  </insert>\n"); 
	      
	       
//	       public int  addBkAdmin (BkAmdin bakadmin);
	       
	       sb1.append("public int  add").append(class1.getSimpleName()).append(" (").append(class1.getSimpleName()).append(" ").append(handlefirstbye(class1.getSimpleName())).append(");\n\n");
	}

	private static Object handlertablename(String name) {
		StringBuffer sb = new StringBuffer();
		boolean isf = true;
				for(char c:name.toCharArray()){
					if(Character.isUpperCase(c)){
						if(isf){
							sb.append(Character.toLowerCase(c));
							isf = false;
						}else{
							sb.append("_").append(Character.toLowerCase(c));
						}
						
					}
					else{
						sb.append(c);
					}
				}
		return sb.toString();
	}

	private static Object handlefirstbye(String name) {
		StringBuffer sb = new StringBuffer();
		boolean isf = true;
				for(char c:name.toCharArray()){
					if(isf){
						sb.append(Character.toLowerCase(c));
						isf = true;
					}else{
						sb.append(c);
					}
				}
		return sb.toString();
	}

	private static String listParam(List<String> fieldlist) {
		StringBuffer sb = new StringBuffer();
		for(String s:fieldlist){
			sb.append("#{").append(s).append("},");
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}

	private static String listFieldlist(List<String> fieldlist) {
		StringBuffer sb = new StringBuffer();
		for(String s:fieldlist){
			sb.append(s).append(",");
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
}
