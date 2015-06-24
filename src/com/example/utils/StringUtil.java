package com.example.utils;

/**
 * Strin 相关的工具类
 * @author anonymous
 */
public class StringUtil {
	
	/**
	 * 获取当前系统的换行符
	 * @return
	 */
	public static String getLineSeparator(){
		return System.getProperty("line.separator");
	}
	
	
	/** 
     * Remove occurences of html, defined as any text 
     * between the characters "<" and ">". Optionally  
     * replace HTML tags with a space. 
     * @version 1.0
     * @author
     * @param str 
     * @param addSpace 
     */  
    public static String removeHTML(String str, boolean addSpace) {  
        if(str == null) return "";  
        StringBuffer ret = new StringBuffer(str.length());  
        int start = 0;  
        int beginTag = str.indexOf("<");  
        int endTag = 0;  
        if(beginTag == -1) return str;  
          
        while(beginTag >= start) {  
            if(beginTag > 0) {  
                ret.append(str.substring(start, beginTag));  
                if(addSpace) ret.append(" ");  
            }  
            endTag = str.indexOf(">", beginTag);  
            if(endTag > -1) {  
                start = endTag + 1;  
                beginTag = str.indexOf("<", start);  
            }  
            else {  
                ret.append(str.substring(beginTag));  
                break;  
            }  
        }  
        if(endTag >-1 && endTag + 1 < str.length()) {  
            ret.append(str.substring(endTag + 1));  
        }  
        return removeBlank(ret.toString().trim());
    }  

    
    /** 
     * Remove occurences of html, defined as any text 
     * between the characters "<" and ">". 
     * Replace any HTML tags with a space.  
     * @param str 
     * @return 
     */  
    public static String removeHTML(String str) {  
        return removeHTML(str, true);  
    }  
    
    
    /**
     * 将字符串首字母大写
     * @param content
     * @return
     */
    public static String upperFirstLetter(String content) {
    	if(content!=null&&content.length()>0){
    		String firstLetter = content.substring(0, 1).toUpperCase();
    		String secondLetter = content.substring(1);
    		content = firstLetter + secondLetter;
    	}
    	return content;
    }
    
    
    /**
     * 去除空格
     * @param str
     * @return
     */
    public static String removeBlank(String str) {
    	StringBuffer sb = new StringBuffer("");
    	for(char ch : str.toCharArray()) {
    		if(ch!=' ' && ch!='\n' && ch!='\r') {
    			sb.append(ch);
    		}
    	}
    	return sb.toString().replace("&nbsp;", " ").replace("<br>", " ").replace("<br/>", " ");
    }
    
    
    /**
     * 实体属性对应数据库列名时的格式转换
     * @param propertyName
     * @return
     */
    public static String formatPropertyName(String propertyName) {
    	StringBuffer buf = new StringBuffer("");
    	for(char ch : propertyName.toCharArray()) {
    		if(ch>=65 && ch<=90) {
    			buf.append("_").append((char)(ch+32));
    		} else {
    			buf.append(ch);
    		}
    	}
    	return buf.toString();
    }
}  