package renaldy.com.pancasilaquiz;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Signup extends AppCompatActivity implements View.OnClickListener {


    Button btnBackLogin, btnSignupNow;
    EditText etUsername, etPassword;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnBackLogin = (Button) findViewById(R.id.btnBackLogin);
        btnBackLogin.setOnClickListener(this);
        btnSignupNow = (Button) findViewById(R.id.btnSignupNow);
        btnSignupNow.setOnClickListener(this);

        connectionClass = new ConnectionClass();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackLogin:
                Intent login = new Intent(Signup.this, Login.class);
                startActivity(login);
                break;
            case R.id.btnSignupNow:
                Doregister doRegister = new Doregister();
                doRegister.execute("");
                break;
            default:
                break;
        }
    }

    public class Doregister extends AsyncTask<String,String,String> {


        String namestr = etUsername.getText().toString();
        String passstr = etPassword.getText().toString();
        String z="";
        boolean isSuccess=false;



        @Override
        protected String doInBackground(String... params) {

            if(namestr.trim().equals("")|| passstr.trim().equals(""))
                z = "Please enter all fields....";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query="insert into quiz values('"+namestr+"','"+passstr+"')";

                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);

                        z = "Register successfull";
                        isSuccess=true;


                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {
                startActivity(new Intent(Signup.this,Dashboard.class));

            }


        }
    }
}

