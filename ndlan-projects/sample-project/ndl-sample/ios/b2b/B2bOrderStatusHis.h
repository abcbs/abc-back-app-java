#import <Foundation/Foundation.h>


@interface B2bOrderStatusHis : NSObject
	
	
    @property(nonatomic,assign)NSInteger  orignVersion ;
    @property(nonatomic,copy)NSDate * orignUpdateTime ;
    /**
     * ��????????????��?
     **/
    @property(nonatomic,copy)NSString * orignStatus ;
    /**
     * ???????��?����????????�졯??��??��
     **/
    @property(nonatomic,copy)NSDate * orignCreateTime ;
    /**
     * ????�C��?��?����????????�졯??��??��
     **/
    @property(nonatomic,copy)NSString * orignUpdateBy ;
    /**
     * ��???????��??�C??��
     **/
    @property(nonatomic,copy)NSString * orderPkgCode ;
    /**
     * ��???????��ID
     **/
    @property(nonatomic,copy)NSString * orderPkgId ;
    /**
     * ?????????
     **/
    @property(nonatomic,copy)NSString * orignCreateBy ;
    @property(nonatomic,assign)NSInteger  orignSynVersion ;
    /**
     * ???����?
     **/
    @property(nonatomic,copy)NSString * orderStatusHisId ;
    /**
     * ?��???????��?��
     **/
    @property(nonatomic,copy)NSString * updateReason ;
    /**
     * ��??????��?ID
     **/
    @property(nonatomic,copy)NSString * orderHeadId ;
 
@end