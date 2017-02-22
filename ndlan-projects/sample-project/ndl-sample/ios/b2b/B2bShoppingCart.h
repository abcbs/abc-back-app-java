#import <Foundation/Foundation.h>


@interface B2bShoppingCart : NSObject
	
	
    /**
     * ?¢ã?¨¦?¡®¨¦??
     **/
    @property(nonatomic,copy)NSString * total ;
    /**
     * ?????¡¤ID
     **/
    @property(nonatomic,copy)NSString * customerId ;
    /**
     * ????¡ë?
     **/
    @property(nonatomic,copy)NSString * allDiscount ;
    /**
     * ??¡ª¨¦¡°?????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * restName ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * allPrivilege ;
    /**
     * ?????¡¤?¡ì¡°???
     **/
    @property(nonatomic,copy)NSString * customerName ;
    /**
     * ??¡ª¨¦¡°?ID
     **/
    @property(nonatomic,copy)NSString * restId ;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @property(nonatomic,copy)NSString * targetClient ;
    /**
     * ¨¨???¡ë?¨¨?????¨¦¡±?
     **/
    @property(nonatomic,copy)NSString * cartId ;
 
    @property(nonatomic,copy)NSArray * cartPackage ;
@end