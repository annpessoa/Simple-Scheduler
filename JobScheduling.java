import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class JobScheduling {
	
	//Put a thing here for units
	int unitsWanted = 2;
	private SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
	private Calendar c = Calendar.getInstance();
	//private 						
	List<JobObject> jobs = new ArrayList<JobObject>();
	JobScheduling(){
		Date d = new Date();
		c.setTime(d);
		
		//Comment this function out if not testing
		testDates();
	}



	// Put insertion of dates into the array list, only first one should have a start date,
	// duration after being selected by the user will be multiplied by the amount of
	// units they want.
	// Precondition: Jobs are put in order of precedence
	
	// How to add dates:
	// However you take input, make sure you get MM/DD/YYY ie 3/05/2018
	// You can set date via a string, or by a date object. I suggest the string.
	// Just have it save to the end of the ArrayList.
	
	
	//Used to populate dates withou needing input for testing
	public void testDates() {
		try {
		jobs.add(new JobObject("6/1/2018", 5*unitsWanted));

		jobs.add(new JobObject(3*unitsWanted));
		jobs.add(new JobObject(6*unitsWanted));
		jobs.add(new JobObject(2*unitsWanted));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void printDates(){
		
		for(int i = 0; i < jobs.size(); i++){
			Date temp = jobs.get(i).getStartDate();
			c.setTime(temp);
			System.out.print("Start date: ");
			System.out.println(c.getTime());
			Date temp2 = jobs.get(i).getFinishDate();
			c.setTime(temp2);
			System.out.print("Finish date: ");
			System.out.println(c.getTime());
		}
		
	}
	

	
	//RIGHT NOW THERE IS NO ERROR CHECKING ON IF CERTAIN TASKS NEED CERTAIN START DATES
	//IN THE FUTURE THIS CAN BE IMPLEMENTED!
	public void calculateTaskDuration(){
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
					
					
				} else { //Need to set the finish date.
					//if no start date
					if(temp.getStartDate() == null){
						c.setTime(jobs.get(i-1).getFinishDate());
						c.roll(Calendar.DATE, true);
						jobs.get(i).setStartDate(c.getTime());
						temp.setStartDate(c.getTime());
					}
					
					//check if startDate+1+Duration is a later date than previous start date
						c.setTime(temp.getStartDate());
						//c.roll(Calendar.DATE, true);
						c.add(Calendar.DATE, temp.getJobDuration()+1);
						Calendar tempCal = Calendar.getInstance();
						tempCal.setTime(jobs.get(i-1).getFinishDate());
						//If it is later
						if(c.after(tempCal)){
							//if it conflicts, then set current's end date to previous endDate + 1, 
							//then it sets current start date to current end date - duration.
							//jobs.get(i).setFinishDate(jobs.get(i-1).getFinishDate());
							c.setTime(jobs.get(i-1).getFinishDate()); //Calculate finish date
							c.roll(Calendar.DATE, true); //adds a single day

							
							//Set finish date
							jobs.get(i).setFinishDate(c.getTime());
							
							tempCal.setTime(jobs.get(i-1).getStartDate());
							//Figure out new start date
							c.add(Calendar.DATE, -temp.getJobDuration());
							if(c.before(tempCal) || c.equals(tempCal)){
								while(c.before(tempCal) || c.equals(tempCal)){
								c.roll(Calendar.DATE, true);
									//c.roll(Calendar.DATE, true);
								}
								jobs.get(i).setStartDate(c.getTime());
								c.add(Calendar.DATE, temp.getJobDuration());
								jobs.get(i).setFinishDate(c.getTime());
							} else {
								jobs.get(i).setStartDate(c.getTime());
							}
							
							//Set new start date.
							
						} else {
							//Sets up the schedule if there is no time conflict
							c.setTime(jobs.get(i-1).getStartDate());
							c.roll(Calendar.DATE, true); //adds one day to start date
							jobs.get(i).setStartDate(c.getTime());
							
							//Sets end date
							c.add(Calendar.DATE, temp.getJobDuration());
							jobs.get(i).setFinishDate(c.getTime());
						} //end else
							
					
				}//end else

			}//end for
		}
	}
}




