#import "Hot_city_dataLocationsgetlist.h"

@implementation Hot_city_dataLocationsgetlist
	
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
    return [NSString stringWithFormat:@" Count_item   %@ Id   %d Name   %@ ",
   self.count_item ,
   self.id ,
   self.name 
 ];
}

@end
