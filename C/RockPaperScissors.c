/* Author: Andrew Schneider */

/* PART 2 of Lab 1*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>


enum comparison {a = 0, b = 1, c = 2} playerHands;

int main(void) {
	int chNext;
	int ch[2];
	int exitFlag;
	int validFlag;
	srand(time(NULL));
	printf("\n");
	printf("Welcome to Roshambo!\n");
	printf("Everyone's favorite game of Rock, Paper, and Scissors\n");
	exitFlag = 0;

	while (exitFlag == 0) {
		printf("Please enter your one-letter move: (R, P, S, or Q to quit)\n");
		scanf("%i", ch);
		if (ch[1] == '\n')  {
			int randy;
			if (ch[0] != 'R' && ch[0] != 'r' && ch[0] != 'P' && ch[0] != 'p' && ch[0] != 'S' && ch[0] != 's' && ch[0] != 'q' && ch[0] != 'Q') {
				printf("Not a valid choice. Please try again.\n");
				scanf("%i", ch);
			} else
			 if (ch[0] == 'q' || ch[0] == 'Q') {
				printf("Quitting...\n\n");
				exitFlag = 1;
			} else {
				printf("Valid move... Generating random move for computer...\n\n");
				randy = rand() % 3 + 1;
				switch(randy) {
					case 1 : 
						printf("The computer chose Rock.\n");
						break;
					case 2 : 
						printf("The computer chose Paper.\n");
						break;
					case 3 : 
						printf("The computer chose Scissors.\n");
						break;
					default : 
						printf("Error, something happened.");			
				}
			
			/* 0 = win, 1 = loss, 2 = draw */

				if ((ch[0] == 'r' || ch[0] == 'R') && randy == 1) {
					playerHands = c;
				} else if ((ch[0] == 'r' || ch[0] == 'R') && randy == 2) {
					playerHands = b;
				} else if ((ch[0] == 'r' || ch[0] == 'R') && randy == 3) {
					playerHands = a;
				} else if ((ch[0] == 'p' || ch[0] == 'P') && randy == 1) {
					playerHands = a;
				} else if ((ch[0] == 'p' || ch[0] == 'P') && randy == 2) {
					playerHands = c;
				} else if ((ch[0] == 'p' || ch[0] == 'P') && randy == 3) {
					playerHands = b;
				} else if ((ch[0] == 's' || ch[0] == 'S') && randy == 1) {
					playerHands = b;
				} else if ((ch[0] == 's' || ch[0] == 'S') && randy == 2) {
					playerHands = a;
				} else {
					playerHands = c;
				}
				if (playerHands == 0) {
					printf("You win!\n");
				} else if (playerHands == 1) {
					printf("You lose.\n");
				} else {
					printf("It's a draw!\n");
				}
				printf("\n");
			}
		} else {
			printf("Your input was not valid. Let's start over.\n");
			scanf("%i", ch);
		}
	}

}
