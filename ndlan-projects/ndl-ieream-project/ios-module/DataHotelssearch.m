#import "DataHotelssearch.h"

@implementation DataHotelssearch
	
- (instancetype)init{
    
    
    if ((self = [super init])!=nil) {
        //
    }
    return self;
}

- (id)copyWithZone:(NSZone *)zone{
    
    
    return self;
}

-(NSString *)description

{   
    return [NSString stringWithFormat:@" Create_time   %@ Sort   %@ Title   %@ Limit_count   %@ Tel   %@ Begin_time   %@ End_time   %@ Reply_count   %@ DistrictDataHotelssearch   %@ Duration   %@ How   %@ STime   %@ Service_days_week   %@ Update_time   %@ Detail   %@ Price_rule   %@ Discount   %@ Designer   %@ Tag_listDataHotelssearch   %@ Camera   %@ ProvinceDataHotelssearch   %@ Site   %@ Is_recommend   %@ Mobile   %@ Cover   %@ CityDataHotelssearch   %@ Email   %@ Price_unit   %@ Service_days_type   %@ Wechat   %@ Wechat_public   %@ Presentation   %@ Service_days_special   %@ View_count   %@ Why   %@ Pos_address   %@ Id   %@ Producer   %@ Price_estimate   %@ Summary   %@ ETime   %@ ",
   self.create_time ,
   self.sort ,
   self.title ,
   self.limit_count ,
   self.tel ,
   self.begin_time ,
   self.end_time ,
   self.reply_count ,
   self.districtDataHotelssearch ,
   self.duration ,
   self.how ,
   self.sTime ,
   self.service_days_week ,
   self.update_time ,
   self.detail ,
   self.price_rule ,
   self.discount ,
   self.designer ,
   self.tag_listDataHotelssearch ,
   self.camera ,
   self.provinceDataHotelssearch ,
   self.site ,
   self.is_recommend ,
   self.mobile ,
   self.cover ,
   self.cityDataHotelssearch ,
   self.email ,
   self.price_unit ,
   self.service_days_type ,
   self.wechat ,
   self.wechat_public ,
   self.presentation ,
   self.service_days_special ,
   self.view_count ,
   self.why ,
   self.pos_address ,
   self.id ,
   self.producer ,
   self.price_estimate ,
   self.summary ,
   self.eTime 
 ];
}

@end
