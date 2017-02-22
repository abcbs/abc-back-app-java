#import "B2bOrderDelivery.h"

@implementation B2bOrderDelivery
	
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
    return [NSString stringWithFormat:@" DeliveryAddressId   %@ Region   %@ ReceiveTellcall   %@ SupplierName   %@ ReceiveName   %@ OrderDeliveryId   %@ CustomId   %@ DetailAddress   %@ SupplierId   %@ Remake   %@ ReceivePhone   %@ CustomName   %@ OrderPkgId   %@ OrderPkgCode   %@ PostCode   %@ ",
   self.deliveryAddressId ,
   self.region ,
   self.receiveTellcall ,
   self.supplierName ,
   self.receiveName ,
   self.orderDeliveryId ,
   self.customId ,
   self.detailAddress ,
   self.supplierId ,
   self.remake ,
   self.receivePhone ,
   self.customName ,
   self.orderPkgId ,
   self.orderPkgCode ,
   self.postCode ,
 ];
}

@end
