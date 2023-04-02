package na.ma.numper.series.storag.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import na.ma.numper.series.model.PersonsDetails;
import na.ma.numper.series.storag.sharedprefrece.UserPreference;

public class myDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME ="persons";
    public static final int DB_VERSION = 2 ;

   // private static final String PERSON_TABLE_NAME ="person";
   // private static final String CLN_IMAGE="image";
   // private static final String CLN_FULL_NAME ="full_name";
   // private static final String CLN_EMAIL ="email_address";
   // private static final String CLN_USER_NAME ="user_name";
   // private static final String CLN_PASSWORD ="password";
   // private static final String CLN_AGE ="age";

    private static final String GAME_TABLE_NAME ="game";
    private static final String CLN_GAME_ID ="id";
    private static final String CLN_GAME_FULL_NAME ="game_full_name";
    private static final String CLN_GAME_SCORE ="score";
    private static final String CLN_GAME_TIME_DATE ="time_date";

    //
    //جملة انشاء جدول الأشخاص لأستدعيها بعدين
   // private String CREATE_TABLE_PERSON =
   //         " CREATE TABLE "+ PERSON_TABLE_NAME + "("
   //                 + CLN_IMAGE + " TEXT NOT NULL ,"
   //                 + CLN_FULL_NAME + " TEXT NOT NULL ,"
   //                 + CLN_EMAIL + " TEXT,"
   //                 + CLN_USER_NAME + " TEXT  PRIMARY KEY,"
   //                 + CLN_PASSWORD + " TEXT  NOT NULL, "
   //                 + CLN_AGE + " TEXT NOT NULL "
   //                 + ")";

    // جملة حذف جدول الأشخاص
   // private String DROP_PERSON_TABLE = "DROP TABLE IF EXISTS " + PERSON_TABLE_NAME ;

    //جملة انشاء جدول الألعاب لأستدعيها بعدين
    private String  CREATE_TABLE_GAME =
            " CREATE TABLE "+ GAME_TABLE_NAME + "("
                    + CLN_GAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + CLN_GAME_FULL_NAME + " TEXT NOT NULL ,"
                    + CLN_GAME_SCORE + " INTEGER NOT NULL ,"
                    + CLN_GAME_TIME_DATE + " TEXT "
                    + ")";

    private String DROP_GAMES_TABLE = "DROP TABLE IF EXISTS " + GAME_TABLE_NAME ;






    //كونستركتور
    public myDatabase(Context context) {
        super(context ,DB_NAME ,null ,DB_VERSION);
    }


    //دالة يتم استدعائها فقط عند انشاء قاعدة البيانات مرة واحدة فقط
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(CREATE_TABLE_PERSON);
        sqLiteDatabase.execSQL(CREATE_TABLE_GAME);
    }


    //دالة يتم استدعائها عند كل تحديث على قاعدة البيانات
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      // // sqLiteDatabase.execSQL(DROP_PERSON_TABLE);
      sqLiteDatabase.execSQL(DROP_GAMES_TABLE);
       onCreate(sqLiteDatabase);
    }

    //دالة الإضافة
    //الآن بدي أعمل دالة حتخزن بيانات الشخص لما يسجل مستخدم جديد
  // public boolean insertPerson(@NonNull PersonsDetails person)
  // {
  //     SQLiteDatabase db = getWritableDatabase();
  //     ContentValues Values = new ContentValues();

  //     Values.put(CLN_IMAGE , person.getImage());
  //     Values.put(CLN_FULL_NAME , person.getFull_name());
  //     Values.put(CLN_EMAIL , person.getEmail_address());
  //     Values.put(CLN_USER_NAME , person.getUser_name());
  //     Values.put(CLN_PASSWORD , person.getPassword());
  //     Values.put(CLN_AGE , person.getAge());

  //     Long result = db.insert(PERSON_TABLE_NAME , null,Values);

  //     return result != -1; // اذا ساوت -1 بكونله حساب
  // }

    public boolean insertGame(@NonNull PersonsDetails person)
    {
        SQLiteDatabase db = getWritableDatabase(); // هان عملت مؤشر على الداتا بيز ، وعادي بيزبط كتابة وقراءة بس الأحسن أحط كتابة عشان دخزن
        ContentValues Values = new ContentValues(); // دعمل قالب وأعبي فيه البيانات لأضيفهن فيه ومن ثم عالداتا بيز
        // الآن ما عملت لل آي دي لأنه بيزيد زيادة تلقائية
        Values.put(CLN_GAME_FULL_NAME , person.getFull_name());
        Values.put(CLN_GAME_SCORE , person.getScore());
        Values.put(CLN_GAME_TIME_DATE , person.getCard_timeAndDate());

        Long result = db.insert(GAME_TABLE_NAME , null,Values); // الآن هان عملت اضافة عالداتا بيز ، اسم الجدول ويلي بالنص في حال حقول ما بدي أسيبها فارغة والقيم

        return result != -1;
    }

    // دالة فحص هل المستخدم له حساب أم لا (بما أن اسم المستخدم مفتاح أساسي ويندرج تحت هادا البند انه وحيد ولا يتكرر)
   // public boolean checkUserName (String userName)
   // {
   //     SQLiteDatabase db = this.getWritableDatabase();
   //     String args [] = {userName}; // هان ما دمجتلها علامات تنصيص لأنها هي نص أصلًا ..

   //     Cursor cursor = db.rawQuery(" select * FROM "+ PERSON_TABLE_NAME + " where " +CLN_USER_NAME+ "=?",args);

   //     if (cursor.getCount() > 0)
   //     {
   //         return true ; // بكونله حساب
   //     }
   //     else return false; // هان ما بكونله حساب ورح يتم إنشاء حساب إله بواسطة دالة الإنسيرت
   // }

    //الآن دالة بتفحص إذا اليوسر والباسوورد فعليًا لهاد الحساب ولا لء
  //   public boolean checkUserNameAndPassword(String userName,String Password)
  //   {
  //       SQLiteDatabase db = this.getWritableDatabase();
  //       String args [] = {userName,Password};
  //       Cursor cursor =
  //               db.rawQuery(" select * FROM "+ PERSON_TABLE_NAME + " where " +CLN_USER_NAME+ "=? " +" AND "+ CLN_PASSWORD + "=?",args);
//
  //       if (cursor.getCount()>0)
  //       {
  //           return true ;
  //       }
  //       else return false;
//
  //   }

    //دالة الإسترجاع
    public ArrayList<PersonsDetails> getAllGame() // دالة بترجعلي ليستة فيها بيانات اللعبة
    {
        ArrayList <PersonsDetails> game = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase(); // مؤشر على الداتا بيز لأقرأ منها
        // الآن بدي أعمل كيرسر
        Cursor cursor =db.rawQuery("select * from "+ GAME_TABLE_NAME , null); // الآن هان جملة السيليكت بنعملهاعن طريق الرو كويري ، وبترجع عشكل كيرسر
        // الآن هادا الكيرسر بدي أحوله لمصفوفة
        // فحص هل الكيرسر يحتوي على بيانات أم لا ؟
        if (cursor.moveToFirst())
        {
            do
            {// قراءة السطر من الكيرسر وتحويله إلى أوبجيكت ومن ثم تخزين الأوبجيكت في مصفوفة
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(CLN_GAME_ID));
                String full_name = cursor.getString(cursor.getColumnIndexOrThrow(CLN_GAME_FULL_NAME));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow(CLN_GAME_SCORE));
                String time_date = cursor.getString(cursor.getColumnIndexOrThrow(CLN_GAME_TIME_DATE));


                // هان عملت أوبجيكت وحطيت فيه البيانات يلي من الكيرسر
                PersonsDetails details_game =new PersonsDetails(id,full_name , score ,time_date);
                game.add(details_game); // هان ضفت الأوجيكت على المصفوفة

                //game.add(new PersonsDetails(id , full_name , score , time_date)); // ممكن كدة اختصار بدل ما أعمل أوبجيكت وأضيفه هان دغري عملت أوبجيكت
            }
            while (cursor.moveToNext());
            cursor.close(); // هان سكرنا الكيرسر
        }
    return game; // هان بيرجعلي مصفوفة فيها الأوجيكتات يلي بعدين حأحط في كل آيتم في الريسايكلر أوبجيكت
    }


    // دالة بترجّع تاريخ آخر لعبة من الداتا بيز
    public String getGameLastDate() // دالة بترجعلي ليستة فيها بيانات اللعبة
    {
        String lastGame = null;
        SQLiteDatabase db = getReadableDatabase(); // مؤشر على الداتا بيز لأقرأ منها
        // الآن بدي أعمل كيرسر
        Cursor cursor =db.rawQuery("select " +CLN_GAME_TIME_DATE +" from "+ GAME_TABLE_NAME , null ); // الآن هان جملة السيليكت بنعملهاعن طريق الرو كويري ، وبترجع عشكل كيرسر

        // الآن هادا الكيرسر بدي أحوله لمصفوفة
        // فحص هل الكيرسر يحتوي على بيانات أم لا ؟
        if (cursor.moveToFirst())
        {
            lastGame = cursor.getString(cursor.getColumnIndexOrThrow(CLN_GAME_TIME_DATE));
            // هان سكرنا الكيرسر
            cursor.close();
        }
        return lastGame; // هان بيرجعلي قيمة نصية فيها التاريخ
    }


    // الآن دعمل دالة الحذف
    public void deleteGame ()
    {
        SQLiteDatabase dp = getWritableDatabase();
        long result = dp.delete(GAME_TABLE_NAME , null , null);
    }
}
