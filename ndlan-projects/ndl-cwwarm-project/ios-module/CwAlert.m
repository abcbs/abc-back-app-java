#import "CwAlert.h"

@implementation CwAlert
	
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
    return [NSString stringWithFormat:@" AlertByPic   %@ AlertSourceType   %@ CwAlertId   %@ AlertRcv   %@ AlertByName   %@ AlertBusiStatus   %@ AlertRcvName   %@ UpdateTime   %@ AlertMsgUri   %@ CreateBy   %@ AlertById   %@ AlertSecondSourceType   %@ AlertRcvPic   %@ AlertLifeStatus   %@ UpdateBy   %@ AlertMsg   %@ CreateTime   %@ ",
   self.alertByPic ,
   self.alertSourceType ,
   self.cwAlertId ,
   self.alertRcv ,
   self.alertByName ,
   self.alertBusiStatus ,
   self.alertRcvName ,
   self.updateTime ,
   self.alertMsgUri ,
   self.createBy ,
   self.alertById ,
   self.alertSecondSourceType ,
   self.alertRcvPic ,
   self.alertLifeStatus ,
   self.updateBy ,
   self.alertMsg ,
   self.createTime 
 ];
}

@end
