package android.ali_fatolahi.tictactoe;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.OnClickListener;
import android.content.DialogInterface;

public class MainActivity extends Activity implements OnClickListener
{
    CellView[] playCells=new CellView[9];
	TicTacToeBoard theBoard=new TicTacToeBoard();
	Button resetButton,quitButton;
	CheckBox noviceCheck;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		int i=0;
		for (int id:CellView.CELL_IDS) {
			//theBoard.getCell(i);
			//showMessage(TicTacToeBoard.TheBoard.getCell(i).getContent()+"");
			playCells[i]=new CellView(this, (TextView)findViewById(id), theBoard.getCell(i));
			i++;
		}
		
		resetButton=(Button)findViewById(R.id.resetButton);
		resetButton.setOnClickListener(this);
		
		quitButton=(Button)findViewById(R.id.quitButton);
		quitButton.setOnClickListener(this);
		
		noviceCheck=(CheckBox)findViewById(R.id.levelCheck);
		/*while (!TicTacToeBoard.TheBoard.isWinner(Cell.COMPUTER) &&
			   !TicTacToeBoard.TheBoard.isWinner(Cell.COMPUTER)) {
				
		}*/
    }
	
	public void play() {
		//showMessage(TicTacToeBoard.TheBoard.isWinner(Cell.HUMAN)+"");
		//showMessage(theBoard.toString());
		if (theBoard.isWinner(Cell.HUMAN))
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
				playCells[index].setContent(getString(R.string.big_ali));
				if (theBoard.isWinner(Cell.COMPUTER))
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
					playCells[i].setContent(getString(R.string.available));
			}
		});
		alertDialog.show();
		/*Toast toast=new Toast(getApplicationContext());
		toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();*/
	}

	//@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.quitButton:
				finish();
			break;
			case R.id.resetButton:
				for (int i=0;i<9;++i)
					playCells[i].setContent(getString(R.string.available));
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
