package na.ma.numper.series.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import na.ma.numper.series.R;
import na.ma.numper.series.databinding.ActivityChangePasswordBinding;
import na.ma.numper.series.databinding.ActivityShowAllGamesBinding;
import na.ma.numper.series.model.PersonsDetails;
import na.ma.numper.series.storag.sharedprefrece.UserPreference;

public class ChangePasswordActivity extends AppCompatActivity {
   ActivityChangePasswordBinding binding;
   UserPreference userPreference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        setTitle(" ");

        userPreference = new UserPreference(this);

        binding.btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_password = binding.etChangePassword.getText().toString();
                userPreference.setNewPassword(new_password);
                Toast.makeText(ChangePasswordActivity.this, getResources().getString(R.string.toast_change_password), Toast.LENGTH_SHORT).show();

                finish();
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
}
