#import <Foundation/Foundation.h>


@interface TagsGetList : NSObject
	
	
    /**
     * status
     **/
    @property(nonatomic,assign)NSInteger  status ;
    /**
     * more
     **/
    @property(nonatomic,assign)NSInteger  more ;
 
    /**
     * DataTagsGetList
     **/
    @property(nonatomic,copy)NSArray * dataTagsGetList ;
@end