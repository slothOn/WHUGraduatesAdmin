package entity;
//student_info(sid, sname, gender,political,sprov,scity,major,tel,sqq)
public class Student_info {
	private String sid;
	private String sname;
	private String gender;
	private String political;
	private String sprov;
	private String scity;
	private String major;
	private String tel;
	private String sqq;
	
	public Student_info(){}
	
	public Student_info(String sid, String sname, String gender, String political, String sprov, String scity,
			String major, String tel, String sqq) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.political = political;
		this.sprov = sprov;
		this.scity = scity;
		this.major = major;
		this.tel = tel;
		this.sqq = sqq;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getSprov() {
		return sprov;
	}
	public void setSprov(String sprov) {
		this.sprov = sprov;
	}
	public String getScity() {
		return scity;
	}
	public void setScity(String scity) {
		this.scity = scity;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSqq() {
		return sqq;
	}
	public void setSqq(String sqq) {
		this.sqq = sqq;
	}
	
}
