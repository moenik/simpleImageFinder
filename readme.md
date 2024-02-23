# Simple Image Finder - simpleImageFinder
A simple image finder using brutalforce(pixel by pixel) algorithm! 

Finds a exact match image inside a source image.

Made with Java

# UI and Headless
## Run as Desktop App (Java Swing)
When have no args it opens a Desktop UI
![altText](example.gif "example") 
## Run in Command Line
	java -jar simpleimagefinder.jar  -s fullImage.png  -i mug01.png -i mug2.png  -o output.png
	X=364,Y=82
	X=214,Y=283
	
Help pass -h or --help

	java -jar simpleimagefinder.jar --help -h

	This program searches for exact match of images inside a source image.
	It returns the top left pixel point(s) of matched image.
	It does NOT have performance! It uses brute force to compare images (pixel by pixel)
	Works better with PNG files because compression
	Params:
	-s  --source	- path of source image to use (necessary)
	-i  --image	- path of image to find, could be more than one Ex: -i file1 -i file2 ... -i fileN 
	-o  --output	- output file if specified