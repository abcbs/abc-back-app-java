#import "CmEmployee.h"

@implementation CmEmployee
	
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
    return [NSString stringWithFormat:@" PlainPassword   %@ EmerConTel   %@ UpdateTime   %@ AuthorizationCode   %@ CreateBy   %@ StoreId   %@ LoginPassword   %@ RestId   %@ AuthorizationMake   %@ EmpId   %@ Mobile   %@ ResProvince   %@ UpdateBy   %@ CreateTime   %@ EmerContact   %@ EmpStatus   %@ IdCard   %@ AdrProvince   %@ JobStatus   %@ ResCity   %@ Gender   %@ Address   %@ EmpNum   %@ SysdataType   %@ JobPic   %@ Salt   %@ BelongRest   %@ LoginUsername   %@ Name   %@ ResidenceAdr   %@ BarPath   %@ AdrCity   %@ ",
   self.plainPassword ,
   self.emerConTel ,
   self.updateTime ,
   self.authorizationCode ,
   self.createBy ,
   self.storeId ,
   self.loginPassword ,
   self.restId ,
   self.authorizationMake ,
   self.empId ,
   self.mobile ,
   self.resProvince ,
   self.updateBy ,
   self.createTime ,
   self.emerContact ,
   self.empStatus ,
   self.idCard ,
   self.adrProvince ,
   self.jobStatus ,
   self.resCity ,
   self.gender ,
   self.address ,
   self.empNum ,
   self.sysdataType ,
   self.jobPic ,
   self.salt ,
   self.belongRest ,
   self.loginUsername ,
   self.name ,
   self.residenceAdr ,
   self.barPath ,
   self.adrCity 
 ];
}

@end
