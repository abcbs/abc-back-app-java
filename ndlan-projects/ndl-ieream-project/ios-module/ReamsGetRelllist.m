#import "ReamsGetRelllist.h"

@implementation ReamsGetRelllist
	
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
    return [NSString stringWithFormat:@" Status   %d DataReamsGetRelllist   %@ More   %d ",
   self.status ,
   self.dataReamsGetRelllist ,
   self.more 
 ];
}

@end
