#import "B2bSupplierCardAccount.h"

@implementation B2bSupplierCardAccount
	
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
    return [NSString stringWithFormat:@" Remake   %@ CatNo   %@ IsDefault   %@ PhoneNo   %@ CardholderId   %@ CardinfoSummay   %@ Branch   %@ Subbranch   %@ SupCardAcntId   %@ Bank   %@ SupplierId   %@ CardNo   %@ CardholderName   %@ SupplierName   %@ ",
   self.remake ,
   self.catNo ,
   self.isDefault ,
   self.phoneNo ,
   self.cardholderId ,
   self.cardinfoSummay ,
   self.branch ,
   self.subbranch ,
   self.supCardAcntId ,
   self.bank ,
   self.supplierId ,
   self.cardNo ,
   self.cardholderName ,
   self.supplierName ,
 ];
}

@end
