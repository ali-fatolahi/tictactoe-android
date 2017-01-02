package android.ali_fatolahi.tictactoe;

import android.app.*;
import android.os.*;
import android.text.method.LinkMovementMethod;
import android.view.*;
import android.widget.*;
import android.view.View.OnClickListener;
import android.content.DialogInterface;

import static android.ali_fatolahi.tictactoe.Symbol.AVAILABLE;
import static android.ali_fatolahi.tictactoe.Symbol.COMPUTER;
import static android.ali_fatolahi.tictactoe.Symbol.HUMAN;

public class MainActivity extends Activity implements OnClickListener
{
    private CellView[] playCells=new CellView[9];
	private TicTacToeBoard theBoard=new TicTacToeBoard();
	private Button resetButton,quitButton;
	private CheckBox noviceCheck;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		int i=0;
		for (int id:CellView.CELL_IDS) {
			playCells[i]=new CellView(this, (TextView)findViewById(id), theBoard.getCell(i));
			i++;
		}
		
		resetButton=(Button)findViewById(R.id.resetButton);
		resetButton.setOnClickListener(this);
		
		quitButton=(Button)findViewById(R.id.quitButton);
		quitButton.setOnClickListener(this);
		
		noviceCheck=(CheckBox)findViewById(R.id.levelCheck);

        final TextView webSite = (TextView)findViewById(R.id.webSite);
        webSite.setMovementMethod(LinkMovementMethod.getInstance());
    }

	public void play() {
		if (theBoard.isWinner(HUMAN.getContent()))
			showMessage("you won!");
		else {
			int index=-1;
			if (noviceCheck.isChecked())
				index=theBoard.getComputerNextDummyMove();
			else
				index=theBoard.getComputerNextGoodMove();
			if (index==-1) {
				showMessage("draw!");
			} else {
				playCells[index].setContent(COMPUTER);
				if (theBoard.isWinner(COMPUTER.getContent()))
					showMessage("you lose!");
			}
		}
	}
	
	private void showMessage(String message)  {
		AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("hey ...");
		alertDialog.setMessage(message);
		alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface di, int id) {
				for (int i=0;i<9;++i)
					playCells[i].setContent(AVAILABLE);
			}
		});
		alertDialog.show();
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.quitButton:
				finish();
			break;
			case R.id.resetButton:
				for (int i=0;i<9;++i)
					playCells[i].setContent(AVAILABLE);
			break;
		}
	}

	@Override
	public boolean onKeyDown(int id, KeyEvent keyEvent) {
		if (id==keyEvent.KEYCODE_BACK) {
			finish();
		}

		return false;
	}
}
