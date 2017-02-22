#import "B2bOrderRmtPkg.h"

@implementation B2bOrderRmtPkg
	
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
    return [NSString stringWithFormat:@" OrderRmtPkgCode   %@ OrderRmtHeadId   %@ LogOrderPkgId   %@ CartId   %@ StrategyDesc   %@ Amount   %@ Derate   %@ SupplierName   %@ OrderPkgId   %@ CodelessSum   %@ Discount   %@ PayType   %@ OrderHeadId   %@ RestId   %@ CustomerName   %@ OrderRmtPkgId   %@ SupplierId   %@ OrignStatus   %@ SlsPmtnId   %@ RestName   %@ AmountPaid   %@ LogOrderPkgCode   %@ CustomerId   %@ Remark   %@ OrderPkgName   %@ GoodsCatQty   %@ OrderPkgCode   %@ StorageStatus   %@ ",
   self.orderRmtPkgCode ,
   self.orderRmtHeadId ,
   self.logOrderPkgId ,
   self.cartId ,
   self.strategyDesc ,
   self.amount ,
   self.derate ,
   self.supplierName ,
   self.orderPkgId ,
   self.codelessSum ,
   self.discount ,
   self.payType ,
   self.orderHeadId ,
   self.restId ,
   self.customerName ,
   self.orderRmtPkgId ,
   self.supplierId ,
   self.orignStatus ,
   self.slsPmtnId ,
   self.restName ,
   self.amountPaid ,
   self.logOrderPkgCode ,
   self.customerId ,
   self.remark ,
   self.orderPkgName ,
   self.goodsCatQty ,
   self.orderPkgCode ,
   self.storageStatus 
 ];
}

@end
