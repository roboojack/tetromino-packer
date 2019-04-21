![](docs/39px-Tetromino_O.svg.png)
![](docs/53px-Tetromino_Z.svg.png)
![](docs/54px-Tetromino_J.svg.png)
![](docs/54px-Tetromino_T.svg.png)
![](docs/56px-Tetromino_L.svg.png)
![](docs/71px-Tetromino_I.svg.png)


## Overview

Tree Search Implementations

### How can we use Tree Search Algorithms to figure out Tetris?

![Tree Search for Bin Packing](docs/treeBuildingGraphic.png)


There are many great Generalized Tree Search Algorithms that can be used
to solve this problem!

In this application we'll implement:

- Depth First Search
- Breadth First Search
- Depth First Search
- Recursive DLS
- Iterative Deepening
- Simulated Annealing
- Hill Climbing
- A*
- Greedy Best First Search



### GUI Screenshot
![Screenshot](docs/Screenshot from 2019-04-21 13-10-31.png)

You can plugin how many of each shape you want to put in the board, select an algorithm
and the app will do search to find a solution. At the end, if prints the location of the 
pieces for the solution.

### Performance Trends
![Screenshot](docs/Screenshot from 2019-04-21 13-53-00.png)


### Running
To run the application just clone or download the zip and run
this in the command line:

`mvn install exec:java
`