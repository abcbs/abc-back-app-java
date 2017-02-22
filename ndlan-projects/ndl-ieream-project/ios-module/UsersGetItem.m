#import "UsersGetItem.h"

@implementation UsersGetItem
	
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
    return [NSString stringWithFormat:@" Status   %d DataUsersGetItem   %@ Info   %@ ",
   self.status ,
   self.dataUsersGetItem ,
   self.info 
 ];
}

@end
