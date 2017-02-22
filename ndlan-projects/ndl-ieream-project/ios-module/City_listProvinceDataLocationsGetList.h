#import <Foundation/Foundation.h>


@interface City_listProvinceDataLocationsGetList : NSObject
	
	
    /**
     * 110100
     **/
    @property(nonatomic,copy)NSString * id ;
    /**
     * 北京市
     **/
    @property(nonatomic,copy)NSString * name ;
    /**
     * 2
     **/
    @property(nonatomic,copy)NSString * count_item ;
    /**
     * has_child
     **/
    @property(nonatomic,assign)NSInteger  has_child ;
 
    /**
     * District_listCity_listProvinceDataLocationsGetList
     **/
    @property(nonatomic,copy)NSArray * district_listCity_listProvinceDataLocationsGetList ;
@end