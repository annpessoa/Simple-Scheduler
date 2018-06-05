import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class JobScheduling {
	
	//Put a thing here for units

	private SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
	private Calendar c = null;

	List<JobObject> jobs = new ArrayList<JobObject>();

	// Put insertion of dates into the array list, only first one should have a start date,
	// duration after being selected by the user will be multiplied by the amount of
	// units they want.
	// Precondition: Jobs are put in order of precedence

	
	
	private void calculateTaskDuration(){
		for(int i= 0; i < jobs.size(); i++){
			JobObject temp = jobs.get(i); //Make a temp copy for comparisons
			if(temp.getFinishDate() == null) {
				if(i==0){
					if(temp.getStartDate()!=null){ //checks if there is actually a start date
						c.setTime(temp.getStartDate()); // Calendar for math on dates bc haaaaaa
						c.add(Calendar.DATE, temp.getJobDuration()); // Adds date in
						jobs.get(i).setFinishDate(c.getTime()); // Actually sets the new finish date in
					} else {
						//There is no start date, only a duration, so it automatically will default
						//To new years 2018.
						//There should be an initial start date to a project however
						//This is just a default case
						c.set(2018, 01, 01);
						jobs.get(i).setStartDate(c.getTime());

						c.add(Calendar.DATE, temp.getJobDuration());
						jobs.get(i).setFinishDate(c.getTime());
					} //end else
				} else {
					//Here is where the gnitty gritty of the math is done
					
					//if no start date
					//check if startDate+1+Duration is a later date than previous start date
					//if it conflicts, then set current's end date to previous endDate + 1, 
					//then it sets current start date to current end date - duration.
				}

			}
		}
	}
}




