package com.dengqiang.bean;

import java.io.Serializable;
/**
 * 图片附件对象,记录图片类型
 * @author 邓强
 *
 */
public class FileBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String fileName;//文件名
	private String fileContent;//文件内容//MongoDB预留
	private String fileType;//文件类型
	private String filePath;//文件路径
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
