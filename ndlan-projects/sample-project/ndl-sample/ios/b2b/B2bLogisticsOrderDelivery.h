#import <Foundation/Foundation.h>


@interface B2bLogisticsOrderDelivery : NSObject
	
	
    /**
     * ��???????��??�C??��
     **/
    @property(nonatomic,copy)NSString * orderPkgCode ;
    /**
     * ???������???????���
     **/
    @property(nonatomic,copy)NSString * customName ;
    /**
     * ��?��?��??��???????0��??????��?1��??��?��??��
     **/
    @property(nonatomic,copy)NSString * goodsType ;
    /**
     * ��???????��ID
     **/
    @property(nonatomic,copy)NSString * orderPkgId ;
    /**
     * ��?????��?����??
     **/
    @property(nonatomic,copy)NSString * orderAmount ;
    /**
     * ����?����??��????ID
     **/
    @property(nonatomic,copy)NSString * lgstOrdDlvId ;
    /**
     * ?��?��?��??��??��ID
     **/
    @property(nonatomic,copy)NSString * deliveryAddressId ;
    /**
     * ???������???ID
     **/
    @property(nonatomic,copy)NSString * customId ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remake ;
    /**
     * ?��????ID
     **/
    @property(nonatomic,copy)NSString * logisticsLineId ;
    /**
     * ?��????????���
     **/
    @property(nonatomic,copy)NSString * logisticsLineName ;
    /**
     * ��????�C
     **/
    @property(nonatomic,copy)NSString * postCode ;
    /**
     * ?��?��?��????��?��??
     **/
    @property(nonatomic,copy)NSString * receiveTellcall ;
    /**
     * ?��?��?��???��?��????�C????
     **/
    @property(nonatomic,copy)NSString * receivePhone ;
    /**
     * ��?????����?����?ID
     **/
    @property(nonatomic,copy)NSString * orderDeliveryId ;
    /**
     * ?????��???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ?���??��??��???
     **/
    @property(nonatomic,copy)NSString * region ;
    /**
     * ��??????��????
     **/
    @property(nonatomic,copy)NSDate * orderDate ;
    /**
     * ?????��???????���
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ?��?��?��???
     **/
    @property(nonatomic,copy)NSString * receiveName ;
    /**
     * ?��??????�C??��
     **/
    @property(nonatomic,copy)NSString * logisticsHeadCode ;
    /**
     * ��???????��??��
     **/
    @property(nonatomic,copy)NSString * detailAddress ;
 
@end