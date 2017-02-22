#import <Foundation/Foundation.h>


@interface CwComment : NSObject
	
	
    /**
     * 评论内容
     **/
    @property(nonatomic,copy)NSString * cmtContent ;
    /**
     * 评论状态【0草稿、1审批中、2审批通过、3审批拒绝】
     **/
    @property(nonatomic,copy)NSString * cmtBusiStatus ;
    /**
     * 扩展属性3
     **/
    @property(nonatomic,copy)NSString * cmtAttr3 ;
    /**
     * 评论人名称
     **/
    @property(nonatomic,copy)NSString * cmtByName ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 备注
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * 扩展属性1
     **/
    @property(nonatomic,copy)NSString * cmtAttr1 ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwCommentId ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 扩展属性5
     **/
    @property(nonatomic,copy)NSString * cmtAttr5 ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 评论对象类型【0项目、1活动、2公益信息、3走失、4物品丢失、5物品求助】
     **/
    @property(nonatomic,copy)NSString * cmtObjType ;
    /**
     * 扩展属性4
     **/
    @property(nonatomic,copy)NSString * cmtAttr4 ;
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * cmtLifeStatus ;
    /**
     * 评论人ID
     **/
    @property(nonatomic,copy)NSString * cmtBy ;
    /**
     * 评论对象ID
     **/
    @property(nonatomic,copy)NSString * cmtObjId ;
    /**
     * 评论对象消息
     **/
    @property(nonatomic,copy)NSString * cmtObjMsg ;
    /**
     * 扩展属性2
     **/
    @property(nonatomic,copy)NSString * cmtAttr2 ;
    /**
     * 评论人图片
     **/
    @property(nonatomic,copy)NSString * cmtByPic ;
 
@end