#import "B2bInventoryStorage.h"

@implementation B2bInventoryStorage
	
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
    return [NSString stringWithFormat:@" InvHeadId   %@ StorageQty   %@ SupplierId   %@ SeriesId   %@ OrderHeadId   %@ ProId   %@ OrderPkgId   %@ Size   %@ ParentIdPath   %@ CategoryName   %@ SpecsName   %@ OrderPkgCode   %@ Volume   %@ SpecsId   %@ OrderLineId   %@ Pic   %@ SeriesName   %@ Remark   %@ BaseProId   %@ ProDesc   %@ StorageUser   %@ InvStorageId   %@ StorageDate   %@ BarCode   %@ ProCode   %@ Source   %@ ProColorNo   %@ BaseProNo   %@ ProName   %@ ParentNamePath   %@ CategoryId   %@ SupplierName   %@ StoragePrice   %@ OrderDetailNo   %@ Capacity   %@ ",
   self.invHeadId ,
   self.storageQty ,
   self.supplierId ,
   self.seriesId ,
   self.orderHeadId ,
   self.proId ,
   self.orderPkgId ,
   self.size ,
   self.parentIdPath ,
   self.categoryName ,
   self.specsName ,
   self.orderPkgCode ,
   self.volume ,
   self.specsId ,
   self.orderLineId ,
   self.pic ,
   self.seriesName ,
   self.remark ,
   self.baseProId ,
   self.proDesc ,
   self.storageUser ,
   self.invStorageId ,
   self.storageDate ,
   self.barCode ,
   self.proCode ,
   self.source ,
   self.proColorNo ,
   self.baseProNo ,
   self.proName ,
   self.parentNamePath ,
   self.categoryId ,
   self.supplierName ,
   self.storagePrice ,
   self.orderDetailNo ,
   self.capacity 
 ];
}

@end
