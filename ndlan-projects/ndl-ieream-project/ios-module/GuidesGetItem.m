#import "GuidesGetItem.h"

@implementation GuidesGetItem
	
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
    return [NSString stringWithFormat:@" More   %d DataGuidesGetItem   %@ Status   %d Info   %@ ",
   self.more ,
   self.dataGuidesGetItem ,
   self.status ,
   self.info 
 ];
}

@end
