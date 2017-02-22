#import <Foundation/Foundation.h>


@interface B2bInventoryStorage : NSObject
	
	
    /**
     * ??��????��?��?��
     **/
    @property(nonatomic,copy)NSString * invHeadId ;
    /**
     * ?��???�㨦??
     **/
    @property(nonatomic,copy)NSString * storageQty ;
    /**
     * ?????��???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    @property(nonatomic,copy)NSString * seriesId ;
    /**
     * ��??????��?ID
     **/
    @property(nonatomic,copy)NSString * orderHeadId ;
    @property(nonatomic,copy)NSString * proId ;
    /**
     * ��???????��ID
     **/
    @property(nonatomic,copy)NSString * orderPkgId ;
    /**
     * ����?????��????
     **/
    @property(nonatomic,copy)NSString * size ;
    @property(nonatomic,copy)NSString * parentIdPath ;
    /**
     * ??��?��?????��?????���
     **/
    @property(nonatomic,copy)NSString * categoryName ;
    /**
     * ����????
     **/
    @property(nonatomic,copy)NSString * specsName ;
    /**
     * ��???????��??�C??��
     **/
    @property(nonatomic,copy)NSString * orderPkgCode ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * volume ;
    @property(nonatomic,copy)NSString * specsId ;
    @property(nonatomic,copy)NSString * orderLineId ;
    /**
     * ????��?
     **/
    @property(nonatomic,copy)NSString * pic ;
    /**
     * ????��??????��
     **/
    @property(nonatomic,copy)NSString * seriesName ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * ??��?��?ID
     **/
    @property(nonatomic,copy)NSString * baseProId ;
    /**
     * ??��?��????��?��
     **/
    @property(nonatomic,copy)NSString * proDesc ;
    /**
     * ?��???��???
     **/
    @property(nonatomic,copy)NSString * storageUser ;
    /**
     * ???����?
     **/
    @property(nonatomic,copy)NSString * invStorageId ;
    /**
     * ?��???��?��????
     **/
    @property(nonatomic,copy)NSDate * storageDate ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * barCode ;
    /**
     * ??��?��???�C???
     **/
    @property(nonatomic,copy)NSString * proCode ;
    /**
     * ?��???��???????��?��?????|����????|��??��?��?�㡮
     **/
    @property(nonatomic,copy)NSString * source ;
    /**
     * ??��?��?����???��
     **/
    @property(nonatomic,copy)NSString * proColorNo ;
    /**
     * ??��?��???�C???
     **/
    @property(nonatomic,copy)NSString * baseProNo ;
    /**
     * ??��?��?????���
     **/
    @property(nonatomic,copy)NSString * proName ;
    @property(nonatomic,copy)NSString * parentNamePath ;
    /**
     * ??��?��?????��?
     **/
    @property(nonatomic,copy)NSString * categoryId ;
    /**
     * ?????��???????���
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ?��???��??��???
     **/
    @property(nonatomic,copy)NSString * storagePrice ;
    /**
     * ????????�C??��
     **/
    @property(nonatomic,copy)NSString * orderDetailNo ;
    /**
     * ???��??
     **/
    @property(nonatomic,copy)NSString * capacity ;
 
@end