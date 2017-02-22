#import "TagsGetList.h"

@implementation TagsGetList
	
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
    return [NSString stringWithFormat:@" DataTagsGetList   %@ Status   %d More   %d ",
   self.dataTagsGetList ,
   self.status ,
   self.more 
 ];
}

@end
