package android.ali_fatolahi.tictactoe;

public class Cell
{
	private char content='?';

	public static final char HUMAN='X';
	public static final char COMPUTER='O';
	public static final char AVAILABLE='?';

	public Cell() {
		content=AVAILABLE;
	}

	public void setContent(char setContent) {
		content=setContent;
	}

	public char getContent() {
		return content;
	}
}
