package org.springboot.sample.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@RestController
@RequestMapping(value = "/upload")
public class UploadUI {
	private static final Logger logger = LoggerFactory.getLogger(UploadUI.class);

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String upload(HttpServletRequest request) throws IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multipartHttpServletRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multipartHttpServletRequest.getFile(iter.next().toString());
				if (file != null) {

					/*
					 * //可以对user做一些操作如存入数据库
					 * //以下的代码是将文件file重新命名并存入Tomcat的webapps目录下项目的下级目录fileDir
					 * String fileRealName = file.getOriginalFilename();
					 * //获得原始文件名; int pointIndex = fileRealName.indexOf(".");
					 * //点号的位置 String fileSuffix =
					 * fileRealName.substring(pointIndex); //截取文件后缀 UUID FileId
					 * = UUID.randomUUID(); //生成文件的前缀包含连字符 String savedFileName
					 * = FileId.toString().replace("-","").concat(fileSuffix);
					 * //文件存取名 String savedDir =
					 * request.getSession().getServletContext().getRealPath(
					 * "FirDir"); //获取服务器指定文件存取路径 File savedFile = new
					 * File(savedDir,savedFileName ); boolean isCreateSuccess =
					 * savedFile.createNewFile(); if(isCreateSuccess){
					 * file.transferTo(savedFile); //转存文件 }
					 */

					// String savedDir =
					// request.getSession().getServletContext().getRealPath("/");
					String savedDir = System.getProperty("user.dir");// 获取当前项目的绝对路径
					String path = savedDir + "//src//main//resources//config//" + file.getOriginalFilename();
					file.transferTo(new File(path));
				}
			}
		}
		return "html";
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload2(HttpServletRequest request) throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			Iterator iter = multipartHttpServletRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multipartHttpServletRequest.getFile(iter.next().toString());
				if (file != null) {
					// 获取文件名
					String fileName = file.getOriginalFilename();
					// 获取文件的后缀名
					String suffixName = fileName.substring(fileName.lastIndexOf("."));
					// 文件上传后的路径
					String savedDir = System.getProperty("user.dir");
					// 解决中文问题，liunx下中文路径，图片显示问题
					fileName = UUID.randomUUID() + suffixName;
					String path = savedDir + "//src//main//resources//config//" + fileName;
					File dest = new File(path);
					// 检测是否存在目录
					if (!dest.getParentFile().exists()) {
						dest.getParentFile().mkdirs();
					}
					file.transferTo(dest);
					return "上传成功";
				}
			}
		}
		return "上传失败";
	}

}
