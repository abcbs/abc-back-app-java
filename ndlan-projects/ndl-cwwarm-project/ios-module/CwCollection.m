#import "CwCollection.h"

@implementation CwCollection
	
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
    return [NSString stringWithFormat:@" CltLifeStatus   %@ CltByPic   %@ CltAttr5   %@ CreateBy   %@ UpdateTime   %@ CltObjId   %@ CltBy   %@ CltByName   %@ CltAttr1   %@ CltAttr3   %@ UpdateBy   %@ CreateTime   %@ Remark   %@ CltObjType   %@ CltAttr4   %@ CltObjMsg   %@ CltBusiStatus   %@ CltAttr2   %@ CwCommentId   %@ ",
   self.cltLifeStatus ,
   self.cltByPic ,
   self.cltAttr5 ,
   self.createBy ,
   self.updateTime ,
   self.cltObjId ,
   self.cltBy ,
   self.cltByName ,
   self.cltAttr1 ,
   self.cltAttr3 ,
   self.updateBy ,
   self.createTime ,
   self.remark ,
   self.cltObjType ,
   self.cltAttr4 ,
   self.cltObjMsg ,
   self.cltBusiStatus ,
   self.cltAttr2 ,
   self.cwCommentId 
 ];
}

@end
