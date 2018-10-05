/* Blueprint for all emotion instances
    imitated from Tweet.java of LonelyTwitter by Joshua Campbell
 */

package com.example.liuxiaohui.xiaohui1_feelsbook;

import java.util.Date;
import android.view.View;

// Emotion can be Joy, Love, etc...
public class Emotion {
    private int count;
    private String emotionName;

    // construtor for emotion
    Emotion(String name) {
        this.emotionName = name;
        this.count = 0;  // initial count for a emotion is 0

    }

    // enables user to get the count value of one motion
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
