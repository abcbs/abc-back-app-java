 package com.ndlan.canyin.sharelogic.printer;
 
 import com.ndlan.canyin.base.entity.ctzh.Printer;
 import com.ndlan.canyin.core.common.PrinterTemplateEnum;
 import java.awt.print.Book;
 import java.awt.print.PageFormat;
 import java.awt.print.Paper;
 import java.awt.print.Printable;
 import java.awt.print.PrinterException;
 import java.awt.print.PrinterJob;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import javax.print.PrintService;
 
 public abstract class AbstractPrinter
 {
   public String template;
   public Printer printer;
   public PageFormat pf;
   public Double margin_left;
   public Double margin_right;
   public Double margin_up;
   public Double margin_down;
   public HashMap<String, Object> printParaments;
   public int printerTime = 1;
 
   public void print(Printer printer, int times, HashMap<String, Object> printParaments)
   {
     this.template = printer.getTemplate();
     this.printer = printer;
     this.printerTime = times;
 
     this.printParaments = printParaments;
 
     PrinterJob job = PrinterJob.getPrinterJob();
 
     Book book = getBook();
     job.setPageable(book);
     try {
       PrintService[] printServices = PrinterJob.lookupPrintServices();
 
       for (PrintService printService : printServices)
         if (printService.getName().equalsIgnoreCase(printer.getSysName())) {
           job.setPrintService(printService);
           job.print();
           break;
         }
     }
     catch (PrinterException e) {
       System.out.println("打印异常，基本信息如下：");
       System.out.println("当前系统时间：【" + new Date() + "】");
       System.out.println("打印机系统名称：【" + printer.getSysName() + "】");
       System.out.println("打印机配置名称：【" + printer.getName() + "】");
       System.out.println("当前打印次数：【" + times + "】");
       System.out.println("当前打印机IP：【" + printer.getIp() + "】");
       System.out.println("当前打印机菜肴类型：【" + printer.getDisheCategory() + "】");
       System.out.println("当前打印机打印区域：【" + printer.getTableArea() + "】");
       e.printStackTrace();
       System.out.println(e.getMessage());
     }
   }
 
   protected Book getBook()
   {
     Book book = new Book();
 
     PageFormat pf = new PageFormat();
 
     pf.setOrientation(1);
 
     Paper p = new Paper();
 
     Double width = getWidth(this.template);
 
     Map margin = getMargin(width.doubleValue());
     this.margin_left = ((Double)margin.get("left"));
     this.margin_up = ((Double)margin.get("up"));
     this.margin_down = ((Double)margin.get("down"));
 
     double length = 9999999.0D;
     p.setSize(width.doubleValue(), length);
     p.setImageableArea(0.0D, 0.0D, width.doubleValue(), length);
     pf.setPaper(p);
     this.pf = pf;
 
     book.append(getPrintable(), pf);
     return book;
   }
 
   public abstract Printable getPrintable();
 
   public abstract double getLength();
 
   public Double getWidth(String template)
   {
     int paperWidth = 80;
     if (PrinterTemplateEnum.C_80.getCode().equals(template))
       paperWidth = 68;
     else if (PrinterTemplateEnum.C_76.getCode().equals(template))
       paperWidth = 76;
     else if (PrinterTemplateEnum.C_58.getCode().equals(template)) {
       paperWidth = 58;
     }
     return Double.valueOf(paperWidth * 72 / 25.399999999999999D);
   }
 
   public Map<String, Double> getMargin(double width)
   {
     double p_x = 0.0D;
     double p_y = 0.0D;
     double p_d = 0.0D;
 
     if (this.printer.getMarginLeft() == null)
       p_x = 0.0D;
     else {
       p_x = Double.valueOf(this.printer.getMarginLeft().toString()).doubleValue();
     }
 
     if (this.printer.getMarginUp() == null)
       p_y = 0.0D;
     else {
       p_y = Double.valueOf(this.printer.getMarginUp().toString()).doubleValue();
     }
 
     if (this.printer.getMarginDown() == null)
       p_d = 0.0D;
     else {
       p_d = Double.valueOf(this.printer.getMarginDown().toString()).doubleValue();
     }
 
     p_x = p_x / 25.399999999999999D * 72.0D;
     p_y = p_y / 25.399999999999999D * 72.0D;
     p_d = p_d / 25.399999999999999D * 72.0D;
 
     Map marg = new HashMap();
     marg.put("left", Double.valueOf(p_x));
     marg.put("up", Double.valueOf(p_y));
     marg.put("down", Double.valueOf(p_d));
     return marg;
   }
 }

