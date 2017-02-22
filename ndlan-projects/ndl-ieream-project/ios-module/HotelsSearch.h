#import <Foundation/Foundation.h>


@interface HotelsSearch : NSObject
	
	
    /**
     * status
     **/
    @property(nonatomic,assign)NSInteger  status ;
    @property(nonatomic,copy)NSString * info ;
    /**
     * more
     **/
    @property(nonatomic,assign)NSInteger  more ;
 
    /**
     * DataHotelsSearch
     **/
    @property(nonatomic,copy)NSArray * dataHotelsSearch ;
@end