#import "HotelsGetRellist.h"

@implementation HotelsGetRellist
	
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
    return [NSString stringWithFormat:@" More   %d Status   %d DataHotelsGetRellist   %@ ",
   self.more ,
   self.status ,
   self.dataHotelsGetRellist 
 ];
}

@end
