package com.ndlan.framework.demo.domain.query;

import com.ndlan.framework.demo.domain.SysDictionary;

/**
 * 
 * @author LiuJQ
 */
public class SysDictionaryQuery extends SysDictionary {
	private static final long serialVersionUID = 4587505733381942426L;

	/**
	 * @fields dicNameLike 字典名称模糊查询
	 */
	private String dicNameLike;
	/**
	 * @fields dicGroupLike 字段组模糊查询
	 */
	private String dicGroupLike;

	public String getDicGroupLike() {
		return dicGroupLike;
	}

	public void setDicGroupLike(String dicGroupLike) {
		this.dicGroupLike = dicGroupLike;
	}

	public String getDicNameLike() {
		return dicNameLike;
	}

	public void setDicNameLike(String dicNameLike) {
		this.dicNameLike = dicNameLike;
	}

}
