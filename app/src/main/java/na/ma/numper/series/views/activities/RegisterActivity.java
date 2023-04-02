package na.ma.numper.series.views.activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import na.ma.numper.series.R;
import na.ma.numper.series.databinding.ActivityRegisterBinding;
import na.ma.numper.series.model.PersonsDetails;
import na.ma.numper.series.storag.sharedprefrece.UserPreference;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private UserPreference userPreference;
    PersonsDetails personsDetails ;

    String image ;
    String full_name;
    String email;
    String d_of_b;
    int age ;
    String user_name;
    String password;
    String re_password;

    public final String USER_NAME_KEY = "user_name";
    public final String PASSWORD_KEY = "password";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userPreference = new UserPreference(this );


        //الآن بدي لما المستخدم يضغط عإضافة صورة يروح عالمعارض المتاحة ويختار المستخدم صورة وأرجعله اياها في التطبيق
        ActivityResultLauncher<String> gallery = registerForActivityResult
                (new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    //ال GetContent تستخدم لأجيب الملفات الصور الفيديوهات يلي عالجهاز
                            @Override
                            public void onActivityResult(Uri result) {
                                binding.regAddPicture.setImageURI(result);
                                 image = result.toString();// صح

                                //Uri uri = Uri.parse(image);
                               // personsDetails.setImage(image);
                            }
                        }
                );

        binding.regAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gallery.launch("image/*");
            }
        });


        binding.regDbDateBirth.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

                                binding.regDbDateBirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                //الآن بدي أجيب العمر طبعًا كيف .. السنة الحالية - سنة الميلاد
                                age = now.get(Calendar.YEAR) - year; // هاد بشكل مبدأي لحد م أخزنه في المتغير تاع صفحة اللعبة
                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Initial day selection
                );
                dpd.show(getSupportFragmentManager(), "Datepickerdialog");
                //غيّرت لون الكاليندر ، بعد محاولات ^_^ yessss!
                dpd.setAccentColor(R.color.colorPrimary);
                dpd.setTitle(getResources().getString(R.string.title_datePicker));
            }
        });



        binding.regBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Variable المتغيرات

                full_name = binding.regEtFullName.getText().toString();
                email     = binding.regEtEmailAddress.getText().toString();
                d_of_b    = binding.regDbDateBirth.getText().toString();
                user_name = binding.regEtUserName.getText().toString();
                password  = binding.regEtPassword.getText().toString();
                re_password = binding.regEtRePassword.getText().toString();

                // Checked
                if (image!=null&&!image.isEmpty() && full_name!=null&&!full_name.isEmpty() && email!=null&&!email.isEmpty() && d_of_b!=null&&!d_of_b.isEmpty() && user_name!=null&&!user_name.isEmpty() && password!=null&&!password.isEmpty() && re_password!=null&&!re_password.isEmpty()&&re_password.equalsIgnoreCase(password))
                {
                    // بعد التحقق من إدخال المستخدم للبيانات يتم تخزين البيانات في المودل ثم تنفيذ دالة الريجيستر
                  personsDetails = new PersonsDetails(image ,full_name , email , user_name , password ,age ); // يتم تمرير البيانات كباراميترات باستخدام الكونستركتور
                  register(personsDetails);
                }
                else if (image==null || (image!=null&&image.isEmpty())){
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.toast_please_add_picture), Toast.LENGTH_SHORT).show();
                }
                else if (full_name==null ||(full_name!=null&&full_name.isEmpty())){
                    binding.regEtFullName.setError(getResources().getString(R.string.toast_please_enter_fullName));
                }
                else if (email==null ||(email!=null&&email.isEmpty())){
                    binding.regEtEmailAddress.setError(getResources().getString(R.string.toast_please_enter_email));
                }
                else if (d_of_b==null ||(d_of_b!=null&&d_of_b.isEmpty())){
                    binding.regDbDateBirth.setError(getResources().getString(R.string.toast_please_enter_DofB));
                }
                else if (user_name==null ||(user_name!=null&&user_name.isEmpty())){
                    binding.regEtUserName.setError(getResources().getString(R.string.toast_please_enter_userName));
                }
                else if (password==null ||(password!=null&&password.isEmpty())){
                    binding.regEtPassword.setError(getResources().getString(R.string.toast_please_enter_password));
                }
                else if (re_password==null ||(re_password!=null&&re_password.isEmpty()))
                {
                    binding.regEtRePassword.setError(getResources().getString(R.string.toast_please_confirm_password));
                }
                else if ((re_password!=null && !re_password.isEmpty() )&& (password!=null&&!password.isEmpty())){
                    if (!re_password.equalsIgnoreCase(password))
                    {
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.toast_not_match), Toast.LENGTH_SHORT).show();
                    }
                }

                else Toast.makeText(RegisterActivity.this, getResources().getString(R.string.toast_please_enter_full_data), Toast.LENGTH_SHORT).show();


            }
        });
    }


    private void register( PersonsDetails personsDetails){
        userPreference.setPersonData(personsDetails);
       Intent intent = new Intent(); // انتنت فارغ لأعبي البيانات وهناك أستقبلهن في آكتيفتي ريزلت لآنشر
       intent.putExtra(USER_NAME_KEY,userPreference.getPersonData().getUser_name());
       intent.putExtra(PASSWORD_KEY,userPreference.getPersonData().getPassword());
       setResult(RESULT_OK,intent);
       finish();
    }
}