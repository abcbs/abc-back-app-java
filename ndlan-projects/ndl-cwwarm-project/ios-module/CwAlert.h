#import <Foundation/Foundation.h>


@interface CwAlert : NSObject
	
	
    /**
     * 消息发送者头像
     **/
    @property(nonatomic,copy)NSString * alertByPic ;
    /**
     * 消息来源分类【0系统通知、1收到捐助通知、2参与活动通知、3审核结果通知、4申请通知】
     **/
    @property(nonatomic,copy)NSString * alertSourceType ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwAlertId ;
    /**
     * 通知人ID
     **/
    @property(nonatomic,copy)NSString * alertRcv ;
    /**
     * 消息发送者名称
     **/
    @property(nonatomic,copy)NSString * alertByName ;
    /**
     * 通知状态【0未处理、1已处理】
     **/
    @property(nonatomic,copy)NSString * alertBusiStatus ;
    /**
     * 通知人名称
     **/
    @property(nonatomic,copy)NSString * alertRcvName ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 链接URI
     **/
    @property(nonatomic,copy)NSString * alertMsgUri ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 消息发送者ID
     **/
    @property(nonatomic,copy)NSString * alertById ;
    /**
     * 二级消息来源分类
     **/
    @property(nonatomic,copy)NSString * alertSecondSourceType ;
    /**
     * 通知人头像
     **/
    @property(nonatomic,copy)NSString * alertRcvPic ;
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * alertLifeStatus ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 通知消息
     **/
    @property(nonatomic,copy)NSString * alertMsg ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
 
@end