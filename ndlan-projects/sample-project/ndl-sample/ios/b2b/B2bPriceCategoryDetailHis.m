#import "B2bPriceCategoryDetailHis.h"

@implementation B2bPriceCategoryDetailHis
	
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
    return [NSString stringWithFormat:@" Price   %@ OrignUpdateTime   %@ OrignCreateBy   %@ PriCtyDtlhisId   %@ OrignUpdateBy   %@ PriCatLineId   %@ StartPointQyt   %@ OrignSynVersion   %d PriCatHeadId   %@ Remarks   %@ ExpiryDate   %@ OrignVersion   %d EffectiveDate   %@ OrignCreateTime   %@ ",
   self.price ,
   self.orignUpdateTime ,
   self.orignCreateBy ,
   self.priCtyDtlhisId ,
   self.orignUpdateBy ,
   self.priCatLineId ,
   self.startPointQyt ,
   self.orignSynVersion ,
   self.priCatHeadId ,
   self.remarks ,
   self.expiryDate ,
   self.orignVersion ,
   self.effectiveDate ,
   self.orignCreateTime 
 ];
}

@end
