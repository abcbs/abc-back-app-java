 package com.ndlan.canyin.base.repository.synch;
 
 import com.ndlan.canyin.base.entity.synch.TransferCarrier;

 import java.io.Serializable;
 import java.util.ArrayList;
import java.util.List;
 
 public class DataPackage
   implements Serializable
 {
   private List<TransferCarrier> transferCarrierList = new ArrayList();
 
   public List<TransferCarrier> getTransferCarrierList() {
     return this.transferCarrierList;
   }
 
   public void setTransferCarrierList(List<TransferCarrier> transferCarrierList) {
     this.transferCarrierList = transferCarrierList;
   }
 }

