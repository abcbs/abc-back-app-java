#import "B2bOrderRmtDetail.h"

@implementation B2bOrderRmtDetail
	
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
    return [NSString stringWithFormat:@" CategoryName   %@ Quantity   %@ ProColorNo   %@ Remake   %@ Volume   %@ ParentNamePath   %@ CustomerId   %@ ProDesc   %@ ApplDesc   %@ RestName   %@ CustomerName   %@ OrderRmtDetailCode   %@ OrderDetailId   %@ OrderDetailNo   %@ Pic   %@ OrderRmtDetailId   %@ BaseProId   %@ ProCode   %@ SupplierName   %@ BaseProNo   %@ SupplierId   %@ SpecsId   %@ SeriesName   %@ ParentIdPath   %@ OrderPkgCode   %@ OrignStatus   %@ Capacity   %@ Price   %@ ProName   %@ RestId   %@ OrderRmtPkgId   %@ OrderRmtHeadId   %@ SeriesId   %@ Size   %@ DamageSpec   %@ OrderPkgId   %@ BarCode   %@ SpecsName   %@ OrderHeadId   %@ StorageStatus   %@ CategoryId   %@ ",
   self.categoryName ,
   self.quantity ,
   self.proColorNo ,
   self.remake ,
   self.volume ,
   self.parentNamePath ,
   self.customerId ,
   self.proDesc ,
   self.applDesc ,
   self.restName ,
   self.customerName ,
   self.orderRmtDetailCode ,
   self.orderDetailId ,
   self.orderDetailNo ,
   self.pic ,
   self.orderRmtDetailId ,
   self.baseProId ,
   self.proCode ,
   self.supplierName ,
   self.baseProNo ,
   self.supplierId ,
   self.specsId ,
   self.seriesName ,
   self.parentIdPath ,
   self.orderPkgCode ,
   self.orignStatus ,
   self.capacity ,
   self.price ,
   self.proName ,
   self.restId ,
   self.orderRmtPkgId ,
   self.orderRmtHeadId ,
   self.seriesId ,
   self.size ,
   self.damageSpec ,
   self.orderPkgId ,
   self.barCode ,
   self.specsName ,
   self.orderHeadId ,
   self.storageStatus ,
   self.categoryId 
 ];
}

@end
