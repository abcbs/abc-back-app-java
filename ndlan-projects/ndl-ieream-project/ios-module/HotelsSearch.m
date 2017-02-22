#import "HotelsSearch.h"

@implementation HotelsSearch
	
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
    return [NSString stringWithFormat:@" Status   %d Info   %@ More   %d DataHotelsSearch   %@ ",
   self.status ,
   self.info ,
   self.more ,
   self.dataHotelsSearch 
 ];
}

@end
