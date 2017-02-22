#import "B2bLogisticsRealtime.h"

@implementation B2bLogisticsRealtime
	
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
    return [NSString stringWithFormat:@" LogisticsLineCode   %@ CurrAddress   %@ LogisticsRealtimeId   %@ NextAddress   %@ Remake   %@ LogisticsLineId   %@ CurrDate   %@ ",
   self.logisticsLineCode ,
   self.currAddress ,
   self.logisticsRealtimeId ,
   self.nextAddress ,
   self.remake ,
   self.logisticsLineId ,
   self.currDate 
 ];
}

@end
