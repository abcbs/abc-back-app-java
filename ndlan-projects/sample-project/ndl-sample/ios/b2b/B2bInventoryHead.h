#import <Foundation/Foundation.h>


@interface B2bInventoryHead : NSObject
	
	
    /**
     * ????¡°??????¡ªID
     **/
    @property(nonatomic,copy)NSString * seriesId ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * barCode ;
    /**
     * ????¡°??????¡ª
     **/
    @property(nonatomic,copy)NSString * seriesName ;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @property(nonatomic,copy)NSString * baseProNo ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * volume ;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * categoryName ;
    /**
     * ???¨¦?¡­??¡ã¨¦??
     **/
    @property(nonatomic,copy)NSString * realQty ;
    /**
     * ???¨¦¡±?
     **/
    @property(nonatomic,copy)NSString * invHeadId ;
    /**
     * 0:???¨¦¡±¢ã????¢ã?1????????¡±???
     **/
    @property(nonatomic,copy)NSString * supplierType ;
    /**
     * ????¡±?¨¦??
     **/
    @property(nonatomic,copy)NSString * availableQty ;
    /**
     * ?????¡±?????¨C????¢ã????¨¦¡±¢ã????¢ã¡®
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ??¡±?¡±¡§??????
     **/
    @property(nonatomic,copy)NSString * applDesc ;
    /**
     * ???¨¦??
     **/
    @property(nonatomic,copy)NSString * capacity ;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    @property(nonatomic,copy)NSString * size ;
    /**
     * ??¡ì?¡°?ID
     **/
    @property(nonatomic,copy)NSString * baseProId ;
    /**
     * ?????¡±???????¡ì¡ã?¢ã????¨¦¡±¢ã????¢ã¡®
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    @property(nonatomic,copy)NSString * proColorNo ;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    @property(nonatomic,copy)NSString * categoryId ;
    @property(nonatomic,copy)NSString * capacityVolume ;
    /**
     * ?¢ã???¡ã¨¦??
     **/
    @property(nonatomic,copy)NSString * totalQty ;
    @property(nonatomic,copy)NSString * specsId ;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    @property(nonatomic,copy)NSString * proDesc ;
    /**
     * ??¡ë?¡­¡§??¡°???
     **/
    @property(nonatomic,copy)NSString * safetyStock ;
    /**
     * ?????¡°¨¦??
     **/
    @property(nonatomic,copy)NSString * deliveryQty ;
    @property(nonatomic,copy)NSString * restId ;
    /**
     * ¨¨¡ì????
     **/
    @property(nonatomic,copy)NSString * specsName ;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * proName ;
 
@end