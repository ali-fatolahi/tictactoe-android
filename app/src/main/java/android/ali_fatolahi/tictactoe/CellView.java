package android.ali_fatolahi.tictactoe;

import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;

import static android.ali_fatolahi.tictactoe.Symbol.AVAILABLE;
import static android.ali_fatolahi.tictactoe.Symbol.HUMAN;

public class CellView implements OnClickListener {
	public static final int[] CELL_IDS={
		R.id.playCell1,
		R.id.playCell2,
		R.id.playCell3,
		R.id.playCell4,
		R.id.playCell5,
		R.id.playCell6,
		R.id.playCell7,
		R.id.playCell8,
		R.id.playCell9
	};
	
	private MainActivity host;
	private TextView textView;
	private Cell cellData;
	
	public CellView(MainActivity host, TextView textView, Cell cellData) {
		this.host=host;
		this.textView=textView;
		this.cellData=cellData;
		this.textView.setOnClickListener(this);
	}
	
	public void setContent(final Symbol symbol) {
		textView.setText(symbol.toString());
		cellData.setSymbol(symbol);
	}

	public void onClick(View v) {
		if (textView.getText().charAt(0) == AVAILABLE.getContent()) {
			textView.setText(HUMAN.toString());
			cellData.setSymbol(HUMAN);
		}
		
		host.play();
	}
}
