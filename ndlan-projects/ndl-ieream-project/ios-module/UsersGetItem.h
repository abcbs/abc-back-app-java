#import <Foundation/Foundation.h>


@interface UsersGetItem : NSObject
	
	
    /**
     * status
     **/
    @property(nonatomic,assign)NSInteger  status ;
    /**
     * 获取用户信息成功
     **/
    @property(nonatomic,copy)NSString * info ;
 
    /**
     * DataUsersGetItem
     **/
    @property(nonatomic,copy) DataUsersGetItem * dataUsersGetItem ;
@end