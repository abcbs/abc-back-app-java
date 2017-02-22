 package com.ndlan.canyin.frontdesk.service.qtsy;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ctzh.Table;
 import com.ndlan.canyin.base.entity.ctzh.TableBillRelation;
 import com.ndlan.canyin.base.repository.ctzh.TableBillRelationDao;
 import java.util.List;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 public class TableBillRelationService extends BaseService<TableBillRelationDao, TableBillRelation>
 {
   @Autowired
   public void setBaseDao(TableBillRelationDao tableBillRelationDao)
   {
     super.setDao(tableBillRelationDao);
   }
 
   public List<TableBillRelation> findByTableAndTabBillType(Table table, String tabBillType) {
     return ((TableBillRelationDao)getDao()).findByTableAndTabBillType(table, tabBillType);
   }
 
   public List<TableBillRelation> findByTable(Table table) {
     return ((TableBillRelationDao)getDao()).findByTable(table);
   }
 }

