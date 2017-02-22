#import "ReamsGetList.h"

@implementation ReamsGetList
	
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
    return [NSString stringWithFormat:@" More   %d Status   %d DataReamsGetList   %@ ",
   self.more ,
   self.status ,
   self.dataReamsGetList 
 ];
}

@end
