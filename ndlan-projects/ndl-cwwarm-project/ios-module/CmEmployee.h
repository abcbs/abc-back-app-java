#import <Foundation/Foundation.h>


@interface CmEmployee : NSObject
	
	
    @property(nonatomic,copy)NSString * plainPassword ;
    @property(nonatomic,copy)NSString * emerConTel ;
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * æŽˆæƒç ï¼ˆç™»å½•åˆ›å»ºé¤åŽ…é¡µé¢ï¼‰
     **/
    @property(nonatomic,copy)NSString * authorizationCode ;
    @property(nonatomic,copy)NSString * createBy ;
    /**
     * ç»„ç»‡ID
     **/
    @property(nonatomic,copy)NSString * storeId ;
    @property(nonatomic,copy)NSString * loginPassword ;
    @property(nonatomic,copy)NSString * restId ;
    /**
     * postæŽˆæƒç 
     **/
    @property(nonatomic,copy)NSString * authorizationMake ;
    @property(nonatomic,copy)NSString * empId ;
    @property(nonatomic,copy)NSString * mobile ;
    @property(nonatomic,copy)NSString * resProvince ;
    @property(nonatomic,copy)NSString * updateBy ;
    @property(nonatomic,copy)NSDate * createTime ;
    @property(nonatomic,copy)NSString * emerContact ;
    @property(nonatomic,copy)NSString * empStatus ;
    @property(nonatomic,copy)NSString * idCard ;
    @property(nonatomic,copy)NSString * adrProvince ;
    /**
     * å¯¹åº” JobStatus
     **/
    @property(nonatomic,copy)NSString * jobStatus ;
    @property(nonatomic,copy)NSString * resCity ;
    /**
     * å¯¹åº” Gender
     **/
    @property(nonatomic,copy)NSString * gender ;
    @property(nonatomic,copy)NSString * address ;
    @property(nonatomic,copy)NSString * empNum ;
    @property(nonatomic,copy)NSString * sysdataType ;
    @property(nonatomic,copy)NSString * jobPic ;
    @property(nonatomic,copy)NSString * salt ;
    /**
     * åˆ¤åˆ«æ˜¯å¦ä¸ºå½“å‰é¤åŽ…äººå‘˜æ ‡è¯†ç¬¦
     **/
    @property(nonatomic,copy)NSString * belongRest ;
    @property(nonatomic,copy)NSString * loginUsername ;
    @property(nonatomic,copy)NSString * name ;
    @property(nonatomic,copy)NSString * residenceAdr ;
    @property(nonatomic,copy)NSString * barPath ;
    @property(nonatomic,copy)NSString * adrCity ;
 
@end