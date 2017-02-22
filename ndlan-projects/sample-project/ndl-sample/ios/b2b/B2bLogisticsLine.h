#import <Foundation/Foundation.h>


@interface B2bLogisticsLine : NSObject
	
	
    /**
     * ?????��???????���
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ??����?��??��??��id
     **/
    @property(nonatomic,copy)NSString * deliveryAddressId ;
    /**
     * ?��????ID
     **/
    @property(nonatomic,copy)NSString * logisticsLineId ;
    /**
     * ����?��?��??��?��??��?����?
     **/
    @property(nonatomic,copy)NSDate * deliveryStartDate ;
    /**
     * ?��??????�C??��
     **/
    @property(nonatomic,copy)NSString * logisticsLineCode ;
    /**
     * ??����?��??��??��
     **/
    @property(nonatomic,copy)NSString * deliveryAddress ;
    /**
     * ?????��???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ����?��?��???��?��????�C????
     **/
    @property(nonatomic,copy)NSString * deliveryUserPhone ;
    /**
     * ����?��?��??��????��?����?
     **/
    @property(nonatomic,copy)NSDate * deliveryEndDate ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remake ;
    @property(nonatomic,copy)NSString * logisticsLineName ;
    /**
     * ����?��?��???
     **/
    @property(nonatomic,copy)NSString * deliveryUser ;
 
@end