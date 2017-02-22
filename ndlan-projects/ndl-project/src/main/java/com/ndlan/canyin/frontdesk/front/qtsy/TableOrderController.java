 package com.ndlan.canyin.frontdesk.front.qtsy;
 
 import com.ndlan.canyin.frontdesk.common.BaseManageController;
import com.ndlan.canyin.frontdesk.service.qtsy.TableOrderService;
import com.ndlan.canyin.base.entity.qtsy.TableOrder;
 import com.ndlan.canyin.core.common.OperationTypeEnum;
 import javax.validation.Valid;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Sort;
 import org.springframework.stereotype.Controller;
 import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
 @Controller
 @RequestMapping({"/qtsy/tableorder"})
 @Lazy
 public class TableOrderController extends BaseManageController
 {
 
   @Autowired
   private TableOrderService tableOrderService;
 
   @RequestMapping({"list"})
   public String list(Model model, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="rows", defaultValue="10") int rows, @RequestParam("status") String status, @RequestParam("keyWords") String keyWords, @RequestParam("restId") String restId)
   {
     PageRequest pageRequest = new PageRequest(page - 1, rows, new Sort(new String[] { "orderNo" }));
     Page p = this.tableOrderService.getPage(pageRequest, status, keyWords, restId);
     model.addAttribute("tableOrderList", p.getContent());
     return "qtsy/";
   }
 
   @RequestMapping(value={"create"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String create(Model model)
   {
     TableOrder obj = new TableOrder();
     obj.setOrderNo(this.tableOrderService.buildOrderNo());
     model.addAttribute("obj", obj);
     return "qtsy/";
   }
 
   @RequestMapping(value={"update/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String updateForm(@PathVariable("id") String id, Model model)
   {
     model.addAttribute("obj", this.tableOrderService.findTableOrderById(id));
     return "qtsy/tableOrderForm";
   }
 
   @RequestMapping(value={"update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
   public String update(@Valid @ModelAttribute("tableOrder") TableOrder obj, RedirectAttributes redirectAttributes)
   {
     this.tableOrderService.save(obj);
 
     doSynchSingleTable(OperationTypeEnum.UPDATE.getCode(), obj);
 
     redirectAttributes.addFlashAttribute("message", "更新任务成功");
     return "redirect:qtsy/tableOrderForm";
   }
 
   @RequestMapping(value={"shenhe"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   @ResponseBody
   public String shenHe(@RequestParam("id") String id, @RequestParam("status") String status)
   {
     TableOrder obj = this.tableOrderService.findTableOrderById(id);
     if (obj != null) {
       obj.setOrderStatus(status);
       return "success";
     }
     return "false";
   }
 
   @ModelAttribute("tableOrder")
   public TableOrder getTableOrder(@RequestParam(value="id", required=false) String id)
   {
     if (id != null) {
       return this.tableOrderService.findTableOrderById(id);
     }
     return null;
   }
 }

