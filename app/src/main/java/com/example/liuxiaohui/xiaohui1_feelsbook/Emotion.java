/* Blueprint for all emotion instances
    imitated from Tweet.java of LonelyTwitter by Joshua Campbell
 */

package com.example.liuxiaohui.xiaohui1_feelsbook;

import java.util.Date;
import android.view.View;


public class Emotion {
    private int count;
    private String emotionName;

    // construtor
    Emotion(String name) {
        this.emotionName = name;
        resetCount();

    }

    public void resetCount(){
        this.count=0;
    }

    public int getCount(){
        return this.count;
    }

    // reload previous count when APP is restarted
    public void setCount(int previousCount){
        this.count = previousCount;
    }

    public void increaseCount(){
        this.count = this.count + 1;
    }

}
