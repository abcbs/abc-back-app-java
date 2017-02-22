#import "GuidesGetList.h"

@implementation GuidesGetList
	
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
    return [NSString stringWithFormat:@" DataGuidesGetList   %@ More   %d Status   %d ",
   self.dataGuidesGetList ,
   self.more ,
   self.status 
 ];
}

@end
