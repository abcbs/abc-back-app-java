#import "DistrictDataReamsGetItem.h"

@implementation DistrictDataReamsGetItem
	
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
    return [NSString stringWithFormat:@" Id   %@ Has_location   %d Name   %@ ",
   self.id ,
   self.has_location ,
   self.name 
 ];
}

@end
