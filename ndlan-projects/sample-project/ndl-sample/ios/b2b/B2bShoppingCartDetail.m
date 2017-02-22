#import "B2bShoppingCartDetail.h"

@implementation B2bShoppingCartDetail
	
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
    return [NSString stringWithFormat:@" ProDesc   %@ Volume   %@ Discount   %@ Price   %@ Subtotal   %@ BarCode   %@ Privilege   %@ RestName   %@ SpecsName   %@ CategoryName   %@ SupplierId   %@ Size   %@ SupplierName   %@ SpecsId   %@ CartPkgId   %@ StartPointQyt   %@ CategoryId   %@ ProColorNo   %@ SlsPmtnId   %@ Name   %@ RestId   %@ StrategyDesc   %@ ApplDesc   %@ PayStatus   %@ Capacity   %@ Pic   %@ BaseProNo   %@ TargetClient   %@ CartId   %@ ProId   %@ CartItemId   %@ Quantity   %@ BaseProId   %@ IsCodeless   %@ PriceAgencyName   %@ PriceAgencyId   %@ ",
   self.proDesc ,
   self.volume ,
   self.discount ,
   self.price ,
   self.subtotal ,
   self.barCode ,
   self.privilege ,
   self.restName ,
   self.specsName ,
   self.categoryName ,
   self.supplierId ,
   self.size ,
   self.supplierName ,
   self.specsId ,
   self.cartPkgId ,
   self.startPointQyt ,
   self.categoryId ,
   self.proColorNo ,
   self.slsPmtnId ,
   self.name ,
   self.restId ,
   self.strategyDesc ,
   self.applDesc ,
   self.payStatus ,
   self.capacity ,
   self.pic ,
   self.baseProNo ,
   self.targetClient ,
   self.cartId ,
   self.proId ,
   self.cartItemId ,
   self.quantity ,
   self.baseProId ,
   self.isCodeless ,
   self.priceAgencyName ,
   self.priceAgencyId 
 ];
}

@end
