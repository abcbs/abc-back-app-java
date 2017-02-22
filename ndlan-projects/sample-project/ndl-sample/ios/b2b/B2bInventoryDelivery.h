#import <Foundation/Foundation.h>


@interface B2bInventoryDelivery : NSObject
	
	
    /**
     * ?????¡°?¡ª????
     **/
    @property(nonatomic,copy)NSDate * deliveryDate ;
    /**
     * ?¡è???¡§
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * ?????¡°???
     **/
    @property(nonatomic,copy)NSString * deliveryUser ;
    /**
     * ?????¡°??¡ã¨¦??
     **/
    @property(nonatomic,copy)NSString * deliveryQty ;
    /**
     * ??¡°????¡è?¨¨?¡§
     **/
    @property(nonatomic,copy)NSString * invHeadId ;
    /**
     * ???¨¦¡±?
     **/
    @property(nonatomic,copy)NSString * invDeliveryId ;
    /**
     * ?????¡°??¡¤???
     **/
    @property(nonatomic,copy)NSString * deliveryPrice ;
    /**
     * ?????¡°???????¢ã???¨C???|¨¨¡ã????|???????¢ã¡®
     **/
    @property(nonatomic,copy)NSString * source ;
 
@end