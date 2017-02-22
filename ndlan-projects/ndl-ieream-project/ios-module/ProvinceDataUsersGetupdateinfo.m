#import "ProvinceDataUsersGetupdateinfo.h"

@implementation ProvinceDataUsersGetupdateinfo
	
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
    return [NSString stringWithFormat:@" Has_location   %d Name   %@ Id   %@ ",
   self.has_location ,
   self.name ,
   self.id 
 ];
}

@end
