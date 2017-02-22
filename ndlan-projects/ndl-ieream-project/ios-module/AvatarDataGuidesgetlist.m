#import "AvatarDataGuidesgetlist.h"

@implementation AvatarDataGuidesgetlist
	
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
    return [NSString stringWithFormat:@" Has_avatar   %d Avatar   %@ ",
   self.has_avatar ,
   self.avatar 
 ];
}

@end
