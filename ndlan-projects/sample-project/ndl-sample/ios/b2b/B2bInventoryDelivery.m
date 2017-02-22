#import "B2bInventoryDelivery.h"

@implementation B2bInventoryDelivery
	
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
    return [NSString stringWithFormat:@" DeliveryDate   %@ Remark   %@ DeliveryUser   %@ DeliveryQty   %@ InvHeadId   %@ InvDeliveryId   %@ DeliveryPrice   %@ Source   %@ ",
   self.deliveryDate ,
   self.remark ,
   self.deliveryUser ,
   self.deliveryQty ,
   self.invHeadId ,
   self.invDeliveryId ,
   self.deliveryPrice ,
   self.source 
 ];
}

@end
