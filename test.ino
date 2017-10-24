#include <SoftwareSerial.h> 

#define IN1 2	// L298N back    (blue)
#define IN2 3	// L298N forward (green)
#define IN3 4	// L298N left    (yellow)
#define IN4 5	// L298N right   (orange)

void _stop();
void forward();
void back();
void turnRight();
void turnLeft();
void turnBackRight();
void turnBackLeft();

void _stop(){
	digitalWrite(IN1, LOW);
	digitalWrite(IN2, LOW);
	digitalWrite(IN3, LOW);
	digitalWrite(IN4, LOW);
}
void forward(){
	digitalWrite(IN1, LOW);
	digitalWrite(IN2, HIGH);
	digitalWrite(IN3, LOW);
	digitalWrite(IN4, LOW);
}
void back(){
	digitalWrite(IN1, HIGH);
	digitalWrite(IN2, LOW);
	digitalWrite(IN3, LOW);
	digitalWrite(IN4, LOW);
}
void forwardRight(){
	digitalWrite(IN1, LOW);
	digitalWrite(IN2, HIGH);
	digitalWrite(IN3, LOW);
	digitalWrite(IN4, HIGH);
}
void forwardLeft(){
	digitalWrite(IN1, LOW);
	digitalWrite(IN2, HIGH);
	digitalWrite(IN3, HIGH);
	digitalWrite(IN4, LOW);
}
void backRight(){
	digitalWrite(IN1, HIGH);
	digitalWrite(IN2, LOW);
	digitalWrite(IN3, LOW);
	digitalWrite(IN4, HIGH);
}
void backLeft(){
	digitalWrite(IN1, HIGH);
	digitalWrite(IN2, LOW);
	digitalWrite(IN3, HIGH);
	digitalWrite(IN4, LOW);
}

void setup(){
	Serial.begin(9600); // set up Serial library at 9600 bps
	pinMode(IN1, OUTPUT);
	pinMode(IN2, OUTPUT);
	pinMode(IN3, OUTPUT);
	pinMode(IN4, OUTPUT);
}

void loop(){
	forward();
	delay(2000);
	back();
	delay(2000);
	_stop();
	delay(2000);
	
	forwardRight();
	delay(2000);
	backRight();
	delay(2000);
	_stop();
	delay(2000);
	
	forwardLeft();
	delay(2000);
	backLeft();
	delay(2000);
	_stop();
	delay(2000);
	/*
	if(Serial.available()>0){
		i=Serial.read();
		switch(i){
			case'1':{
				forward();
				break;
			}
			case'2':{
				back();
				break;
			}
			case'3':{
				turnRight();
				break;
			}
			case'4':{
				turnLeft();
				break;
			}
			case'5':{
				turnBackRight();
				break;
			}
			case'6':{
				turnBackLeft();
				break;
			}
			case'7':{
				_stop();
				break;
			}
		}
	}*/
}

