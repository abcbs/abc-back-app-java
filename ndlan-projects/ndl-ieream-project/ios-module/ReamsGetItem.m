#import "ReamsGetItem.h"

@implementation ReamsGetItem
	
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
    return [NSString stringWithFormat:@" Status   %d DataReamsGetItem   %@ More   %d ",
   self.status ,
   self.dataReamsGetItem ,
   self.more 
 ];
}

@end
