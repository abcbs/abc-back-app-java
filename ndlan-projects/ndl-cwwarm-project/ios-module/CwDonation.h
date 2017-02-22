#import <Foundation/Foundation.h>


@interface CwDonation : NSObject
	
	
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwDonationId ;
    /**
     * 被捐赠联系电话
     **/
    @property(nonatomic,copy)NSString * dnDonatedPhone ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * dnLifeStatus ;
    /**
     * 被捐赠人头像
     **/
    @property(nonatomic,copy)NSString * dnDonatedPic ;
    /**
     * 捐赠人机构名称
     **/
    @property(nonatomic,copy)NSString * dnDonaterOrgName ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 被捐赠人机构ID
     **/
    @property(nonatomic,copy)NSString * dnDonatedOrg ;
    /**
     * 捐赠人ID
     **/
    @property(nonatomic,copy)NSString * dnDonaterId ;
    /**
     * 捐赠方式
     **/
    @property(nonatomic,copy)NSString * dnDonationWay ;
    /**
     * 相关图片
     **/
    @property(nonatomic,copy)NSString * dnDonationPic ;
    /**
     * 扩展属性2
     **/
    @property(nonatomic,copy)NSString * dnAttr2 ;
    /**
     * 被捐赠项目信息描述
     **/
    @property(nonatomic,copy)NSString * dnDonatedDetailMsg ;
    /**
     * 捐赠人机构ID
     **/
    @property(nonatomic,copy)NSString * dnDonaterOrg ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 捐助分类【0捐助、1每日一捐、2捐赠求助】
     **/
    @property(nonatomic,copy)NSString * cwDonationType ;
    /**
     * 被捐赠项目ID
     **/
    @property(nonatomic,copy)NSString * dnDonatedMsgId ;
    /**
     * 被捐赠人名称
     **/
    @property(nonatomic,copy)NSString * dnDonatedName ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 捐赠者头像
     **/
    @property(nonatomic,copy)NSString * dnDonaterPic ;
    /**
     * 扩展属性1
     **/
    @property(nonatomic,copy)NSString * dnAttr1 ;
    /**
     * 捐赠状态【0捐赠中、1已发货、2已收获】
     **/
    @property(nonatomic,copy)NSString * dnDonationStatus ;
    /**
     * 捐助物品件数
     **/
    @property(nonatomic,copy)NSString * dnDonateGoodsQty ;
    /**
     * 捐赠人名称
     **/
    @property(nonatomic,copy)NSString * dnDonaterName ;
    /**
     * 扩展属性3
     **/
    @property(nonatomic,copy)NSString * dnAttr3 ;
    /**
     * 被捐赠人ID
     **/
    @property(nonatomic,copy)NSString * dnDonatedId ;
    /**
     * 被捐赠人机构名称
     **/
    @property(nonatomic,copy)NSString * dnDonatedOrgName ;
    /**
     * 扩展属性4
     **/
    @property(nonatomic,copy)NSString * dnAttr4 ;
    /**
     * 捐助物品
     **/
    @property(nonatomic,copy)NSString * dnDonateGoods ;
    /**
     * 被捐赠联系地址
     **/
    @property(nonatomic,copy)NSString * dnDonatedAddr ;
    /**
     * 捐赠描述
     **/
    @property(nonatomic,copy)NSString * dnDonationDesc ;
    /**
     * 扩展属性5
     **/
    @property(nonatomic,copy)NSString * dnAttr5 ;
    /**
     * 捐赠人联系方式
     **/
    @property(nonatomic,copy)NSString * dnDonaterPhone ;
 
@end