import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Record {
	private SimpleStringProperty taskName;
	private SimpleDoubleProperty duration;

	Record(String fDay, double fValue1) {
		this.taskName = new SimpleStringProperty(fDay);
		this.duration = new SimpleDoubleProperty(fValue1);

	}

	public String getTaskName() {
		return taskName.get();
	}

	public double getDuration() {
		return duration.get();
	}

	public void setTaskName(String fDay) {
		taskName.set(fDay);
	}

	public void setDuration(Double fValue1) {
		duration.set(fValue1);
	}

}