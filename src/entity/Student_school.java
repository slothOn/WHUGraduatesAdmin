package entity;
//student_school(ssid, sid, activity, honor, startyear, endyear)
public class Student_school {
	private Integer ssid;
	private String sid;
	private String activity;
	private String honor;
	private String startyear;
	private String endyear;
	
	private String sname;
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Student_school(){}
	public Student_school(Integer ssid, String sid, String sname, String activity, String honor, String startyear, String endyear) {
		super();
		this.ssid = ssid;
		this.sid = sid;
		this.sname = sname;
		this.activity = activity;
		this.honor = honor;
		this.startyear = startyear;
		this.endyear = endyear;
	}
	
	public Integer getSsid() {
		return ssid;
	}
	public void setSsid(Integer ssid) {
		this.ssid = ssid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	public String getStartyear() {
		return startyear;
	}
	public void setStartyear(String startyear) {
		this.startyear = startyear;
	}
	public String getEndyear() {
		return endyear;
	}
	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ssid + "," + sid + "," + sname + "," + activity + "," + honor + "," + startyear + "," + endyear + ",";
	}
}
