#import <Foundation/Foundation.h>


@interface B2bOrderStatusHis : NSObject
	
	
    @property(nonatomic,assign)NSInteger  orignVersion ;
    @property(nonatomic,copy)NSDate * orignUpdateTime ;
    /**
     * ¨¨????????????¢ã?
     **/
    @property(nonatomic,copy)NSString * orignStatus ;
    /**
     * ???????¡ª?¨¦¡ª????????¡ì¡¯??¡ì??¡ë
     **/
    @property(nonatomic,copy)NSDate * orignCreateTime ;
    /**
     * ????¨C¡ã?¡ª?¨¦¡ª????????¡ì¡¯??¡ì??¡ë
     **/
    @property(nonatomic,copy)NSString * orignUpdateBy ;
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    @property(nonatomic,copy)NSString * orderPkgCode ;
    /**
     * ¨¨???????¡­ID
     **/
    @property(nonatomic,copy)NSString * orderPkgId ;
    /**
     * ?????????
     **/
    @property(nonatomic,copy)NSString * orignCreateBy ;
    @property(nonatomic,assign)NSInteger  orignSynVersion ;
    /**
     * ???¨¦¡±?
     **/
    @property(nonatomic,copy)NSString * orderStatusHisId ;
    /**
     * ?¡°???????¨¨?¡ã
     **/
    @property(nonatomic,copy)NSString * updateReason ;
    /**
     * ¨¨??????¡è?ID
     **/
    @property(nonatomic,copy)NSString * orderHeadId ;
 
@end