#import "DistrictDataHotelssearch.h"

@implementation DistrictDataHotelssearch
	
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
    return [NSString stringWithFormat:@" Id   %@ Name   %@ Has_location   %d ",
   self.id ,
   self.name ,
   self.has_location 
 ];
}

@end
