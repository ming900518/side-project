package com.blog.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {	
	public static Map<String, Object> uploadMultipartFile(MultipartFile file,String path){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", false);
		result.put("path", null);
		InputStream is = null;
		FileOutputStream fos = null;
		int length = 0;

		String filename = file.getOriginalFilename();
		String extension = filename.substring(filename.lastIndexOf("."));
		String rootPath = System.getProperty("catalina.home");
		String childrenPath = "/files/" + path;
		File dir = new File(rootPath + childrenPath);
		
		if (!dir.exists())
			dir.mkdirs();
        try {
        	is = file.getInputStream();
        	String newFileName = SercurityUtil.getUUID() + extension;
        	int byteNum = 1024;
			switch(extension){
				case ".png":
					byteNum = 1024;
					newFileName = SercurityUtil.getUUID() + extension;
					break;
				case ".mp4":
					byteNum = (int)file.getSize();
					newFileName = filename.replace(extension, extension);
					break;
				case ".pdf":
					byteNum = (int)file.getSize();
					newFileName = filename.replace(extension, extension);
					break;
				default:
					byteNum = 1024;
					newFileName = SercurityUtil.getUUID() + extension;
			}
			byte[] b = new byte[byteNum];
            File uploadFile = new File(dir.getAbsolutePath() + "/" +newFileName);
            if(uploadFile.exists()){
            	uploadFile.delete();
            }


            fos = new FileOutputStream(uploadFile);
            length = 0;
            while ((length = is.read(b)) != -1) {
            	fos.write(b, 0, length);
            }
            fos.flush();
            fos.close();
            result.put("status", true);
    		result.put("path", (uploadFile.getAbsolutePath().replace(rootPath, "")).replace("\\", "/"));
        }catch (Exception e) {
        	LogUtils.fileConteollerError(filename + " uploadMultipartFile has error :" + e);
        }finally{
        	try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return result;
	}

	public static int deleteMultipartFileForVideo(String path){
		int res = 2;
		String rootPath = System.getProperty("catalina.home");
		String childrenPath = path;
		File dir = new File(rootPath + childrenPath);
		try {
	        if(dir.exists()){
	        	dir.delete();
	        }
	        res = 1;
	        return res;
	    }catch (Exception e) {
        	LogUtils.fileConteollerError(path + " deleteMultipartFileForVideo has error :" + e);
        	 return res;
        }
	}
}
