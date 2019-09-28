"""
In uninformed_search.py, you will implement generic search algorithms 
(using uninformed search approaches) which are called by
Pacman agents (in searchAgents.py).
"""

""" Andrew Schneider """

import util

def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.

    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print "Start:", problem.getStartState()
    print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    print "Start's successors:", problem.getSuccessors(problem.getStartState())
    """
    "*** YOUR CODE HERE ***"
    children = util.Stack()
    visited = set()
    children.push((problem.getStartState(), [], 0))
    
    while not children.isEmpty():
    	currentState, currentMoves, currentCost = children.pop()
    	
    	if (currentState in visited):
    		continue
    		
    	visited.add(currentState)
    	
    	if problem.isGoalState(currentState):
    		return currentMoves
    	
    	for state, direction, cost in problem.getSuccessors(currentState):
    		children.push((state, currentMoves+[direction], currentCost))
    
    return []


    
def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "*** YOUR CODE HERE ***"
    children = util.Queue()
    visited = set()	
    children.push((problem.getStartState(), [], 0))
    
    while not children.isEmpty():
    	currentState, currentMoves, currentCost = children.pop()
    	
    	if currentState in visited:
    		continue
    		
    	visited.add(currentState)
    	
    	if problem.isGoalState(currentState):
    		return currentMoves
    		
    	for state, direction, cost in problem.getSuccessors(currentState):
    		children.push((state, currentMoves+[direction], currentCost))
    		
    return []

def uniformCostSearch(problem):
    """Search the node of least total cost first."""
    "*** YOUR CODE HERE ***"
    fringe = util.PriorityQueue()
    startState = (problem.getStartState(), {})
    fringe.push(startState, problem.getCostOfActions(startState[1]) )
    explored = set([])
    
    currentNode = None
    nextNode = None
    while True : 
        if fringe.isEmpty() :
            return 'failure'
        nextNode = fringe.pop()
        if problem.isGoalState(nextNode[0]) :
            return nextNode[1]
        if not nextNode[0] in explored :
            explored.add(nextNode[0])
            successors = problem.getSuccessors(nextNode[0])
            for i in range( len(successors )) :
                path = list(nextNode[1])
                path.append(successors[i][1])
                fringe.push( ( successors[i][0], path ), problem.getCostOfActions(path))

# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
ucs = uniformCostSearch
