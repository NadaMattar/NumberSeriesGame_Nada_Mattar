package na.ma.numper.series.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import na.ma.numper.series.R;
import na.ma.numper.series.storag.sharedprefrece.UserPreference;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    private UserPreference userPreference; //  عملت مؤشر على كلاس الشيرد

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        userPreference = new UserPreference( this ); // فعليًا هان صار أوبجيكت ممن الشيرد

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userPreference.isLogin()){//   الآن هان بحكيله ، اذا دالة is login يلي في كلاس اليوسر بريفرنسر بترجع تروو ، خلص فوتلي عاللعبة ، يعني معناته مسيّف عملية التسجيل
                    Intent intent = new Intent(SplashScreenActivity.this , MainActivity.class); // هان حيفوت اللعبة دغري
                    startActivity(intent);
                    finish(); // وحسكّر السبلاش
                }
                else if (userPreference.logout()){ // هادي الدالة ازا بترجّع تروو ، معناته عملية التسجيل غير محفوظة وحوديه عاللوقين ليعمل تسجيل دخول
                    Intent intent = new Intent(SplashScreenActivity.this , LoginActivity.class);
                    startActivity(intent);
                    finish(); // طبعًا حسكر السبلاش ، ما بتلزمني
                }
                else {
                    Intent intent = new Intent(SplashScreenActivity.this , LoginActivity.class); // غير هيك معناته روح عاللوقين لأنه ما بكون عامل تذكّرني
                    startActivity(intent);
                    finish();
                }
            }
        } , 2000); // مقدار التأخير لحتى ينفّذ الأكواد يلي قبل
    }
}