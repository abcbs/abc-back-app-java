package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;
import java.util.Date;

public class AvatarDataGuidesGetItemBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * has_avatar
     **/
    private Integer has_avatar;
    /**
     * /Uploads/Avatar/102/
     **/
    private String avatar;


  
	
    public void setHas_avatar(Integer has_avatar) {
        this.has_avatar = has_avatar;
    }

    public Integer getHas_avatar() {
        return has_avatar;
    }

	
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
