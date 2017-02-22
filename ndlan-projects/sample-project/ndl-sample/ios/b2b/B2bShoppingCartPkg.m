#import "B2bShoppingCartPkg.h"

@implementation B2bShoppingCartPkg
	
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
    return [NSString stringWithFormat:@" StrategyDesc   %@ SlsPmtnId   %@ CartId   %@ Remark   %@ Amount   %@ Derate   %@ Discount   %@ RestId   %@ CustomerName   %@ CartPkgId   %@ PayType   %@ SupplierName   %@ CustomerId   %@ RestName   %@ SupplierId   %@ AmountPaid   %@ CodelessSum   %@ TargetClient   %@ ",
   self.strategyDesc ,
   self.slsPmtnId ,
   self.cartId ,
   self.remark ,
   self.amount ,
   self.derate ,
   self.discount ,
   self.restId ,
   self.customerName ,
   self.cartPkgId ,
   self.payType ,
   self.supplierName ,
   self.customerId ,
   self.restName ,
   self.supplierId ,
   self.amountPaid ,
   self.codelessSum ,
   self.targetClient ,
 ];
}

@end
