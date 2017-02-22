#import "B2bLogisticsOrderDelivery.h"

@implementation B2bLogisticsOrderDelivery
	
- (instancetype)init{
    
    
    if ((self = [super init])!=nil) {
        //
    }
    return self;
}

- (id)copyWithZone:(NSZone *)zone{
    
    
    return self;
}

-(NSString *)description

{   
    return [NSString stringWithFormat:@" OrderPkgCode   %@ CustomName   %@ GoodsType   %@ OrderPkgId   %@ OrderAmount   %@ LgstOrdDlvId   %@ DeliveryAddressId   %@ CustomId   %@ Remake   %@ LogisticsLineId   %@ LogisticsLineName   %@ PostCode   %@ ReceiveTellcall   %@ ReceivePhone   %@ OrderDeliveryId   %@ SupplierId   %@ Region   %@ OrderDate   %@ SupplierName   %@ ReceiveName   %@ LogisticsHeadCode   %@ DetailAddress   %@ ",
   self.orderPkgCode ,
   self.customName ,
   self.goodsType ,
   self.orderPkgId ,
   self.orderAmount ,
   self.lgstOrdDlvId ,
   self.deliveryAddressId ,
   self.customId ,
   self.remake ,
   self.logisticsLineId ,
   self.logisticsLineName ,
   self.postCode ,
   self.receiveTellcall ,
   self.receivePhone ,
   self.orderDeliveryId ,
   self.supplierId ,
   self.region ,
   self.orderDate ,
   self.supplierName ,
   self.receiveName ,
   self.logisticsHeadCode ,
   self.detailAddress 
 ];
}

@end
