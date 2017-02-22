#import "Tag_listDataReamsGetItem.h"

@implementation Tag_listDataReamsGetItem
	
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
    return [NSString stringWithFormat:@" Id   %@ Title   %@ ",
   self.id ,
   self.title 
 ];
}

@end
