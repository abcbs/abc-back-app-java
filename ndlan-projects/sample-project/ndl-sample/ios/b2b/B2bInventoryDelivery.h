#import <Foundation/Foundation.h>


@interface B2bInventoryDelivery : NSObject
	
	
    /**
     * ?????��?��????
     **/
    @property(nonatomic,copy)NSDate * deliveryDate ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * ?????��???
     **/
    @property(nonatomic,copy)NSString * deliveryUser ;
    /**
     * ?????��??�㨦??
     **/
    @property(nonatomic,copy)NSString * deliveryQty ;
    /**
     * ??��????��?��?��
     **/
    @property(nonatomic,copy)NSString * invHeadId ;
    /**
     * ???����?
     **/
    @property(nonatomic,copy)NSString * invDeliveryId ;
    /**
     * ?????��??��???
     **/
    @property(nonatomic,copy)NSString * deliveryPrice ;
    /**
     * ?????��???????��???�C???|����????|???????�㡮
     **/
    @property(nonatomic,copy)NSString * source ;
 
@end