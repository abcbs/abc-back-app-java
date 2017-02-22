#import "Union_guide_id_listDataGuidesGetItem.h"

@implementation Union_guide_id_listDataGuidesGetItem
	
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
    return [NSString stringWithFormat:@" Uid   %@ ",
   self.uid 
 ];
}

@end
