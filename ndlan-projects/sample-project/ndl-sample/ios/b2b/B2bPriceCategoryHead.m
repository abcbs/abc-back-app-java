#import "B2bPriceCategoryHead.h"

@implementation B2bPriceCategoryHead
	
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
    return [NSString stringWithFormat:@" BarCode   %@ Pic   %@ ProId   %@ CategoryName   %@ AgencyId   %@ Name   %@ TargetClient   %@ BaseProNo   %@ ProDesc   %@ Capacity   %@ SpecsName   %@ SpuulierName   %@ AgencyName   %@ ParentIdPath   %@ ProColorNo   %@ ParentNamePath   %@ Remarks   %@ SupplierId   %@ PriCatHeadId   %@ SpecsId   %@ BaseProId   %@ CategoryId   %@ ApplDesc   %@ Price   %@ Volume   %@ Size   %@ ",
   self.barCode ,
   self.pic ,
   self.proId ,
   self.categoryName ,
   self.agencyId ,
   self.name ,
   self.targetClient ,
   self.baseProNo ,
   self.proDesc ,
   self.capacity ,
   self.specsName ,
   self.spuulierName ,
   self.agencyName ,
   self.parentIdPath ,
   self.proColorNo ,
   self.parentNamePath ,
   self.remarks ,
   self.supplierId ,
   self.priCatHeadId ,
   self.specsId ,
   self.baseProId ,
   self.categoryId ,
   self.applDesc ,
   self.price ,
   self.volume ,
   self.size 
 ];
}

@end
