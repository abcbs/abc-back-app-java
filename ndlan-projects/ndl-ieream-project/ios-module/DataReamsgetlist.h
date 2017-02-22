#import <Foundation/Foundation.h>


@interface DataReamsgetlist : NSObject
	
	
    @property(nonatomic,copy)NSString * producer ;
    /**
     * 1456329600
     **/
    @property(nonatomic,copy)NSString * sTime ;
    /**
     * 1456290460
     **/
    @property(nonatomic,copy)NSString * create_time ;
    /**
     * 0.00
     **/
    @property(nonatomic,copy)NSString * price_unit ;
    /**
     * 5
     **/
    @property(nonatomic,copy)NSString * limit_count ;
    @property(nonatomic,copy)NSString * preface ;
    @property(nonatomic,copy)NSString * camera ;
    /**
     * 1456329600
     **/
    @property(nonatomic,copy)NSString * eTime ;
    /**
     * 6,7,5
     **/
    @property(nonatomic,copy)NSString * service_days_week ;
    /**
     * 标有
     **/
    @property(nonatomic,copy)NSString * pos_address ;
    @property(nonatomic,copy)NSString * postscript ;
    @property(nonatomic,copy)NSString * summary ;
    /**
     * 离离
     **/
    @property(nonatomic,copy)NSString * title ;
    @property(nonatomic,copy)NSString * event ;
    @property(nonatomic,copy)NSString * email ;
    /**
     * 1
     **/
    @property(nonatomic,copy)NSString * id ;
    @property(nonatomic,copy)NSString * duration ;
    @property(nonatomic,copy)NSString * how ;
    @property(nonatomic,copy)NSString * surprise ;
    @property(nonatomic,copy)NSString * price_rule ;
    @property(nonatomic,copy)NSString * reply_count ;
    /**
     * <p>242243<br/></p>
     **/
    @property(nonatomic,copy)NSString * detail ;
    @property(nonatomic,copy)NSString * site ;
    /**
     * 1456290460
     **/
    @property(nonatomic,copy)NSString * update_time ;
    @property(nonatomic,copy)NSString * wechat ;
    @property(nonatomic,copy)NSString * is_recommend ;
    @property(nonatomic,copy)NSString * discount ;
    @property(nonatomic,copy)NSString * why ;
    @property(nonatomic,copy)NSString * mobile ;
    @property(nonatomic,copy)NSString * tel ;
    @property(nonatomic,copy)NSString * view_count ;
 
    /**
     * DistrictDataReamsgetlist
     **/
    @property(nonatomic,copy) DistrictDataReamsgetlist * districtDataReamsgetlist ;
    /**
     * Tag_listDataReamsgetlist
     **/
    @property(nonatomic,copy)NSArray * tag_listDataReamsgetlist ;
    /**
     * CityDataReamsgetlist
     **/
    @property(nonatomic,copy) CityDataReamsgetlist * cityDataReamsgetlist ;
    /**
     * ProvinceDataReamsgetlist
     **/
    @property(nonatomic,copy) ProvinceDataReamsgetlist * provinceDataReamsgetlist ;
@end