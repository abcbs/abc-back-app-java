#import <Foundation/Foundation.h>


@interface B2bShoppingCartPkg : NSObject
	
	
    /**
     * ???¨¦¡±¢ã??¨C??????¨¨?¡ã
     **/
    @property(nonatomic,copy)NSString * strategyDesc ;
    /**
     * ???¨¦¡±¢ã??¨C???
     **/
    @property(nonatomic,copy)NSString * slsPmtnId ;
    /**
     * ¨¨???¡ë?¨¨???¡è?ID
     **/
    @property(nonatomic,copy)NSString * cartId ;
    /**
     * ?¡è???¡§
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    @property(nonatomic,copy)NSString * amount ;
    @property(nonatomic,copy)NSString * derate ;
    @property(nonatomic,copy)NSString * discount ;
    /**
     * ???¨¦¡±¢ã???id
     **/
    @property(nonatomic,copy)NSString * restId ;
    /**
     * ?????¡¤?¡ì¡°???
     **/
    @property(nonatomic,copy)NSString * customerName ;
    /**
     * ¨¨???¡ë?¨¨????¡­ID
     **/
    @property(nonatomic,copy)NSString * cartPkgId ;
    /**
     * ?¡±?????¨C????
     **/
    @property(nonatomic,copy)NSString * payType ;
    /**
     * ?????¡±???????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ?????¡¤ID
     **/
    @property(nonatomic,copy)NSString * customerId ;
    /**
     * ???¨¦¡±¢ã???????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * restName ;
    /**
     * ?????¡±???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    @property(nonatomic,copy)NSString * amountPaid ;
    /**
     * ?¡ª?????¡±????
     **/
    @property(nonatomic,copy)NSString * codelessSum ;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @property(nonatomic,copy)NSString * targetClient ;
 
    @property(nonatomic,copy)NSArray * cartItem ;
@end