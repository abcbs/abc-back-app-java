 package com.ndlan.canyin.frontdesk.front.bill;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;

 import javax.servlet.http.HttpServletRequest;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
 @Controller
 @RequestMapping({"/fastfood"})
 @Lazy
 public class FastfoodController extends BaseManageController
 {
   @RequestMapping({"diancai"})
   public String diancai(@RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="billId", defaultValue="") String billId, @RequestParam(value="billType", defaultValue="4") String billType, Model model, HttpServletRequest request)
   {
     model.addAttribute("billId", billId);
     model.addAttribute("billType", billType);
     return "bill/diancai";
   }
 }

