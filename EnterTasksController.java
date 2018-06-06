
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class EnterTasksController implements Initializable {
	//this stores values for all tasks
	ObservableList<Record> taskList = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Record> Table;

	@FXML
	private TableColumn<Record, String> Task;

	@FXML
	private TableColumn<Record, Double> Duration;

	@FXML
	private DatePicker Date;

	@FXML
	private Button BuildSchedule;

	@FXML
	private TextField enterTask;

	@FXML
	private TextField enterDuration;

	@FXML
	private Button addTaskButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Task.setMinWidth(200);
		Duration.setMinWidth(200);
		//set where the table will get values from Record object
		Task.setCellValueFactory(new PropertyValueFactory<>("taskName"));
		Duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		//Table.setItems(getProduct());
		Table.setEditable(true);
	}

	@FXML
	private void handleButtonAction(ActionEvent e) throws IOException {
		
		//load graph page and change scenes
		Parent homepage = FXMLLoader.load(getClass().getResource("Graph.fxml"));
		Scene homepageScene = new Scene(homepage);
		Stage stage = (Stage) (((Node) e.getSource()).getScene().getWindow());
		stage.setScene(homepageScene);
		stage.show();

		//center window in the middle of the screen
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 4);
	}

	//this initializes the table with random vals
	public ObservableList<Record> getProduct() {
		//ObservableList<Record> products = FXCollections.observableArrayList();
		taskList.add(new Record("1", 2));
		taskList.add(new Record("2", 4));
		taskList.add(new Record("3", 6));
		taskList.add(new Record("4", 8));
		return taskList;
	}
	
	public void addButtonClicked() {
		//get the task name and duration from user input
		String task = enterTask.getText();
		int duration = Integer.parseInt(enterDuration.getText());
		//create new record object
		Record record = new Record(task,duration);
		//add to task list observableList
		taskList.add(record);
		//add to table
		Table.getItems().add(record);
	}

}
