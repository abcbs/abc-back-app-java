#import <Foundation/Foundation.h>


@interface CwOrgSite : NSObject
	
	
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * ostLifeStatus ;
    /**
     * 站点地址市
     **/
    @property(nonatomic,copy)NSString * ostSiteAddrCity ;
    /**
     * 联系人电话
     **/
    @property(nonatomic,copy)NSString * ostSiteContactsPhone ;
    /**
     * 站点状态【0不可用、1可用】
     **/
    @property(nonatomic,copy)NSString * ostBusiStatus ;
    /**
     * 站点名称路径
     **/
    @property(nonatomic,copy)NSString * ostParentSiteNamePath ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 站点父ID
     **/
    @property(nonatomic,copy)NSString * ostParentId ;
    /**
     * 站点等级
     **/
    @property(nonatomic,copy)NSString * ostSiteGrade ;
    /**
     * 站点地址明细
     **/
    @property(nonatomic,copy)NSString * ostSiteAddrDetail ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 站点地址省
     **/
    @property(nonatomic,copy)NSString * ostSiteAddrProvince ;
    /**
     * 联系人
     **/
    @property(nonatomic,copy)NSString * ostSiteContacts ;
    /**
     * 站点地址县
     **/
    @property(nonatomic,copy)NSString * ostSiteAddrCounty ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 站点父名称
     **/
    @property(nonatomic,copy)NSString * ostParentSiteName ;
    /**
     * 站点ID路径
     **/
    @property(nonatomic,copy)NSString * ostParentIdPath ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwOrgSiteId ;
    /**
     * 站点名称
     **/
    @property(nonatomic,copy)NSString * ostSiteName ;
 
@end