#import "CmRole.h"

@implementation CmRole
	
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
    return [NSString stringWithFormat:@" SysdataType   %@ CreateBy   %@ CrId   %@ Name   %@ RestId   %@ RoleType   %@ UpdateTime   %@ CrStatus   %@ BarPath   %@ IsAllTablearea   %@ CreateTime   %@ UpdateBy   %@ IsStopUse   %@ ",
   self.sysdataType ,
   self.createBy ,
   self.crId ,
   self.name ,
   self.restId ,
   self.roleType ,
   self.updateTime ,
   self.crStatus ,
   self.barPath ,
   self.isAllTablearea ,
   self.createTime ,
   self.updateBy ,
   self.isStopUse 
 ];
}

@end
