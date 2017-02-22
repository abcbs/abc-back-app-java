#import "CwDonation.h"

@implementation CwDonation
	
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
    return [NSString stringWithFormat:@" CwDonationId   %@ DnDonatedPhone   %@ CreateTime   %@ DnLifeStatus   %@ DnDonatedPic   %@ DnDonaterOrgName   %@ CreateBy   %@ DnDonatedOrg   %@ DnDonaterId   %@ DnDonationWay   %@ DnDonationPic   %@ DnAttr2   %@ DnDonatedDetailMsg   %@ DnDonaterOrg   %@ UpdateBy   %@ CwDonationType   %@ DnDonatedMsgId   %@ DnDonatedName   %@ UpdateTime   %@ DnDonaterPic   %@ DnAttr1   %@ DnDonationStatus   %@ DnDonateGoodsQty   %@ DnDonaterName   %@ DnAttr3   %@ DnDonatedId   %@ DnDonatedOrgName   %@ DnAttr4   %@ DnDonateGoods   %@ DnDonatedAddr   %@ DnDonationDesc   %@ DnAttr5   %@ DnDonaterPhone   %@ ",
   self.cwDonationId ,
   self.dnDonatedPhone ,
   self.createTime ,
   self.dnLifeStatus ,
   self.dnDonatedPic ,
   self.dnDonaterOrgName ,
   self.createBy ,
   self.dnDonatedOrg ,
   self.dnDonaterId ,
   self.dnDonationWay ,
   self.dnDonationPic ,
   self.dnAttr2 ,
   self.dnDonatedDetailMsg ,
   self.dnDonaterOrg ,
   self.updateBy ,
   self.cwDonationType ,
   self.dnDonatedMsgId ,
   self.dnDonatedName ,
   self.updateTime ,
   self.dnDonaterPic ,
   self.dnAttr1 ,
   self.dnDonationStatus ,
   self.dnDonateGoodsQty ,
   self.dnDonaterName ,
   self.dnAttr3 ,
   self.dnDonatedId ,
   self.dnDonatedOrgName ,
   self.dnAttr4 ,
   self.dnDonateGoods ,
   self.dnDonatedAddr ,
   self.dnDonationDesc ,
   self.dnAttr5 ,
   self.dnDonaterPhone 
 ];
}

@end
