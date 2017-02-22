package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;

public class ReamsGetListBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * more
     **/
    private Integer more;
    /**
     * status
     **/
    private Integer status;

      private List< DataReamsgetlistBean > dataReamsGetList;

  
	
    public void setMore(Integer more) {
        this.more = more;
    }

    public Integer getMore() {
        return more;
    }

	
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

	


    public void setData(List< DataReamsgetlistBean > dataReamsGetList) {
        this.dataReamsGetList = dataReamsGetList;
    }

    public List< DataReamsgetlistBean > getData() {
        return dataReamsGetList;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
