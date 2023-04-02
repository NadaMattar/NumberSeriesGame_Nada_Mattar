package na.ma.numper.series.adapter.recycler.gamedata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import na.ma.numper.series.R;
import na.ma.numper.series.model.PersonsDetails;

// كلاس الآدبتر عمل وراثة من كلاس الأدابتر الداخلي يلي موجود في كلاس الريسايكلر
// طبعًا نوع الأدابتر من نوع كلاس الهولدر يلي عملناه في نفس البكج في كلاس تاني.
public class CardGamePersonAdapters extends RecyclerView.Adapter<CardGamePersonViewHolder> {

    private ArrayList<PersonsDetails> personsDetailsArrayList = new ArrayList<>();


    // هان عملت كونستركتور بياخذ أرري ليست نوعها طبعًا كلاس البيانات
    public CardGamePersonAdapters(ArrayList<PersonsDetails> personsDetailsArrayList) {
        this.personsDetailsArrayList = personsDetailsArrayList;
    }


    // في هادي الدالة فعليًا بما انّه الهولدر بيتحكّم في فيو واحد (آيتم) يلي هو في 3 تيكست فيو
    // دالة بترجّع   Holder
    // الآن دالة ال on create  يتم انشائها أول مرة لعرض أول عناصر في الشاشة ظاهرة
    //  يعني عملت للعناصر هادي انفلات لأول مرة علشان يتم استخدامها بعد هيك وما أرجع أعمللها انفلات
    // يتم استدعائها فقط لكل عنصر يظهر لأول مرة على الشاشة
    //  والعناصر يلي عملتلها انفلات يتم وضعها في حاوية يلي هي Card Game ViewHolder
    // يعني مثلا الشاشة فيها 9 آيتم في كل مرة راح ترجعلي Holder وفيه العناصر يلي خاصة فيه .
    @NonNull
    @Override
    public CardGamePersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_game_person, parent,false); // هان بنعمل انفلات
        CardGamePersonViewHolder holder = new CardGamePersonViewHolder(v); // هان أعطيت الهولدر v يلي هي عبارة عن الآيتم كلّه بكل محتوياته
                                                                    // هان عملت أوبجيكت من كلاس الهولدر وال v  فعليًا راحت ل الكونستركتور ليعمل انفلات وهيك بتكون ك آيتم واحد
        return holder;
    }// باختصار وظيفتها فقط أعمل انفلات للتصميم


    // دالة بتأخذ  Holder
    // طبعًا هان بيجي هيك في البراميتر ، بناءً ع الوراثة يلي عملها فوق يلي نوعها <CardGamePersonViewHolder> ..
    @Override
    public void onBindViewHolder(@NonNull CardGamePersonViewHolder holder, int position) {
        // personsDetailsArrayList.get(position)هان عملت جلب للبيانات
        holder.setData(personsDetailsArrayList.get(position)); // وهان عملت تعبئة ، طبعًا عن طريق الدالة يلي عملتها في الهولدر
        // طبعًا دالة ال setData بتاخد أوبجيكت ، والأوبجيكت هادا جبته من الاندكس تاع المصفوفة ، لأنه المصفوفة مخزّن فيها أوبجيكتات عبارة عن آيتمات

    }

    // دالة بترجع حجم المصفوفة ، يعني كم عنصر في المصفوفة ،كم آيتم في الرسياكيلر
    //
    @Override
    public int getItemCount() {
        return personsDetailsArrayList.size(); // برجّع طول المصفوفة ، كم (اندكس فيها || كم أوبجيكت || كم آيتم ) متل كأني بعبّر عم شي واحد هان
    }



    // طبعًا حدث خطأ شنيع لما عدت المشروع ، ف اسم البكج سميته غلط وخفت أعدله بعد الانشاء ، عشان ما يضرب شي عندي -__-
}
