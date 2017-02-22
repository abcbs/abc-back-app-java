#import "DataLocationsGetList.h"

@implementation DataLocationsGetList
	
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
    return [NSString stringWithFormat:@" ProvinceDataLocationsGetList   %@ ",
   self.provinceDataLocationsGetList 
 ];
}

@end
