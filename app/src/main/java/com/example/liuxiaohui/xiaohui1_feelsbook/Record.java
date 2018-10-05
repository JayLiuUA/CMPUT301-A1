/* this is the class of all records */

package com.example.liuxiaohui.xiaohui1_feelsbook;

public class Record {
    protected String emotionName;
    protected String date;
    protected String comment;

    Record(String name, String comment, String date){
        this.emotionName = name;
        // if no comment is given, set comment to a empty space
        if (comment == null) {
            this.comment = "";
        } else {
            this.comment = comment;
        }
        setDate(date);
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    public String toStringWithouComment(){
        return this.date.toString() + " | " + this.emotionName + " | " + this.comment;
    }

}
