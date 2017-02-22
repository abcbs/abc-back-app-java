#import "Union_guide_id_listDataGuidesgetlist.h"

@implementation Union_guide_id_listDataGuidesgetlist
	
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
