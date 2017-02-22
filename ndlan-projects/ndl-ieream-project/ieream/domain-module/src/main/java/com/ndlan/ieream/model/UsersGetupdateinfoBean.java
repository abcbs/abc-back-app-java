package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class UsersGetupdateinfoBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * status
     **/
    private Integer status;
    /**
     * 获取用户信息成功
     **/
    private String info;

      private DataUsersGetupdateinfoBean dataUsersGetupdateinfo;

  
	
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

	
    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

	


    public void setData(DataUsersGetupdateinfoBean dataUsersGetupdateinfo) {
        this.dataUsersGetupdateinfo = dataUsersGetupdateinfo;
    }

    public DataUsersGetupdateinfoBean getData() {
        return dataUsersGetupdateinfo;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
