package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. Youâ€™ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}
	
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Donâ€™t change
	// â€œthisâ€� grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = 0;
		int yOfNextEmptyCell = 0;
		
		// Find x,y of an empty cell.

		// Construct array list to contain 9 new grids.
		ArrayList<Grid> grids = new ArrayList<Grid>();
		for(int i = 0; i < values.length;i++) 
		{
			for(int j = 0; j < values[0].length;j++)
			{
				int num = values[i][j];
				if(num == 0)
				{
					xOfNextEmptyCell = i;
					yOfNextEmptyCell = j;
				}
			}
		}
		// Create 9 new grids as described in the comments above. Add them to grids.
		for(int k = 1; k <= 9; k++)
		{
			Grid newGrid = new Grid(this);
			newGrid.values[xOfNextEmptyCell][yOfNextEmptyCell] = k;
			grids.add(newGrid);
		}
		return grids;
	}
	
	
	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean rowIsLegal()
	{
		// Check every row. If you find an illegal row, return false.
				for(int i = 0; i<values.length; i++) 
				{
					for(int j = 0; j < values[0].length;j++)
					{
						int value = values[i][j];
						for(int otherCol = j + 1; otherCol < values[0].length; otherCol++)
						{
							int otherValue = values[i][otherCol];
								if(value != 0 && otherValue != 0 && value == otherValue)
								{
									return false;
								}
							
						}
					}
				}
				return true;
	}
	
	public boolean columnIsLegal()
	{
		// Check every column. If you find an illegal column, return false.
				for(int i = 0; i<values.length; i++) 
				{
					for(int j = 0; j < values[0].length;j++)
					{
						int value2 = values[i][j];
						for(int otherRow = i + 1; otherRow < values.length; otherRow++)
						{
							int otherValue2 = values[otherRow][j];
								if(value2 != 0 && otherValue2 != 0 && value2 == otherValue2)
								{
									return false;
								}
							
						}
					}
				}
				return true;
	}
	public boolean blockIsLegal()
	{
		// Check every block. If you find an illegal block, return false.
				for(int k = 0; k < values.length; k +=3)
				{
					for(int l = 0; l < values[k].length; l+=3)
					{
						HashSet<Integer> block = new HashSet<>();
						for(int z = k; z < k+3; z++)
						{
							for(int x = l; x < (l+3); x++)
							{
								if(values[z][x] != 0 && block.contains(values[z][x]))
								{
									return false;
								}
								block.add(values[z][x]);
							}
						}
					}
				}
				return true;

	}
	public boolean isLegal()
	{
		// All rows/cols/blocks are legal.
		if(rowIsLegal() && columnIsLegal() && blockIsLegal())
		{
				return true;
		}
		else
		{
			return false;
		}
	}

	
	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		boolean isFull = true;
		for(int i = 0; i < values.length; i++)
		{
			for(int j = 0; j < values[0].length; j++)
			{
				int digit = values[i][j];
				if(digit < 1 || digit > 9 && digit != 0)
				{
					isFull = false;
				}
			}
		}
		return isFull;
	}
	

	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		boolean equals = true;
		Grid that = (Grid) x;
		for(int i = 0; i < this.values.length;i++)
		{
			for(int j = 0; j < this.values[0].length; j++)
			{
				if(that.values[i][j] != this.values[i][j])
				{
					equals = false;
				}
			}
		}
		return equals;
	}
}
