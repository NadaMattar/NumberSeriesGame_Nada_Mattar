package na.ma.numper.series.views.activities;

public class Question {

    private String [][] data ; //عرفت مصفوفة ثنائية البُعد اسمها data
    private int hiddenNumber;// عرفت متغيّر نوعه رقم صحيح اسمه hiddenNumber

    public Question(String[][] data, int hiddenNumber) { //  هادا كونستركتور بياخد المصفوفة والرقم المخفي
        this.data = data;
        this.hiddenNumber = hiddenNumber;
    }

    // وتحت عملنا ستر وقيتر لأن المتغيرات نوعهن برايفت ، لأطبق مبدأ التغليف Encapsulation

    public String[][] getData() { // دالة بترجّع مصفوفة ثنائية اسمها داتا ونوعها سترينج
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public int getHiddenNumber() {
        return hiddenNumber;
    }

    public void setHiddenNumber(int hiddenNumber) {
        this.hiddenNumber = hiddenNumber;
    }
}
