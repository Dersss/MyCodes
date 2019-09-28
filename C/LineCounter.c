
#include <stdio.h>

/* Author: Andrew Schneider */

int main(void) {
	int numberOfLines;
	int decimalFlag = 0;
	int decimalSpacing = 1;
	int index = 0;
	double total = 0;
	printf("Please enter number of lines then enter each number on own line: \n");
	scanf("%i", &numberOfLines);
	while (numberOfLines >=  0) {
		int ch;
		double tens = 0.1;
		double hunds = 0.01;
		double thous = 0.001;
		double tentho = 0.0001;
		double num = 0;
		while ((ch = getchar()) != '\n' && ch != EOF) {
			if (ch != '.') {
				ch -= '0';
				if (decimalFlag == 0) {
					if (num > 9) {
						num *= 10;
						num += ch;
					} else {
						num *= 10;
						num += ch;
					}
				} else {
					if (decimalSpacing == 1) {
						num += (tens * ch);
						decimalSpacing++;
					} else if (decimalSpacing == 2) {
						num += (hunds * ch);
						decimalSpacing++;
					} else if (decimalSpacing == 3) {
						num += (thous * ch);
						decimalSpacing++;
					} else {
						num += (tentho * ch);
						decimalSpacing = 1;
					}
				}
			} else {
				decimalFlag = 1;
			} 			
		}
		total += num;		
		numberOfLines--;
		decimalFlag = 0;
		decimalSpacing = 1;
	}
	printf("Total: %.4f\n", total);
}
