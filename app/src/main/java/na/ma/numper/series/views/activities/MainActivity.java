package na.ma.numper.series.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import na.ma.numper.series.databinding.ActivityMainBinding;


import na.ma.numper.series.R;
import na.ma.numper.series.model.PersonsDetails;
import na.ma.numper.series.storag.db.myDatabase;
import na.ma.numper.series.storag.sharedprefrece.UserPreference;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserPreference userPreference;

    int HiddenNumber ;
    int score = 0 ;
    int new_score ;

    View bravo;
    View error;

    MediaPlayer bravo_sound;
    MediaPlayer error_sound;

    myDatabase dp ;
    PersonsDetails personsDetails ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        setTitle(" ");

        userPreference = new UserPreference( this );
        personsDetails = new PersonsDetails();
        dp = new myDatabase(this);

        //توست الاجابة الصحيحة
        // الآن تحويل أوبجيكت التصميم من xml إلى أوبجيكت جافا لأتعامل معاه
        bravo = LayoutInflater.from(this).inflate(R.layout.activity_custom_toast_bravo , null ,false);
        // الآن التصميم كله رجع في ال bravo
        // الآن بدي أجيب كل عنصر من البرافو وأعمله انفلات
        TextView tv_bravo = bravo.findViewById(R.id.toast_message_bravo);
        GifImageView image_bravo = bravo.findViewById(R.id.bravooo);
        //الآن دعمل أوبجيكت من كلاس التوست وبنعطيها الكونتكست ، ما بستدعي الجاهز
        Toast b = new Toast(this);
        // الآن دعطيه الفيو تاعي يلي هو برافو
        b.setView(bravo);
        // الآن دحدد مدّته
        b.setDuration(Toast.LENGTH_SHORT);

        //الآن دعمل أوبجيكت من كلاس الميديا لأنشأ الملف يلي حيجيب صوت الفوز
        bravo_sound = MediaPlayer.create(this , R.raw.bravo);


        // الآن دعمل توست الإجابة الخاطئة
        error = LayoutInflater.from(this).inflate(R.layout.activity_custom_toast_error , null , false);
        TextView tv_error = error.findViewById(R.id.toast_message_error);
        GifImageView image_error = error.findViewById(R.id.error);
        Toast e = new Toast(this);
        e.setView(error);
        e.setDuration(Toast.LENGTH_SHORT);

        //الآن دعمل أوبجيكت من كلاس الميديا لأنشأ الملف يلي حيجيب صوت الخسارة
        error_sound = MediaPlayer.create(this , R.raw.error);




        //لعرض الأرقام في اللعبة أول ما تفتح الشاشة
        showGame();
        binding.gameTvScore.setText(String.valueOf(score)); // وهان حطيت في السكور القيمة الأولية يلي هي 0
        binding.gameTvFullName.setText(userPreference.getPersonData().getFull_name());
        binding.gameTvAge.setText(String.valueOf(userPreference.getPersonData().getAge()));

        binding.gameBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // هان تتم عملية الفحص ، هل الرقم يلي دخّله المستخدم = الرقم المخفي ؟!

                binding.gameEtEnterNumber.setEnabled(true);

                if (binding.gameEtEnterNumber.getText().toString().equals(String.valueOf(HiddenNumber)) )
                {
                    b.show(); // لعرض توست الاجابة الصحيحة
                    bravo_sound.start(); // لتشغيل صوت الاجابة صحيحة
                    //زيادة السكور
                    binding.gameTvNumber5.setText(String.valueOf(HiddenNumber)); // وهان حيعرضله الرقم في النُص ليشوفه
                    new_score = score +2; // وهان حيزيد السكور
                    //كود توقيف عملية الإدخال لحتى المستخدم ما يضل يدخل الرقم بعض ما ظهر ويزوّد السكور


                }
                else
                {
                    e.show(); // لعرض توست الاجابة خاطئة
                    error_sound.start();
                    new_score = score - 1 ; // هان حينقص السكور اذا كان الجواب خطأ
                    binding.gameTvNumber5.setText(String.valueOf(HiddenNumber));// وهان حيعرضله الرقم في النُص ليشوفه


                }

                score = new_score;
                binding.gameTvScore.setText(String.valueOf(new_score));
                binding.gameEtEnterNumber.setEnabled(false); // هان وقّفت عملية الادخال ، عشان ما يزيد سكور عكيفه ، بعد ما عرضتله الاجابة الصحيحة
                binding.gameEtEnterNumber.setText(""); // فرّغت الايديت تيكست
                Toast.makeText(MainActivity.this,getResources().getString(R.string.toast_enter_to_new_game), Toast.LENGTH_SHORT).show();

                personsDetails.setFull_name(userPreference.getPersonData().getFull_name()); // طبعًا هان حطيت الاسم يلي من الشيرد
                personsDetails.setScore(score); // وهان حطيت السكور عشان كمان شوية أعطيهن الأوبجيكت للداتا بيز لتحفظهن



            }
        });

      binding.gameBtnNewGame.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              binding.gameEtEnterNumber.setEnabled(true);// هان فعّلت الخاصية من جديد لما يضغط عزر لعبة جديدة ،
              showGame(); // واعرضلي اللعبة وبياناتها وأرقامها من جديد
          }
      });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.game_setting:
                // بدي اياه ينتقللي عشاشة الاعدادات
                Intent go_toSetting = new Intent(getApplicationContext() ,SettingActivity.class);
                startActivity( go_toSetting );
                return true ;

            case R.id.game_logOut:
                userPreference.logout();
                Intent intent_login = new Intent(getApplicationContext() ,LoginActivity.class);
                startActivity(intent_login);
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_logOut), Toast.LENGTH_SHORT).show();
                finish();
                return true;

            case R.id.delete_account:
                userPreference.deleteAccount();
                Intent intent = new Intent(getApplicationContext() ,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_delete_account), Toast.LENGTH_SHORT).show();
                finish();

        }
        return true ;
    }



    public void showGame()
    {
        Question game ;
        game = Function.generateQuestion(); //    الآن هادي  Function.generateQuestion بترجع سؤال ورقم مخفي تمام ، الآن هدول حخزنهن في أوبجيكت من كلاس ال Question يلي اسمه game
        String[][] data= game.getData();// الآن من ال game دجيب مصفوفة الأسئلة ، وأخزنها في مصفوفة ثنائية نوعها String اسمها data
        HiddenNumber = game.getHiddenNumber(); // وكذلك الرقم المخفي دجيبه من ال game  ، ومن ثم دخزنه في متغير نوعه رقم صحيح


        binding.gameTvNumber1.setText(data[0][0]);// الآن في العنصر الأول راح أعرض الرقم يلي جاي من المصفوفة الثنائيّة النصية ويلي الاندكس اله 0,0
        binding.gameTvNumber2.setText(data[0][1]);// ...مثل ما عملت للعنصر الأول بس باختلاف قيمة الإندكس المرجعة عشانها راجعة بالعنصر التاني وهيك للباقي ،حسب ترقيم العنصر بجيب القيمة للاندكس وبحطها فيه
        binding.gameTvNumber3.setText(data[0][2]);

        binding.gameTvNumber4.setText(data[1][0]);
        binding.gameTvNumber5.setText(data[1][1]); // الرقم المخفي
        binding.gameTvNumber6.setText(data[1][2]);

        binding.gameTvNumber7.setText(data[2][0]);
        binding.gameTvNumber8.setText(data[2][1]);
        binding.gameTvNumber9.setText(data[2][2]);
    }


    @Override
    protected void onDestroy() { // لما اليوسر يعمل باك لشاشة اللعبة ، فبتدمّر
        super.onDestroy();
        personsDetails.setCard_timeAndDate(getDate_Time()); // بحفظ التاريخ وقتها
        dp.insertGame(personsDetails); // وهان بضيف عالداتابيز الأوبجيكت يلي فيه تفاصيل اللعبة


    }

    //دالة لحساب التاريخ والوقت الحالي
    public String getDate_Time (){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy  hh:mm", Locale.ENGLISH);
        String last_date = simpleDateFormat.format(date);
        return last_date ; //طبعًا بترجعلي تاريخ الوقت الحالي واليوم الحالي
    }


}