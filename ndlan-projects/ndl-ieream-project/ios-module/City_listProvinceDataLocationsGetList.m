#import "City_listProvinceDataLocationsGetList.h"

@implementation City_listProvinceDataLocationsGetList
	
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
    return [NSString stringWithFormat:@" Id   %@ Name   %@ Count_item   %@ District_listCity_listProvinceDataLocationsGetList   %@ Has_child   %d ",
   self.id ,
   self.name ,
   self.count_item ,
   self.district_listCity_listProvinceDataLocationsGetList ,
   self.has_child 
 ];
}

@end
