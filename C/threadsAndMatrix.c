#include <time.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdlib.h>
#include <stdio.h>

void *thread1();
void *thread2();

int matrixA[6000][3000];
int matrixB[3000][1000];
int matrixC[6000][1000];
int matrixC2[6000][1000];

pthread_t PID[6];

int n, p, m;
int numberOfThreads;

int main(){

	int i, j, k;

	n = 6000;
	p = 1000;
	m = 3000;
	for(i = 0; i < n; i++){
		for(j = 0; j < m; j++){
			matrixA[i][j] = i*j;
		}
	}

	for(i = 0; i < m; i++){
		for(j = 0; j < p; j++){
			matrixB[i][j] = i+j;
		}
	}
	printf("Matrix Initialized \n");
	k = pthread_create(&PID[0], NULL, thread1, NULL);

	for (i = 0;i < 1; i++){ pthread_join(PID[i],NULL);}
		exit(0);

}

void *thread1(){
	int i, j, k, a, b, c, d, e, f;
	int start, end;
	int isIncorrect;

	isIncorrect = 0;
//PART ONE
	printf("THREAD ONE START \n");
	start = time(NULL);
	for (i=0; i < n; i++){
		for (j=0; j < p; j++){
			matrixC[i][j]=0;
			for (k=0; k < m; k++){
				matrixC[i][j] += matrixA[i][k]*matrixB[k][j];
			}
		}
	}
	end = time(NULL);
	printf("Time Taken for Thread One: %d \n", end - start);
//PART TWO

	numberOfThreads = 2;
	a = 1;
	k = pthread_create(&PID[1], NULL, thread2, &a);

	b = 2;
	k = pthread_create(&PID[2], NULL, thread2, &b);
	start = time(NULL);
	for (i = 1;i < 3; i++){ pthread_join(PID[i],NULL);}
		end = time(NULL);
	printf("Time Taken for Thread Two: %d\n", end - start);
	for (i=0; i < n; i++){
		for (j=0; j < p; j++){
			if(matrixC[i][j] != matrixC2[i][j]){printf("i: %d j: %d\n", i, j);}
		}
	}
	if(isIncorrect != 1){printf("This is correct!! \n");}else{printf("Incorrect");}

//PART THREE

	numberOfThreads = 3;
	a = 1;
	b = 2;
	c = 3;
	k = pthread_create(&PID[4], NULL, thread2, &a);
	k = pthread_create(&PID[5], NULL, thread2, &b);
	k = pthread_create(&PID[6], NULL, thread2, &c);
	start = time(NULL);
	for (i = 4;i < 7; i++){ pthread_join(PID[i],NULL);}
		end = time(NULL);
	printf("Time Taken for Thread Three: %d\n", end - start);
	for (i=0; i < n; i++){
		for (j=0; j < p; j++){
			if(matrixC[i][j] != matrixC2[i][j]){printf("i: %d j: %d\n", i, j);}
		}
	}
	if(isIncorrect != 1){printf("This is correct!! \n");}else{printf("Incorrect");}
//PART FOUR

	numberOfThreads = 4;
	a = 1;
	b = 2;
	c = 3;
	d = 4;
	start = time(NULL);
	k = pthread_create(&PID[1], NULL, thread2, &a);
	k = pthread_create(&PID[2], NULL, thread2, &b);
	k = pthread_create(&PID[3], NULL, thread2, &c);
	k = pthread_create(&PID[4], NULL, thread2, &d);

	for (i = 1;i < 5; i++){ pthread_join(PID[i],NULL);}
		end = time(NULL);
	printf("Time Taken for Thread Four: %d\n", end - start);
	for (i=0; i < n; i++){
		for (j=0; j < p; j++){
			if(matrixC[i][j] != matrixC2[i][j]){printf("i: %d j: %d\n", i, j);}
		}
	}
	if(isIncorrect != 1){printf("This is correct!! \n");}else{printf("Incorrect");}

	numberOfThreads = 5;
	a = 1;
	b = 2;
	c = 3;
	d = 4;
	e = 5;
	start = time(NULL);
	k = pthread_create(&PID[0], NULL, thread2, &a);
	k = pthread_create(&PID[1], NULL, thread2, &b);
	k = pthread_create(&PID[2], NULL, thread2, &c);
	k = pthread_create(&PID[3], NULL, thread2, &d);
	k = pthread_create(&PID[4], NULL, thread2, &e);

	for (i = 0;i < 5; i++){ pthread_join(PID[i],NULL);}
		end = time(NULL);
	printf("Time Taken for Thread Five: %d\n", end - start);
	for (i=0; i < n; i++){
		for (j=0; j < p; j++){
			if(matrixC[i][j] != matrixC2[i][j]){printf("i: %d j: %d\n", i, j);}
		}
	}
	if(isIncorrect != 1){printf("This is correct!! \n");}else{printf("Incorrect");}

	numberOfThreads = 6;
	a = 1;
	b = 2;
	c = 3;
	d = 4;
	e = 5;
	f = 6;
	start = time(NULL);
	k = pthread_create(&PID[0], NULL, thread2, &a);
	k = pthread_create(&PID[1], NULL, thread2, &b);
	k = pthread_create(&PID[2], NULL, thread2, &c);
	k = pthread_create(&PID[3], NULL, thread2, &d);
	k = pthread_create(&PID[4], NULL, thread2, &e);
	k = pthread_create(&PID[5], NULL, thread2, &f);

	for (i = 0;i < 5; i++){ pthread_join(PID[i],NULL);}
		end = time(NULL);
	printf("Time Taken for Thread Six: %d\n", end - start);
	for (i=0; i < n; i++){
		for (j=0; j < p; j++){
			if(matrixC[i][j] != matrixC2[i][j]){printf("i: %d j: %d\n", i, j);}
		}
	}
	if(isIncorrect != 1){printf("This is correct!! \n");}else{printf("Incorrect");}

	pthread_exit(NULL);

}

void *thread2(void* arg){
	int i, j, k, thrnum;
	int mnum, nnum, pnum;
	thrnum = *((int*)arg);

	mnum = m / numberOfThreads;
	nnum = n / numberOfThreads;
	pnum = p / numberOfThreads;

	for (i=(nnum*thrnum) - nnum; i < nnum*thrnum; i++){
		for (j=0; j < p; j++){
			matrixC2[i][j]=0;
			for (k=0; k < m; k++){
				matrixC2[i][j] += matrixA[i][k]*matrixB[k][j];
			}
		}
	}

	pthread_exit(NULL);

}


