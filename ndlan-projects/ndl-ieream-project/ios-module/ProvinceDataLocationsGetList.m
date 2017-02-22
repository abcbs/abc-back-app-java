#import "ProvinceDataLocationsGetList.h"

@implementation ProvinceDataLocationsGetList
	
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
    return [NSString stringWithFormat:@" Name   %@ City_listProvinceDataLocationsGetList   %@ Count_item   %@ Id   %@ Has_child   %d ",
   self.name ,
   self.city_listProvinceDataLocationsGetList ,
   self.count_item ,
   self.id ,
   self.has_child 
 ];
}

@end
