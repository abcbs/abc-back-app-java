#import <Foundation/Foundation.h>


@interface CwPartake : NSObject
	
	
    /**
     * 扩展属性5
     **/
    @property(nonatomic,copy)NSString * ptkAttr5 ;
    /**
     * 参与者头像
     **/
    @property(nonatomic,copy)NSString * ptkByPic ;
    /**
     * 扩展属性1
     **/
    @property(nonatomic,copy)NSString * ptkAttr1 ;
    /**
     * 参与状态【0草稿、1审批中、2审批通过、3审批拒绝】
     **/
    @property(nonatomic,copy)NSString * ptkBusiStatus ;
    /**
     * 扩展属性4
     **/
    @property(nonatomic,copy)NSString * ptkAttr4 ;
    /**
     * 参与对象ID
     **/
    @property(nonatomic,copy)NSString * ptkObjId ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 扩展属性3
     **/
    @property(nonatomic,copy)NSString * ptkAttr3 ;
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * ptkLifeStatus ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwPartakeId ;
    /**
     * 扩展属性2
     **/
    @property(nonatomic,copy)NSString * ptkAttr2 ;
    /**
     * 参与者名称
     **/
    @property(nonatomic,copy)NSString * ptkByName ;
    /**
     * 参与对象类型【0参加活动】
     **/
    @property(nonatomic,copy)NSString * ptkObjType ;
    /**
     * 参与对象信息
     **/
    @property(nonatomic,copy)NSString * ptkObjMsg ;
    /**
     * 备注
     **/
    @property(nonatomic,copy)NSString * ptkRemark ;
    /**
     * 参与者ID
     **/
    @property(nonatomic,copy)NSString * ptkBy ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
 
@end