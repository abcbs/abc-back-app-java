#import <Foundation/Foundation.h>


@interface B2bPriceCategoryDetailHis : NSObject
	
	
    /**
     * ?????¡¤
     **/
    @property(nonatomic,copy)NSString * price ;
    /**
     * ????¨C¡ã?¡ª?¨¦¡ª?
     **/
    @property(nonatomic,copy)NSDate * orignUpdateTime ;
    /**
     * ?????????
     **/
    @property(nonatomic,copy)NSString * orignCreateBy ;
    /**
     * ??¡¤???????????????ID
     **/
    @property(nonatomic,copy)NSString * priCtyDtlhisId ;
    /**
     * ????¨C¡ã???
     **/
    @property(nonatomic,copy)NSString * orignUpdateBy ;
    /**
     * ??¡¤?????????ID
     **/
    @property(nonatomic,copy)NSString * priCatLineId ;
    /**
     * ¨¨?¡¤??????
     **/
    @property(nonatomic,copy)NSString * startPointQyt ;
    @property(nonatomic,assign)NSInteger  orignSynVersion ;
    /**
     * ??¡¤??????????¡è?ID
     **/
    @property(nonatomic,copy)NSString * priCatHeadId ;
    /**
     * ?¡è???¡§
     **/
    @property(nonatomic,copy)NSString * remarks ;
    /**
     * ?¡è¡À????¡ª????
     **/
    @property(nonatomic,copy)NSDate * expiryDate ;
    @property(nonatomic,assign)NSInteger  orignVersion ;
    /**
     * ?¡±?????¡ª????
     **/
    @property(nonatomic,copy)NSDate * effectiveDate ;
    /**
     * ???????¡ª?¨¦¡ª?
     **/
    @property(nonatomic,copy)NSDate * orignCreateTime ;
 
@end