package na.ma.numper.series.views.activities;

public class Function { //

    public static Question generateQuestion() // هان عملت دالة نوعها كلاس السؤال لترجّع Question
                                             //generateQuestion اسم الدالة
    {
        String [][] x = new String[3][3]; // هان مصفوفة ثنائية طولها 3x3
        int startNumber = (int) (Math.random() * 10) + 1 ; // startNumber الآن هادي الدالة بتولّد رقم عشوائي بين ال 0 وال 1 ، فبضربها ب 10 لتصير صحيح ، وبزّود 1 ، في حال أصبح الرقم 0 ، ومن ثم برجّعها في متغيّر نوعه رقم صحيح واسمه
        int incStartNumber = (int) (Math.random() * 5) + 1 ; // مقدار الزيادة
        int nextNumber ; // الرقم التالي
        int number = -1 ;

        for (int i = 0 ; i < x.length ; i++)
        {
            for (int j = 0 ; j < x[i].length ; j++)
            {
                nextNumber = startNumber + incStartNumber; // المتغير الذي يتم تخزين فيه الرقم التالي
                if (i == 1 && j == 1)
                {
                    x[i][j] = "??";
                    number = nextNumber ;
                }
                else
                {
                    x[i][j] = nextNumber +"" ;
                }

                incStartNumber += 2;
                startNumber = nextNumber;

            }
        }

        return new Question( x , number);
    }
}
