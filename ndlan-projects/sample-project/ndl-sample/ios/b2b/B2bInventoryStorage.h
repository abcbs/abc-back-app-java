#import <Foundation/Foundation.h>


@interface B2bInventoryStorage : NSObject
	
	
    /**
     * ??¡°????¡è?¨¨?¡§
     **/
    @property(nonatomic,copy)NSString * invHeadId ;
    /**
     * ?¡­???¡ã¨¦??
     **/
    @property(nonatomic,copy)NSString * storageQty ;
    /**
     * ?????¡±???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    @property(nonatomic,copy)NSString * seriesId ;
    /**
     * ¨¨??????¡è?ID
     **/
    @property(nonatomic,copy)NSString * orderHeadId ;
    @property(nonatomic,copy)NSString * proId ;
    /**
     * ¨¨???????¡­ID
     **/
    @property(nonatomic,copy)NSString * orderPkgId ;
    /**
     * ¨¨¡ì?????¡ã????
     **/
    @property(nonatomic,copy)NSString * size ;
    @property(nonatomic,copy)NSString * parentIdPath ;
    /**
     * ??¡ì?¡°?????¡À?????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * categoryName ;
    /**
     * ¨¨¡ì????
     **/
    @property(nonatomic,copy)NSString * specsName ;
    /**
     * ¨¨???????¡­??¨C??¡¤
     **/
    @property(nonatomic,copy)NSString * orderPkgCode ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * volume ;
    @property(nonatomic,copy)NSString * specsId ;
    @property(nonatomic,copy)NSString * orderLineId ;
    /**
     * ????¡ë?
     **/
    @property(nonatomic,copy)NSString * pic ;
    /**
     * ????¡°??????¡ª
     **/
    @property(nonatomic,copy)NSString * seriesName ;
    /**
     * ?¡è???¡§
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * ??¡ì?¡°?ID
     **/
    @property(nonatomic,copy)NSString * baseProId ;
    /**
     * ??¡ì?¡°????¨¨?¡ã
     **/
    @property(nonatomic,copy)NSString * proDesc ;
    /**
     * ?¡­???¡°???
     **/
    @property(nonatomic,copy)NSString * storageUser ;
    /**
     * ???¨¦¡±?
     **/
    @property(nonatomic,copy)NSString * invStorageId ;
    /**
     * ?¡­???¡°?¡ª????
     **/
    @property(nonatomic,copy)NSDate * storageDate ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * barCode ;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @property(nonatomic,copy)NSString * proCode ;
    /**
     * ?¡­???¡°???????¢ã?¨¨?????|¨¨¡ã????|¨¨??¨¨?¡ì?¢ã¡®
     **/
    @property(nonatomic,copy)NSString * source ;
    /**
     * ??¡ì?¡°?¨¨¡ë???¡¤
     **/
    @property(nonatomic,copy)NSString * proColorNo ;
    /**
     * ??¡ì?¡°???¨C???
     **/
    @property(nonatomic,copy)NSString * baseProNo ;
    /**
     * ??¡ì?¡°?????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * proName ;
    @property(nonatomic,copy)NSString * parentNamePath ;
    /**
     * ??¡ì?¡°?????¡À?
     **/
    @property(nonatomic,copy)NSString * categoryId ;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ?¡­???¡°??¡¤???
     **/
    @property(nonatomic,copy)NSString * storagePrice ;
    /**
     * ????????¨C??¡¤
     **/
    @property(nonatomic,copy)NSString * orderDetailNo ;
    /**
     * ???¨¦??
     **/
    @property(nonatomic,copy)NSString * capacity ;
 
@end