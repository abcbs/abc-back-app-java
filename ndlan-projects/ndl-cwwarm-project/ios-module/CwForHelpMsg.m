#import "CwForHelpMsg.h"

@implementation CwForHelpMsg
	
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
    return [NSString stringWithFormat:@" FhbContactAddr   %@ FhbContactPhone   %@ ForHelpByType   %@ FhbJoinQty   %@ CreateTime   %@ ForHelpOrg   %@ CreateBy   %@ FhbDefaultPic   %@ FhbRcvGoodsQty   %@ FhbMsgType   %@ FhbContact   %@ ForHelpBy   %@ FhbRcvLoveQty   %@ FhbStatus   %@ UpdateBy   %@ UpdateTime   %@ FhbPic   %@ ForHelpBusiType   %@ FhbOccurDatetime   %@ FhbCollectionQty   %@ ForHelpType   %@ ForHelpBusi   %@ FhbOccurPlace   %@ ForHelpByName   %@ FhbCommentQty   %@ CwHelpMsgId   %@ ForHelpOrgName   %@ FhbDetailMsg   %@ FhbLifeStatus   %@ ForHelpByPic   %@ ",
   self.fhbContactAddr ,
   self.fhbContactPhone ,
   self.forHelpByType ,
   self.fhbJoinQty ,
   self.createTime ,
   self.forHelpOrg ,
   self.createBy ,
   self.fhbDefaultPic ,
   self.fhbRcvGoodsQty ,
   self.fhbMsgType ,
   self.fhbContact ,
   self.forHelpBy ,
   self.fhbRcvLoveQty ,
   self.fhbStatus ,
   self.updateBy ,
   self.updateTime ,
   self.fhbPic ,
   self.forHelpBusiType ,
   self.fhbOccurDatetime ,
   self.fhbCollectionQty ,
   self.forHelpType ,
   self.forHelpBusi ,
   self.fhbOccurPlace ,
   self.forHelpByName ,
   self.fhbCommentQty ,
   self.cwHelpMsgId ,
   self.forHelpOrgName ,
   self.fhbDetailMsg ,
   self.fhbLifeStatus ,
   self.forHelpByPic 
 ];
}

@end
