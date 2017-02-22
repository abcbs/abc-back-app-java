#import "CmRoleUser.h"

@implementation CmRoleUser
	
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
    return [NSString stringWithFormat:@" FkUserId   %@ RestId   %@ BarPath   %@ FkRoleId   %@ RoleUserId   %@ ",
   self.fkUserId ,
   self.restId ,
   self.barPath ,
   self.fkRoleId ,
   self.roleUserId 
 ];
}

@end
