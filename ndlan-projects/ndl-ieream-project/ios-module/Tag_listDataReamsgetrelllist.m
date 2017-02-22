#import "Tag_listDataReamsgetrelllist.h"

@implementation Tag_listDataReamsgetrelllist
	
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
    return [NSString stringWithFormat:@" Title   %@ Id   %@ ",
   self.title ,
   self.id 
 ];
}

@end
