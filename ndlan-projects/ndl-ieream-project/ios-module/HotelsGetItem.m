#import "HotelsGetItem.h"

@implementation HotelsGetItem
	
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
    return [NSString stringWithFormat:@" More   %d DataHotelsGetItem   %@ Status   %d Info   %@ ",
   self.more ,
   self.dataHotelsGetItem ,
   self.status ,
   self.info 
 ];
}

@end
