package com.graphql.demo.graphqldemo.entity;

import java.util.Date;

public class CommonEntity {

	private Integer del_flg;

	private Date create_date;

	private Date update_date;

	private Long create_id;

	private Long update_id;

	private Long create_pgm_id;

	private Long update_pgm_id;

	private Long version;

	public Integer getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(Integer del_flg) {
		this.del_flg = del_flg;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Long getCreate_id() {
		return create_id;
	}

	public void setCreate_id(Long create_id) {
		this.create_id = create_id;
	}

	public Long getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(Long update_id) {
		this.update_id = update_id;
	}

	public Long getCreate_pgm_id() {
		return create_pgm_id;
	}

	public void setCreate_pgm_id(Long create_pgm_id) {
		this.create_pgm_id = create_pgm_id;
	}

	public Long getUpdate_pgm_id() {
		return update_pgm_id;
	}

	public void setUpdate_pgm_id(Long update_pgm_id) {
		this.update_pgm_id = update_pgm_id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
