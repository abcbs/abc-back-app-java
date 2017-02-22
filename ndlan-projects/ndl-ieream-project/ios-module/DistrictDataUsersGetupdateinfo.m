#import "DistrictDataUsersGetupdateinfo.h"

@implementation DistrictDataUsersGetupdateinfo
	
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
    return [NSString stringWithFormat:@" Has_location   %d Id   %@ Name   %@ ",
   self.has_location ,
   self.id ,
   self.name 
 ];
}

@end
