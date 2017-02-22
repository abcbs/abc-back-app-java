#import "B2bLogisticsLine.h"

@implementation B2bLogisticsLine
	
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
    return [NSString stringWithFormat:@" SupplierName   %@ DeliveryAddressId   %@ LogisticsLineId   %@ DeliveryStartDate   %@ LogisticsLineCode   %@ DeliveryAddress   %@ SupplierId   %@ DeliveryUserPhone   %@ DeliveryEndDate   %@ Remake   %@ LogisticsLineName   %@ DeliveryUser   %@ ",
   self.supplierName ,
   self.deliveryAddressId ,
   self.logisticsLineId ,
   self.deliveryStartDate ,
   self.logisticsLineCode ,
   self.deliveryAddress ,
   self.supplierId ,
   self.deliveryUserPhone ,
   self.deliveryEndDate ,
   self.remake ,
   self.logisticsLineName ,
   self.deliveryUser 
 ];
}

@end
