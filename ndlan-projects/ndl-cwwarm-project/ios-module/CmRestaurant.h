#import <Foundation/Foundation.h>


@interface CmRestaurant : NSObject
	
	
    @property(nonatomic,copy)NSString * mid ;
    /**
     * 市
     **/
    @property(nonatomic,copy)NSString * adrCity ;
    @property(nonatomic,copy)NSString * synDatabaseStatus ;
    @property(nonatomic,copy)NSString * updateBy ;
    @property(nonatomic,assign)NSInteger  deliveryCharge ;
    /**
     * 房屋租凭合同
     **/
    @property(nonatomic,copy)NSString * fangwu ;
    @property(nonatomic,assign)NSInteger  serviceScore ;
    @property(nonatomic,copy)NSString * overlaycoordinateX ;
    /**
     * 用逗号隔开多个菜系的ID
     **/
    @property(nonatomic,copy)NSString * dashesStyleIdArray ;
    /**
     * G1设备码
     **/
    @property(nonatomic,copy)NSString * sn ;
    @property(nonatomic,copy)NSString * notes ;
    @property(nonatomic,copy)NSString * storeName ;
    @property(nonatomic,copy)NSString * msg ;
    /**
     * 商户描述
     **/
    @property(nonatomic,copy)NSString * description ;
    @property(nonatomic,copy)NSString * busStop ;
    @property(nonatomic,copy)NSString * busiEnd ;
    @property(nonatomic,copy)NSString * busiSupperStart ;
    @property(nonatomic,assign)NSInteger  takeOutStatus ;
    @property(nonatomic,copy)NSDate * createTime ;
    /**
     * 支行名称
     **/
    @property(nonatomic,copy)NSString * bankBranch ;
    /**
     * 人均消费
     **/
    @property(nonatomic,assign)NSInteger  consPerPerson ;
    /**
     * G2 设备摆放位置照片
     **/
    @property(nonatomic,copy)NSString * file11 ;
    @property(nonatomic,copy)NSString * subway ;
    @property(nonatomic,copy)NSString * storeId ;
    /**
     * 对应ForeignCategory
     **/
    @property(nonatomic,copy)NSString * foreignLa ;
    @property(nonatomic,copy)NSDate * updateTime ;
    /**
     * 电话
     **/
    @property(nonatomic,copy)NSString * linkmanTel ;
    @property(nonatomic,copy)NSString * synVersionId ;
    /**
     * 登陆密码
     **/
    @property(nonatomic,copy)NSString * denglumima ;
    @property(nonatomic,copy)NSString * barPath ;
    @property(nonatomic,copy)NSString * parentshopId ;
    /**
     * 餐饮服务许可证
     **/
    @property(nonatomic,copy)NSString * file13 ;
    @property(nonatomic,copy)NSString * busiTakeoutSupperEnd ;
    @property(nonatomic,copy)NSString * overlaycoordinateY ;
    @property(nonatomic,assign)NSInteger  tasteScore ;
    /**
     * 手机
     **/
    @property(nonatomic,copy)NSString * linkmanPhone ;
    @property(nonatomic,copy)NSString * tilecoordinateY ;
    /**
     * 法人手持身份证正面照
     **/
    @property(nonatomic,copy)NSString * file3 ;
    @property(nonatomic,copy)NSString * busiHoursClose ;
    @property(nonatomic,copy)NSString * eventSummary ;
    @property(nonatomic,copy)NSString * busiBreakfastName ;
    @property(nonatomic,copy)NSString * invoiceStatus ;
    /**
     * 法人
     **/
    @property(nonatomic,copy)NSString * linkmanName ;
    @property(nonatomic,copy)NSString * busiSupperEnd ;
    /**
     * 注册码
     **/
    @property(nonatomic,copy)NSString * zhucema ;
    /**
     * 商户营业地址
     **/
    @property(nonatomic,copy)NSString * mchAddr ;
    /**
     * 法人
     **/
    @property(nonatomic,copy)NSString * juridicalPerson ;
    /**
     * 省
     **/
    @property(nonatomic,copy)NSString * adrProvince ;
    /**
     * 开户行号联行号
     **/
    @property(nonatomic,copy)NSString * settleBank ;
    @property(nonatomic,copy)NSString * viewportcoordinateY ;
    /**
     * 开户行所在省
     **/
    @property(nonatomic,copy)NSString * province ;
    /**
     * 法人身份证正面照
     **/
    @property(nonatomic,copy)NSString * file1 ;
    @property(nonatomic,copy)NSString * onlineOrderStatus ;
    /**
     * 开户行名称
     **/
    @property(nonatomic,copy)NSString * bankName ;
    /**
     * 开户许可证
     **/
    @property(nonatomic,copy)NSString * kaihuo ;
    @property(nonatomic,copy)NSString * complaintTel ;
    /**
     * 邮箱
     **/
    @property(nonatomic,copy)NSString * email ;
    /**
     * 对应枚举DiningEnv
     **/
    @property(nonatomic,copy)NSString * diningEnv ;
    @property(nonatomic,copy)NSString * busiTakeoutLunchEnd ;
    @property(nonatomic,copy)NSString * busiStart ;
    /**
     * 法人有效证件种类 1.身份证;2.护照;3军(警)官证;4士兵证; 5台胞证; 6回乡证
     **/
    @property(nonatomic,copy)NSString * certType ;
    @property(nonatomic,copy)NSString * isBandCloudAccount ;
    /**
     * 营业执照（path）
     **/
    @property(nonatomic,copy)NSString * businessLicense ;
    @property(nonatomic,copy)NSString * busiHoursOpen ;
    @property(nonatomic,copy)NSString * onlineOrderPhone ;
    /**
     * 现场照片
     **/
    @property(nonatomic,copy)NSString * xianchang ;
    /**
     * 名称
     **/
    @property(nonatomic,copy)NSString * restName ;
    @property(nonatomic,copy)NSString * busiBreakfastEnd ;
    @property(nonatomic,copy)NSString * specialStyle ;
    @property(nonatomic,assign)NSInteger  joinAuthentication ;
    /**
     * 设备类型
     **/
    @property(nonatomic,copy)NSString * shebeileixing ;
    @property(nonatomic,copy)NSString * onlineOrderDishStatus ;
    @property(nonatomic,copy)NSString * restId ;
    @property(nonatomic,copy)NSString * viewportcoordinateX ;
    @property(nonatomic,copy)NSString * shopType ;
    /**
     * 地铁
     **/
    @property(nonatomic,copy)NSString * adrDetail ;
    @property(nonatomic,copy)NSString * busiLunchStart ;
    @property(nonatomic,copy)NSString * shopStatus ;
    /**
     * 收单二联单
     **/
    @property(nonatomic,copy)NSString * file14 ;
    /**
     * 法人电话
     **/
    @property(nonatomic,copy)NSString * juridicalPhone ;
    /**
     * 开户行账号
     **/
    @property(nonatomic,copy)NSString * settleAccno ;
    /**
     * 商户类型
     **/
    @property(nonatomic,copy)NSString * shangleixing ;
    @property(nonatomic,copy)NSString * code ;
    @property(nonatomic,copy)NSString * busiSupperName ;
    @property(nonatomic,copy)NSString * takeOutEnd ;
    /**
     * 用逗号隔开多个菜系name
     **/
    @property(nonatomic,copy)NSString * dishesStyleNameArray ;
    @property(nonatomic,copy)NSString * pixelcoordinateY ;
    @property(nonatomic,copy)NSString * busiLunchName ;
    /**
     * 法人证件有效日期 格式：YYYYMMDD
     **/
    @property(nonatomic,copy)NSString * certExpdate ;
    /**
     * 组织机构代码
     **/
    @property(nonatomic,copy)NSString * zhouzhijiguo ;
    @property(nonatomic,assign)NSInteger  serveSpeedScore ;
    @property(nonatomic,copy)NSString * cloudPassword ;
    /**
     * 开户姓名
     **/
    @property(nonatomic,copy)NSString * accName ;
    @property(nonatomic,copy)NSString * cloudUsername ;
    /**
     * 详细地址
     **/
    @property(nonatomic,copy)NSString * adrArea ;
    /**
     * 开户行所在市
     **/
    @property(nonatomic,copy)NSString * city ;
    @property(nonatomic,copy)NSString * busiLatesnackStart ;
    @property(nonatomic,copy)NSString * eventNotice ;
    @property(nonatomic,copy)NSString * pixelcoordinateX ;
    @property(nonatomic,assign)NSInteger  orderDays ;
    @property(nonatomic,copy)NSString * onlineTakeoutPrompts ;
    @property(nonatomic,copy)NSString * tilecoordinateX ;
    @property(nonatomic,copy)NSDate * synDataCompleteTime ;
    /**
     * 银行账户
     **/
    @property(nonatomic,copy)NSString * bankAccount ;
    /**
     * 银行卡类型 1.借记卡2.贷记卡
     **/
    @property(nonatomic,copy)NSString * accountType ;
    /**
     * 营业执照名称
     **/
    @property(nonatomic,copy)NSString * cName ;
    @property(nonatomic,copy)NSString * busiLatesnackName ;
    /**
     * 对应SpecialEnv
     **/
    @property(nonatomic,copy)NSString * specialEnv ;
    @property(nonatomic,copy)NSString * manager ;
    @property(nonatomic,copy)NSString * busiTakeoutLunchStart ;
    /**
     * 面积
     **/
    @property(nonatomic,assign)NSInteger  restArea ;
    /**
     * 法人身份证反面照
     **/
    @property(nonatomic,copy)NSString * file2 ;
    @property(nonatomic,assign)NSInteger  sendPrice ;
    @property(nonatomic,assign)NSInteger  totalScore ;
    @property(nonatomic,copy)NSString * orderTel ;
    @property(nonatomic,copy)NSString * busiTakeoutSupperStart ;
    /**
     * 商户id
     **/
    @property(nonatomic,copy)NSString * merchantId ;
    /**
     * 地区编码
     **/
    @property(nonatomic,copy)NSString * areaCode ;
    /**
     * 对公或者对私账户标识   0对公 1对私
     **/
    @property(nonatomic,copy)NSString * isPrivate ;
    @property(nonatomic,copy)NSString * worldcoordinateX ;
    @property(nonatomic,copy)NSString * createBy ;
    @property(nonatomic,copy)NSString * onlineRestStatus ;
    @property(nonatomic,copy)NSString * busiBreakfastStart ;
    /**
     * 店面门头照
     **/
    @property(nonatomic,copy)NSString * file12 ;
    @property(nonatomic,assign)NSInteger  deliveryRange ;
    @property(nonatomic,copy)NSString * advertisingSlogan ;
    @property(nonatomic,copy)NSString * onlineOrderPrompts ;
    @property(nonatomic,copy)NSString * lng ;
    /**
     * 法人身份证号
     **/
    @property(nonatomic,copy)NSString * juridicalId ;
    @property(nonatomic,copy)NSString * busiLatesnackEnd ;
    /**
     * -1 初始化数据 0未审核 1正在审核 2 审核通过 3 审核被拒绝 4审核被驳回 ,5 走父店结账
     **/
    @property(nonatomic,copy)NSString * restExamine ;
    @property(nonatomic,copy)NSString * worldcoordinateY ;
    @property(nonatomic,copy)NSString * busiLunchEnd ;
    @property(nonatomic,assign)NSInteger  environmentScore ;
    @property(nonatomic,copy)NSString * lat ;
    /**
     * 用逗号隔开多个菜系type
     **/
    @property(nonatomic,copy)NSString * dishesStyleTypeArray ;
    /**
     * 税务登记证
     **/
    @property(nonatomic,copy)NSString * shuiwu ;
    /**
     * 银行卡正面照
     **/
    @property(nonatomic,copy)NSString * file4 ;
    @property(nonatomic,copy)NSString * takeOutStart ;
    @property(nonatomic,copy)NSDate * synDataTime ;
 
@end