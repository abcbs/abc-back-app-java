#import <Foundation/Foundation.h>


@interface B2bDeliveryAddress : NSObject
	
	
    /**
     * ?���??��??��???
     **/
    @property(nonatomic,copy)NSString * region ;
    /**
     * ?��?��?��???
     **/
    @property(nonatomic,copy)NSString * receiveName ;
    /**
     * ?��?��?��???��?��????�C????
     **/
    @property(nonatomic,copy)NSString * receivePhone ;
    /**
     * ��???????��??��
     **/
    @property(nonatomic,copy)NSString * detailAddress ;
    /**
     * ?��?��?��????��?��??
     **/
    @property(nonatomic,copy)NSString * receiveTellcall ;
    /**
     * ???������???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ??������???��??��ID
     **/
    @property(nonatomic,copy)NSString * deliveryAddressId ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remake ;
    /**
     * ???������???????���
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ??????��??��?��?��?��?��??��??��???1??????0?????��
     **/
    @property(nonatomic,copy)NSString * isDefault ;
    /**
     * ��????�C
     **/
    @property(nonatomic,copy)NSString * postCode ;
 
@end