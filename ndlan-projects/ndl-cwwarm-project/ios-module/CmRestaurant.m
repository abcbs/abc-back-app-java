#import "CmRestaurant.h"

@implementation CmRestaurant
	
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
    return [NSString stringWithFormat:@" Mid   %@ AdrCity   %@ SynDatabaseStatus   %@ UpdateBy   %@ DeliveryCharge   %d Fangwu   %@ ServiceScore   %d OverlaycoordinateX   %@ DashesStyleIdArray   %@ Sn   %@ Notes   %@ StoreName   %@ Msg   %@ Description   %@ BusStop   %@ BusiEnd   %@ BusiSupperStart   %@ TakeOutStatus   %d CreateTime   %@ BankBranch   %@ ConsPerPerson   %d File11   %@ Subway   %@ StoreId   %@ ForeignLa   %@ UpdateTime   %@ LinkmanTel   %@ SynVersionId   %@ Denglumima   %@ BarPath   %@ ParentshopId   %@ File13   %@ BusiTakeoutSupperEnd   %@ OverlaycoordinateY   %@ TasteScore   %d LinkmanPhone   %@ TilecoordinateY   %@ File3   %@ BusiHoursClose   %@ EventSummary   %@ BusiBreakfastName   %@ InvoiceStatus   %@ LinkmanName   %@ BusiSupperEnd   %@ Zhucema   %@ MchAddr   %@ JuridicalPerson   %@ AdrProvince   %@ SettleBank   %@ ViewportcoordinateY   %@ Province   %@ File1   %@ OnlineOrderStatus   %@ BankName   %@ Kaihuo   %@ ComplaintTel   %@ Email   %@ DiningEnv   %@ BusiTakeoutLunchEnd   %@ BusiStart   %@ CertType   %@ IsBandCloudAccount   %@ BusinessLicense   %@ BusiHoursOpen   %@ OnlineOrderPhone   %@ Xianchang   %@ RestName   %@ BusiBreakfastEnd   %@ SpecialStyle   %@ JoinAuthentication   %d Shebeileixing   %@ OnlineOrderDishStatus   %@ RestId   %@ ViewportcoordinateX   %@ ShopType   %@ AdrDetail   %@ BusiLunchStart   %@ ShopStatus   %@ File14   %@ JuridicalPhone   %@ SettleAccno   %@ Shangleixing   %@ Code   %@ BusiSupperName   %@ TakeOutEnd   %@ DishesStyleNameArray   %@ PixelcoordinateY   %@ BusiLunchName   %@ CertExpdate   %@ Zhouzhijiguo   %@ ServeSpeedScore   %d CloudPassword   %@ AccName   %@ CloudUsername   %@ AdrArea   %@ City   %@ BusiLatesnackStart   %@ EventNotice   %@ PixelcoordinateX   %@ OrderDays   %d OnlineTakeoutPrompts   %@ TilecoordinateX   %@ SynDataCompleteTime   %@ BankAccount   %@ AccountType   %@ CName   %@ BusiLatesnackName   %@ SpecialEnv   %@ Manager   %@ BusiTakeoutLunchStart   %@ RestArea   %d File2   %@ SendPrice   %d TotalScore   %d OrderTel   %@ BusiTakeoutSupperStart   %@ MerchantId   %@ AreaCode   %@ IsPrivate   %@ WorldcoordinateX   %@ CreateBy   %@ OnlineRestStatus   %@ BusiBreakfastStart   %@ File12   %@ DeliveryRange   %d AdvertisingSlogan   %@ OnlineOrderPrompts   %@ Lng   %@ JuridicalId   %@ BusiLatesnackEnd   %@ RestExamine   %@ WorldcoordinateY   %@ BusiLunchEnd   %@ EnvironmentScore   %d Lat   %@ DishesStyleTypeArray   %@ Shuiwu   %@ File4   %@ TakeOutStart   %@ SynDataTime   %@ ",
   self.mid ,
   self.adrCity ,
   self.synDatabaseStatus ,
   self.updateBy ,
   self.deliveryCharge ,
   self.fangwu ,
   self.serviceScore ,
   self.overlaycoordinateX ,
   self.dashesStyleIdArray ,
   self.sn ,
   self.notes ,
   self.storeName ,
   self.msg ,
   self.description ,
   self.busStop ,
   self.busiEnd ,
   self.busiSupperStart ,
   self.takeOutStatus ,
   self.createTime ,
   self.bankBranch ,
   self.consPerPerson ,
   self.file11 ,
   self.subway ,
   self.storeId ,
   self.foreignLa ,
   self.updateTime ,
   self.linkmanTel ,
   self.synVersionId ,
   self.denglumima ,
   self.barPath ,
   self.parentshopId ,
   self.file13 ,
   self.busiTakeoutSupperEnd ,
   self.overlaycoordinateY ,
   self.tasteScore ,
   self.linkmanPhone ,
   self.tilecoordinateY ,
   self.file3 ,
   self.busiHoursClose ,
   self.eventSummary ,
   self.busiBreakfastName ,
   self.invoiceStatus ,
   self.linkmanName ,
   self.busiSupperEnd ,
   self.zhucema ,
   self.mchAddr ,
   self.juridicalPerson ,
   self.adrProvince ,
   self.settleBank ,
   self.viewportcoordinateY ,
   self.province ,
   self.file1 ,
   self.onlineOrderStatus ,
   self.bankName ,
   self.kaihuo ,
   self.complaintTel ,
   self.email ,
   self.diningEnv ,
   self.busiTakeoutLunchEnd ,
   self.busiStart ,
   self.certType ,
   self.isBandCloudAccount ,
   self.businessLicense ,
   self.busiHoursOpen ,
   self.onlineOrderPhone ,
   self.xianchang ,
   self.restName ,
   self.busiBreakfastEnd ,
   self.specialStyle ,
   self.joinAuthentication ,
   self.shebeileixing ,
   self.onlineOrderDishStatus ,
   self.restId ,
   self.viewportcoordinateX ,
   self.shopType ,
   self.adrDetail ,
   self.busiLunchStart ,
   self.shopStatus ,
   self.file14 ,
   self.juridicalPhone ,
   self.settleAccno ,
   self.shangleixing ,
   self.code ,
   self.busiSupperName ,
   self.takeOutEnd ,
   self.dishesStyleNameArray ,
   self.pixelcoordinateY ,
   self.busiLunchName ,
   self.certExpdate ,
   self.zhouzhijiguo ,
   self.serveSpeedScore ,
   self.cloudPassword ,
   self.accName ,
   self.cloudUsername ,
   self.adrArea ,
   self.city ,
   self.busiLatesnackStart ,
   self.eventNotice ,
   self.pixelcoordinateX ,
   self.orderDays ,
   self.onlineTakeoutPrompts ,
   self.tilecoordinateX ,
   self.synDataCompleteTime ,
   self.bankAccount ,
   self.accountType ,
   self.cName ,
   self.busiLatesnackName ,
   self.specialEnv ,
   self.manager ,
   self.busiTakeoutLunchStart ,
   self.restArea ,
   self.file2 ,
   self.sendPrice ,
   self.totalScore ,
   self.orderTel ,
   self.busiTakeoutSupperStart ,
   self.merchantId ,
   self.areaCode ,
   self.isPrivate ,
   self.worldcoordinateX ,
   self.createBy ,
   self.onlineRestStatus ,
   self.busiBreakfastStart ,
   self.file12 ,
   self.deliveryRange ,
   self.advertisingSlogan ,
   self.onlineOrderPrompts ,
   self.lng ,
   self.juridicalId ,
   self.busiLatesnackEnd ,
   self.restExamine ,
   self.worldcoordinateY ,
   self.busiLunchEnd ,
   self.environmentScore ,
   self.lat ,
   self.dishesStyleTypeArray ,
   self.shuiwu ,
   self.file4 ,
   self.takeOutStart ,
   self.synDataTime 
 ];
}

@end