#import "CwPartake.h"

@implementation CwPartake
	
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
    return [NSString stringWithFormat:@" PtkAttr5   %@ PtkByPic   %@ PtkAttr1   %@ PtkBusiStatus   %@ PtkAttr4   %@ PtkObjId   %@ CreateTime   %@ PtkAttr3   %@ PtkLifeStatus   %@ UpdateTime   %@ UpdateBy   %@ CwPartakeId   %@ PtkAttr2   %@ PtkByName   %@ PtkObjType   %@ PtkObjMsg   %@ PtkRemark   %@ PtkBy   %@ CreateBy   %@ ",
   self.ptkAttr5 ,
   self.ptkByPic ,
   self.ptkAttr1 ,
   self.ptkBusiStatus ,
   self.ptkAttr4 ,
   self.ptkObjId ,
   self.createTime ,
   self.ptkAttr3 ,
   self.ptkLifeStatus ,
   self.updateTime ,
   self.updateBy ,
   self.cwPartakeId ,
   self.ptkAttr2 ,
   self.ptkByName ,
   self.ptkObjType ,
   self.ptkObjMsg ,
   self.ptkRemark ,
   self.ptkBy ,
   self.createBy 
 ];
}

@end
