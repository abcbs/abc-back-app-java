#import "DistrictDataHotelsGetItem.h"

@implementation DistrictDataHotelsGetItem
	
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
    return [NSString stringWithFormat:@" Name   %@ Id   %@ Has_location   %d ",
   self.name ,
   self.id ,
   self.has_location 
 ];
}

@end
