#import <Foundation/Foundation.h>


@interface UsersGetupdateinfo : NSObject
	
	
    /**
     * status
     **/
    @property(nonatomic,assign)NSInteger  status ;
    /**
     * 获取用户信息成功
     **/
    @property(nonatomic,copy)NSString * info ;
 
    /**
     * DataUsersGetupdateinfo
     **/
    @property(nonatomic,copy) DataUsersGetupdateinfo * dataUsersGetupdateinfo ;
@end