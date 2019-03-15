package com.iot.texteditor_1607094250;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class SizeListener implements View.OnClickListener {

    private TextView tv = null;

    SizeListener(TextView tv) {
        this.tv = tv;
    }

    @Override
    public void onClick(View v) {
        float textSize = tv.getTextSize();

        switch (v.getId()) {
            case R.id.bigger:
                textSize += (float) 2;
                break;
            case R.id.smaller:
                textSize -= (float) 2;
                break;
            default:
                break;
        }

        // 防止设置超出字符大小界限
//        if (textSize > 72){
//            textSize = (float)72;
//        }
//        if (textSize < 8){
//            textSize = (float)8;
//        }
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

    }
}
