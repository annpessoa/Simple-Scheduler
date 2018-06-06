import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;

public class GraphController implements Initializable{
	//line chart object
	@FXML
    private LineChart<?, ?> LineChart;

	//axes
    @FXML
    private NumberAxis x;

    @FXML
    private NumberAxis y;
    
    //text fields that will update based on calculations
    @FXML
    private Text TotalDays;

    @FXML
    private Text StartDate;

    @FXML
    private Text EndDate;
    
    //simple date formatter
    private SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyy");
    
    //This initializes the graph view
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//this is the job scheduling algorithm
		JobScheduling j = new JobScheduling();
		//create tasks
		j.testDates();
		//calculate durations for all tasks
		j.calculateTaskDuration();
		
		int duration = 20;
		// TODO Auto-generated method stub
		
		//how to add data to chart
		//create a new series object for every new line in the chart
		//each series is a line on the chart
		XYChart.Series series = new XYChart.Series();
		XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series3 = new XYChart.Series();
		XYChart.Series series4 = new XYChart.Series();
		
		//add new XYChart data (x,y)
		//x is the date in june
		//y is the number of units completed
		
		//make calendar instance
		Calendar c = Calendar.getInstance();
		
		//get start date of the first object in job graph
		c.setTime(j.jobs.get(0).getStartDate());
		//plot the data on the graph
		series.getData().add(new XYChart.Data(c.get(Calendar.DATE),0));
		//get end tate of the first objext
		c.setTime(j.jobs.get(0).getFinishDate());
		//plot the data on the graph
		series.getData().add(new XYChart.Data(c.get(Calendar.DATE),2));
		//series.getData().add(new XYChart.Data(21,2));
		
		
		//repeat that same process for all tasks
		
		c.setTime(j.jobs.get(1).getStartDate());
		series2.getData().add(new XYChart.Data(c.get(Calendar.DATE),0));
		c.setTime(j.jobs.get(1).getFinishDate());
		series2.getData().add(new XYChart.Data(c.get(Calendar.DATE),2));
		//series2.getData().add(new XYChart.Data(18,2));
		
		c.setTime(j.jobs.get(2).getStartDate());
		series3.getData().add(new XYChart.Data(c.get(Calendar.DATE),0));
		c.setTime(j.jobs.get(2).getFinishDate());
		series3.getData().add(new XYChart.Data(c.get(Calendar.DATE),2));
		//series3.getData().add(new XYChart.Data(31,2));
		
		c.setTime(j.jobs.get(3).getStartDate());
		series4.getData().add(new XYChart.Data(c.get(Calendar.DATE),0));
		c.setTime(j.jobs.get(3).getFinishDate());
		series4.getData().add(new XYChart.Data(c.get(Calendar.DATE),2));
		//series4.getData().add(new XYChart.Data(24,2));
		
		//This makes the legend visible 
		LineChart.setLegendVisible(true);
		
		//set task names
		series.setName("Task 1");
		series2.setName("Task 2");
		series3.setName("Task 3");
		series4.setName("Task 4");
		
		//add durations, initial start date, and final end date
		TotalDays.setText(duration+ " days");
		StartDate.setText(j.jobs.get(0).getStartDateString());
		EndDate.setText(sdf.format(j.jobs.get(3).getFinishDate()));
		
		//add all the points to the chart
		LineChart.getData().addAll(series, series2, series3,series4);
	}
	
	

}
