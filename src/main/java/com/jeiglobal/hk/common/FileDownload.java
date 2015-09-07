package com.jeiglobal.hk.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
/**
 * 
 * 클래스명 : FileDownload.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 파일 다운로드(BeanNameResolver로 연결)
 */
public class FileDownload extends AbstractView{
	
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		File file = (File) model.get("downloadFile");
        String fileOriginalName = (String) model.get("fileOriginalName");
        response.setContentType(getContentType());
        response.setContentLength((int) file.length());
        String fileName = fileOriginalName;
        String browser = getBrowser(request);
        //Internet Explorer
        if (browser.contains("MSIE")) {
            fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", " ");
        //그 외(Chrome, FireFox, Opera)
        }else{
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } 
        
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){}
            }
        }
        out.flush();
	}
	/**
	 * 브라우저 정보를 가져오는 메서드
	 * @param request
	 */
	private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.contains("MSIE")||header.contains("Trident/7.0")) {
               return "MSIE";
        } else if(header.contains("Chrome")) {
               return "Chrome";
        } else if(header.contains("Opera")) {
               return "Opera";
        }
        return "Firefox";
  }
}
