#import <Foundation/Foundation.h>


@interface CmRole : NSObject
	
	
    @property(nonatomic,copy)NSString * sysdataType ;
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * 角色表
     **/
    @property(nonatomic,copy)NSString * crId ;
    @property(nonatomic,copy)NSString * name ;
    @property(nonatomic,copy)NSString * restId ;
    @property(nonatomic,copy)NSString * roleType ;
    @property(nonatomic,copy)NSDate * updateTime ;
    @property(nonatomic,copy)NSString * crStatus ;
    @property(nonatomic,copy)NSString * barPath ;
    @property(nonatomic,copy)NSString * isAllTablearea ;
    @property(nonatomic,copy)NSDate * createTime ;
    @property(nonatomic,copy)NSString * updateBy ;
    @property(nonatomic,copy)NSString * isStopUse ;
 
@end