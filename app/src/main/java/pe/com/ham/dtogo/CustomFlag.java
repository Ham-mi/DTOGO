package pe.com.ham.dtogo;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.flag.FlagView;

public class CustomFlag extends FlagView {

    TextView flag_color_code = findViewById(R.id.flag_color_code);
    LinearLayout flag_color_layout = findViewById(R.id.flag_color_layout);

    public CustomFlag(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onRefresh(ColorEnvelope colorEnvelope) {
        flag_color_code.setText("#"+colorEnvelope.getHexCode().substring(2));
        flag_color_layout.setBackgroundColor(colorEnvelope.getColor());
    }
}
