#import "District_listCity_listProvinceDataLocationsGetList.h"

@implementation District_listCity_listProvinceDataLocationsGetList
	
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
    return [NSString stringWithFormat:@" Id   %@ Name   %@ Count_item   %@ Has_child   %d ",
   self.id ,
   self.name ,
   self.count_item ,
   self.has_child 
 ];
}

@end
