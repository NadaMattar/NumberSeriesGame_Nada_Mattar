package na.ma.numper.series.storag.sharedprefrece;
import android.content.Intent;

import android.content.Context;
import android.content.SharedPreferences;

import na.ma.numper.series.model.PersonsDetails;

public class UserPreference { // هان عملنا كلاس شيرد بريفيرنس لتخزين بيانات مستخدم جديد ولما بدي اياها بآخدها من هان

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor; /// هان في كلاس داخلي اسمه Editor بيتم فعليًا تخزين البيانات فيه والتعديل عليها
    PersonsDetails personsDetails ;

    public final String FULL_NAME_KEY="full_name" ;
    public final String USER_NAME_KEY = "user_name";
    public final String PASSWORD_KEY = "password";
    public final String AGE_KEY = "age";

    public UserPreference(Context context) { // الآن عملنا كونستركتور بيآخذ كونتكست
        this.context = context;
        sharedPreferences = context.getSharedPreferences("com_nadamattar_user_pref" , Context.MODE_PRIVATE);
        //  هان المود برايفت ، يعني ما حدا بيقدر يصل للشيرد وبياناتها من خارج التطبيق ،
        editor = sharedPreferences.edit(); // في الإيديتور بنخزن أو بنعدل عالبيانات يلي هو تخزين جديد
    }

    public void setPersonData(PersonsDetails personsDetails){ // عملنا دالة بتاخد  المديول (البيانات يلي حنخزنها من PersonsDetails)
        // بدي أعطيها أوبجيكت كامل في بياناته لتحفظهن
        editor.putString("image" , personsDetails.getImage());
        editor.putString(FULL_NAME_KEY , personsDetails.getFull_name());
        editor.putString("email" , personsDetails.getEmail());
        editor.putString(USER_NAME_KEY , personsDetails.getUser_name());
        editor.putString(PASSWORD_KEY , personsDetails.getPassword());
        editor.putInt(AGE_KEY,personsDetails.getAge());
        editor.apply(); // هان عملت حفظ ، وعفكرة ال apply متل ال commit ولكن الاختلاف انو الكوميت بتخلي البيرفورمنز تاع الجهاز عالي وبتعلقه فالأفضل أبلآي
    }


    public PersonsDetails getPersonData(){ // هادي الدالة بترجعلي مديول وطبعًا فيها البيانات المخزنة عشان لما أستدعيها ترجعلي البيانات يلي قبل خزنتها
        personsDetails = new PersonsDetails(); // هان عملنا أوبجيكت من كلاس تفاصيل الشخص
        personsDetails.setFull_name(sharedPreferences.getString(FULL_NAME_KEY , "")); // الآن هان حطلي في اسم المستخدم ، النص يلي بعته في الشيرد قبل شوية
        personsDetails.setUser_name(sharedPreferences.getString(USER_NAME_KEY,""));
        personsDetails.setPassword(sharedPreferences.getString(PASSWORD_KEY , ""));
        personsDetails.setAge(sharedPreferences.getInt(AGE_KEY,0));
         // هادي فعليًا عملية قراءة من الشيرد ...sharedPreferences.get
        return personsDetails;
    }

    // هادي دالة لحفظ عملية تسجيل الدخول
    public void setLogin(){
        editor.putBoolean("is_login" , true);
        editor.apply();
    }

    // هادي دالة لما بكون مسجل
    public Boolean isLogin(){
        return sharedPreferences.getBoolean("is_login" , false); // return true لأنه حيكون حافظ البيانات
        //الآن جبت البولين فولز لأنه طبيعي حيكون مسجل فلما أستدعيها بدي اياه ينتقللي عشاشة التانية ، لكن لما تكون القيمة ترو معناته هيك حأحفظ عملية التسجيل
    }

 // public void setLastDate(String date){ // دالة كنت عملاهآ عأساس أحفظ التاريخ الأخير في الشيرد بريفيرنس -_-
 //     editor.putString("last_date" , date);
 //     editor.apply();
 //  }

  // public String getLastDate(){ // وهادي عملتها ليجيب يلي حفظته في الدالة يلي فوق
  //     return sharedPreferences.getString("last_date" , "");
  // }


    public Boolean logout(){ // دالة عملتها عشان لما يعمل تسجيل خروج أستدعيها وأغير حالة دالة ال set login
        editor.putBoolean("is_login" , false);
        editor.apply();
        return true;
    }


    public void deleteAccount(){ //دالة اضافية من عندي لحذف الحساب وبياناته ، طبعًا عدى يلي خزنتهن بالداتا بيز ، عشان قصة الاسم وكل لاعب واسمه شو لاعب ، يعني متل صفحة مشتركة ^_^
        editor.clear();
        editor.apply();
    }


    public void setNewPassword(String new_password){ // دالة بستدعيها وبعطيها الباسوورد لجديدة عشان تغير القديمة وتصير هادي بدالها
        personsDetails = new PersonsDetails(new_password); // هان كونستركتور وأعطيته الكلمة الجديدة
        personsDetails.setPassword(personsDetails.getNew_password()); // هان شو عملت ، حطيت قيمة بدلت القيمة القديمة بالجديدة
        editor.putString(PASSWORD_KEY,personsDetails.getPassword()); // وهان خزّنت القيمة لجديدة ، في الباسوورد ، لأن هيني حاطة المفتاح تاع القديمة ، يعني لتحل محلها
        editor.apply(); // هان عملت سيف
    }
}
