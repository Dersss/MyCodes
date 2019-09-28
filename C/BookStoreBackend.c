/* Author: Andrew Schneider 
    Lab 3 Par 1 
*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Data {
    char title[50];
    char author[50];
    int stockNumber;
    float wholesalePrice;
    float retailPrice;
    int wholesaleQuantity;
    int retailQuantity;
};
typedef struct Node {
    struct Data book;
    struct Node *next;
} Node;

/*
    Function that prints all of the nodes within the list. Not required but was a good
    helper function. 

    @param listHeadPtr points the pointer at the head of the list
    
*/
void printList(Node *listHeadPtr) {
    int index;
    Node *traversePtr;
    traversePtr = listHeadPtr->next;
    printf("Total Library \n");
    while (traversePtr != NULL) {
        printf("%s\n", traversePtr->book.title);
        printf("%s\n", traversePtr->book.author);
        printf("%i\n", traversePtr->book.stockNumber);
        printf("%.2f\n", traversePtr->book.wholesalePrice);
        printf("%.2f\n", traversePtr->book.retailPrice);
        printf("%i\n", traversePtr->book.wholesaleQuantity);
        printf("%i\n", traversePtr->book.retailQuantity);
        traversePtr = traversePtr->next;
    }
}

/*
    Function that prints all of the books that are in stock.

    @param listHeadPtr points the pointer at the head of the list
    
*/
void printInStock(Node *listHeadPtr) {
    Node *traversePtr;
    traversePtr = listHeadPtr->next;
    printf("Books in Stock: \n");
    while (traversePtr != NULL) {
        if ((traversePtr->book.wholesaleQuantity - traversePtr->book.retailQuantity) > 0) {
            printf("%i\t%i\t%s\t%s\n", (traversePtr->book.wholesaleQuantity - traversePtr->book.retailQuantity), traversePtr->book.stockNumber, traversePtr->book.author, traversePtr->book.title);
            traversePtr = traversePtr->next;
        }
        traversePtr = traversePtr->next;
    }
}

/*
    Function that prints all of the books that are OUT of stock.

    @param listHeadPtr points the pointer at the head of the list
    
*/
void printOutOfStock(Node *listHeadPtr) {
    int index;
    Node *traversePtr;
    traversePtr = listHeadPtr->next;
    printf("Books Out Of Stock: \n");
    while (traversePtr != NULL) {
        if ((traversePtr->book.wholesaleQuantity - traversePtr->book.retailQuantity) == 0) {
            printf("%i\t%i\t%s\t%s\n", (traversePtr->book.wholesaleQuantity - traversePtr->book.retailQuantity), traversePtr->book.stockNumber, traversePtr->book.author, traversePtr->book.title); 
        }
        traversePtr = traversePtr->next;
    }
}

/*
    Function that deletes the book associated with the user given stock number. 

    @param listHeadPtr points the pointer at the head of the list
    
*/
void deleteBook(Node *listHeadPtr, int *numNodesPtr) {
    
    if (numNodesPtr == 0) {
        printf("Error. There are no nodes in the list.");
    } else {
        Node *traversePtr;
        int stockNumToDelete;
        int deleted = 0;
        printf("Enter the stock number of book to delete: \n");
        scanf("%i", &stockNumToDelete);
        
        traversePtr = listHeadPtr;
        if(listHeadPtr->book.stockNumber == stockNumToDelete) {
            listHeadPtr = traversePtr->next;
            free(traversePtr);
            printf("Deleted...\n");
            numNodesPtr = numNodesPtr - 1;
        } else {
            struct Node *priorNode;
            int deleted = 0;
            while (deleted == 0) {
                priorNode = traversePtr;
                traversePtr = traversePtr->next;
                if (traversePtr == NULL) {
                    printf("Error. Stock Number %i is not in the list.\n", stockNumToDelete);
                    deleted = 1;
                } else {
                    if (traversePtr->book.stockNumber == stockNumToDelete) {
                        priorNode->next = traversePtr->next;
                        free(traversePtr);
                        printf("Deleted...\n");
                        numNodesPtr = numNodesPtr - 1;
                        deleted = 1;
                    }
                }
            }
        } 
    } 
}

/*
    Function that inserts a book and all of its' data into the list in sorted order 

    @param listHeadPtr 
        points to the pointer at the head of the list
    @param insertNode 
        points to the new node to be inserted
    @param numNodesPtr
        points to the number of books within the list
    
*/
void insert(Node *listHeadPtr, Node *insertNode, int *numNodesPtr) {
    
    Node *traversePtr;
    traversePtr = listHeadPtr->next;
    if (*numNodesPtr == 0) {
        listHeadPtr->next = insertNode;
        insertNode->next = NULL;
        traversePtr = insertNode;
        *numNodesPtr = *numNodesPtr + 1;
    } else {
        if (insertNode->book.stockNumber < traversePtr->book.stockNumber)  {
            listHeadPtr->next = insertNode;
            insertNode->next = traversePtr;
            *numNodesPtr = *numNodesPtr + 1;
        } else {
            Node *priorNode;
            int inserted = 0;
            priorNode = traversePtr;

            while (inserted == 0) {
                priorNode = traversePtr;
                traversePtr = traversePtr->next;
                if (traversePtr == NULL) {
                    priorNode->next = insertNode;
                    insertNode->next = traversePtr;
                    *numNodesPtr = *numNodesPtr + 1;
                    inserted = 1;
                } else {
                    if (insertNode->book.stockNumber < traversePtr->book.stockNumber) {
                        insertNode->next = traversePtr;
                        priorNode->next = insertNode;
                        *numNodesPtr = *numNodesPtr + 1;
                        inserted = 1;
                    }
                }
            }
        }
    }
}


/*
    Function that determines the total revenue for all books in the list 

    @param listHeadPtr 
        points to the pointer at the head of the list
    
*/
float totalRevenue(Node *listHeadPtr) {
    
    int index;
    Node *traversePtr;
    float total = 0;
    traversePtr = listHeadPtr->next;
    while (traversePtr != NULL) {
        total += (traversePtr->book.retailPrice * traversePtr->book.retailQuantity);
        traversePtr = traversePtr->next;
    }
    return total;
}


/*
    Function that determines the total wholesale cost for all books in the list 

    @param listHeadPtr 
        points to the pointer at the head of the list
    
*/
float wholesaleCost(Node *listHeadPtr) {
    
    int index;
    Node *traversePtr;
    float total = 0;
    traversePtr = listHeadPtr->next;
    while (traversePtr != NULL) {
        total += (traversePtr->book.wholesalePrice * traversePtr->book.wholesaleQuantity);
        traversePtr = traversePtr->next;
    }
    return total;
}


/*
    Function that determines the total current investment for all books in the list 

    @param listHeadPtr 
        points to the pointer at the head of the list
    
*/
float currentInvestment(Node *listHeadPtr) {
    
    int index;
    Node *traversePtr;
    float total = 0;
    traversePtr = listHeadPtr->next;

    while (traversePtr != NULL) {
        total += (traversePtr->book.wholesalePrice * (traversePtr->book.wholesaleQuantity - traversePtr->book.retailQuantity));
        traversePtr = traversePtr->next;
    }
    return total;
}


/*
    Function that determines the total profit for all books in the list 

    @param listHeadPtr 
        points to the pointer at the head of the list
    
*/
float totalProfit(Node *listHeadPtr) {
    float rev = totalRevenue(listHeadPtr);
    float wholCost = wholesaleCost(listHeadPtr);
    int currentInv = currentInvestment(listHeadPtr);
    return (rev - wholCost + (float)currentInv);
}


/*
    Function that determines the number of sales for all books in the list 

    @param listHeadPtr 
        points to the pointer at the head of the list
    
*/
int numberOfSales(Node *listHeadPtr) {
    Node *traversePtr;
    int sum = 0;
    traversePtr = listHeadPtr->next;
    while (traversePtr->next != NULL) {
        sum += (traversePtr->book.retailQuantity);
        traversePtr = traversePtr->next;
    }
    return sum;
}

/*
    Function that determines the average profit per sale for books in the list 

    @param totalProf 
        The total profit
    @param numSale
        The number of sales
    
*/
float averageProfit(float totalProf, float numSales) {
    return (totalProf/numSales);
}

/*
    Function that reads the initial input and creates the initial sorted link list

    @param listHeadPtr 
        points to the pointer at the head of the list
    @param numNodesPtr
        pointer to an int to track number of nodes in the list
    
*/
void readAndCreate(Node *listHeadPtr, int *numNodesPtr) {
    
    int exit = 0;
    int listSize = 0;
    Node *traversePtr = listHeadPtr->next;
    Node **priorNodePtr = NULL;
    char end[] = "END_DATA";

    while (exit == 0) {
        Node *newNodePtr = NULL;
        newNodePtr = (struct Node*)malloc(sizeof(struct Node));
        if (newNodePtr == NULL) {
            printf("Storage not allocated. Exiting.\n");
            return;
        } else {
            scanf("%[^\n]", newNodePtr->book.title);
            if (strcmp(newNodePtr->book.title, end) == 0) {
                free(newNodePtr);
                exit = 1;
            } else {
                getchar();
                scanf("%[^\n]", newNodePtr->book.author);
                scanf("%i", &newNodePtr->book.stockNumber);
                scanf("%f", &newNodePtr->book.wholesalePrice);
                scanf("%f", &newNodePtr->book.retailPrice);
                scanf("%i", &newNodePtr->book.wholesaleQuantity);
                scanf("%i", &newNodePtr->book.retailQuantity);
                getchar();
                insert(listHeadPtr, newNodePtr, numNodesPtr);
            }
        }
    }
}


/*
    Main 

*/
int main(void) {
    char string[50];
    float data;
    int data2;
    int userChoice;
    int exit = 0;
    int numNodes = 0;
    Node *head = NULL;
    head = (struct Node*)malloc(sizeof(struct Node)); 
    
    readAndCreate(head, &numNodes);
    printf("\nBook Inventory has been read.\n");
    printf("Inventory contains %i different books.\n\n", numNodes);
    while (exit == 0) {
        
        Node *inserter = NULL;
        inserter = (struct Node*)malloc(sizeof(struct Node)); 
        printf("\nPlease select from the following options:\n");
        printf("1.\tDetermine and print total revenue.\n");
        printf("2.\tDetermine and print total wholesale cost.\n");
        printf("3.\tDetermine and print current investment in books.\n");
        printf("4.\tDetermine and print total profit for all books.\n");
        printf("5.\tDetermine and print total number of sales.\n");
        printf("6.\tDetermine and print average profit per sale.\n");
        printf("7.\tPrint books in stock.\n");
        printf("8.\tPrint books out of stock.\n");
        printf("9.\tAdd a book.\n");
        printf("10.\tDelete a book.\n");
        printf("11.\tExit the program.\n\n");
        scanf("%i", &userChoice);

        switch (userChoice) {
            
            case 1 : 
                data = totalRevenue(head);
                printf("The total revenue is:\t$%.2f\n", data);
                break;
            case 2 : 
                data = wholesaleCost(head);
                printf("The total wholesale cost is:\t$%.2f\n", data);
                break;
            case 3 : 
                data = currentInvestment(head);
                printf("The total revenue is:\t$%.2f\n", data);
                break;
            case 4 : 
                data = totalProfit(head);
                printf("The total profit for all books is:\t$%.2f\n", data);
                break;
            case 5 : 
                data2 = numberOfSales(head);
                printf("The total number of sales is:\t%i\n", data2);
                break;
            case 6 : 
                data = averageProfit(totalProfit(head), (float)numberOfSales(head));
                printf("The average profit per sale is:\t$%.2f\n", data);
                break;
            case 7 : 
                printInStock(head);
                break;
            case 8 : 
                printOutOfStock(head);
                break;
            case 9 : 
                printf("Enter the title of the book: \n");
                scanf("%s", inserter->book.title);
                printf("Enter the author of the book: \n");
                scanf("%s", inserter->book.author);
                printf("Enter the stock number of the book: \n");
                scanf("%i", &inserter->book.stockNumber);
                printf("Enter the wholesale price of the book: \n");
                scanf("%f", &inserter->book.wholesalePrice);
                printf("Enter the retail price of the book: \n");
                scanf("%f", &inserter->book.retailPrice);
                printf("Enter the wholesale quantity of the book: \n");
                scanf("%i", &inserter->book.wholesaleQuantity);
                printf("Enter the retail quantity of the book: \n");
                scanf("%i", &inserter->book.retailQuantity);
                insert(head, inserter, &numNodes); 
                break;
            case 10 : 
                deleteBook(head, &numNodes);
                break;
            case 11 : 
                printf("QUITTING..\n");
                exit = 1;
                break;
            default : 
                ;
        }
    }
    

    
}
