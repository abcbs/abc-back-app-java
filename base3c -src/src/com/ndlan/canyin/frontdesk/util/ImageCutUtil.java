 package com.ndlan.canyin.frontdesk.util; 
import com.ndlan.canyin.core.common.OperationTypeEnum;
import com.ndlan.canyin.core.common.TrueFalseEnum;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
 /**
  * 图片上载
  * @author zhengjiansong
  *
  */
 public class ImageCutUtil
 {
	public static final String FILESEPARATOR = System.getProperty("file.separator"); 
   public String getRealPath(HttpServletRequest request, String uploadPath)
   {
	 
     uploadPath = uploadPath.replaceAll("/upload/", "");
     String realPath = request.getSession().getServletContext().getRealPath("/upload");
//     File f = new File(realPath + "\\" + uploadPath);
     File f = new File(realPath + FILESEPARATOR + uploadPath);
     if (!f.exists())
     {
       f.mkdir();
     }
//     return realPath + "\\" + uploadPath;
     return realPath + FILESEPARATOR + uploadPath;
   }
 
   public String uploadEmployeeImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/employee";
     if (!file.isEmpty()) {
       String realPath = getRealPath(request, uploadPath);
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         uploadFileName = gen200x200(file.getInputStream(), realPath, uploadFileName);
         map.put(fileName + "200x200_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + uploadFileName + "," + 2);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + uploadFileName;
   }
   /**
    * 上传营业执照
    * @param file
    * @param request
    * @param restId
    * @param map
    * @return
    */
   public String uploadBusinessLicenseImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/restaurant";
     if (!file.isEmpty()) {
       String realPath = getRealPath(request, uploadPath);
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         uploadFileName = gen1024x1024(file.getInputStream(), realPath, uploadFileName);
         map.put(fileName + "1024x1024_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + uploadFileName + "," + 2);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + uploadFileName;
   }
   /**
    * 进件上传资质文件
    * @param file
    * @param request
    * @param restId
    * @param map
    * @return
    */
   public String uploadQualificationImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String commpleteUpload  = "";
     String uploadPath = "/upload/qualification"+UUID.randomUUID().toString();
     String realPath = getRealPath(request, uploadPath);
     if (!file.isEmpty()) {
       
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         uploadFileName = gen1024x1024(file.getInputStream(), realPath, file.getOriginalFilename());
         map.put(fileName + "1024x1024_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + uploadFileName + "," + 2);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     
     return realPath+FILESEPARATOR+uploadFileName;
   }
   public String uploadDishImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/dish/"+restId+"/";
     String picPathName = "";
     if (!file.isEmpty()) {
       String realPath = getRealPath(request, uploadPath);
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         String[] name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).size(1024, 1024).toFile(realPath + "/" + name[0] + "_1024x1024" + name[1]);
 
         picPathName = name[0] + "_1024x1024" + name[1];
 
         map.put(fileName + "1024x1024_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 1);
 
         BufferedImage image = Thumbnails.of(new InputStream[] { file.getInputStream() }).scale(1.0D).asBufferedImage();
         int lin = image.getHeight();
         if (lin > image.getWidth())
         {
           lin = image.getWidth();
         }
 
         name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).sourceRegion(Positions.CENTER, lin, lin).size(80, 80).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_80x80" + name[1]);
 
         picPathName = name[0] + "_80x80" + name[1];
         map.put(fileName + "80x80_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 1);
         
         name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).sourceRegion(Positions.CENTER, lin, lin).size(240, 240).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_240x240" + name[1]);
 
         picPathName = name[0] + "_240x240" + name[1];
         map.put(fileName + "240x240_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 1);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + picPathName;
   }
 
   public String uploadTableImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/product";
     if (!file.isEmpty()) {
       String realPath = getRealPath(request, uploadPath);
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         uploadFileName = gen200x200(file.getInputStream(), realPath, uploadFileName);
         map.put(fileName + "200x200_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + uploadFileName + "," + 5);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + uploadFileName;
   }
 
   public String uploadRestNewsImageFile(File file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getName();
     String uploadPath = "/upload/restNews";
     if (file.exists()) {
       String realPath = getRealPath(request, uploadPath);
       try {
         byte[] fileByte = new byte[(int)file.length()];
         String fileName = ImageMD5.MD5(fileByte);
         uploadFileName = getUploadFileName(fileName, file.getName());
         FileInputStream is = new FileInputStream(file);
         BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
         uploadFileName = genOriginalSize(is, realPath, uploadFileName, sourceImg.getWidth(), sourceImg.getHeight());
         map.put(fileName + sourceImg.getWidth() + "x" + sourceImg.getHeight() + "_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + uploadFileName + "," + 6);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + uploadFileName;
   }
 
   public String uploadPrinterImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/printer";
     if (!file.isEmpty()) {
       String realPath = getRealPath(request, uploadPath);
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         uploadFileName = gen200x200(file.getInputStream(), realPath, uploadFileName);
         map.put(fileName + "200x200_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + uploadFileName + "," + 3);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + uploadFileName;
   }
 /**
  * 上传餐厅logo
  * @param file
  * @param request
  * @param restId
  * @param map
  * @return
  */
   public String uploadRestLogoImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/restaurant";
     String picPathName = "";
     if (!file.isEmpty()) {
       String realPath = getRealPath(request, uploadPath);
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         String[] name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).size(1024, 1024).toFile(realPath + "/" + name[0] + "_1024x1024" + name[1]);
 
         picPathName = name[0] + "_1024x1024" + name[1];
 
         map.put(fileName + "1024x1024_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
 
         BufferedImage image = Thumbnails.of(new InputStream[] { file.getInputStream() }).scale(1.0D).asBufferedImage();
         int lin = image.getHeight();
         if (lin > image.getWidth())
         {
           lin = image.getWidth();
         }
 
         name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).sourceRegion(Positions.CENTER, lin, lin).size(200, 200).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_200x200" + name[1]);
 
         picPathName = name[0] + "_200x200" + name[1];
         map.put(fileName + "200x200_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
 
         name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).sourceRegion(Positions.CENTER, lin, lin).size(80, 80).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_80x80" + name[1]);
 
         picPathName = name[0] + "_80x80" + name[1];
         map.put(fileName + "80x80_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + picPathName;
   }
 /**
  * 上传餐厅图片
  * @param file
  * @param request
  * @param restId
  * @param map
  * @return
  */
   public String uploadRestImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/restaurant";
     String picPathName = "";
     if (!file.isEmpty()) {
       String realPath = getRealPath(request, uploadPath);
       try
       {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         String[] name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).size(1200, 800).toFile(realPath + "/" + name[0] + "_1200x800" + name[1]);
 
         picPathName = name[0] + "_1200x800" + name[1];
 
         map.put(fileName + "1200x800_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
 
         BufferedImage image = Thumbnails.of(new InputStream[] { file.getInputStream() }).scale(1.0D).asBufferedImage();
         int lin = image.getHeight();
         if (lin > image.getWidth())
         {
           lin = image.getWidth();
         }
         int l = lin * 0;
         name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).sourceRegion(Positions.CENTER, lin, lin / 3 * 2).size(300, 200).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_300x200" + name[1]);
 
         picPathName = name[0] + "_300x200" + name[1];
         map.put(fileName + "300x200_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
 
         name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).sourceRegion(Positions.CENTER, lin, lin / 3 * 2).size(150, 100).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_150x100" + name[1]);
 
         picPathName = name[0] + "_150x100" + name[1];
         map.put(fileName + "150x100_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + picPathName;
   }
 
   /**
    * 上传广告图片
    * @param file
    * @param request
    * @param restId
    * @param map
    * @return
    */
   public String uploadAdvertisingImageFile(MultipartFile file, HttpServletRequest request, String restId, LinkedHashMap<String, Object> map)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/advertising";
     String picPathName = "";
     String urlString = "";
     if (!file.isEmpty()) {
       String realPath = getRealPath(request, uploadPath);
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
         String[] name = getUploadFileNames(uploadFileName);
         urlString=name[0]+name[1];
         Thumbnails.of(new InputStream[] { file.getInputStream() }).size(1024, 1024).toFile(realPath + "/" + name[0] + "_1024x1024" + name[1]);
 
         picPathName = name[0] + "_1024x1024" + name[1];
 
         map.put(fileName + "1024x1024_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
 
         BufferedImage image = Thumbnails.of(new InputStream[] { file.getInputStream() }).scale(1.0D).asBufferedImage();
         int lin = image.getHeight();
         if (lin > image.getWidth())
         {
           lin = image.getWidth();
         }
 
         name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).sourceRegion(Positions.CENTER, lin, lin).size(200, 200).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_200x200" + name[1]);
 
         picPathName = name[0] + "_200x200" + name[1];
         map.put(fileName + "200x200_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
 
         name = getUploadFileNames(uploadFileName);
         Thumbnails.of(new InputStream[] { file.getInputStream() }).sourceRegion(Positions.CENTER, lin, lin).size(80, 80).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_80x80" + name[1]);
 
         picPathName = name[0] + "_80x80" + name[1];
         map.put(fileName + "80x80_" + OperationTypeEnum.UPLOAD.getCode(), realPath + File.separator + picPathName + "," + 4);
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + urlString;
   }
   
   
   
   public void deleteUploadedFile(String fileName, HttpServletRequest request)
   {
     try
     {
       String targetPath = fileName.substring(0, fileName.lastIndexOf("/"));
       String uploadFileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
       String realPath = request.getSession().getServletContext().getRealPath(targetPath);
       String[] name = getUploadFileNames(uploadFileName);
       String uploadFilePath_1024x1024 = name[0] + "_1024x1024" + name[1];
       System.out.println("uploadFilePath_1024x1024:" + uploadFilePath_1024x1024);
 
       File file1 = new File(realPath, uploadFilePath_1024x1024);
       if (file1.exists())
       {
         FileUtils.forceDelete(file1);
       }
 
       String uploadFilePath_200x200 = name[0] + "_200x200" + name[1];
       File file2 = new File(realPath, uploadFilePath_200x200);
       if (file2.exists())
       {
         FileUtils.forceDelete(file2);
       }
 
       String uploadFilePath_80x80 = name[0] + "_80x80" + name[1];
       File file3 = new File(realPath, uploadFilePath_80x80);
       if (file3.exists())
       {
         FileUtils.forceDelete(file3);
       }
 
       String uploadFilePath_300x200 = name[0] + "_300x200" + name[1];
       File file4 = new File(realPath, uploadFilePath_300x200);
       if (file4.exists())
       {
         FileUtils.forceDelete(file4);
       }
 
       String uploadFilePath_150x100 = name[0] + "_150x100" + name[1];
       File file5 = new File(realPath, uploadFilePath_150x100);
       if (file5.exists())
       {
         FileUtils.forceDelete(file5);
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public String uploadFile(MultipartFile file, HttpServletRequest request, int uploadType, String isCut)
   {
     String uploadFileName = file.getOriginalFilename();
     String uploadPath = "/upload/dish";
     if (!file.isEmpty()) {
       if (uploadType == 1)
       {
         uploadPath = "/upload/dish";
       }
       else if (uploadType == 2)
       {
         uploadPath = "/upload/employee";
       }
       else if (uploadType == 3)
       {
         uploadPath = "/upload/printer";
       }
       else if (uploadType == 4)
       {
         uploadPath = "/upload/restaurant";
       }
       else if (uploadType == 5)
       {
         uploadPath = "/upload/table";
       }
       String realPath = request.getSession().getServletContext().getRealPath(uploadPath);
       try {
         String fileName = ImageMD5.MD5(file.getBytes());
         uploadFileName = getUploadFileName(fileName, file.getOriginalFilename());
 
         FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, uploadFileName));
 
         if (TrueFalseEnum.TRUE.getCode().equals(isCut))
         {
           if (uploadType == 4)
           {
             gen1200x800(file.getInputStream(), realPath, uploadFileName);
             gen300x200(file.getInputStream(), realPath, uploadFileName);
             gen150x100(file.getInputStream(), realPath, uploadFileName);
           }
           else
           {
             gen1024x1024(file.getInputStream(), realPath, uploadFileName);
             gen200x200(file.getInputStream(), realPath, uploadFileName);
             gen80x80(file.getInputStream(), realPath, uploadFileName);
           }
         }
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return uploadPath + "/" + uploadFileName;
   }
 
   public String getUploadFileName(String fileName, String originalFilename)
   {
     return fileName + "_ori" + originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
   }
 
   public String[] getUploadFileNames(String uploadFileName)
   {
     String name1 = uploadFileName.substring(0, uploadFileName.lastIndexOf("."));
     name1 = name1.replaceAll("_ori", "");
     return new String[] { name1, uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length()) };
   }
 
   public static void main(String[] args) throws Exception
   {
     String fileName = "/upload/dish/123321123321123.jpg";
     String targetPath = fileName.substring(0, fileName.lastIndexOf("/"));
     System.out.println(targetPath);
   }
 
   public String gen1024x1024(InputStream oriFile, String realPath, String uploadFileName)
     throws Exception
   {
     String[] name = getUploadFileNames(uploadFileName);
     Thumbnails.of(new InputStream[] { oriFile }).size(1024, 1024).toFile(realPath + "/" + name[0] + "_1024x1024" + name[1]);
 
     return name[0]  + name[1];
   }
 
   public String gen1200x800(InputStream oriFile, String realPath, String uploadFileName) throws Exception
   {
     String[] name = getUploadFileNames(uploadFileName);
     Thumbnails.of(new InputStream[] { oriFile }).size(1200, 800).toFile(realPath + "/" + name[0] + "_1200x800" + name[1]);
 
     return name[0] + "_1200x800" + name[1];
   }
 
   public String gen200x200(InputStream oriFile, String realPath, String uploadFileName) throws Exception
   {
     String[] name = getUploadFileNames(uploadFileName);
     Thumbnails.of(new InputStream[] { oriFile }).sourceRegion(Positions.CENTER, 400, 400).size(200, 200).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_200x200" + name[1]);
 
     return name[0] + "_200x200" + name[1];
   }
 
   public String genOriginalSize(InputStream oriFile, String realPath, String uploadFileName, int width, int height) throws Exception
   {
     String[] name = getUploadFileNames(uploadFileName);
     Thumbnails.of(new InputStream[] { oriFile }).size(width, height).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_" + width + "x" + height + name[1]);
 
     return name[0] + "_" + width + "x" + height + name[1];
   }
 
   public String gen80x80(InputStream oriFile, String realPath, String uploadFileName) throws Exception
   {
     String[] name = getUploadFileNames(uploadFileName);
     Thumbnails.of(new InputStream[] { oriFile }).sourceRegion(Positions.CENTER, 400, 400).size(80, 80).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_80x80" + name[1]);
 
     return name[0] + "_80x80" + name[1];
   }
 
   public String gen300x200(InputStream oriFile, String realPath, String uploadFileName)
     throws Exception
   {
     String[] name = getUploadFileNames(uploadFileName);
     Thumbnails.of(new InputStream[] { oriFile }).sourceRegion(Positions.CENTER, 600, 400).size(300, 200).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_300x200" + name[1]);
 
     return name[0] + "_300x200" + name[1];
   }
 
   public String gen150x100(InputStream oriFile, String realPath, String uploadFileName) throws Exception
   {
     String[] name = getUploadFileNames(uploadFileName);
     Thumbnails.of(new InputStream[] { oriFile }).sourceRegion(Positions.CENTER, 500, 400).size(150, 100).keepAspectRatio(false).toFile(realPath + "/" + name[0] + "_150x100" + name[1]);
 
     return name[0] + "_150x100" + name[1];
   }
 }

