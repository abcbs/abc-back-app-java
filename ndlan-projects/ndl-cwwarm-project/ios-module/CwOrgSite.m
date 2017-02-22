#import "CwOrgSite.h"

@implementation CwOrgSite
	
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
    return [NSString stringWithFormat:@" OstLifeStatus   %@ OstSiteAddrCity   %@ OstSiteContactsPhone   %@ OstBusiStatus   %@ OstParentSiteNamePath   %@ CreateTime   %@ OstParentId   %@ OstSiteGrade   %@ OstSiteAddrDetail   %@ UpdateTime   %@ OstSiteAddrProvince   %@ OstSiteContacts   %@ OstSiteAddrCounty   %@ CreateBy   %@ OstParentSiteName   %@ OstParentIdPath   %@ UpdateBy   %@ CwOrgSiteId   %@ OstSiteName   %@ ",
   self.ostLifeStatus ,
   self.ostSiteAddrCity ,
   self.ostSiteContactsPhone ,
   self.ostBusiStatus ,
   self.ostParentSiteNamePath ,
   self.createTime ,
   self.ostParentId ,
   self.ostSiteGrade ,
   self.ostSiteAddrDetail ,
   self.updateTime ,
   self.ostSiteAddrProvince ,
   self.ostSiteContacts ,
   self.ostSiteAddrCounty ,
   self.createBy ,
   self.ostParentSiteName ,
   self.ostParentIdPath ,
   self.updateBy ,
   self.cwOrgSiteId ,
   self.ostSiteName 
 ];
}

@end
