#import "B2bInventoryHead.h"

@implementation B2bInventoryHead
	
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
    return [NSString stringWithFormat:@" SeriesId   %@ BarCode   %@ SeriesName   %@ BaseProNo   %@ Volume   %@ CategoryName   %@ RealQty   %@ InvHeadId   %@ SupplierType   %@ AvailableQty   %@ SupplierId   %@ ApplDesc   %@ Capacity   %@ Size   %@ BaseProId   %@ SupplierName   %@ ProColorNo   %@ CategoryId   %@ CapacityVolume   %@ TotalQty   %@ SpecsId   %@ ProDesc   %@ SafetyStock   %@ DeliveryQty   %@ RestId   %@ SpecsName   %@ ProName   %@ ",
   self.seriesId ,
   self.barCode ,
   self.seriesName ,
   self.baseProNo ,
   self.volume ,
   self.categoryName ,
   self.realQty ,
   self.invHeadId ,
   self.supplierType ,
   self.availableQty ,
   self.supplierId ,
   self.applDesc ,
   self.capacity ,
   self.size ,
   self.baseProId ,
   self.supplierName ,
   self.proColorNo ,
   self.categoryId ,
   self.capacityVolume ,
   self.totalQty ,
   self.specsId ,
   self.proDesc ,
   self.safetyStock ,
   self.deliveryQty ,
   self.restId ,
   self.specsName ,
   self.proName 
 ];
}

@end
