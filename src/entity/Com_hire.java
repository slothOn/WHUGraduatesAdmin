package entity;
//com_hire(hid, cid, startdate, enddate, sid)
public class Com_hire {
	private Integer hid;
	private Integer cid;
	private String startdate;
	private String enddate;
	private Integer sid;
	
	public Com_hire(){}
	
	public Com_hire(Integer hid, Integer cid, String startdate, String enddate, Integer sid) {
		super();
		this.hid = hid;
		this.cid = cid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.sid = sid;
	}


	public Integer getHid() {
		return hid;
	}
	public void setHid(Integer hid) {
		this.hid = hid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	
}
