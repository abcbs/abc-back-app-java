#import <Foundation/Foundation.h>


@interface B2bPriceCategoryDetailHis : NSObject
	
	
    /**
     * ?????��
     **/
    @property(nonatomic,copy)NSString * price ;
    /**
     * ????�C��?��?����?
     **/
    @property(nonatomic,copy)NSDate * orignUpdateTime ;
    /**
     * ?????????
     **/
    @property(nonatomic,copy)NSString * orignCreateBy ;
    /**
     * ??��???????????????ID
     **/
    @property(nonatomic,copy)NSString * priCtyDtlhisId ;
    /**
     * ????�C��???
     **/
    @property(nonatomic,copy)NSString * orignUpdateBy ;
    /**
     * ??��?????????ID
     **/
    @property(nonatomic,copy)NSString * priCatLineId ;
    /**
     * ��?��??????
     **/
    @property(nonatomic,copy)NSString * startPointQyt ;
    @property(nonatomic,assign)NSInteger  orignSynVersion ;
    /**
     * ??��??????????��?ID
     **/
    @property(nonatomic,copy)NSString * priCatHeadId ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remarks ;
    /**
     * ?���????��????
     **/
    @property(nonatomic,copy)NSDate * expiryDate ;
    @property(nonatomic,assign)NSInteger  orignVersion ;
    /**
     * ?��?????��????
     **/
    @property(nonatomic,copy)NSDate * effectiveDate ;
    /**
     * ???????��?����?
     **/
    @property(nonatomic,copy)NSDate * orignCreateTime ;
 
@end