#import <Foundation/Foundation.h>


@interface CwCollection : NSObject
	
	
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * cltLifeStatus ;
    /**
     * 收藏者头像
     **/
    @property(nonatomic,copy)NSString * cltByPic ;
    /**
     * 扩展属性5
     **/
    @property(nonatomic,copy)NSString * cltAttr5 ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 收藏对象ID
     **/
    @property(nonatomic,copy)NSString * cltObjId ;
    /**
     * 收藏者ID
     **/
    @property(nonatomic,copy)NSString * cltBy ;
    /**
     * 收藏者名称
     **/
    @property(nonatomic,copy)NSString * cltByName ;
    /**
     * 扩展属性1
     **/
    @property(nonatomic,copy)NSString * cltAttr1 ;
    /**
     * 扩展属性3
     **/
    @property(nonatomic,copy)NSString * cltAttr3 ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 备注
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * 收藏对象类型【0项目、1活动、2公益信息、3走失、4物品丢失、5物品求助】
     **/
    @property(nonatomic,copy)NSString * cltObjType ;
    /**
     * 扩展属性4
     **/
    @property(nonatomic,copy)NSString * cltAttr4 ;
    /**
     * 收藏对象信息
     **/
    @property(nonatomic,copy)NSString * cltObjMsg ;
    /**
     * 收藏状态
     **/
    @property(nonatomic,copy)NSString * cltBusiStatus ;
    /**
     * 扩展属性2
     **/
    @property(nonatomic,copy)NSString * cltAttr2 ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwCommentId ;
 
@end