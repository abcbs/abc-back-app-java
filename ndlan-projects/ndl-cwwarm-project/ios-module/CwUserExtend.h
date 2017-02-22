#import <Foundation/Foundation.h>


@interface CwUserExtend : NSObject
	
	
    /**
     * 认证时间
     **/
    @property(nonatomic,copy)NSString * authorizationMake ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwUserExtendId ;
    /**
     * 状态【0不可用、1可用】
     **/
    @property(nonatomic,copy)NSString * cueBusiStatus ;
    /**
     * 职业
     **/
    @property(nonatomic,copy)NSString * residenceAdr ;
    /**
     * 志愿者编号
     **/
    @property(nonatomic,copy)NSString * empNum ;
    /**
     * 头像
     **/
    @property(nonatomic,copy)NSString * jobPic ;
    /**
     * 用户类型【10超级管理员、11个人、12志愿者、13机构、14组织】
     **/
    @property(nonatomic,copy)NSString * sysdataType ;
    /**
     * 账号
     **/
    @property(nonatomic,copy)NSString * loginUsername ;
    /**
     * 姓名
     **/
    @property(nonatomic,copy)NSString * name ;
    /**
     * 已评论量
     **/
    @property(nonatomic,copy)NSString * cueCommentQty ;
    /**
     * 组织ID
     **/
    @property(nonatomic,copy)NSString * restId ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 
联系方式
     **/
    @property(nonatomic,copy)NSString * moblie ;
    /**
     * 联系地址
     **/
    @property(nonatomic,copy)NSString * address ;
    /**
     * 参与活动数量
     **/
    @property(nonatomic,copy)NSString * cueJoinQty ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 志愿者状态【0未申请、2审核中、1审核通过、3审核拒绝】
     **/
    @property(nonatomic,copy)NSString * empStatus ;
    /**
     * 性别【0男，1女】
     **/
    @property(nonatomic,copy)NSString * gender ;
    /**
     * 捐出物品量
     **/
    @property(nonatomic,copy)NSString * cueRcvGoodsQty ;
    /**
     * 用户ID
     **/
    @property(nonatomic,copy)NSString * empId ;
    /**
     * 已收藏量
     **/
    @property(nonatomic,copy)NSString * cueCollectionQty ;
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * cueLifeStatus ;
    /**
     * 手持身份证照
     **/
    @property(nonatomic,copy)NSString * barPath ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 职业ID
     **/
    @property(nonatomic,copy)NSString * authorizationCode ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 身份证号
     **/
    @property(nonatomic,copy)NSString * idCard ;
    /**
     * 昵称
     **/
    @property(nonatomic,copy)NSString * emerContact ;
    /**
     * 献出爱心量
     **/
    @property(nonatomic,copy)NSString * cueRcvLoveQty ;
 
@end