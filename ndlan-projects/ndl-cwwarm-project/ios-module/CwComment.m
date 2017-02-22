#import "CwComment.h"

@implementation CwComment
	
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
    return [NSString stringWithFormat:@" CmtContent   %@ CmtBusiStatus   %@ CmtAttr3   %@ CmtByName   %@ UpdateBy   %@ Remark   %@ CmtAttr1   %@ CwCommentId   %@ UpdateTime   %@ CmtAttr5   %@ CreateTime   %@ CreateBy   %@ CmtObjType   %@ CmtAttr4   %@ CmtLifeStatus   %@ CmtBy   %@ CmtObjId   %@ CmtObjMsg   %@ CmtAttr2   %@ CmtByPic   %@ ",
   self.cmtContent ,
   self.cmtBusiStatus ,
   self.cmtAttr3 ,
   self.cmtByName ,
   self.updateBy ,
   self.remark ,
   self.cmtAttr1 ,
   self.cwCommentId ,
   self.updateTime ,
   self.cmtAttr5 ,
   self.createTime ,
   self.createBy ,
   self.cmtObjType ,
   self.cmtAttr4 ,
   self.cmtLifeStatus ,
   self.cmtBy ,
   self.cmtObjId ,
   self.cmtObjMsg ,
   self.cmtAttr2 ,
   self.cmtByPic 
 ];
}

@end
