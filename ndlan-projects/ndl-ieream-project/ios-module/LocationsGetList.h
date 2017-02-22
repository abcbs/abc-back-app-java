#import <Foundation/Foundation.h>


@interface LocationsGetList : NSObject
	
	
    /**
     * more
     **/
    @property(nonatomic,assign)NSInteger  more ;
    /**
     * status
     **/
    @property(nonatomic,assign)NSInteger  status ;
 
    /**
     * DataLocationsGetList
     **/
    @property(nonatomic,copy) DataLocationsGetList * dataLocationsGetList ;
    /**
     * Hot_city_dataLocationsGetList
     **/
    @property(nonatomic,copy)NSArray * hot_city_dataLocationsGetList ;
@end