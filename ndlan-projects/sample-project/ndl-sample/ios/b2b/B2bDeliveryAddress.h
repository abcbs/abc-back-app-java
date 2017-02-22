#import <Foundation/Foundation.h>


@interface B2bDeliveryAddress : NSObject
	
	
    /**
     * ?°Î¢„??°ß??°„???
     **/
    @property(nonatomic,copy)NSString * region ;
    /**
     * ?°±?®®?°Ï???
     **/
    @property(nonatomic,copy)NSString * receiveName ;
    /**
     * ?°±?®®?°Ï???®®?°±????®C????
     **/
    @property(nonatomic,copy)NSString * receivePhone ;
    /**
     * ®®???????°„??¢„
     **/
    @property(nonatomic,copy)NSString * detailAddress ;
    /**
     * ?°±?®®?°Ï????°±?®®??
     **/
    @property(nonatomic,copy)NSString * receiveTellcall ;
    /**
     * ???®¶°±¢„???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ??°Æ®¶¢„???°„??¢„ID
     **/
    @property(nonatomic,copy)NSString * deliveryAddressId ;
    /**
     * ?°Ë???°ß
     **/
    @property(nonatomic,copy)NSString * remake ;
    /**
     * ???®¶°±¢„???????°Ï°„
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ??????®¶??®®?°Ë?°±?®®?°§??°„??¢„???1??????0?????°Î
     **/
    @property(nonatomic,copy)NSString * isDefault ;
    /**
     * ®¶????®C
     **/
    @property(nonatomic,copy)NSString * postCode ;
 
@end