#import "CityDataReamsgetlist.h"

@implementation CityDataReamsgetlist
	
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
    return [NSString stringWithFormat:@" Name   %@ Has_location   %d Id   %d ",
   self.name ,
   self.has_location ,
   self.id 
 ];
}

@end
