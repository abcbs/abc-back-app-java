#import "GuidesGetRellist.h"

@implementation GuidesGetRellist
	
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
    return [NSString stringWithFormat:@" Status   %d DataGuidesGetRellist   %@ More   %d ",
   self.status ,
   self.dataGuidesGetRellist ,
   self.more 
 ];
}

@end
