#import "B2bPriceCategoryDetail.h"

@implementation B2bPriceCategoryDetail
	
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
    return [NSString stringWithFormat:@" StartPointQyt   %@ PriCatLineId   %@ EffectiveDate   %@ BaseProId   %@ ParentNamePath   %@ PriCatHeadId   %@ AgencyId   %@ ExpiryDate   %@ SpecsId   %@ ParentIdPath   %@ CategoryId   %@ Name   %@ PromotionPrice   %@ ProColorNo   %@ CategoryName   %@ ProId   %@ BaseProNo   %@ SpecsName   %@ Price   %@ SpuulierName   %@ AgencyName   %@ Volume   %@ Remarks   %@ SupplierId   %@ ",
   self.startPointQyt ,
   self.priCatLineId ,
   self.effectiveDate ,
   self.baseProId ,
   self.parentNamePath ,
   self.priCatHeadId ,
   self.agencyId ,
   self.expiryDate ,
   self.specsId ,
   self.parentIdPath ,
   self.categoryId ,
   self.name ,
   self.promotionPrice ,
   self.proColorNo ,
   self.categoryName ,
   self.proId ,
   self.baseProNo ,
   self.specsName ,
   self.price ,
   self.spuulierName ,
   self.agencyName ,
   self.volume ,
   self.remarks ,
   self.supplierId ,
 ];
}

@end
