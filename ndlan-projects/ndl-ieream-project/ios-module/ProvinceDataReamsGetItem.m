#import "ProvinceDataReamsGetItem.h"

@implementation ProvinceDataReamsGetItem
	
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
    return [NSString stringWithFormat:@" Name   %@ Has_location   %d Id   %@ ",
   self.name ,
   self.has_location ,
   self.id 
 ];
}

@end
