 package com.ndlan.canyin.core.utils;
 
 import java.io.PrintStream;
 import java.util.Iterator;
 import java.util.List;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import com.ndlan.canyin.core.utils.Identities;
 
 public class AuthorityInitialUtil
 {
   private static String path = "permission";
 
   public static void init()
     throws Exception
   {
     SAXReader saxReader = new SAXReader();
     Document document = saxReader.read(ResourceUtils.getFile("classpath:permission.xml"));
     List list = document.selectNodes("/permissions");
     select(((Element)list.iterator().next()).selectNodes(path), "");
   }
   public static void select(List<Element> list, String parentId) {
     for (Element e : list) {
       String id = generate(e, parentId);
       select(e.selectNodes(path), id);
     }
   }
 
   public static String generate(Element e, String parentId)
   {
     String uuid = Identities.uuid2();
     String sql = "INSERT INTO `cm_authority_module` (`cam_id`,`cam_name`,`cam_level`,`parent_cam_id`,`module_status`,`version`,`action_url`,`permission`,`show_seq`,`fk_parent_id`) VALUES ('" + 
       uuid + "','" + e.attribute("name").getText() + "','" + e.attribute("level").getText() + "','" + parentId + "','',0,'" + e.attribute("value").getText() + "','perms[" + e.attribute("perm").getText() + "]',0,'" + parentId + "');";
     System.out.println(sql);
     return uuid;
   }
   public static void main(String[] args) throws Exception {
     System.out.println("delete from cm_role_auth;delete from cm_authority_module;");
     System.out.println("INSERT INTO `cm_role` (`cr_id`,`rest_id`,`name`,`create_time`,`create_by`,`update_time`,`update_by`,`version`,`role`,`value`) VALUES ('402882173d819671013d81a70aa90000',NULL,'管理�?,'2013-03-19 15:57:20','402881b33d248bde013d249179f20003','2013-03-21 11:21:29','402881b33d248bde013d249179f20003',5,NULL,NULL);");
     init();
   }
 }

