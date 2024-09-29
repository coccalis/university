# -*- coding: utf-8 -*-
"""

@class:
    A2


"""

import copy
from math import ceil

"""--- Variables ---"""

capacity = 10
people_in_the_elevator = 0;
position = 0;
floor_1 = 12;
floor_2 = 3
floor_3 = 7
floor_4 = 8
# to count how many times the find_solution runs to find the best algorithm
cnt_recursively = 0

"""--- End of variables ---"""

"""--- Initialization ---"""

def make_front(state): 
    return [state] 

def make_queue(state):
    return [[state]]

"""--- End of Initialization ---"""

"""--- Boolean functions ---"""

def all_floors_are_empty(state): 
    if state[1] == 0 and state[2] == 0 and state[3] == 0 and state[4] == 0:
        return True
    return False 

def goal (state):
    if state[0] == 0 and all_floors_are_empty(state):
        return True
    return False 
    
def can_go_to_the_floor (state,pos):
    if state[5] < capacity and state[pos] > 0:
        return True
    return False

"""--- End of boolean functions ---"""

"""--- Go to floor functions ---"""

def go_to_floor1(state): 
      new_state = modify_floor(state, 1)
      return new_state 
  
def go_to_floor2(state): 
      new_state = modify_floor(state, 2)
      return new_state
  
def go_to_floor3(state):
    new_state = modify_floor(state, 3)
    return new_state 

def go_to_floor4(state): 
    new_state = modify_floor(state, 4)
    return new_state 

# for not repeating code the change of the floor is been handle by this function
def modify_floor(state,pos):
    new_state = state.copy()
    if can_go_to_the_floor(state, pos):
        if state[pos] > capacity-state[5]:
            new_state[0] = pos
            new_state[pos] = state[pos] + state[5] - capacity
            new_state[5] = 10
        else:
            new_state[0] = pos
            new_state[pos] = 0
            new_state[5] += state[pos]
    return new_state

def go_to_ground_floor(state):
    if (state[5]==capacity and state[0]!=0) or (all_floors_are_empty(state)):
        new_state = [0]+[state[1]]+[state[2]]+[state[3]]+[state[4]]+[0]
        return new_state 
    
"""--- End to go to floor functions ---"""

"""--- Expanding functions ---"""

def extend_queue(queue, method):
    if method=='DFS':
        print("Queue:")
        print(queue)
        node=queue.pop(0)  #LIFO
        queue_copy=copy.deepcopy(queue)
        children=find_children(node[-1])
        for child in children:
            path=copy.deepcopy(node)
            path.append(child)
            queue_copy.insert(0,path)
            
    elif method=='BFS' or method == 'BEST_FS':
        print("Queue:")
        print(queue)
        node=queue.pop(0)  #FIFO
        queue_copy=copy.deepcopy(queue)
        children=find_children(node[-1]) 
        for child in children:
            path=copy.deepcopy(node)
            path.append(child)
            queue_copy.append(path)

    return queue_copy

def expand_front(front, method):  
    if method=='DFS':        
        if front:
            print("Front:")
            print(front)
            node=front.pop(0) #LIFO
            for child in find_children(node):     
                front.insert(0,child)
    
    elif method=='BFS' or method=='BEST_FS':
        if front:
           # print("FRONT: ",method)
            #print(front)
            node = front.pop(0) #FIFO
            for child in find_children(node):
                front.append(child)
                
    return front

"""--- End of expanding functions ---"""

"""--- Algorithms functions ---"""

def find_solution(front, queue, closed, method): 
    # we use global to allow us modify the variable outside the current scope
    global cnt_recursively
    cnt_recursively += 1 #counts how many times the find solutions runs
    if not front:
        print('_NO_SOLUTION_FOUND_')
       
    elif front[0] in closed: #if the state already exist in the closed
        new_front=copy.deepcopy(front) #create a copy of the front
        new_front.pop(0) #remove the first state 
        new_queue = copy.deepcopy(queue)
        new_queue.pop(0)
        find_solution(new_front, new_queue, closed, method)#run the algorithm with out the state that already exist to run the expand in else
 
    elif goal(front[0]):
        print('_GOAL_FOUND_')
        print(queue[0])
        print("Cycles recursively : ", cnt_recursively)
     
    else: #if front[0] is not in the closed
        closed.append(front[0])
        front_copy=copy.deepcopy(front)
        front_children = expand_front(front_copy, method)
        queue_copy = copy.deepcopy(queue)
        queue_children = extend_queue(queue_copy, method)
        closed_copy = copy.deepcopy(closed)
        if method == 'BEST_FS': #sort the data with our criterias if is BestFs
            front_children, queue_children = sort_h(front_children, queue_children)
        find_solution(front_children, queue_children, closed_copy, method)

"""--- Returns a list of new states ---"""
def find_children(state): 
    children=[]
        
    floor4_state=copy.deepcopy(state)
    floor4_child=go_to_floor4(floor4_state)
    
    floor3_state=copy.deepcopy(state)
    floor3_child=go_to_floor3(floor3_state)
    
    floor2_state=copy.deepcopy(state)
    floor2_child=go_to_floor2(floor2_state)
    
    floor1_state=copy.deepcopy(state)
    floor1_child=go_to_floor1(floor1_state)
    
    ground_floor_state = copy.deepcopy(state)
    ground_floor_child = go_to_ground_floor(ground_floor_state)
    
            
    if floor4_child!=None: 
        children.append(floor4_child)
    
            
    if floor3_child!=None: 
        children.append(floor3_child)
    
    if floor2_child!=None: 
        children.append(floor2_child)
          
    if floor1_child!=None: 
        children.append(floor1_child)
        
    if ground_floor_child!=None: 
        children.append(ground_floor_child)
        
    return children 

"""--- BEST_FS algo ---"""
def getHeuristic (state):
    people_in_the_elevator = state[-1]
    floor_1 = state[1]
    floor_2 = state[2]
    floor_3 = state[3]
    floor_4 = state[4]
    """ceils returns the smallest integer not less than x 
    for example (10.12 returns 10.0), without ceils the findsolution runs
    10 times and with ceils 9 so is faster with ceil"""
    #counts how many times the elevator goes to ground floor
    ground_floor = people_in_the_elevator + floor_1 + floor_2 + floor_3 + floor_4
    times_to_groundfloor = ceil(ground_floor/capacity)
    
    #counts how many times wants to take all the people from the floors
    times_to_floor_1 = ceil(floor_1 / capacity)
    times_to_floor_2 = ceil(floor_2 / capacity)
    times_to_floor_3 = ceil(floor_3 / capacity)
    times_to_floor_4 = ceil(floor_4 / capacity)
    
    times_to_all_floors =  times_to_floor_1 + times_to_floor_2 + times_to_floor_3 + times_to_floor_4
    h_value = times_to_groundfloor + times_to_all_floors
    return h_value


def best_fs (tmpList):
    #get the h and then to be sorter to find the best path to go
    return getHeuristic(tmpList[-1])

def sort_h(front, queue):
        # created a new list based on queue
        z = list(queue)  

        # sort the list based on the heuristic value
        z.sort(key=lambda x: best_fs(x))

    
        front = list()
        queue = list()

        # create again the queue and front from the sorted list
        for i in range(len(z)):
            front.append(z[i][-1])
            queue.append(z[i])

        # returns the new values
        return front, queue
"""--- End of BEST_FS algo ---"""

"""--- End of algorithms functions ---"""



"""--- Main ---"""

def main(): 

    initial_state =[position, floor_1, floor_2, floor_3, floor_4, people_in_the_elevator]  
 
 
    method = input("Please choose a searching method: [BFS or DFS or BEST_FS] \n")
    if(method == 'BFS' or method == 'DFS' or method == 'BEST_FS'):
        print('____BEGIN__SEARCHING____')
        find_solution(make_front(initial_state),make_queue(initial_state),[],  method)
    else:
        print("Error! Invalid Input Please exit and try again.\n")

if __name__ == "__main__":
    main()  

"""--- End of main ---"""

 