#import "DistrictDataGuidesgetlist.h"

@implementation DistrictDataGuidesgetlist
	
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
    return [NSString stringWithFormat:@" Name   %@ Id   %d Has_location   %d ",
   self.name ,
   self.id ,
   self.has_location 
 ];
}

@end
