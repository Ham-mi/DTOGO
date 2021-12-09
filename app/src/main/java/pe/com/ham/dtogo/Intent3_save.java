package pe.com.ham.dtogo;

import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.maltaisn.icondialog.IconDialog;
import com.maltaisn.icondialog.IconDialogSettings;
import com.maltaisn.icondialog.data.Icon;
import com.maltaisn.icondialog.pack.IconPack;
import com.maltaisn.iconpack.defaultpack.IconPackDefault;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.flag.FlagView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pe.com.ham.dtogo.dao.Dday;
import pe.com.ham.dtogo.dao.DdayViewModel;


public class Intent3_save extends AppCompatActivity{

    InputMethodManager inputMethodManager;

    private ImageView imageBack_g1;
    private TextView textSave_g1;
    private EditText editTitle_g1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal_1);

        getSupportActionBar().hide();
    }
}
