#import <Foundation/Foundation.h>


@interface DataHotelssearch : NSObject
	
	
    /**
     * 2016-03-20 06:13
     **/
    @property(nonatomic,copy)NSString * create_time ;
    @property(nonatomic,copy)NSString * sort ;
    /**
     * 文艺的民宿 一缕茶香
     **/
    @property(nonatomic,copy)NSString * title ;
    /**
     * 999
     **/
    @property(nonatomic,copy)NSString * limit_count ;
    /**
     * 0571-881777882
     **/
    @property(nonatomic,copy)NSString * tel ;
    /**
     * 2016-03-20
     **/
    @property(nonatomic,copy)NSDate * begin_time ;
    /**
     * 2016-03-20
     **/
    @property(nonatomic,copy)NSDate * end_time ;
    @property(nonatomic,copy)NSString * reply_count ;
    @property(nonatomic,copy)NSString * duration ;
    @property(nonatomic,copy)NSString * how ;
    /**
     * 1458403200
     **/
    @property(nonatomic,copy)NSString * sTime ;
    /**
     * 1,2,3,4,5,6,7
     **/
    @property(nonatomic,copy)NSString * service_days_week ;
    /**
     * 2016-03-21 12:33
     **/
    @property(nonatomic,copy)NSString * update_time ;
    /**
     * <p>一期一会，一缕茶香</p><p>享
     **/
    @property(nonatomic,copy)NSString * detail ;
    @property(nonatomic,copy)NSString * price_rule ;
    @property(nonatomic,copy)NSString * discount ;
    @property(nonatomic,copy)NSString * designer ;
    @property(nonatomic,copy)NSString * camera ;
    @property(nonatomic,copy)NSString * site ;
    @property(nonatomic,copy)NSString * is_recommend ;
    /**
     * 13305816576
     **/
    @property(nonatomic,copy)NSString * mobile ;
    /**
     * /Uploads/Picture/201
     **/
    @property(nonatomic,copy)NSString * cover ;
    @property(nonatomic,copy)NSString * email ;
    /**
     * 380.00
     **/
    @property(nonatomic,copy)NSString * price_unit ;
    /**
     * 1
     **/
    @property(nonatomic,copy)NSString * service_days_type ;
    @property(nonatomic,copy)NSString * wechat ;
    /**
     * yuanlutanluzhe
     **/
    @property(nonatomic,copy)NSString * wechat_public ;
    @property(nonatomic,copy)NSString * presentation ;
    @property(nonatomic,copy)NSString * service_days_special ;
    /**
     * 11
     **/
    @property(nonatomic,copy)NSString * view_count ;
    @property(nonatomic,copy)NSString * why ;
    /**
     * 四眼井180号(近虎跑路)
     **/
    @property(nonatomic,copy)NSString * pos_address ;
    /**
     * 18
     **/
    @property(nonatomic,copy)NSString * id ;
    @property(nonatomic,copy)NSString * producer ;
    /**
     * 不详
     **/
    @property(nonatomic,copy)NSString * price_estimate ;
    @property(nonatomic,copy)NSString * summary ;
    /**
     * 1458403200
     **/
    @property(nonatomic,copy)NSString * eTime ;
 
    /**
     * CityDataHotelssearch
     **/
    @property(nonatomic,copy) CityDataHotelssearch * cityDataHotelssearch ;
    /**
     * DistrictDataHotelssearch
     **/
    @property(nonatomic,copy) DistrictDataHotelssearch * districtDataHotelssearch ;
    /**
     * ProvinceDataHotelssearch
     **/
    @property(nonatomic,copy) ProvinceDataHotelssearch * provinceDataHotelssearch ;
    /**
     * Tag_listDataHotelssearch
     **/
    @property(nonatomic,copy)NSArray * tag_listDataHotelssearch ;
@end