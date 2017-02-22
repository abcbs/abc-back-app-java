#import <Foundation/Foundation.h>


@interface B2bOrderDelivery : NSObject
	
	
    /**
     * ??������???��??��ID
     **/
    @property(nonatomic,copy)NSString * deliveryAddressId ;
    /**
     * ?���??��??��???
     **/
    @property(nonatomic,copy)NSString * region ;
    /**
     * ?��?��?��????��?��??
     **/
    @property(nonatomic,copy)NSString * receiveTellcall ;
    /**
     * ?????��???????���
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ?��?��?��???
     **/
    @property(nonatomic,copy)NSString * receiveName ;
    /**
     * ��?????����?����?ID
     **/
    @property(nonatomic,copy)NSString * orderDeliveryId ;
    /**
     * ???������???ID
     **/
    @property(nonatomic,copy)NSString * customId ;
    /**
     * ��???????��??��
     **/
    @property(nonatomic,copy)NSString * detailAddress ;
    /**
     * ?????��???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remake ;
    /**
     * ?��?��?��???��?��????�C????
     **/
    @property(nonatomic,copy)NSString * receivePhone ;
    /**
     * ???������???????���
     **/
    @property(nonatomic,copy)NSString * customName ;
    /**
     * ��???????��ID
     **/
    @property(nonatomic,copy)NSString * orderPkgId ;
    /**
     * ��???????��??�C??��
     **/
    @property(nonatomic,copy)NSString * orderPkgCode ;
    /**
     * ��????�C
     **/
    @property(nonatomic,copy)NSString * postCode ;
 
@end