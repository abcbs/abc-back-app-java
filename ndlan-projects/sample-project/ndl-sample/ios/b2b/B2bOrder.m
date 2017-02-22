#import "B2bOrder.h"

@implementation B2bOrder
	
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
    return [NSString stringWithFormat:@" CodelessSum   %@ RestName   %@ Remark   %@ BId   %@ PayType   %@ TargetClient   %@ AllPrivilege   %@ CustomerId   %@ AllDiscount   %@ CustomerName   %@ BAmount   %@ BName   %@ CartId   %@ BNo   %@ RestId   %@ AmountPaid   %@ ",
   self.codelessSum ,
   self.restName ,
   self.remark ,
   self.bId ,
   self.payType ,
   self.targetClient ,
   self.allPrivilege ,
   self.customerId ,
   self.allDiscount ,
   self.customerName ,
   self.bAmount ,
   self.bName ,
   self.cartId ,
   self.bNo ,
   self.restId ,
   self.amountPaid 
 ];
}

@end
