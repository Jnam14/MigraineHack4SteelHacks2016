package steelhacks.migrainebuddy.com.migraine;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by Jason on 2/19/2016.
 */
public class MigraineQuestions {
    public String question;
    public int answer;

    public MigraineQuestions(String mQue, int mAns){
        super();

        question = mQue;
        answer = mAns;

    }

    public String getQue() {
        return question;
    }
    public void setAns(int newAns) {
        answer=newAns;
    }

    public int getAns() {
        return answer;
    }


}
