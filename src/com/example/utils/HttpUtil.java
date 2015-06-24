/**
 * 软件著作权：学科网
 * 系统名称：学易汇
 */
package com.example.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * url相关处理工具类
 * 创建日期：2015-01-07
 * @version $Revision: 1.0 $
 * @author 
 */
public class HttpUtil {

	private static Logger logger = Logger.getLogger(HttpUtil.class);
	
    /**
     * 文件静态化方法(post请求)
     * <br>修改历史：
     * <br>修改日期  修改者 BUG小功能修改申请单号
     * <br>
     * 
     * @param url http地址
     * @param fileDir 静态文件所在目录
     * @param fileName 静态文件名
     * @param charcode url字符集
     * @param charcode2 保存文件的字符集
     * @throws MalformedURLException
     * @throws IOException
     * @throws FileNotFoundException
     */
	public static void createStaticPages(String url, String fileDir,String fileName,String charcode,String charcode2) 
           throws MalformedURLException, IOException, FileNotFoundException{        
        FileUtil.writeStoFile(HttpUtil.getHTML(url,"",charcode), fileDir, fileName,charcode2);        
    }
	
	
	/**
     * 文件静态化方法(get请求)
     * <br>修改历史：
     * <br>修改日期  修改者 BUG小功能修改申请单号
     * <br>
     * 
     * @param url http地址
     * @param fileDir 静态文件所在目录
     * @param fileName 静态文件名
     * @param charcode url字符集
     * @param charcode2 保存文件的字符集
     * @throws MalformedURLException
     * @throws IOException
     * @throws FileNotFoundException
     */
	public static void createStaticPagesByGet(String url, String fileDir,String fileName,String charcode,String charcode2) 
			throws MalformedURLException, IOException, FileNotFoundException{        
		FileUtil.writeStoFile(HttpUtil.getHTMLByGet(url,charcode), fileDir, fileName,charcode2);        
	}
    
    /**
     * 
     * 山寨文件静态化方法2
     * <br>修改历史：
     * <br>修改日期  修改者 BUG小功能修改申请单号
     * <br>
     * 
     * @param staticParam 静态化常量文件(StaticHtmlConstant)中定义的变量名
     * @param filenameReplaces filename中的需要将<#urlcanshu#>替换成的内容
     * @param urlReplaces url中的需要将<#urlcanshu#>替换成的内容
     * @param pathReplaces path中的需要将<#pathcanshu#>替换成的内容
     * @throws MalformedURLException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void createStaticPages(String[] staticParam,String filenameReplaces,String urlReplaces,String pathReplaces){
        //读取url地址中的内容
    	String url = staticParam[1].replaceAll("<#urlcanshu#>", urlReplaces);
    	String content = HttpUtil.getHTML(url,"","UTF-8");    	
    	content=content.replaceAll("<!@@#", "<!--#");
        FileUtil.writeStoFile(content, staticParam[2].replaceAll("<#pathcanshu#>", pathReplaces), staticParam[0].replaceAll("<#filenamecanshu#>",filenameReplaces), "UTF-8");
        
    }
    
    /**
     *2011-07-26  张晓斌   #10629: 制作旅游频道投票活动 
     * 山寨文件静态化方法3 页面UTF-8转为gb2312
     * <br>修改历史：
     * <br>修改日期  修改者 BUG小功能修改申请单号
     * <br>
     * 
     * @param staticParam 静态化常量文件(StaticHtmlConstant)中定义的变量名
     * @param filenameReplaces filename中的需要将<#urlcanshu#>替换成的内容
     * @param urlReplaces url中的需要将<#urlcanshu#>替换成的内容
     * @param pathReplaces path中的需要将<#pathcanshu#>替换成的内容
     * @throws MalformedURLException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void createStaticPagesGBK(String[] staticParam,String filenameReplaces,String urlReplaces,String pathReplaces){
        //读取url地址中的内容
    	String url = staticParam[1].replaceAll("<#urlcanshu#>", urlReplaces);
    	String content = HttpUtil.getHTML(url, "", "gb2312");
    	System.out.println(content);
    	content=content.replaceAll("<!@@#", "<!--#").replaceAll("UTF-8", "gb2312");    	
        FileUtil.writeStoFile(content, staticParam[2].replaceAll("<#pathcanshu#>", pathReplaces), staticParam[0].replaceAll("<#filenamecanshu#>",filenameReplaces), "gb2312");
        
    }
    
	/**
	 * URL页面抓取
	 * @param destURL URL地址
	 * @param param 请求参数
	 * @oaran charcode  URL的字符集编码
	 * @return 得到页面的HTML字符串
	 */
	public static String getHTML(String destURL, String param, String characterSet) {
		StringBuffer buf = new StringBuffer();
		HttpURLConnection httpConn = null;
		PrintWriter out = null;
		BufferedReader reader = null;
		try {
			// 与对方建立一个连接
			URL url = new URL(destURL);
			// 打开连接
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			out = new PrintWriter(httpConn.getOutputStream());
			out.print(param);	//参数
			out.flush();
			httpConn.connect();
			reader = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(),characterSet));
			
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line).append(StringUtil.getLineSeparator());
			}
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				out.close();
				httpConn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}
		return buf.toString();
	}
	
	/**
	 * URL页面抓取
	 * @param destURL URL地址
	 * @oaran charcode  URL的字符集编码
	 * @return 得到页面的HTML字符串
	 */
	public static String getHTMLByGet(String destURL,String characterSet) {
		StringBuffer buf = new StringBuffer();
		HttpURLConnection httpConn = null;
		BufferedReader reader = null;
		try {
			// 与对方建立一个连接
			URL url = new URL(destURL);
			// 打开连接
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("GET");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.connect();
			reader = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(),characterSet));

			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line).append(StringUtil.getLineSeparator());
			}
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				httpConn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(),e);
			}
		}
		return buf.toString();
	}
	
}
