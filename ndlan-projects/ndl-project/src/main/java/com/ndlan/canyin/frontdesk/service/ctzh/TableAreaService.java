 package com.ndlan.canyin.frontdesk.service.ctzh;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.TableArea;
 import com.ndlan.canyin.base.repository.ctzh.TableAreaDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class TableAreaService extends BaseService<TableAreaDao, TableArea>
 {
   private TableAreaDao tableAreaDao;
 
   public List<TableArea> getTableAreaByRestID(String restId)
   {
     return this.tableAreaDao.getTableAreaByRestId(restId);
   }
 
   public List<TableArea> loadTableAreaByRestID(String restId)
   {
     return this.tableAreaDao.getTableAreaByRestId(restId);
   }
 
   public TableArea loadTableAreaByTableId(String tableId) {
     return this.tableAreaDao.getTableAreaByTableId(tableId);
   }
   @Autowired
   public void setTableAreaDao(TableAreaDao tableAreaDao) { super.setDao(tableAreaDao);
     this.tableAreaDao = tableAreaDao;
   }
 }

