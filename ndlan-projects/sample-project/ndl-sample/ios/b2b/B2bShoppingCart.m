#import "B2bShoppingCart.h"

@implementation B2bShoppingCart
	
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
    return [NSString stringWithFormat:@" Total   %@ CustomerId   %@ AllDiscount   %@ RestName   %@ AllPrivilege   %@ CustomerName   %@ RestId   %@ TargetClient   %@ CartId   %@ ",
   self.total ,
   self.customerId ,
   self.allDiscount ,
   self.restName ,
   self.allPrivilege ,
   self.customerName ,
   self.restId ,
   self.targetClient ,
   self.cartId ,
 ];
}

@end
