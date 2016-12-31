package android.ali_fatolahi.tictactoe;

import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class CellView implements OnClickListener
{	
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
	
	public void setContent(String newContent) {
		textView.setText(newContent);
		cellData.setContent(newContent.charAt(0));
	}
	
	//@Override
	public void onClick(View v) {
	//	TextView tx=(TextView)v;

		if (textView.getText().equals(host.getString(R.string.available))) {
			textView.setText(R.string.the_player);
			cellData.setContent(host.getString(R.string.the_player).charAt(0));
		}
		
		host.play();
	}
}
