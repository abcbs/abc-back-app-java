#import <Foundation/Foundation.h>


@interface B2bLogisticsRealtime : NSObject
	
	
    /**
     * ?¡ë??????¨C??¡¤
     **/
    @property(nonatomic,copy)NSString * logisticsLineCode ;
    /**
     * ??¡°?¡ë???¡ã??¢ã
     **/
    @property(nonatomic,copy)NSString * currAddress ;
    /**
     * ?¡ë????????¡ª???????ID
     **/
    @property(nonatomic,copy)NSString * logisticsRealtimeId ;
    /**
     * ?????¢ã?????¡ã??¢ã
     **/
    @property(nonatomic,copy)NSString * nextAddress ;
    /**
     * ?¡è???¡§
     **/
    @property(nonatomic,copy)NSString * remake ;
    /**
     * ?¡ë????¨¨??ID
     **/
    @property(nonatomic,copy)NSString * logisticsLineId ;
    /**
     * ??¡°?¡ë??¡ª?¨¦¡ª?
     **/
    @property(nonatomic,copy)NSDate * currDate ;
 
@end