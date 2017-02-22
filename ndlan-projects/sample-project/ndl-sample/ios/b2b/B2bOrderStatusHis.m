#import "B2bOrderStatusHis.h"

@implementation B2bOrderStatusHis
	
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
    return [NSString stringWithFormat:@" OrignVersion   %d OrignUpdateTime   %@ OrignStatus   %@ OrignCreateTime   %@ OrignUpdateBy   %@ OrderPkgCode   %@ OrderPkgId   %@ OrignCreateBy   %@ OrignSynVersion   %d OrderStatusHisId   %@ UpdateReason   %@ OrderHeadId   %@ ",
   self.orignVersion ,
   self.orignUpdateTime ,
   self.orignStatus ,
   self.orignCreateTime ,
   self.orignUpdateBy ,
   self.orderPkgCode ,
   self.orderPkgId ,
   self.orignCreateBy ,
   self.orignSynVersion ,
   self.orderStatusHisId ,
   self.updateReason ,
   self.orderHeadId ,
 ];
}

@end
