#import "B2bProduct.h"

@implementation B2bProduct
	
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
    return [NSString stringWithFormat:@" Size   %@ SupplierType   %@ BaseProNo   %@ Volume   %@ ProId   %@ PicAll   %@ Name   %@ SpuulierName   %@ SupplierId   %@ PrimeCost   %@ ProDesc   %@ ApplDesc   %@ GoodsAttr3   %@ CategoryId   %@ ParentIdPath   %@ Pic   %@ CategoryName   %@ Price   %@ Quantity   %@ GoodsAttr2   %@ Capacity   %@ GoodsAttr4   %@ ParentNamePath   %@ BaseStatus   %@ BaseProId   %@ GoodsAttr1   %@ BarCode   %@ SpecsId   %@ Remarks   %@ RestId   %@ TargetClient   %@ GoodsAttr5   %@ ProColorNo   %@ SpecsName   %@ ",
   self.size ,
   self.supplierType ,
   self.baseProNo ,
   self.volume ,
   self.proId ,
   self.picAll ,
   self.name ,
   self.spuulierName ,
   self.supplierId ,
   self.primeCost ,
   self.proDesc ,
   self.applDesc ,
   self.goodsAttr3 ,
   self.categoryId ,
   self.parentIdPath ,
   self.pic ,
   self.categoryName ,
   self.price ,
   self.quantity ,
   self.goodsAttr2 ,
   self.capacity ,
   self.goodsAttr4 ,
   self.parentNamePath ,
   self.baseStatus ,
   self.baseProId ,
   self.goodsAttr1 ,
   self.barCode ,
   self.specsId ,
   self.remarks ,
   self.restId ,
   self.targetClient ,
   self.goodsAttr5 ,
   self.proColorNo ,
   self.specsName 
 ];
}

@end
