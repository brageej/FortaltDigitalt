package leselyst.fortaltdigitalt.fragments.story;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import leselyst.fortaltdigitalt.R;

public class CustomDialogClass extends Dialog {

  public Activity c;
  public Dialog d;
  public Button continueBtn, beginningBtn;

  public CustomDialogClass(Activity a) {
    super(a);
    // TODO Auto-generated constructor stub
    this.c = a;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.custom_dialog);
    continueBtn = (Button) findViewById(R.id.btn_continue);
    beginningBtn = (Button) findViewById(R.id.btn_beginning);
  }
}