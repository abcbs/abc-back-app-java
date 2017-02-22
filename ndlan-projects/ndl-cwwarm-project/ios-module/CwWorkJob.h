#import <Foundation/Foundation.h>


@interface CwWorkJob : NSObject
	
	
    /**
     * 父类ID路径
     **/
    @property(nonatomic,copy)NSString * wjobParentPathId ;
    /**
     * 品类名称
     **/
    @property(nonatomic,copy)NSString * wjobCategoryName ;
    /**
     * 物理主键
     **/
    @property(nonatomic,copy)NSString * cwWorkJobId ;
    /**
     * 品类等级【0行业分级、1业务分级、2职位分级】
     **/
    @property(nonatomic,copy)NSString * wjobCategoryGrade ;
    /**
     * 创建人
     **/
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 父类id
     **/
    @property(nonatomic,copy)NSString * wjobParentCategoryId ;
    /**
     * 品类状态【0不可用、1可用】
     **/
    @property(nonatomic,copy)NSString * wjobBusiStatus ;
    /**
     * 品类描述
     **/
    @property(nonatomic,copy)NSString * wjobCategoryDesc ;
    /**
     * 父类名称路径
     **/
    @property(nonatomic,copy)NSString * wjobParentPathName ;
    /**
     * 更新时间
     **/
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 更新人
     **/
    @property(nonatomic,copy)NSString * updateBy ;
    /**
     * 父类名称
     **/
    @property(nonatomic,copy)NSString * wjobParentCategoryName ;
    /**
     * 生命状态【0删除、1正常】
     **/
    @property(nonatomic,copy)NSString * wjobLifeStatus ;
    /**
     * 创建时间
     **/
    @property(nonatomic,copy)NSDate * createTime ;
 
@end