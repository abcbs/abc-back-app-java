#import "CwUserExtend.h"

@implementation CwUserExtend
	
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
    return [NSString stringWithFormat:@" AuthorizationMake   %@ CwUserExtendId   %@ CueBusiStatus   %@ ResidenceAdr   %@ EmpNum   %@ JobPic   %@ SysdataType   %@ LoginUsername   %@ Name   %@ CueCommentQty   %@ RestId   %@ UpdateTime   %@ Moblie   %@ Address   %@ CueJoinQty   %@ UpdateBy   %@ EmpStatus   %@ Gender   %@ CueRcvGoodsQty   %@ EmpId   %@ CueCollectionQty   %@ CueLifeStatus   %@ BarPath   %@ CreateBy   %@ AuthorizationCode   %@ CreateTime   %@ IdCard   %@ EmerContact   %@ CueRcvLoveQty   %@ ",
   self.authorizationMake ,
   self.cwUserExtendId ,
   self.cueBusiStatus ,
   self.residenceAdr ,
   self.empNum ,
   self.jobPic ,
   self.sysdataType ,
   self.loginUsername ,
   self.name ,
   self.cueCommentQty ,
   self.restId ,
   self.updateTime ,
   self.moblie ,
   self.address ,
   self.cueJoinQty ,
   self.updateBy ,
   self.empStatus ,
   self.gender ,
   self.cueRcvGoodsQty ,
   self.empId ,
   self.cueCollectionQty ,
   self.cueLifeStatus ,
   self.barPath ,
   self.createBy ,
   self.authorizationCode ,
   self.createTime ,
   self.idCard ,
   self.emerContact ,
   self.cueRcvLoveQty 
 ];
}

@end
