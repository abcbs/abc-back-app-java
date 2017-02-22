#import "B2bProCategory.h"

@implementation B2bProCategory
	
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
    return [NSString stringWithFormat:@" RestId   %@ CategoryGrade   %@ CategoryToneIos   %@ CategoryId   %@ ParentCategoryName   %@ CategoryStatus   %@ ParentCategoryId   %@ CategoryName   %@ ParentIdPath   %@ ParentNamePath   %@ CategoryToneAndroid   %@ ",
   self.restId ,
   self.categoryGrade ,
   self.categoryToneIos ,
   self.categoryId ,
   self.parentCategoryName ,
   self.categoryStatus ,
   self.parentCategoryId ,
   self.categoryName ,
   self.parentIdPath ,
   self.parentNamePath ,
   self.categoryToneAndroid 
 ];
}

@end
