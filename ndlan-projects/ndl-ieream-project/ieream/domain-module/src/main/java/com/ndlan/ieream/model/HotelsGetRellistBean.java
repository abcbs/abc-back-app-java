package com.ndlan.ieream.model;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ndlan.framework.core.api.Identifiable;
import com.ndlan.framework.core.api.DefaultBeanIdentifiable;

public class HotelsGetRellistBean extends DefaultBeanIdentifiable implements Identifiable{

	private static final long serialVersionUID =-1;
	
    /**
     * more
     **/
    private Integer more;
    /**
     * status
     **/
    private Integer status;

      private List< DataHotelsgetrellistBean > dataHotelsGetRellist;

  
	
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

	


    public void setData(List< DataHotelsgetrellistBean > dataHotelsGetRellist) {
        this.dataHotelsGetRellist = dataHotelsGetRellist;
    }

    public List< DataHotelsgetrellistBean > getData() {
        return dataHotelsGetRellist;
    }
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
