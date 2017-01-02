package android.ali_fatolahi.tictactoe;

import static android.ali_fatolahi.tictactoe.Symbol.AVAILABLE;

public class Cell {
	private Symbol symbol = AVAILABLE;

	public Cell() {
		symbol = AVAILABLE;
	}

	public void setSymbol(final Symbol symbol) {
		this.symbol = symbol;
	}

	public Symbol getSymbol() {
		return symbol;
	}
}
