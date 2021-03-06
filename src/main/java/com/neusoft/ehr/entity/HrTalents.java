package com.neusoft.ehr.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Model class of hr_talents.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class HrTalents implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** id. */
	private Long id;

	/** name. */
	private String name;

	/** t_code. */
	private String tCode;

	/** sexual. */
	private Integer sexual;

	/** employ_date. */
	private Date employDate;

	/** unemploy_date. */
	private Date unemployDate;

	/** del_flag. */
	private String delFlag;

	/**
	 * Constructor.
	 */
	public HrTalents() {
	}

	public HrTalents(Long id, String name, String tCode, Integer sexual, Date employDate, Date unemployDate) {
		this.id = id;
		this.name = name;
		this.tCode = tCode;
		this.sexual = sexual;
		this.employDate = employDate;
		this.unemployDate = unemployDate;
	}

	public HrTalents(Long id, String name, String tCode, Integer sexual) {
		this.id = id;
		this.name = name;
		this.tCode = tCode;
		this.sexual = sexual;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String gettCode() {
		return tCode;
	}

	public void settCode(String tCode) {
		this.tCode = tCode;
	}

	public Integer getSexual() {
		return sexual;
	}

	public void setSexual(Integer sexual) {
		this.sexual = sexual;
	}

	public Date getEmployDate() {
		return employDate;
	}

	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}

	public Date getUnemployDate() {
		return unemployDate;
	}

	public void setUnemployDate(Date unemployDate) {
		this.unemployDate = unemployDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
