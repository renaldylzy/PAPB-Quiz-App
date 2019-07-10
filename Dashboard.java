package renaldy.com.pancasilaquiz;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{

    ImageView imgLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imgLogout = (ImageView) findViewById(R.id.img_logout);
        imgLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_logout:
                Intent login = new Intent(Dashboard.this, Login.class);
                startActivity(login);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //nothing
    }
}
