 package com.ndlan.canyin.frontdesk.front.open;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;

 import javax.servlet.http.HttpServletRequest;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
 @Controller
 @RequestMapping({"/open/test"})
 @Lazy
 public class TestOpenAPIController extends BaseManageController
 {
   @RequestMapping({""})
   public String list(Model model, HttpServletRequest request)
   {
     return "open/test";
   }
   @RequestMapping({"shutdown"})
   public String shutdown(Model model, HttpServletRequest request) { return "shutdown";
   }
 }

