#import <Foundation/Foundation.h>


@interface B2bLogisticsRealtime : NSObject
	
	
    /**
     * ?��??????�C??��
     **/
    @property(nonatomic,copy)NSString * logisticsLineCode ;
    /**
     * ??��?��???��??��
     **/
    @property(nonatomic,copy)NSString * currAddress ;
    /**
     * ?��????????��???????ID
     **/
    @property(nonatomic,copy)NSString * logisticsRealtimeId ;
    /**
     * ?????��?????��??��
     **/
    @property(nonatomic,copy)NSString * nextAddress ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remake ;
    /**
     * ?��????��??ID
     **/
    @property(nonatomic,copy)NSString * logisticsLineId ;
    /**
     * ??��?��??��?����?
     **/
    @property(nonatomic,copy)NSDate * currDate ;
 
@end