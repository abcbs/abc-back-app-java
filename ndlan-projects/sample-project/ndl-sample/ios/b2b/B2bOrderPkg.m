#import "B2bOrderPkg.h"

@implementation B2bOrderPkg
	
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
    return [NSString stringWithFormat:@" PayType   %@ CustomerId   %@ OrderPkgId   %@ CustomerName   %@ OrderPkgName   %@ ExceptionDesc   %@ SupplierId   %@ StrategyDesc   %@ TargetClient   %@ OrderHeadId   %@ AllDiscount   %@ SupplierName   %@ StorageStatus   %@ OrderPkgCode   %@ UnpaidAmount   %@ Remark   %@ RestName   %@ CartId   %@ StatusDesc   %@ AmountPaid   %@ ExceptionSolve   %@ SlsPmtnId   %@ AllPrivilege   %@ CodelessSum   %@ RestId   %@ Amount   %@ ",
   self.payType ,
   self.customerId ,
   self.orderPkgId ,
   self.customerName ,
   self.orderPkgName ,
   self.exceptionDesc ,
   self.supplierId ,
   self.strategyDesc ,
   self.targetClient ,
   self.orderHeadId ,
   self.allDiscount ,
   self.supplierName ,
   self.storageStatus ,
   self.orderPkgCode ,
   self.unpaidAmount ,
   self.remark ,
   self.restName ,
   self.cartId ,
   self.statusDesc ,
   self.amountPaid ,
   self.exceptionSolve ,
   self.slsPmtnId ,
   self.allPrivilege ,
   self.codelessSum ,
   self.restId ,
   self.amount 
 ];
}

@end
