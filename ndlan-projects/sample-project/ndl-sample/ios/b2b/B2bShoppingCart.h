#import <Foundation/Foundation.h>


@interface B2bShoppingCart : NSObject
	
	
    /**
     * ?��?��?����??
     **/
    @property(nonatomic,copy)NSString * total ;
    /**
     * ?????��ID
     **/
    @property(nonatomic,copy)NSString * customerId ;
    /**
     * ????��?
     **/
    @property(nonatomic,copy)NSString * allDiscount ;
    /**
     * ??������?????���
     **/
    @property(nonatomic,copy)NSString * restName ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * allPrivilege ;
    /**
     * ?????��?�조???
     **/
    @property(nonatomic,copy)NSString * customerName ;
    /**
     * ??������?ID
     **/
    @property(nonatomic,copy)NSString * restId ;
    /**
     * ?????��??????��??��?��CUST|BUSI??��
     **/
    @property(nonatomic,copy)NSString * targetClient ;
    /**
     * ��???��?��?????����?
     **/
    @property(nonatomic,copy)NSString * cartId ;
 
    @property(nonatomic,copy)NSArray * cartPackage ;
@end