#import <Foundation/Foundation.h>


@interface CwForHelpMsg : NSObject
	
	
    /**
     * 联系地址
     **/
    @property(nonatomic,copy)NSString * fhbContactAddr ;
    /**
     * 联系电话
     **/
    @property(nonatomic,copy)NSString * fhbContactPhone ;
    /**
     * 求助者类型【0组织、1个人、2志愿者、3机构】
     **/
    @property(nonatomic,copy)NSString * forHelpByType ;
    /**
     * 参与人数
     **/
    @property(nonatomic,copy)NSString * fhbJoinQty ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 求助者组织ID【志愿者、个人此信息为空】
     **/
    @property(nonatomic,copy)NSString * forHelpOrg ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 默认图片
     **/
    @property(nonatomic,copy)NSString * fhbDefaultPic ;
    /**
     * 收到物品数量
     **/
    @property(nonatomic,copy)NSString * fhbRcvGoodsQty ;
    /**
     * 求助分类【0走失、1物品丢失、2物品求助】
     **/
    @property(nonatomic,copy)NSString * fhbMsgType ;
    /**
     * 联系人
     **/
    @property(nonatomic,copy)NSString * fhbContact ;
    /**
     * 求助者ID
     **/
    @property(nonatomic,copy)NSString * forHelpBy ;
    /**
     * 收到爱心数量
     **/
    @property(nonatomic,copy)NSString * fhbRcvLoveQty ;
    /**
     * 状态【0草稿、1审批中、2活动、3审批拒绝、4截止】
     **/
    @property(nonatomic,copy)NSString * fhbStatus ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 相关图片
     **/
    @property(nonatomic,copy)NSString * fhbPic ;
    /**
     * 求助事物类型【性别】
     **/
    @property(nonatomic,copy)NSString * forHelpBusiType ;
    /**
     * 发生时间【走失时间】
     **/
    @property(nonatomic,copy)NSDate * fhbOccurDatetime ;
    /**
     * 收藏数量
     **/
    @property(nonatomic,copy)NSString * fhbCollectionQty ;
    /**
     * 求助类型
     **/
    @property(nonatomic,copy)NSString * forHelpType ;
    /**
     * 求助事物【走失人姓名】
     **/
    @property(nonatomic,copy)NSString * forHelpBusi ;
    /**
     * 发生地点【走失地点】
     **/
    @property(nonatomic,copy)NSString * fhbOccurPlace ;
    /**
     * 求助者名称
     **/
    @property(nonatomic,copy)NSString * forHelpByName ;
    /**
     * 评论数量
     **/
    @property(nonatomic,copy)NSString * fhbCommentQty ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwHelpMsgId ;
    /**
     * 求助者组织名称【志愿者、个人此信息为空】
     **/
    @property(nonatomic,copy)NSString * forHelpOrgName ;
    /**
     * 详细信息描述
     **/
    @property(nonatomic,copy)NSString * fhbDetailMsg ;
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * fhbLifeStatus ;
    /**
     * 求助者头像
     **/
    @property(nonatomic,copy)NSString * forHelpByPic ;
 
@end