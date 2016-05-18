package entity;
//student_job(sjid,sid, time, state, cname, job,comment)

import java.util.Date;

public class Student_job {
	private Integer sjid;
	private String sid;
	private Date time;
	private Integer type;
	private String cname;
	private String job;
	private String comment;
	
	public Student_job(){}
	
	public Student_job(Integer sjid, String sid, Date time, Integer type, String cname, String job, String comment) {
		super();
		this.sjid = sjid;
		this.sid = sid;
		this.time = time;
		this.type = type;
		this.cname = cname;
		this.job = job;
		this.comment = comment;
	}
	public Integer getSjid() {
		return sjid;
	}
	public void setSjid(Integer sjid) {
		this.sjid = sjid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
