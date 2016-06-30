package info.msadeghi.persiannumberspeller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import info.msadeghi.persiannumberspeller.R;
import info.msadeghi.persiannumberspeller.util.NumberToWordConverter;
import info.msadeghi.persiannumberspeller.util.Utils;

public class MainActivity extends AppCompatActivity {


    private EditText number_et, amount_et;
    private TextView number_word_tv, amount_word_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number_et = (EditText) findViewById(R.id.number_et);
        amount_et = (EditText) findViewById(R.id.amount_et);
        number_word_tv = (TextView) findViewById(R.id.number_word_tv);
        amount_word_tv = (TextView) findViewById(R.id.amount_word_tv);

        addNumberTextWatcher();
        addAmountTextWatcher();

    }

    private TextWatcher numberTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String value = s.toString();
            if(value.length() == 0)
            {
                number_word_tv.setText("");
                return;
            }

            try{
                Long l = Long.parseLong(value);
                String amount = NumberToWordConverter.convert(l);
                number_word_tv.setText(amount);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            removeNumberTextWatcher();
            number_et.setText(value);
            number_et.setTextKeepState(value);
            Selection.setSelection(number_et.getText(), value.length());
            addNumberTextWatcher();
        }
    };

    private void addNumberTextWatcher()
    {
        number_et.addTextChangedListener(numberTextWatcher);
    }

    private void removeNumberTextWatcher()
    {
        number_et.removeTextChangedListener(numberTextWatcher);
    }

    private TextWatcher amountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String value = s.toString();
            if(value.length() == 0)
            {
                amount_word_tv.setText("");
                return;
            }

            value = Utils.clear(value);
            try{
                Long l = Long.parseLong(value);
                String amount = NumberToWordConverter.convert(l) + " " + getString(R.string.rial);
                amount_word_tv.setText(amount);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            removeAmountTextWatcher();
            String newAmount = Utils.amountFormater(value);
            amount_et.setText(newAmount);
            amount_et.setTextKeepState(newAmount);
            Selection.setSelection(amount_et.getText(), newAmount.length());
            addAmountTextWatcher();
        }
    };

    private void addAmountTextWatcher()
    {
        amount_et.addTextChangedListener(amountTextWatcher);
    }

    private void removeAmountTextWatcher()
    {
        amount_et.removeTextChangedListener(amountTextWatcher);
    }
}
