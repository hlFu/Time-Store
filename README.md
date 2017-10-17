# Time-Store
An android app as a project in Zhejiang University.
Created by BadStudent, GoodManElliot, Impavidity

## Backend and API declaration
The back end of the APP is based on the project "Back_End_for_Time_Store".

### Direction Upload
	String direction = "forward"; 
	//Define the direction including "forward" "backward" "left" "right"
	DirectionClass postDirection = new DirectionClass(direction);
	//New a DirectionClass, put the direction into it
	postDirection.upload();
	//Upload the direction to server
