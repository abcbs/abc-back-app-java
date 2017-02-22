 package com.ndlan.canyin.frontdesk.service.ylgl;
 
 import com.ndlan.canyin.frontdesk.service.BaseService;
import com.ndlan.canyin.base.entity.ylgl.RawMaterial;
 import com.ndlan.canyin.base.repository.ylgl.RawMaterialDao;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
 @Component
 @Transactional(readOnly=true)
 @Lazy
 public class RawMaterialService extends BaseService<RawMaterialDao, RawMaterial>
 {
   @Autowired
   public void setRawMaterialDao(RawMaterialDao rawMaterialDao)
   {
     super.setDao(rawMaterialDao);
   }
 }

