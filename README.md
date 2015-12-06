# Sudoku-AI
Sudoku is puzzle that is played on an ```n * n``` subgrid and ```n^2 * n^2``` full lengthed grid. This implementation of the Sudoku puzzle however will look at dynamic different lengthed Sudoku puzzles and run path-search algorithms to find a unique solution. The Sudoku puzzle is a constraint-satisfaction problem that requires the solution to have rows, columns and subgrids have non-conflicting values in them. There are no duplicates in every row/column/subgrid. 

The Sudoku puzzle is randomly instantiated with a partial assignment of variables into the Sudoku grid. This does not guarantee that the search algorithms will find a solution, however, because it is randomly instantiated in way that although nothing conflicts at first, it may cause the constraint-propagation to fail.

The A* search algorithm, along with the default brute force algorithm was implemented for this AI project.
