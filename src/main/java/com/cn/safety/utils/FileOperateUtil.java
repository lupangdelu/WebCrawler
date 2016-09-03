package com.cn.safety.utils;

/** 
 * 文件上传下载
 * @author tech 
 * @date 2016-4-14 00:16:00 
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileOperateUtil {
	private static final String REALNAME = "realName";
	private static final String STORENAME = "storeName";
	private static final String SIZE = "size";
	private static final String SUFFIX = "suffix";
	private static final String CONTENTTYPE = "contentType";
	private static final String CREATETIME = "createTime";
	private static final String UPLOADDIR = "uploadDir/";

	/**
	 * 将上传的文件进行重命名
	 * 
	 * @param name
	 * @return
	 */
	private static String rename(String name) {
		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date()));
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;
		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}
		return fileName;
	}

	/**
	 * 压缩后的文件名
	 * 
	 * @param name 原文件名(可包括后缀也可不包括后缀)
	 * @return
	 */
	private static String zipName(String name) {
		String prefix = "";
		if (name.indexOf(".") != -1) {
			prefix = name.substring(0, name.lastIndexOf("."));
		} else {
			prefix = name;
		}
		return prefix + ".zip";
	}


	/**
	 * 上传文件
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> upload(HttpServletRequest request)
			throws Exception {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		// 上传目录
		String uploadDir = request.getSession().getServletContext()
				.getRealPath("/")
				+ FileOperateUtil.UPLOADDIR;
//		String uploadDir = "F:/test/";
		File file = new File(uploadDir);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = null;
		Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();//得到一个文件

			fileName = mFile.getOriginalFilename();//原始文件名
			String storeName = rename(fileName);//重命名后的文件名
			String localName = uploadDir + storeName;//带路径文件名

			File localFile = new File(localName);
			mFile.transferTo(localFile);
			
			Map<String, Object> map = new HashMap<String, Object>();
			// 固定参数值对
			map.put(FileOperateUtil.REALNAME, fileName);
			map.put(FileOperateUtil.STORENAME, localFile);
			map.put(FileOperateUtil.SIZE, new File(localName).length());
			map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
			map.put(FileOperateUtil.CREATETIME, new Date());
			result.add(map);
		}
		return result;
	}

	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType,
			String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ FileOperateUtil.UPLOADDIR;
		String downLoadPath = ctxPath + storeName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(realName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
}









