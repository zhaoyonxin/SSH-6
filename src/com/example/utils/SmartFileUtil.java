package com.example.utils;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * 文件上传下载工具
 * @author anonymous
 *
 */
public class SmartFileUtil {
	
	
	/**
	 * 文件上传: 此方法将上传的文件上传指定目录, 并助保持原文件名命名
	 * @param servletConfig
	 * @param request
	 * @param response
	 * @param mimeTyps 允许的上传文件类型
	 * @param maxSize  单个文件允许的最大大小
	 * @param totalSize	此次上传文件允许的总文件大小
	 * @param uploadDir	上传的目录
	 * @throws ServletException
	 * @throws IOException
	 * @throws SmartUploadException
	 */
	public static int upload(ServletConfig servletConfig, HttpServletRequest request, HttpServletResponse response, 
			String mimeTyps, long maxSize, long totalSize, String uploadDir) throws ServletException, IOException, SmartUploadException {
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(servletConfig, request, response);
		smartUpload.setAllowedFilesList(mimeTyps);
		smartUpload.setMaxFileSize(maxSize);
		smartUpload.setTotalMaxFileSize(totalSize);
		smartUpload.upload();
		java.io.File fileDir = new java.io.File(uploadDir);
		if(!fileDir.exists() && fileDir.isDirectory()) {
			fileDir.mkdirs();
		}
		return smartUpload.save(uploadDir, File.SAVEAS_PHYSICAL);
	}
	
	
	/**
	 * 
	 * @param servletConfig
	 * @param request
	 * @param response
	 * @param uploadDir 上传的目录
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws SmartUploadException
	 */
	public static int upload(ServletConfig servletConfig, HttpServletRequest request, HttpServletResponse response, 
			String uploadDir) throws ServletException, IOException, SmartUploadException {
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(servletConfig, request, response);
		smartUpload.upload();
		java.io.File fileDir = new java.io.File(uploadDir);
		if(!fileDir.exists() && fileDir.isDirectory()) {
			fileDir.mkdirs();
		}
		return smartUpload.save(uploadDir, File.SAVEAS_PHYSICAL);
	}
	
	
	/**
	 * 文件下载
	 * @param servletConfig 在servlet中可通过this.getServletConfig()获取
	 * @param request 
	 * @param response
	 * @param sourceFile 要下载的文件名,带路径的全文件名
	 * @param mimeType	为内容类型（MIME格式的文件类型信息，可被浏览器识别）
	 * @param downloadFilename  返回给浏览器下载后的另存文件名
	 * @throws ServletException
	 * @throws IOException
	 * @throws SmartUploadException
	 */
	public static void downloadFile(ServletConfig servletConfig, HttpServletRequest request, HttpServletResponse response, 
			String sourceFile, String mimeType, String downloadFilename) throws ServletException, IOException, SmartUploadException {
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(servletConfig, request, response);
		smartUpload.setContentDisposition(null);
		String agent = request.getHeader("User-Agent");
		boolean isMSIE = (agent != null && agent.contains("MSIE"));
		// 解决Firefox中下载文件名为汉字时乱码的问题
		if(!isMSIE) {
			downloadFilename = new String(downloadFilename.getBytes("UTF-8"), "ISO8859-1");
		} else {
			downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
		}
		smartUpload.downloadFile(sourceFile, mimeType, downloadFilename);
	}
	
}
