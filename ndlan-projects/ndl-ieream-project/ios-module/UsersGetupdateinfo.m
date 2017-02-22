#import "UsersGetupdateinfo.h"

@implementation UsersGetupdateinfo
	
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
    return [NSString stringWithFormat:@" Status   %d Info   %@ DataUsersGetupdateinfo   %@ ",
   self.status ,
   self.info ,
   self.dataUsersGetupdateinfo 
 ];
}

@end
