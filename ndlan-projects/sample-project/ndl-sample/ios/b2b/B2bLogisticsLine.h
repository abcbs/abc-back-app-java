#import <Foundation/Foundation.h>


@interface B2bLogisticsLine : NSObject
	
	
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ??¡®¨¨?¡ì??¡ã??¢ãid
     **/
    @property(nonatomic,copy)NSString * deliveryAddressId ;
    /**
     * ?¡ë????ID
     **/
    @property(nonatomic,copy)NSString * logisticsLineId ;
    /**
     * ¨¦¢ã?¨¨?¡ì??¢ã?¡ì??¡ª?¨¦¡ª?
     **/
    @property(nonatomic,copy)NSDate * deliveryStartDate ;
    /**
     * ?¡ë??????¨C??¡¤
     **/
    @property(nonatomic,copy)NSString * logisticsLineCode ;
    /**
     * ??¡®¨¨?¡ì??¡ã??¢ã
     **/
    @property(nonatomic,copy)NSString * deliveryAddress ;
    /**
     * ?????¡±???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ¨¦¢ã?¨¨?¡ì???¨¨?¡±????¨C????
     **/
    @property(nonatomic,copy)NSString * deliveryUserPhone ;
    /**
     * ¨¦¢ã?¨¨?¡ì??¡°????¡ª?¨¦¡ª?
     **/
    @property(nonatomic,copy)NSDate * deliveryEndDate ;
    /**
     * ?¡è???¡§
     **/
    @property(nonatomic,copy)NSString * remake ;
    @property(nonatomic,copy)NSString * logisticsLineName ;
    /**
     * ¨¦¢ã?¨¨?¡ì???
     **/
    @property(nonatomic,copy)NSString * deliveryUser ;
 
@end