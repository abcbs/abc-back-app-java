#import "Tag_listDataHotelssearch.h"

@implementation Tag_listDataHotelssearch
	
- (instancetype)init{
    
    
    if ((self = [super init])!=nil) {
        //
    }
    return self;
}

- (id)copyWithZone:(NSZone *)zone{
    
    
    return self;
}

-(NSString *)description

{   
    return [NSString stringWithFormat:@" Title   %@ Id   %@ ",
   self.title ,
   self.id 
 ];
}

@end
