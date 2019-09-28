// Test for p5

var height2 = 100
var frame = 30;



function setup() {
  createCanvas(400, 400);
	stroke(0, 255, 0);
	frameRate(frame);
	
}

function draw() {
  background(0);
	drawLine();
	
}

function drawLine() {
	var index = 0;
	height2 = height2 - 1;
	if (height2 < 0) {
		height2 = height;
		
	}
	line(0, height2, width, height2);
	line(0, height2 + 10, width, height2 + 10);
	if (mouseIsPressed) {
		print("Pressed");
		frame = 100;
		height2 -= 15;
		textSize(32);
		text('Pressed', 150, 200);
		fill(0, 255, 0);
		line(mouseX- 70, 0, mouseX - 70, 400);
		line(mouseX + 70, 0, mouseX + 70, 400);
	}
	print(mouseIsPressed);
}
