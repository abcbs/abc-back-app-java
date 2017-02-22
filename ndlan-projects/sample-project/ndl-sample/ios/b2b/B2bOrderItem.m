#import "B2bOrderItem.h"

@implementation B2bOrderItem
	
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
    return [NSString stringWithFormat:@" Price   %@ BId   %@ ProDesc   %@ RmtDetailId   %@ Quantity   %@ Volume   %@ OrderPkgCode   %@ NumberOfBroken   %@ SupplierId   %@ PriceAgencyName   %@ Privilege   %@ Pic   %@ IsCodeless   %@ RmtPkgId   %@ BdNo   %@ StartPointQyt   %@ AmountPaid   %@ Capacity   %@ ProColorNo   %@ RestName   %@ Discount   %@ RmtStatus   %@ BaseProId   %@ ParentNamePath   %@ StrategyDesc   %@ Size   %@ ParentIdPath   %@ SupplierName   %@ ApplDesc   %@ SlsPmtnId   %@ PriceAgencyId   %@ Subtotal   %@ ProName   %@ SpecsId   %@ TargetClient   %@ OrderPkgId   %@ ProId   %@ CategoryName   %@ CartItemId   %@ DamageBdId   %@ SpecsName   %@ BaseProNo   %@ BdId   %@ CategoryId   %@ RestId   %@ StorageStatus   %@ PriCatLineId   %@ BarCode   %@ ",
   self.price ,
   self.bId ,
   self.proDesc ,
   self.rmtDetailId ,
   self.quantity ,
   self.volume ,
   self.orderPkgCode ,
   self.numberOfBroken ,
   self.supplierId ,
   self.priceAgencyName ,
   self.privilege ,
   self.pic ,
   self.isCodeless ,
   self.rmtPkgId ,
   self.bdNo ,
   self.startPointQyt ,
   self.amountPaid ,
   self.capacity ,
   self.proColorNo ,
   self.restName ,
   self.discount ,
   self.rmtStatus ,
   self.baseProId ,
   self.parentNamePath ,
   self.strategyDesc ,
   self.size ,
   self.parentIdPath ,
   self.supplierName ,
   self.applDesc ,
   self.slsPmtnId ,
   self.priceAgencyId ,
   self.subtotal ,
   self.proName ,
   self.specsId ,
   self.targetClient ,
   self.orderPkgId ,
   self.proId ,
   self.categoryName ,
   self.cartItemId ,
   self.damageBdId ,
   self.specsName ,
   self.baseProNo ,
   self.bdId ,
   self.categoryId ,
   self.restId ,
   self.storageStatus ,
   self.priCatLineId ,
   self.barCode 
 ];
}

@end
