#import <Foundation/Foundation.h>


@interface ReamsGetItem : NSObject
	
	
    /**
     * status
     **/
    @property(nonatomic,assign)NSInteger  status ;
    /**
     * more
     **/
    @property(nonatomic,assign)NSInteger  more ;
 
    /**
     * DataReamsGetItem
     **/
    @property(nonatomic,copy) DataReamsGetItem * dataReamsGetItem ;
@end