#import "DataReamsGetItem.h"

@implementation DataReamsGetItem
	
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
    return [NSString stringWithFormat:@" Price_rule   %@ Camera   %@ Summary   %@ Wechat   %@ Preface   %@ Price_unit   %@ Title   %@ Email   %@ STime   %@ Tel   %@ Site   %@ CityDataReamsGetItem   %@ Producer   %@ Update_time   %@ Why   %@ ETime   %@ Duration   %@ Detail   %@ Event   %@ Is_recommend   %@ DistrictDataReamsGetItem   %@ Mobile   %@ Postscript   %@ Limit_count   %@ Service_days_week   %@ How   %@ Create_time   %@ Reply_count   %@ View_count   %@ Surprise   %@ ProvinceDataReamsGetItem   %@ Id   %@ Pos_address   %@ Discount   %@ Tag_listDataReamsGetItem   %@ ",
   self.price_rule ,
   self.camera ,
   self.summary ,
   self.wechat ,
   self.preface ,
   self.price_unit ,
   self.title ,
   self.email ,
   self.sTime ,
   self.tel ,
   self.site ,
   self.cityDataReamsGetItem ,
   self.producer ,
   self.update_time ,
   self.why ,
   self.eTime ,
   self.duration ,
   self.detail ,
   self.event ,
   self.is_recommend ,
   self.districtDataReamsGetItem ,
   self.mobile ,
   self.postscript ,
   self.limit_count ,
   self.service_days_week ,
   self.how ,
   self.create_time ,
   self.reply_count ,
   self.view_count ,
   self.surprise ,
   self.provinceDataReamsGetItem ,
   self.id ,
   self.pos_address ,
   self.discount ,
   self.tag_listDataReamsGetItem 
 ];
}

@end
