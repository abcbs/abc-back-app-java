#import <Foundation/Foundation.h>


@interface B2bOrder : NSObject
	
	
    /**
     * ?¡ª?????¡±????
     **/
    @property(nonatomic,copy)NSString * codelessSum ;
    @property(nonatomic,copy)NSString * restName ;
    /**
     * ?¡è???¡§
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * ¨¨??????¡è?ID
     **/
    @property(nonatomic,copy)NSString * bId ;
    /**
     * ?¡±?????¨C????
     **/
    @property(nonatomic,copy)NSString * payType ;
    /**
     * ?????¡¤??????¨¦??¨¨?¡èCUST|BUSI??¡ë
     **/
    @property(nonatomic,copy)NSString * targetClient ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * allPrivilege ;
    /**
     * ?????¡¤ID
     **/
    @property(nonatomic,copy)NSString * customerId ;
    /**
     * ????¡ë?
     **/
    @property(nonatomic,copy)NSString * allDiscount ;
    /**
     * ?????¡¤????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * customerName ;
    /**
     * ??¡±?¡±?¨¦?¡®¨¦??
     **/
    @property(nonatomic,copy)NSString * bAmount ;
    /**
     * ¨¨?????????¡ì¡ã
     **/
    @property(nonatomic,copy)NSString * bName ;
    /**
     * ¨¨???¡ë?¨¨??id
     **/
    @property(nonatomic,copy)NSString * cartId ;
    /**
     * ¨¨??????¡è???¨C??¡¤
     **/
    @property(nonatomic,copy)NSString * bNo ;
    @property(nonatomic,copy)NSString * restId ;
    /**
     * ????¡±?¨¦?¡®¨¦??
     **/
    @property(nonatomic,copy)NSString * amountPaid ;
 
@end