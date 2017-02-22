#import "CwHoldActivityMsg.h"

@implementation CwHoldActivityMsg
	
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
    return [NSString stringWithFormat:@" HamCommentQty   %@ HamBusiStatus   %@ HamBusiSummary   %@ HamPic   %@ HamJoinQty   %@ UpdateTime   %@ UpdateBy   %@ HamOrgName   %@ HamDefaultPic   %@ CwActivityMsgId   %@ HamBusiAddr   %@ CreateTime   %@ HamOrg   %@ HamType   %@ HamSponsor   %@ HamRcvLoveQty   %@ HamByType   %@ HamBusiStartPlace   %@ HamBusiType   %@ HamEnrollEndDatetime   %@ HamBusiDetailMsg   %@ HamByPic   %@ HamContactPhone   %@ HamLifeStatus   %@ HamEnrollStartDatetime   %@ HamBy   %@ HamBusiEndPlace   %@ CreateBy   %@ HamRcvGoodsQty   %@ HamCollectionQty   %@ HamByName   %@ ",
   self.hamCommentQty ,
   self.hamBusiStatus ,
   self.hamBusiSummary ,
   self.hamPic ,
   self.hamJoinQty ,
   self.updateTime ,
   self.updateBy ,
   self.hamOrgName ,
   self.hamDefaultPic ,
   self.cwActivityMsgId ,
   self.hamBusiAddr ,
   self.createTime ,
   self.hamOrg ,
   self.hamType ,
   self.hamSponsor ,
   self.hamRcvLoveQty ,
   self.hamByType ,
   self.hamBusiStartPlace ,
   self.hamBusiType ,
   self.hamEnrollEndDatetime ,
   self.hamBusiDetailMsg ,
   self.hamByPic ,
   self.hamContactPhone ,
   self.hamLifeStatus ,
   self.hamEnrollStartDatetime ,
   self.hamBy ,
   self.hamBusiEndPlace ,
   self.createBy ,
   self.hamRcvGoodsQty ,
   self.hamCollectionQty ,
   self.hamByName 
 ];
}

@end
