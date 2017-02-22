package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;

public class ReamsGetItemBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * status
     **/
    private Integer status;
    /**
     * more
     **/
    private Integer more;

      private DataReamsGetItemBean dataReamsGetItem;

  
	
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

	

	
    public void setMore(Integer more) {
        this.more = more;
    }

    public Integer getMore() {
        return more;
    }


    public void setData(DataReamsGetItemBean dataReamsGetItem) {
        this.dataReamsGetItem = dataReamsGetItem;
    }

    public DataReamsGetItemBean getData() {
        return dataReamsGetItem;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
