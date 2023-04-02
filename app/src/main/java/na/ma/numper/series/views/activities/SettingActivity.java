package na.ma.numper.series.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import na.ma.numper.series.R;
import na.ma.numper.series.databinding.ActivitySettingBinding;
import na.ma.numper.series.storag.db.myDatabase;
import na.ma.numper.series.storag.sharedprefrece.UserPreference;

public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding binding;
    private UserPreference userPreference;
    private myDatabase dp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate( getLayoutInflater() );
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        setTitle(" ");


        userPreference = new UserPreference( this );
        dp = new myDatabase(this);

        binding.settingBtnShowAllGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( SettingActivity.this , ShowAllGamesActivity.class );
                startActivity( intent );
            }
        });

        binding.settingBtnShowLastGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(SettingActivity.this, dp.getGameLastDate(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.settingBtnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext() , ChangePasswordActivity.class);
                startActivity(intent);

            }
        });

        binding.settingBtnClearAllGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp.deleteGame();
                Toast.makeText(SettingActivity.this, getResources().getString(R.string.toast_clear_game), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, getResources().getString(R.string.toast_in_it), Toast.LENGTH_SHORT).show();
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
}