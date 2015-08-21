package com.example.onourway;

import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Web;
import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.TinyDB;
import com.google.appinventor.components.runtime.LocationSensor;
import com.google.appinventor.components.runtime.Texting;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.Clock;

import java.util.ArrayList;


public class OnOurWay extends Form implements HandlesEventDispatching{
	
private Texting mytexting;	

private Canvas canvas;

private Button submit1;
private Button submit2;
private Button submit3;
private Button send;
private Button location;
private Button stop;


private TextBox textbox1;
private TextBox textbox2;
private TextBox textbox3;
private TextBox countdown;
private TextBox phonenumber;


private HorizontalArrangement horiz1;
private HorizontalArrangement horiz2;
private HorizontalArrangement horiz3;
private HorizontalArrangement horiz4;
private HorizontalArrangement horiz5;
private HorizontalArrangement horiz6;
private HorizontalArrangement horiz7;
private HorizontalArrangement horiz8;
private HorizontalArrangement horiz9;
private HorizontalArrangement horiz10;
private HorizontalArrangement horiz11;
private HorizontalArrangement horizArrangement;


private Label label1;
private Label label2;
private Label label3;
private Label label4;
private Label labelCurrGps; // displays lat & long info
private Label labelCurrAddr; // displays street address
private Label confirm1;
private Label confirm2;
private Label confirm3;
//private Label header;
//private Label labelAddy;
//private Label labelcoord;

//find location
private LocationSensor locationSensor1;
private String gpsProviderName;

//catch lat and longitude information
private String latitude;
private String longitude; 


//notifiy user if gps is not activated
private Notifier notifier1;

private Label timeLeftLabel;
private Label timeLeftValueLabel;


private Clock countdownClock;

private ArrayList<String> message;

private String messageSent;//the actual message that will be sent

protected void $define(){

this.BackgroundImage("background.jpg");
this.Scrollable(true);

message = new ArrayList<String>();	

messageSent = "";

canvas = new Canvas(this);
canvas.Width(LENGTH_FILL_PARENT);
canvas.Height(150);
canvas.BackgroundImage("OnOurWay.jpg");


// countdown label
horizArrangement = new HorizontalArrangement(this);
timeLeftLabel= new Label(horizArrangement);
timeLeftLabel.Text("Time Left before sending the message:");
timeLeftLabel.FontBold(true);
timeLeftLabel.FontItalic(true);
timeLeftLabel.TextColor(COLOR_BLUE);
timeLeftValueLabel= new Label(horizArrangement);
timeLeftValueLabel.Text("");

countdownClock = new Clock(this);
// timer disabled at start	
countdownClock.TimerEnabled(false);
countdownClock.TimerInterval(60000); //60,000 miliseconds = 1min, but for testing, we use 1000(1 second)

// texting component
mytexting = new Texting(this);


//header = new Label(this);
//header.Text("On My Way");



//init Non-visual components
locationSensor1 = new LocationSensor(this);

//find provider of location data e.g. apple/goog
locationSensor1.RefreshProvider();

//enable the notifier
notifier1 = new Notifier(this);

//horiz = new HorizontalArrangement(this);
//horiz.Width(LENGTH_FILL_PARENT);

// opening screen
//screen1 = new Button(horiz);
//screen1.Text("Location");

// navigation to second screen
//screen2 = new Button(horiz);
//screen2.Text("Contacts");


horiz1 = new HorizontalArrangement(this);
label2 = new Label(horiz1);
label2.Text("Where Are You?");
label2.FontBold(true);
label2.FontItalic(true);


horiz2 = new HorizontalArrangement(this);
textbox1 = new TextBox(horiz2);
textbox1.Height(140);
textbox1.Hint("Enter your current location or use the help!");
textbox1.Width(500);


location = new Button(horiz2);
location.Text("Find GPS");
location.Image("globe.jpg");
location.TextColor(COLOR_RED);


//horiz00 = new HorizontalArrangement(this);
//labelAddy = new Label(horiz00);
//labelAddy.Text("Address: ");

//labelCurrAddr = new Label(horiz00);
//labelCurrAddr.Text("");

//labelcoord = new Label(horiz00);
//labelcoord.Text("GPS:");

//labelCurrGps = new Label(horiz00);
//labelCurrGps.Text("");


horiz3 = new HorizontalArrangement(this);
submit1 = new Button(horiz3);
submit1.Image("submit.jpg");

confirm1 = new Label(horiz3);
confirm1.Text("");

horiz4 = new HorizontalArrangement(this);
label3 = new Label(horiz4);
label3.Text("Where Are You Going?");
label3.FontBold(true);
label3.FontItalic(true);


horiz5 = new HorizontalArrangement(this);
textbox2 = new TextBox(horiz5);
textbox2.Height(140);
textbox2.Width(1000);
textbox2.Hint("Sample:2139 Fulton Street");


horiz6 = new HorizontalArrangement(this);
submit2 = new Button(horiz6);
submit2.Image("submit.jpg");

confirm2 = new Label(horiz6);
confirm2.Text("");


horiz7 = new HorizontalArrangement(this);
label4 = new Label(horiz7);
label4.Text("Write Your Message");
label4.FontBold(true);
label4.FontItalic(true);
	

horiz8 = new HorizontalArrangement(this);
textbox3 = new TextBox(horiz8);
textbox3.Height(140);
textbox3.Width(1000);
textbox3.Hint("Sample: I'm leaving Jessica's Party!");


horiz9 = new HorizontalArrangement(this);
submit3 = new Button(horiz9);
submit3.Image("submit.jpg");

confirm3= new Label(horiz9);
confirm3.Text("");


horiz11 = new HorizontalArrangement(this);
phonenumber = new TextBox(horiz11);
phonenumber.Width(LENGTH_FILL_PARENT);
phonenumber.Hint("Sample Number: 777-8888-9999");
	

horiz10 = new HorizontalArrangement(this);
countdown = new TextBox(horiz10);
countdown.Hint("Minutes!");
countdown.Width(250);
	

send = new Button(horiz10);
send.Image("send.jpg");
send.Width(400);

stop = new Button(horiz10);
stop.Image("stop.png");

	
EventDispatcher.registerEventForDelegation( this, "Button", "Click" );
EventDispatcher.registerEventForDelegation( this, "Clock", "Timer" );

//register the event that shows location has changed
EventDispatcher.registerEventForDelegation( this, "GPS","LocationChanged");


	}

	
public boolean dispatchEvent(Component component, String componentName, String eventName, Object[] args ){
	
	
	if (component.equals(location) && eventName.equals("Click")) {	
		gpsClick();
		return true;
	}

	
	if (eventName.equals("LocationChanged")) {
		getGpsLocation((Double) args[0], (Double) args[0], (Double) args[0]);
		return true;
	}
	
	if (component.equals(submit1) && eventName.equals("Click")){
		submit1Click();
		return true;
	}
	
	if (component.equals(submit2) && eventName.equals("Click")){
		submit2Click();
		return true;
	}
	
	if (component.equals(submit3) && eventName.equals("Click")){
		submit3Click();
		return true;
	}
	
	if (component.equals(send) && eventName.equals("Click") ){
		sendClick();
	return true;
	}
	
	if(component.equals(stop) && eventName.equals("Click")){
		countdownClock.TimerEnabled(false);
	}
	
	
	
	if( component.equals(countdownClock) && eventName.equals("Timer") ){
		// the user will enter minutes
		int count = Integer.parseInt(timeLeftValueLabel.Text());
		// change the minutes to seconds
		count = count - 1;
		timeLeftValueLabel.Text(String.valueOf(count));
		if (count==0) {
			message.add(textbox1.Text());
			message.add(textbox2.Text());
			message.add(textbox3.Text());
			for (int i=0;i<message.size();i++){
				messageSent += message.get(i)+",";
			}
			timeLeftValueLabel.Text("Message Sent");
			mytexting.PhoneNumber(phonenumber.Text());
			mytexting.Message(messageSent);
			mytexting.SendMessage();
			countdownClock.TimerEnabled(false);
		}
	
		return true;
	}
	
	return false;
}


public void submit1Click(){
	confirm1.Text("Message Received Successfully");
}

public void submit2Click(){
	confirm2.Text("Message Received Successfully");
}

public void submit3Click(){
	confirm3.Text("Message Received Successfully");
}

public void sendClick(){
	countdownClock.TimerEnabled(true);
	timeLeftValueLabel.Text(countdown.Text());
}


public void gpsClick(){

	gpsProviderName = locationSensor1.ProviderName();
	//if(!gpsProviderName.equals("GPS")){

		//alert the user that they must enable gps
		//notifier1.ShowAlert("Enable GPS in Settings");
	//}
	if (locationSensor1.HasLongitudeLatitude()){
	latitude = Double.toString(locationSensor1.Latitude());
	longitude = Double.toString(locationSensor1.Longitude());
	//display in a label
	
	//labelCurrGps.Text(latitude + "," + longitude);
	//labelCurrAddr.Text(locationSensor1.CurrentAddress());
	
	// display in a textbox
	
	textbox1.Text(locationSensor1.CurrentAddress());
	}
}
	

public void getGpsLocation(Double lat, Double lon, Double alt) {
textbox1.Text(locationSensor1.CurrentAddress());

}



}
