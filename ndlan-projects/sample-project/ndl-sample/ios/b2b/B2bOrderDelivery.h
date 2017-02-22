#import <Foundation/Foundation.h>


@interface B2bOrderDelivery : NSObject
	
	
    /**
     * ??°Æ®¶¢„???°„??¢„ID
     **/
    @property(nonatomic,copy)NSString * deliveryAddressId ;
    /**
     * ?°Î¢„??°ß??°„???
     **/
    @property(nonatomic,copy)NSString * region ;
    /**
     * ?°±?®®?°Ï????°±?®®??
     **/
    @property(nonatomic,copy)NSString * receiveTellcall ;
    /**
     * ?????°±???????°Ï°„
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ?°±?®®?°Ï???
     **/
    @property(nonatomic,copy)NSString * receiveName ;
    /**
     * ®®?????®¶°≠?®¶¢„?ID
     **/
    @property(nonatomic,copy)NSString * orderDeliveryId ;
    /**
     * ???®¶°±¢„???ID
     **/
    @property(nonatomic,copy)NSString * customId ;
    /**
     * ®®???????°„??¢„
     **/
    @property(nonatomic,copy)NSString * detailAddress ;
    /**
     * ?????°±???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ?°Ë???°ß
     **/
    @property(nonatomic,copy)NSString * remake ;
    /**
     * ?°±?®®?°Ï???®®?°±????®C????
     **/
    @property(nonatomic,copy)NSString * receivePhone ;
    /**
     * ???®¶°±¢„???????°Ï°„
     **/
    @property(nonatomic,copy)NSString * customName ;
    /**
     * ®®???????°≠ID
     **/
    @property(nonatomic,copy)NSString * orderPkgId ;
    /**
     * ®®???????°≠??®C??°§
     **/
    @property(nonatomic,copy)NSString * orderPkgCode ;
    /**
     * ®¶????®C
     **/
    @property(nonatomic,copy)NSString * postCode ;
 
@end