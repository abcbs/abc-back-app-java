 package com.ndlan.canyin.frontdesk.front.self;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.util.SelfServiceCollective;

 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
 @Controller
 @RequestMapping({"/self/entery"})
 @Lazy
 public class EnteryController extends BaseManageController
 {
 
   @Autowired
   SelfServiceCollective selfServiceCollective;
 
   @RequestMapping({"ch_table"})
   public String chooseTable(@RequestParam(value="tn", defaultValue="") String tn, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request)
   {
     return "self/im";
   }
   @RequestMapping({"introduce"})
   public String introduce(@RequestParam(value="tn", defaultValue="") String tn, @RequestParam(value="page", defaultValue="1") int pageNumber, @RequestParam(value="sortType", defaultValue="") String sortType, @RequestParam(value="direction", defaultValue="asc") String direction, Model model, HttpServletRequest request) {
     return "self/introduce";
   }
 }

