#import "CwWorkJob.h"

@implementation CwWorkJob
	
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
    return [NSString stringWithFormat:@" WjobParentPathId   %@ WjobCategoryName   %@ CwWorkJobId   %@ WjobCategoryGrade   %@ CreateBy   %@ WjobParentCategoryId   %@ WjobBusiStatus   %@ WjobCategoryDesc   %@ WjobParentPathName   %@ UpdateTime   %@ UpdateBy   %@ WjobParentCategoryName   %@ WjobLifeStatus   %@ CreateTime   %@ ",
   self.wjobParentPathId ,
   self.wjobCategoryName ,
   self.cwWorkJobId ,
   self.wjobCategoryGrade ,
   self.createBy ,
   self.wjobParentCategoryId ,
   self.wjobBusiStatus ,
   self.wjobCategoryDesc ,
   self.wjobParentPathName ,
   self.updateTime ,
   self.updateBy ,
   self.wjobParentCategoryName ,
   self.wjobLifeStatus ,
   self.createTime 
 ];
}

@end
