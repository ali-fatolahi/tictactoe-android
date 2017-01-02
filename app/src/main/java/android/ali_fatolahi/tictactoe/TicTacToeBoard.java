package android.ali_fatolahi.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.ali_fatolahi.tictactoe.Symbol.AVAILABLE;
import static android.ali_fatolahi.tictactoe.Symbol.COMPUTER;
import static android.ali_fatolahi.tictactoe.Symbol.HUMAN;

public class TicTacToeBoard {
	private byte size=3;	
	private Cell[][] cells=new Cell[size][size];
	private Random random=new Random();

	public TicTacToeBoard() {
		for (int i=0;i<size;++i)
			for (int j=0;j<size;++j) {
				cells[i][j]=new Cell();
			}
	}

	private TicTacToeBoard(byte n) {
		size=n;
		cells=new Cell[size][size];
		for (int i=0;i<size;++i)
			for (int j=0;j<size;++j) {
				cells[i][j]=new Cell();
			}
	}

	public void setSize(byte n) {
		size=n;
		cells=new Cell[size][size];
		for (int i=0;i<size;++i)
			for (int j=0;j<size;++j) {
				cells[i][j]=new Cell();
			}
	}

	public byte getSize() {
		return size;
	}

	public Cell getCell(int row, int col) {
		return cells[row][col];
	}

	public Cell getCell(int index) {
		int row=index/size;
		int col=index%size;
		return cells[row][col];
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (Cell[] cellRow:cells) {
			for (Cell cell:cellRow) {
				sb.append(cell.getSymbol());
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public boolean isWinner(char player) { // either X or O, e.g X
		StringBuilder sb=new StringBuilder("");
		for (int i=0;i<size;++i)
			sb.append(player);
		String strPlayer=sb.toString();
		
		String diagonal1="";
		String diagonal2="";

		for (int i=0;i<size;++i) {
			String row="";
			for (int j=0;j<size;++j) // e.g. i=1
				row+=cells[i][j].getSymbol();
			if (row.compareTo(strPlayer)==0) // if (col==strPlayer)
				return true;

			String column="";
			for (int j=0;j<size;++j)
				column+=cells[j][i].getSymbol();
			if (column.compareTo(strPlayer)==0)
				return true;

			diagonal1+=cells[i][i].getSymbol();
			diagonal2+=cells[i][size-1-i].getSymbol();
		}

		if (diagonal1.compareTo(strPlayer)==0)
			return true;

		if (diagonal2.compareTo(strPlayer)==0)
			return true;

		return false;
	}

	public int getComputerNextDummyMove() {
		List<Integer> emptyCells=new ArrayList<Integer>();

		for (int i=0;i<cells.length;++i)
			for (int j=0;j<cells[i].length;++j)
				if (cells[i][j].getSymbol() == AVAILABLE)
					emptyCells.add(i*size+j);

		int n=emptyCells.size();
		if (n<=0)
			return -1;

		int nextIndex=random.nextInt(n);

		if (nextIndex<0)
			return -1;

		return emptyCells.get(nextIndex);
	}

	public int getComputerNextGoodMove() {
		int[] directions=new int[size+size+2];
		for (int i=0;i<directions.length;++i)
			directions[i]=0;

		for (int i=0;i<size;++i) {
			for (int j=0;j<size;++j) {
				if (cells[i][j].getSymbol() == COMPUTER) {
					if (directions[i]>=0)
						++directions[i];
					else
						directions[i]=-128;
				} else if (cells[i][j].getSymbol() == HUMAN) {
					if (directions[i]<=0 && directions[i]!=-128)
						--directions[i];
					else
						directions[i]=-128;
				}

				if (cells[j][i].getSymbol() == COMPUTER) {
					if (directions[i+size]>=0)
						++directions[i+size];
					else
						directions[i+size]=-128;
				} else if (cells[j][i].getSymbol() == HUMAN) {
					if (directions[i+size]<=0 && directions[i+3]!=-128)
						--directions[i+size];
					else
						directions[i+size]=-128;
				}
			}
			if (cells[i][i].getSymbol() == COMPUTER) {
				//alert(directions[6]);
				if (directions[size+size]>=0)
					++directions[size+size];
				else
					directions[size+size]=-128;
			} else if (cells[i][i].getSymbol() == HUMAN) {
				if (directions[size+size]<=0 && directions[size+size]!=-128)
					--directions[size+size];
				else
					directions[size+size]=-128;
			}

			if (cells[i][size-1-i].getSymbol() == COMPUTER) {
				if (directions[size+size+1]>=0)
					++directions[size+size+1];
				else
					directions[size+size+1]=-128;
			} else if (cells[i][size-1-i].getSymbol() == HUMAN) {
				if (directions[size+size+1]<=0 && directions[size+size+1]!=-128)
					--directions[size+size+1];
				else
					directions[size+size+1]=-128;
			}
		}

		//for (i=0;i<directions.length;i++)
		//	alert(i+":"+directions[i]);

		int computerWin=-1;
		int humanWin=-1;
		int computerWeakWin=-1;
		int humanWeakWin=-1;

		for (int i=0;i<directions.length;++i) {
			if (directions[i]==2)
				computerWin=i;
			if (directions[i]==-2)
				humanWin=i;
			if (directions[i]==1)
				computerWeakWin=i;
			if (directions[i]==-1)
				humanWeakWin=i;
		}

		//alert("compuetrWin:"+computerWin);
		///alert("compuetrWeakWin:"+computerWeakWin);
		//alert("humanWin:"+humanWin);
		//alert("humanWeakWin:"+humanWeakWin);
		List<Integer> goodCells=new ArrayList<Integer>();
		if (computerWin!=-1) {
			//alert("Win in direction "+computerWin);
			if (computerWin<size) {
				for (int i=0;i<size;++i)
					if (cells[computerWin][i].getSymbol() == AVAILABLE)
						goodCells.add(computerWin*size+i);
				//return computerWin*3+i;
			} else if (computerWin<size+size) {
				for (int i=0;i<size;++i)
					if (cells[i][computerWin-size].getSymbol() == AVAILABLE)
						goodCells.add(computerWin-size+i*size);
				//return computerWin-3+i*3;
			} else if (computerWin==size+size) {
				for (int i=0;i<size;++i)
					if (cells[i][i].getSymbol() == AVAILABLE)
						goodCells.add(i+i*size);
				//return i+i*3;
			} else {
				for (int i=0;i<size;++i)
					if (cells[i][size-1-i].getSymbol() == AVAILABLE)
						goodCells.add(size-1-i+i*size);
				//return 2-i+i*3;
			}
		} else if (humanWin!=-1) {
			//alert(humanWin);
			if (humanWin<size) {
				for (int i=0;i<size;++i)
					if (cells[humanWin][i].getSymbol() == AVAILABLE)
						goodCells.add(humanWin*size+i);
				//return humanWin*3+i;
			} else if (humanWin<size+size) {
				for (int i=0;i<size;++i)
					if (cells[i][humanWin-size].getSymbol() == AVAILABLE)
						goodCells.add(humanWin-size+i*size);
				//return humanWin-3+i*3;
			} else if (humanWin==size+size) {
				for (int i=0;i<size;++i)
					if (cells[i][i].getSymbol() == AVAILABLE)
						goodCells.add(i+i*size);
				//return i+i*3;
			} else {
				for (int i=0;i<size;++i)
					if (cells[i][size-1-i].getSymbol() == AVAILABLE)
						goodCells.add(size-1-i+i*size);
				//return 2-i+i*3;
			}
		} else if (computerWeakWin!=-1) {
			if (computerWeakWin<size) {
				for (int i=0;i<size;++i)
					if (cells[computerWeakWin][i].getSymbol() == AVAILABLE)
						goodCells.add(computerWeakWin*size+i);
				//return computerWeakWin*3+i;
			} else if (computerWeakWin<size+size) {
				for (int i=0;i<size;++i)
					if (cells[i][computerWeakWin-size].getSymbol() == AVAILABLE)
						goodCells.add(computerWeakWin-size+i*size);
				//return computerWeakWin-3+i*3;
			} else if (computerWeakWin==size+size) {
				for (int i=0;i<size;++i)
					if (cells[i][i].getSymbol() == AVAILABLE)
						goodCells.add(i+i*size);
				//return i+i*3;
			} else {
				for (int i=0;i<size;++i)
					if (cells[i][size-1-i].getSymbol() == AVAILABLE)
						goodCells.add(size-1-i+i*size);
				//return 2-i+i*3;
			}
		} else if (humanWeakWin!=-1) {
			if (humanWeakWin<size) {
				for (int i=0;i<size;++i)
					if (cells[humanWeakWin][i].getSymbol() == AVAILABLE)
						goodCells.add(humanWeakWin*size+i);
				//return humanWeakWin*3+i;
			} else if (humanWeakWin<size+size) {
				for (int i=0;i<size;++i)
					if (cells[i][humanWeakWin-size].getSymbol() == AVAILABLE)
						goodCells.add(humanWeakWin-size+i*size);
				//return humanWeakWin-3+i*3;
			} else if (humanWeakWin==size+size) {
				for (int i=0;i<size;++i)
					if (cells[i][i].getSymbol() == AVAILABLE)
						goodCells.add(i+i*size);
				//return i+i*3;
			} else {
				for (int i=0;i<size;++i)
					if (cells[i][size-1-i].getSymbol() == AVAILABLE)
						goodCells.add(size-1-i+i*size);
				//return 2-i+i*3;
			}
		}

		int n=goodCells.size();
		if (n<=0)
			return -1;

		int index=random.nextInt(n);
		return goodCells.get(index);
	}

	public int getComputerNextBestMove() {
		return -1;
	}
}

