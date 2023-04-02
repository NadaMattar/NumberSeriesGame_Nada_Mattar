package na.ma.numper.series.views.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import na.ma.numper.series.R;
import na.ma.numper.series.databinding.ActivityLoginBinding;
import na.ma.numper.series.model.PersonsDetails;
import na.ma.numper.series.storag.sharedprefrece.UserPreference;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserPreference userPreference;

    String login_user_name ;
    String login_password;
    Boolean login_remember_me;

    // هان المفاتيح
    public final String USER_NAME_KEY = "user_name";
    public final String PASSWORD_KEY = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userPreference = new UserPreference( this ); // عملت مؤشر على كلاس الشيرد ، عملت أوبجيكت وأعطيته الكونتكست تاع الشاشة


        ActivityResultLauncher<Intent> from_register = registerForActivityResult // عند العودة من شاشة الريجستر ، طبعًا النوع انتنت لأنه من عملية انتقال وفيها داتا حاملة بيانات ، وكمان النوع بيكون متل النوع يلي حطيته في زر الريجستر تحت
                (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    // SAFR الاختصار طبعًا هادا ، النوعية  يلي  بنحطها هان ببين شو هيَّ العملية سواء اكسبلست ولا امبلست ولا
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        binding.loginEtUserName.setText(result.getData().getStringExtra(USER_NAME_KEY)); // طبعًا هان قرأت البيانات يلي رجعت من الداتا تاعت الانتنت يلي من الريجستر (يلي راجعة في ال result ) ,وحطيتها في اليوسر نيم
                        binding.loginEtPassword.setText(result.getData().getStringExtra(PASSWORD_KEY)); // ~ ~ ~ ~

                        binding.logBtnLogin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                login_user_name = binding.loginEtUserName.getText().toString(); // هان قرأت النصوص يلي الايديت
                                login_password = binding.loginEtPassword.getText().toString();//~ ~ ~ ~
                                login_remember_me = binding.logCbRememberMe.isChecked();// هان شفت شو قيمة البوليين يلي رجعت ، اذا عامل تذكرني ولا لء

                                if (login_user_name!=null && !login_user_name.isEmpty() && login_password!=null && !login_password.isEmpty()){ // هان بفحص اذا كل الصناديق مش فارغة ومش نل ،(لأنه بفرق )
                                    login(login_user_name,login_password,login_remember_me); // اذن حنادي الدالة وأعطيها ال 3 بيانات وهي تشوف شغلها
                                }
                                else if (login_user_name==null || login_user_name.isEmpty()){ // اذا اسم المستخدم نل أو فارغ ، مارح يفوت وحأعرضله أحمر انه دخل الاسم ، وطبعًا ما تتم عملية الانتقال
                                    binding.loginEtUserName.setError(getResources().getString(R.string.toast_please_enter_userName));
                                }
                                else if (login_password==null || login_password.isEmpty()){ // ~~~ مثل ما عملت باسم المستخدم
                                    binding.loginEtPassword.setError(getResources().getString(R.string.toast_please_enter_password));
                                }
                            }
                        });
                    }
                });

        binding.logBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this , RegisterActivity.class); // لما أضغط هان حينقلني عالريجستر
                from_register.launch(intent);
            }
        });


        binding.logBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // تكرار لحكي فوق
               login_user_name = binding.loginEtUserName.getText().toString();
               login_password = binding.loginEtPassword.getText().toString();
               login_remember_me = binding.logCbRememberMe.isChecked();

                if (login_user_name!=null && !login_user_name.isEmpty() && login_password!=null && !login_password.isEmpty()){
                    login(login_user_name,login_password,login_remember_me);
                }
                else if (login_user_name==null || login_user_name.isEmpty()){
                    binding.loginEtUserName.setError(getResources().getString(R.string.toast_please_enter_userName));
                }
                else if (login_password==null || login_password.isEmpty()){
                    binding.loginEtPassword.setError(getResources().getString(R.string.toast_please_enter_password));
                }
            }

        });
    }

    private void login( String name , String password , Boolean rememberMe){ // الآن هادي الدالة بتاخد 3 مدخلات 2 نصية و 1 منطقية
        if (name.equals(userPreference.getPersonData().getUser_name()) //  هان عملية فحص ااذ اسم المستخدم  بيساوي اسم المستخدم يلي في الشيرد وكمان اذا الباسوورد بتساوي الباسوورد يلي هناك ولهاد المستخدم
                && password.equals(userPreference.getPersonData().getPassword())){

            if (rememberMe) // هان حيفحص اذا حاطط تذكّرني
            {
                userPreference.setLogin(); // يبقى احفظ عملية تسجيل الدخول
            }
            Intent intent1 = new Intent(this , MainActivity.class ); // انتقل للعبة  ،(طبعًا في الحالتين رح ينتقل سواء حط تذكّرني ولا ما حط مادام البيانات سليمة )
            startActivity(intent1);
            finish(); // وسكّرلي الشاشة
        }
        else
        {
            // اذا لء ، واللهِ راجعلي بياناتك اذا سليمة أو اعملّك حساب ، لأنه ما رح يكونله
            Toast.makeText(this, getResources().getString(R.string.toast_pleas_check_data), Toast.LENGTH_SHORT).show();
        }
    }
}