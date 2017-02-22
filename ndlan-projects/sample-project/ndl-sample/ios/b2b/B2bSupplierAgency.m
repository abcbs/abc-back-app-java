#import "B2bSupplierAgency.h"

@implementation B2bSupplierAgency
	
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
    return [NSString stringWithFormat:@" SupplierName   %@ DeliveryAddress   %@ SendAddressId   %@ ContactUser   %@ SupplierAgencyId   %@ AgencyId   %@ AgencyName   %@ Remake   %@ ContactPhone   %@ SupplierId   %@ ",
   self.supplierName ,
   self.deliveryAddress ,
   self.sendAddressId ,
   self.contactUser ,
   self.supplierAgencyId ,
   self.agencyId ,
   self.agencyName ,
   self.remake ,
   self.contactPhone ,
   self.supplierId 
 ];
}

@end
