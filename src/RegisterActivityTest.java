import static org.junit.Assert.*;

import main.java.edu.gatech.DBHandler;
import main.java.edu.gatech.RegisterActivity;

import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity> {

	private Activity mActivity;
	private Button registerButton;
	private EditText mUserName, mPassword, mEmail;
	private DBHandler database;
	private String accounts;
	public RegisterActivityTest() {
		super(RegisterActivity.class); 
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	    setActivityInitialTouchMode(false);
		mActivity = getActivity();
		database = new DBHandler(mActivity);
		registerButton = (Button) mActivity.findViewById(main.java.edu.gatech.R.id.button_return);
		mUserName = (EditText) mActivity.findViewById(main.java.edu.gatech.R.id.field_username);
		mPassword = (EditText) mActivity.findViewById(main.java.edu.gatech.R.id.field_password);
		mEmail = (EditText) mActivity.findViewById(main.java.edu.gatech.R.id.field_email);
	}

	@Test
	public void testAttemptReg() {
		mActivity.runOnUiThread(new Runnable() {
		      public void run() {
		    	  mUserName.setText("Account 1");
				  mPassword.setText("1234");
				  mEmail.setText("Account@account.com");
		          registerButton.performClick();
		          accounts = database.getAllUsers();
		        }
		      });
		getInstrumentation().waitForIdleSync();
		mActivity.runOnUiThread(new Runnable() {
		      public void run() {
		    	  mUserName.setText("Account 2");
				  mPassword.setText("1");
				  mEmail.setText("AccountWhat@account.com");
		          registerButton.performClick();
		          accounts = database.getAllUsers();
		        }
		      });
		getInstrumentation().waitForIdleSync();
		mActivity.runOnUiThread(new Runnable() {
		      public void run() {
		    	  mUserName.setText("Account 3");
		    	  mPassword.setText("");
				  mEmail.setText("Account3@account.com");
		          registerButton.performClick();
		          accounts = database.getAllUsers();
		        }
		      });
		getInstrumentation().waitForIdleSync();
		mActivity.runOnUiThread(new Runnable() {
		      public void run() {
		    	  mUserName.setText("Account 4");
				  mPassword.setText("1234");
				  mEmail.setText("");
		          registerButton.performClick();
		          accounts = database.getAllUsers();
		        }
		      });
		getInstrumentation().waitForIdleSync();
		mActivity.runOnUiThread(new Runnable() {
		      public void run() {
		    	  mUserName.setText("Account 5");
				  mPassword.setText("1234");
				  mEmail.setText("Account5account.com");
		          registerButton.performClick();
		          accounts = database.getAllUsers();
		        }
		      });
		getInstrumentation().waitForIdleSync();
		mActivity.runOnUiThread(new Runnable() {
		      public void run() {
		    	  mUserName.setText("");
				  mPassword.setText("1234");
				  mEmail.setText("Account6@account.com");
		          registerButton.performClick();
		          accounts = database.getAllUsers();
		        }
		      });
		getInstrumentation().waitForIdleSync();
		assertTrue(accounts.equalsIgnoreCase("Account@account.com\n"));
	}

}
