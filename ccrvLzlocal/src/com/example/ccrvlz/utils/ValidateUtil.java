package com.example.ccrvlz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ֶ���֤����
 * @author lixinglei
 *
 */
public class ValidateUtil {
	
	/**
	 * �ж��Ƿ�Ϊ��������������
	 * @param str
	 * @return true Or false
	 */
	public static boolean isNumeric(String str){
          Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
          Matcher isNum = pattern.matcher(str);
          if( !isNum.matches() ){
                return false;
          }
          return true;
    }
	
	/**
	 * �ж��Ƿ�Ϊ��ȷ���ʼ���ʽ
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmail(String str){
		if(isEmpty(str))
			return false;
		return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ�Ϸ��ֻ��� 11λ 13 14 15 18��ͷ
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(String str){
		if(isEmpty(str))
			return false;
		return str.matches("^(13|14|15|18)\\d{9}$");
	}
	
	/**
	 * �ж��Ƿ�Ϊ����
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		try{
			Integer.parseInt(str);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
		
	/**
	 * �ж��ַ����Ƿ�Ϊ�ǿ�(����null��"")
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(str == null || "".equals(str))
			return false;
		return true;
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ�ǿ�(����null��"","    ")
	 * @param str
	 * @return
	 */
	public static boolean isNotEmptyIgnoreBlank(String str){
		if(str == null || "".equals(str) || "".equals(str.trim()))
			return false;
		return true;
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��(����null��"")
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str))
			return true;
		return false;
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��(����null��"","    ")
	 * @param str
	 * @return
	 */
	public static boolean isEmptyIgnoreBlank(String str){
		if(str == null || "".equals(str) || "".equals(str.trim()))
			return true;
		return false;
	}
	
	
	//��ֹʵ����
	private ValidateUtil(){} 
}
