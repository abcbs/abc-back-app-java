 package com.ndlan.canyin.open.bldcbservices.front;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.jour.JourService;
import com.ndlan.canyin.open.utils.OpenResponseDataFormat;
import com.ndlan.canyin.base.entity.jour.Jour;
 import javax.annotation.Resource;
 import javax.servlet.ServletRequest;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
 @Controller
 @Qualifier("openOtherController")
 @Resource(name="openOtherController")
 @RequestMapping({"/mxbapi/bldcb/other"})
 @Lazy
 public class OpenOtherController extends BaseManageController
 {
 
   @Autowired
   JourService jourService;
 
   @RequestMapping(value={"repeatrequest"}, produces={"application/json"})
   @ResponseBody
   public Object[] repeatRequest(@RequestParam(value="jourid", defaultValue="") String jourId, Model model, ServletRequest request)
   {
     if (StringUtils.isNotEmpty(jourId)) {
       Jour bean = (Jour)this.jourService.getOne(jourId);
       if (bean == null) {
         return null;
       }
       OpenResponseDataFormat ordf = new OpenResponseDataFormat(bean.getFunctionNo(), "");
       return ordf.custom(Integer.parseInt(bean.getReturnNo()), bean.getReturnMessage());
     }
     return null;
   }
 }

