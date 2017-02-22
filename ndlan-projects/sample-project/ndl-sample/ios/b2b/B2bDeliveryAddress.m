#import "B2bDeliveryAddress.h"

@implementation B2bDeliveryAddress
	
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
    return [NSString stringWithFormat:@" Region   %@ ReceiveName   %@ ReceivePhone   %@ DetailAddress   %@ ReceiveTellcall   %@ SupplierId   %@ DeliveryAddressId   %@ Remake   %@ SupplierName   %@ IsDefault   %@ PostCode   %@ ",
   self.region ,
   self.receiveName ,
   self.receivePhone ,
   self.detailAddress ,
   self.receiveTellcall ,
   self.supplierId ,
   self.deliveryAddressId ,
   self.remake ,
   self.supplierName ,
   self.isDefault ,
   self.postCode ,
 ];
}

@end
