#import <Foundation/Foundation.h>


@interface B2bOrder : NSObject
	
	
    /**
     * ?��?????��????
     **/
    @property(nonatomic,copy)NSString * codelessSum ;
    @property(nonatomic,copy)NSString * restName ;
    /**
     * ?��???��
     **/
    @property(nonatomic,copy)NSString * remark ;
    /**
     * ��??????��?ID
     **/
    @property(nonatomic,copy)NSString * bId ;
    /**
     * ?��?????�C????
     **/
    @property(nonatomic,copy)NSString * payType ;
    /**
     * ?????��??????��??��?��CUST|BUSI??��
     **/
    @property(nonatomic,copy)NSString * targetClient ;
    /**
     * ??????
     **/
    @property(nonatomic,copy)NSString * allPrivilege ;
    /**
     * ?????��ID
     **/
    @property(nonatomic,copy)NSString * customerId ;
    /**
     * ????��?
     **/
    @property(nonatomic,copy)NSString * allDiscount ;
    /**
     * ?????��????���
     **/
    @property(nonatomic,copy)NSString * customerName ;
    /**
     * ??��?��?��?����??
     **/
    @property(nonatomic,copy)NSString * bAmount ;
    /**
     * ��?????????���
     **/
    @property(nonatomic,copy)NSString * bName ;
    /**
     * ��???��?��??id
     **/
    @property(nonatomic,copy)NSString * cartId ;
    /**
     * ��??????��???�C??��
     **/
    @property(nonatomic,copy)NSString * bNo ;
    @property(nonatomic,copy)NSString * restId ;
    /**
     * ????��?��?����??
     **/
    @property(nonatomic,copy)NSString * amountPaid ;
 
@end