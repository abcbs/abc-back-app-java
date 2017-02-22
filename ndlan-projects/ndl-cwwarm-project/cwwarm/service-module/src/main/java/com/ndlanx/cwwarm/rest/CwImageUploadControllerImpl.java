package com.ndlanx.cwwarm.rest;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ndlan.framework.core.web.domain.Result;
import com.ndlan.framework.core.web.domain.Result.Status;
import com.ndlan.framework.core.web.restful.BaseBusinessControllerImpl;

@Controller
@RequestMapping("/business/cw/imageUpload/")
public class CwImageUploadControllerImpl {
	private Logger log = LoggerFactory.getLogger(BaseBusinessControllerImpl.class);
	
	
	
	@RequestMapping(value="/uploadPic",method = RequestMethod.POST)
	@ResponseBody
	public Result uploadPic(MultipartHttpServletRequest request) {
		log.info("商品上传图片");
		String uploadUrl="";
		try {
			LinkedHashMap mapUpload = new LinkedHashMap();
			List<MultipartFile> tablePics = request.getFiles("pic");
			if ((tablePics != null) && (tablePics.size() > 0)) {
				for (MultipartFile tablePic : tablePics) {
					log.info("tablePic.getName():\t"+tablePic.getName());
				}
			}
			System.out.println(uploadUrl+"==============");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("添加对象失败:" + e.getLocalizedMessage(), e);
			return new Result(Status.ADDERROR, "上传失败!");
		}
		
		return new Result(Status.OK, uploadUrl);
	}
	
	@RequestMapping(value = "/postformdata", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
	public @ResponseBody Result handleFormUpload(
			@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			byte[] bytes = null;
			try {
				bytes = file.getBytes();
			} catch (IOException e) {
				log.info("error processing uploaded file", e);
			}
			
			return new Result(Status.ADDERROR, "上传失败!");
		} else {
			
			return new Result(Status.OK, file.getName());
		}
	}


}
