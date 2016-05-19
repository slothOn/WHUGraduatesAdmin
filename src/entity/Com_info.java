package entity;

import java.util.Date;

//com_info(cid, cname, cfield, ctype, cprov, ccity)
public class Com_info {
	private Integer cid;
	private String cname;
	private String cfield;
	private String ctype;
	private String cprov;
	private String ccity;
	private Date startdate;
	private Date enddate;
	
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Com_info(){}
	
	public Com_info(Integer cid, String cname, String cfield, String ctype, String cprov, String ccity, Date startdate, Date enddate) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cfield = cfield;
		this.ctype = ctype;
		this.cprov = cprov;
		this.ccity = ccity;
		
		this.startdate = startdate;
		this.enddate = enddate;
	}


	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCfield() {
		return cfield;
	}
	public void setCfield(String cfield) {
		this.cfield = cfield;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getCprov() {
		return cprov;
	}
	public void setCprov(String cprov) {
		this.cprov = cprov;
	}
	public String getCcity() {
		return ccity;
	}
	public void setCcity(String ccity) {
		this.ccity = ccity;
	}
	
}
