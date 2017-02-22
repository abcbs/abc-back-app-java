#import "LocationsGetList.h"

@implementation LocationsGetList
	
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
    return [NSString stringWithFormat:@" More   %d Hot_city_dataLocationsGetList   %@ Status   %d DataLocationsGetList   %@ ",
   self.more ,
   self.hot_city_dataLocationsGetList ,
   self.status ,
   self.dataLocationsGetList 
 ];
}

@end
