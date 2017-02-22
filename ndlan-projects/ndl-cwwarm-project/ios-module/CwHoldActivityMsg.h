#import <Foundation/Foundation.h>


@interface CwHoldActivityMsg : NSObject
	
	
    /**
     * 评论数量
     **/
    @property(nonatomic,copy)NSString * hamCommentQty ;
    /**
     * 项目状态【0草稿、1审批中、3审批拒绝、5报名中、6进行中、7结束】
     **/
    @property(nonatomic,copy)NSString * hamBusiStatus ;
    /**
     * 项目概述
     **/
    @property(nonatomic,copy)NSString * hamBusiSummary ;
    /**
     * 相关图片
     **/
    @property(nonatomic,copy)NSString * hamPic ;
    /**
     * 参与人数
     **/
    @property(nonatomic,copy)NSString * hamJoinQty ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 发布者组织名称
     **/
    @property(nonatomic,copy)NSString * hamOrgName ;
    /**
     * 默认图片
     **/
    @property(nonatomic,copy)NSString * hamDefaultPic ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwActivityMsgId ;
    /**
     * 活动地址
     **/
    @property(nonatomic,copy)NSString * hamBusiAddr ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 发布者组织ID
     **/
    @property(nonatomic,copy)NSString * hamOrg ;
    /**
     * 发布分类【0公益、1项目、2活动】
     **/
    @property(nonatomic,copy)NSString * hamType ;
    /**
     * 主办方
     **/
    @property(nonatomic,copy)NSString * hamSponsor ;
    /**
     * 收到安心数量【针对项目】
     **/
    @property(nonatomic,copy)NSString * hamRcvLoveQty ;
    /**
     * 发布者类型【0组织、1机构、2总部】
     **/
    @property(nonatomic,copy)NSString * hamByType ;
    /**
     * 活动开始时间
     **/
    @property(nonatomic,copy)NSDate * hamBusiStartPlace ;
    /**
     * 项目类型
     **/
    @property(nonatomic,copy)NSString * hamBusiType ;
    /**
     * 报名结束时间
     **/
    @property(nonatomic,copy)NSDate * hamEnrollEndDatetime ;
    /**
     * 活动详情
     **/
    @property(nonatomic,copy)NSString * hamBusiDetailMsg ;
    /**
     * 发布者头像
     **/
    @property(nonatomic,copy)NSString * hamByPic ;
    /**
     * 联系方式
     **/
    @property(nonatomic,copy)NSString * hamContactPhone ;
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * hamLifeStatus ;
    /**
     * 报名开始时间
     **/
    @property(nonatomic,copy)NSDate * hamEnrollStartDatetime ;
    /**
     * 发布者ID
     **/
    @property(nonatomic,copy)NSString * hamBy ;
    /**
     * 活动结束时间
     **/
    @property(nonatomic,copy)NSDate * hamBusiEndPlace ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 收到物品数量【针对项目】
     **/
    @property(nonatomic,copy)NSString * hamRcvGoodsQty ;
    /**
     * 收藏数量
     **/
    @property(nonatomic,copy)NSString * hamCollectionQty ;
    /**
     * 发布者名称
     **/
    @property(nonatomic,copy)NSString * hamByName ;
 
@end