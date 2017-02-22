#import <Foundation/Foundation.h>


@interface B2bLogisticsOrderDelivery : NSObject
	
	
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    @property(nonatomic,copy)NSString * orderPkgCode ;
    /**
     * ???¨¦¡±¢ã???????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * customName ;
    /**
     * ¨¨?¡ì?¡ë??¡À???????0¨¨??????¢ã?1¨¨??¨¨?¡ì??¡ë
     **/
    @property(nonatomic,copy)NSString * goodsType ;
    /**
     * ¨¨???????¡­ID
     **/
    @property(nonatomic,copy)NSString * orderPkgId ;
    /**
     * ¨¨?????¨¦?¡®¨¦??
     **/
    @property(nonatomic,copy)NSString * orderAmount ;
    /**
     * ¨¦¡­?¨¦¢ã??¡­????ID
     **/
    @property(nonatomic,copy)NSString * lgstOrdDlvId ;
    /**
     * ?¡±?¨¨?¡ì??¡ã??¢ãID
     **/
    @property(nonatomic,copy)NSString * deliveryAddressId ;
    /**
     * ???¨¦¡±¢ã???ID
     **/
    @property(nonatomic,copy)NSString * customId ;
    /**
     * ?¡è???¡§
     **/
    @property(nonatomic,copy)NSString * remake ;
    /**
     * ?¡ë????ID
     **/
    @property(nonatomic,copy)NSString * logisticsLineId ;
    /**
     * ?¡ë????????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * logisticsLineName ;
    /**
     * ¨¦????¨C
     **/
    @property(nonatomic,copy)NSString * postCode ;
    /**
     * ?¡±?¨¨?¡ì????¡±?¨¨??
     **/
    @property(nonatomic,copy)NSString * receiveTellcall ;
    /**
     * ?¡±?¨¨?¡ì???¨¨?¡±????¨C????
     **/
    @property(nonatomic,copy)NSString * receivePhone ;
    /**
     * ¨¨?????¨¦¡­?¨¦¢ã?ID
     **/
    @property(nonatomic,copy)NSString * orderDeliveryId ;
    /**
     * ?????¡±???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ?¡ë¢ã??¡§??¡ã???
     **/
    @property(nonatomic,copy)NSString * region ;
    /**
     * ¨¨??????¡ª????
     **/
    @property(nonatomic,copy)NSDate * orderDate ;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ?¡±?¨¨?¡ì???
     **/
    @property(nonatomic,copy)NSString * receiveName ;
    /**
     * ?¡ë??????¨C??¡¤
     **/
    @property(nonatomic,copy)NSString * logisticsHeadCode ;
    /**
     * ¨¨???????¡ã??¢ã
     **/
    @property(nonatomic,copy)NSString * detailAddress ;
 
@end