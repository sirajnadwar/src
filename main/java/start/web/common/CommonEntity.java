package start.web.common;

import java.util.Date;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class CommonEntity implements CommonFields {
	private Date updateTime;
	private Date deleteTime;
	private Boolean isDelete;
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public void setCommonFields(Date time) {
		if (time == null) {
			this.setUpdateTime(new Date());
		}
		else {
			this.setUpdateTime(time);
		}
		this.setIsDelete(false);
		this.setDeleteTime(null);
	}

	public void saveDeleteTime(Date time) {
		// TODO Auto-generated method stub

	}

}
