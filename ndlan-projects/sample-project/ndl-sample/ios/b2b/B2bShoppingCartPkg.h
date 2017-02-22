#import <Foundation/Foundation.h>


@interface B2bShoppingCartPkg : NSObject
	
	
    /**
     * ???������??�C??????��?��
     **/
    @property(nonatomic,copy)NSString * strategyDesc ;
    /**
     * ???������??�C???
     **/
    @property(nonatomic,copy)NSString * slsPmtnId ;
    /**
     * ��???��?��???��?ID
     **/
    @property(nonatomic,copy)NSString * cartId ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * ??��?��?��?����??
     **/
    @property(nonatomic,copy)NSString * amount ;
    @property(nonatomic,copy)NSString * derate ;
    @property(nonatomic,copy)NSString * discount ;
    /**
     * ???������???id
     **/
    @property(nonatomic,copy)NSString * restId ;
    /**
     * ?????��?�조???
     **/
    @property(nonatomic,copy)NSString * customerName ;
    /**
     * ��???��?��????��ID
     **/
    @property(nonatomic,copy)NSString * cartPkgId ;
    /**
     * ?��?????�C????
     **/
    @property(nonatomic,copy)NSString * payType ;
    /**
     * ?????��???????���
     **/
    @property(nonatomic,copy)NSString * supplierName ;
    /**
     * ?????��ID
     **/
    @property(nonatomic,copy)NSString * customerId ;
    /**
     * ???������???????���
     **/
    @property(nonatomic,copy)NSString * restName ;
    /**
     * ?????��???ID
     **/
    @property(nonatomic,copy)NSString * supplierId ;
    /**
     * ????��?��?����??
     **/
    @property(nonatomic,copy)NSString * amountPaid ;
    /**
     * ?��?????��????
     **/
    @property(nonatomic,copy)NSString * codelessSum ;
    /**
     * ?????��??????��??��?��CUST|BUSI??��
     **/
    @property(nonatomic,copy)NSString * targetClient ;
 
    @property(nonatomic,copy)NSArray * cartItem ;
@end