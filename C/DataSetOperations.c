/* Andrew Schneider */

/* Lab2 */

#include <stdio.h>
#include <stdlib.h>



void allocateAndReadDataSets(float ***dataSetsPtrPtr, int **dataSetSizesPtr, int *numSetsPtr) {

	printf("Enter the number of data sets: \n");
	scanf("%i", numSetsPtr);
	*dataSetsPtrPtr = malloc(*numSetsPtr * sizeof(float*));
	*dataSetSizesPtr = malloc(*numSetsPtr * sizeof(int));
}

void buildDataSets(float ***dataSetsPtrPtr, int **dataSetSizesPtr, int *numSetsPtr) {
    int sizeOfLine;
    int ch;
    int index;
    int decimalSeen;
    int decimalPlace;
    float *arr;
    int insertPosition;
    float loader;

    double tens, hundreds, thousands;
    for (index = 0; index < *numSetsPtr; index++) {
        
        decimalSeen = 0;
        decimalPlace = 1;
        insertPosition = 0;
        tens = 0.1;
        hundreds = 0.01;
        thousands = 0.001;
        loader = 0;
        scanf("%i", &sizeOfLine);
        loader = 0;
        arr = malloc(sizeOfLine * sizeof(float));

        *(*dataSetSizesPtr + index) = sizeOfLine;
        while ((ch = getchar()) != EOF) {
            if (ch != ' ' && ch != '\n') {
                if (ch == '.') {
                    decimalSeen = 1;
                } else {
                    if (decimalSeen == 0) {
                        loader *= 10;
                        loader += (ch - '0');
                    } else if (decimalSeen == 1) {
                        if (decimalPlace == 1) {
                            loader += ((ch - '0') * tens);
                            decimalPlace++;
                        } else if (decimalPlace == 2) {
                            loader += ((ch -'0') * hundreds);
                            decimalPlace++;
                        } else if(decimalPlace == 3) {
                            loader += ((ch - '0') * thousands);
                            decimalPlace++;
                        }
                    }
                }
            } else {
                if (loader != 0) {
                    if (ch == '\n') {
                        *(arr + insertPosition) = loader;
                        insertPosition++;
                        decimalSeen = 0;
                        decimalPlace = 1;
                        loader = 0;
                        break;
                    } else {
                        *(arr + insertPosition) = loader;
                        insertPosition++;
                        decimalSeen = 0;
                        decimalPlace = 1;
                        loader = 0;
                    }
                }
            }
        }
        sizeOfLine = 0;
        decimalSeen = 0;
        decimalPlace = 1;
        *(*dataSetsPtrPtr + index) = arr;
    }
}

void findSum(int selectedSet, float ***dataSetsPtrPtr, int **dataSetSizesPtr) {
    float sum;
    int index;
    int actualSet = selectedSet - 1;
    int limit = *(*dataSetSizesPtr + actualSet);
    printf("\nComputing the sum of set %i.\n", selectedSet);
    
    sum = 0;
    for (index = 0; index < limit; index++) {
        sum += *((*(*dataSetsPtrPtr + actualSet)) + index);
    }
    printf("The sum of set %i is: %.3f\n\n", selectedSet, sum);
}

void findAverage(int selectedSet, float ***dataSetsPtrPtr, int **dataSetSizesPtr) {
    float average;
    int index;
    int count;
    int actualSet = selectedSet - 1;
    int limit = *(*dataSetSizesPtr + actualSet);
    printf("\nComputing the average of set %i.\n\n", selectedSet);
    
    average = 0;
    for (index = 0; index < limit; index++) {
        average += *((*(*dataSetsPtrPtr + actualSet)) + index);
    }
    
    printf("The average of set %i is: %.3f\n\n", selectedSet, average / (index + 1));
}

void findMinValue(int selectedSet, float ***dataSetsPtrPtr, int **dataSetSizesPtr) {
    float minValue;
    int index;
    int actualSet = selectedSet - 1;
    int limit = *(*dataSetSizesPtr + actualSet);
    printf("\nComputing minimum value in set %i.\n", selectedSet);
    
    minValue = -1;
    for (index = 0; index < limit; index++) {
        if (minValue == -1){
            minValue = *((*(*dataSetsPtrPtr + actualSet)) + index);
        } else {
            if (minValue > *((*(*dataSetsPtrPtr + actualSet)) + index)) {
                minValue = *((*(*dataSetsPtrPtr + actualSet)) + index);
            }
        }
    }
    printf("The minimum value in set %i is: %.3f\n\n", selectedSet, minValue);
}

void findMaxValue(int selectedSet, float ***dataSetsPtrPtr, int **dataSetSizesPtr) {
    float maxValue;
    int index;
    int actualSet = selectedSet - 1;
    int limit = *(*dataSetSizesPtr + actualSet);
    printf("\nComputing maximum value in set %i.\n", selectedSet);
    
    maxValue = -1;
    for (index = 0; index < limit; index++) {
        if (maxValue == -1){
            maxValue = *((*(*dataSetsPtrPtr + actualSet)) + index);
        } else {
            if (maxValue < *((*(*dataSetsPtrPtr + actualSet)) + index)) {
                maxValue = *((*(*dataSetsPtrPtr + actualSet)) + index);
            }
        }
    }
    printf("The maximum value in set %i is: %.3f\n\n", selectedSet, maxValue);
}

void printAllValues(int selectedSet, float ***dataSetsPtrPtr, int **dataSetSizesPtr) {
    int index;
    int actualSet = selectedSet - 1;
    int limit = *(*dataSetSizesPtr + actualSet);
    printf("\nAll elements values of data set %i.\n", selectedSet);
    for (index = 0; index < limit; index++) {
        printf("%.3f ", *((*(*dataSetsPtrPtr + actualSet)) + index));
    }
    printf("\n\n");
}

void bubbleSort(int selectedSet, float ***dataSetsPtrPtr, int **dataSetSizesPtr) {
    int index;
    int index2;
    float temp;
    int actualSet = selectedSet - 1;
    int limit = *(*dataSetSizesPtr + actualSet);
    
    for (index = 0; index <= limit; index++) {
        for (index2 = index + 1; index2 < limit; index2++) {
            if (*((*(*dataSetsPtrPtr + actualSet)) + index2) < *((*(*dataSetsPtrPtr + actualSet)) + index)) {
                temp = *((*(*dataSetsPtrPtr + actualSet)) + index2);
                *((*(*dataSetsPtrPtr + actualSet)) + index2) = *((*(*dataSetsPtrPtr + actualSet)) + index);
                 *((*(*dataSetsPtrPtr + actualSet)) + index ) = temp;
            }
        }
    }
    printf("\nSorted elements of set %i: \n", selectedSet);
     for (index = 0; index < limit; index++) {
        printf("%.3f ", *((*(*dataSetsPtrPtr + actualSet)) + index));
    }
    printf("\n");

}

int main(void) {
	int numSets;
    int index;
    int index2;
    int userChoice;
    int userSet;
	int *dataSetSizes = NULL;
	float **dataSetsPtr = NULL;
    int exit = 0;
	allocateAndReadDataSets(&dataSetsPtr, &dataSetSizes, &numSets);
    buildDataSets(&dataSetsPtr, &dataSetSizes, &numSets);
    printf("\n");
    while (exit == 0) {
        printf("Please enter the number of the set on which to do calculations: \n");
        scanf("%i", &userSet);
        printf("\n");
        printf("1. Find the minimum value.\n");
        printf("2. Find the maximum value.\n");
        printf("3. Calculate the sum of all the values.\n");
        printf("4. Calculate the average of all the values.\n");
        printf("5. Print the values in the data set.\n");
        printf("6. Sort the data set, leaving memory addresses the same.\n");
        printf("7. Quit the program.\n");
        printf("\n");

        scanf("%i", &userChoice);
        if (userChoice == 7) {
            printf("\nQuiting..\n");
            exit = 1;
        } else {
            
            if (userChoice == 1) {
                findMinValue(userSet, &dataSetsPtr, &dataSetSizes);
            } else if (userChoice == 2) {
                findMaxValue(userSet, &dataSetsPtr, &dataSetSizes);
            } else if (userChoice == 3) {
                findSum(userSet, &dataSetsPtr, &dataSetSizes);
            } else if (userChoice == 4) {
                findAverage(userSet, &dataSetsPtr, &dataSetSizes);
            } else if (userChoice == 5) {
                printAllValues(userSet, &dataSetsPtr, &dataSetSizes);
            } else {
                bubbleSort(userSet, &dataSetsPtr, &dataSetSizes);
            }
        }

    }

    
	
}
