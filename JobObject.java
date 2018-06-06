import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class JobObject {
	
	//taskName
	private String jobName;
	//Start date
	private Date startDate;
	private Date endDate;
	
	//An important formatting to to use for date, will only allow
	//Strings in that format for formatting
	private SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
	private SimpleDateFormat jdf = new SimpleDateFormat("Myydd");

	private int jobDuration;
	
	//Empty object
	JobObject(){
		jobName = "";
		startDate = new Date();
		//endDate = new Date();
		jobDuration = 1;
	}
	
	JobObject(int duration){
		jobName = "";
		//startDate = new Date();
		//endDate = new Date();
		jobDuration = duration;
	}
	
	//Copy constructor
	JobObject(JobObject copy){
		this.jobName = copy.getJobName();
		this.startDate = copy.getStartDate();
		this.endDate = copy.getFinishDate();
	}
	
	//Constructor where only the start date is known
	JobObject(Date startDate, int duration){
		this.startDate = startDate;
		
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, duration);
		this.endDate = c.getTime();
		this.jobDuration = duration;
	}
	
	JobObject(String startdate, int duration) throws ParseException{
		startDate = sdf.parse(startdate);
		jobDuration = duration;
	}
	
	

	
	//setTaskName
	public void setJobName(String jName){
		jobName = jName;
	}
	
	public String getJobName(){
		return jobName;
	}
	
	
	public void setJobkDuration(int tDur){
		if(tDur < 1) return;
		jobDuration = tDur;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public String getStartDateString() {
		String formattedDate = sdf.format(startDate);
		return formattedDate;
	}
	
	public int getJulianStart() {
		String temp = jdf.format(startDate);
		return Integer.parseInt(temp);
	}
	
	public int getJulianFinish(){
		String temp = jdf.format(startDate);
		return Integer.parseInt(temp);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setStartDate(String dateInString) throws ParseException{
		startDate = sdf.parse(dateInString);
	}
	
	
	
	public void setFinishDate(Date eDate) {
		this.endDate = eDate;
	}
	public void setFinishDate(String dateInString) throws ParseException{
		endDate = sdf.parse(dateInString);
	}
	
	public Date getFinishDate(){
		return this.endDate;
	}
	
	//getTaskDuration
	public int getJobDuration(){
		return jobDuration;
	}
	
	
	
	public void changeEndDate(int numOfDays){
		Calendar c = Calendar.getInstance();
		c.setTime(endDate);
		c.add(Calendar.DATE, numOfDays);
		setFinishDate(c.getTime());
	}
	
	public void changeStartDate(int numOfDays){
		Calendar c = Calendar.getInstance();
		c.setTime(endDate);
		c.add(Calendar.DATE, numOfDays);
		setFinishDate(c.getTime());
	}

	
}