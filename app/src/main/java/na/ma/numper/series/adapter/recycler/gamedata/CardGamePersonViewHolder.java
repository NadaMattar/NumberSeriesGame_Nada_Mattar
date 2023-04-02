package na.ma.numper.series.adapter.recycler.gamedata;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import na.ma.numper.series.R;
import na.ma.numper.series.databinding.ItemCardGamePersonBinding;
import na.ma.numper.series.model.PersonsDetails;

// كلاس الهولدر( الحاوية )
public class CardGamePersonViewHolder extends RecyclerView.ViewHolder {
  // هان بتصير عملية الإنفلات
    private ItemCardGamePersonBinding binding; //
    //   كونستركتور.. أول ما عملت وراثة من( الريساكيلر . فيو هولدر) inner class  ، توجّب عليّ انشاء كونستركتور لهاد الكلاس بما انه موجود في الأب تاعه
    // يستقبل فيو ويرسلها لكلاس الأب
    public CardGamePersonViewHolder(@NonNull View itemView) {
        super(itemView);
      // الآن ال view عبارة عن التصميم يلي حعمله انفلات
        binding = ItemCardGamePersonBinding.bind(itemView); // هان ربطت ال binding ب الآيتم يلي اجى من الأب يلي هو  itemView وعملت bind وما عملت getLayout Inflater عشان ما بعمل انفلات من جديد ،... بس بربط
             // بالمختصر ،حوّلت ال itemView ل binding

    }

    // هان عملت دالة ما بترجع شي بس لأقدر أحط  فيها البيانات يلي جاية من كلاس بيانات الأشخاص يلي حيكون فيها تفاصيل اللعبة
    // ولأقدر أعطيها للأدابتر يلي حيتولى مهمة تعبئة البيانات
    public void setData(PersonsDetails personsDetails){
        binding.cardFullName.setText(personsDetails.getFull_name());
        binding.cardScore.setText(String.valueOf(personsDetails.getScore())); // هان حوّلت الصحيح ل قيمة نصية
        binding.cardTimeAndDate.setText(personsDetails.getCard_timeAndDate());
    }
}
