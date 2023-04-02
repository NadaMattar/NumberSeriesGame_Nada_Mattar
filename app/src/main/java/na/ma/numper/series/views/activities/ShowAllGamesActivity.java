package na.ma.numper.series.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import na.ma.numper.series.R;
import na.ma.numper.series.adapter.recycler.gamedata.CardGamePersonAdapters;
import na.ma.numper.series.databinding.ActivityShowAllGamesBinding;
import na.ma.numper.series.model.PersonsDetails;
import na.ma.numper.series.storag.db.myDatabase;
import na.ma.numper.series.storag.sharedprefrece.UserPreference;

public class ShowAllGamesActivity extends AppCompatActivity {

    private ActivityShowAllGamesBinding binding;
    private UserPreference userPreference;

    private myDatabase dp ;
    private CardGamePersonAdapters adapters;
    //private ArrayList<PersonsDetails> personsDetailsArrayList =new ArrayList<>(); ;
    private ArrayList<PersonsDetails> personsDetailsArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllGamesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        setTitle(" "); // هان حطيتله عنوان فارغ ليشيل العنوان تاع التطبيق

        userPreference = new UserPreference(this);


        initializeRecycler(); //

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
                startActivity( intent_login );

                Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_logOut), Toast.LENGTH_SHORT).show();
                return true;

            case R.id.delete_account:
                userPreference.deleteAccount();
                Intent intent = new Intent(getApplicationContext() ,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_delete_account), Toast.LENGTH_SHORT).show();

        }
        return true ;
    }


    private void initializeRecycler(){
       //personsDetailsArrayList.clear();
     // personsDetailsArrayList.add(new PersonsDetails("nada majed 1" , 2 , "1:4:50 1/2/2022"));
     // personsDetailsArrayList.add(new PersonsDetails("nada majed 2" , 2 , "1:4:50 1/2/2022"));
     // personsDetailsArrayList.add(new PersonsDetails("nada majed 3" , 2 , "1:4:50 1/2/2022"));
     // personsDetailsArrayList.add(new PersonsDetails("nada majed 4" , 2 , "1:4:50 1/2/2022"));

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL ,true); // هان حددت كيف حيكون شكللها ،حتكون عشكل لينير عامودي
       dp = new myDatabase(this);// الآن عرّفت المؤشر على الداتابيز  وأعطيته الكونتكست
       personsDetailsArrayList = dp.getAllGame(); // الآن جبت البيانات من الدالة يلي في الداتا بيز  بترجّع  مصفوفة فيها أوبجيكتات
        adapters = new CardGamePersonAdapters(personsDetailsArrayList); // الآن عرفت الأدابتر وأعطيته المصفوفة بالبيانات يلي فيها
        binding.rv.setAdapter(adapters); // وهان أعطيت ل الريسايكلر الأدابتر يلي حيقوم بباقي الشغل
        binding.rv.setHasFixedSize(true);// هان يعني طول الريسايكلر فيو ثابت
        binding.rv.setLayoutManager(lm); // وهان اللآوت مانيجر هو المدير يلي بقوم بكل شي بصير بالأدابتر ، يلي عرفته فوق ال lm أعطيته الو

    }
}