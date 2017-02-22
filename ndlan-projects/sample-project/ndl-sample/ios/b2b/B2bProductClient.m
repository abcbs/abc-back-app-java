#import "B2bProductClient.h"

@implementation B2bProductClient
	
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
    return [NSString stringWithFormat:@" Size   %@ SpuulierName   %@ SupplierId   %@ Name   %@ Capacity   %@ ProDesc   %@ SupplierType   %@ ApplDesc   %@ GoodsAttr4   %@ Pic   %@ Volume   %@ RestId   %@ Price   %@ ParentIdPath   %@ ProColorNo   %@ Remarks   %@ SpecsName   %@ SpecsId   %@ BaseProNo   %@ CategoryId   %@ GoodsAttr3   %@ GoodsAttr2   %@ Quantity   %@ BarCode   %@ GoodsAttr1   %@ TargetClient   %@ PrimeCost   %@ CategoryName   %@ BaseStatus   %@ GoodsAttr5   %@ BaseProId   %@ ParentNamePath   %@ ProId   %@ PicAll   %@ ",
   self.size ,
   self.spuulierName ,
   self.supplierId ,
   self.name ,
   self.capacity ,
   self.proDesc ,
   self.supplierType ,
   self.applDesc ,
   self.goodsAttr4 ,
   self.pic ,
   self.volume ,
   self.restId ,
   self.price ,
   self.parentIdPath ,
   self.proColorNo ,
   self.remarks ,
   self.specsName ,
   self.specsId ,
   self.baseProNo ,
   self.categoryId ,
   self.goodsAttr3 ,
   self.goodsAttr2 ,
   self.quantity ,
   self.barCode ,
   self.goodsAttr1 ,
   self.targetClient ,
   self.primeCost ,
   self.categoryName ,
   self.baseStatus ,
   self.goodsAttr5 ,
   self.baseProId ,
   self.parentNamePath ,
   self.proId ,
   self.picAll 
 ];
}

@end
