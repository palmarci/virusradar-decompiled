package p008hu.gov.virusradar.Utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.style.MetricAffectingSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Listeners.IEditTextDoneListener;

/* renamed from: hu.gov.virusradar.Utilities.ValidateTextView */
public class ValidateTextView extends LinearLayout {
    private static final String empty_string = "";
    private Context context;
    public EditText editText;
    private OnEditorActionListener editorActionListener;
    private String hintText;
    private int idNextfocus;
    private int idResource;
    private String imeOption;
    private String inputType;
    /* access modifiers changed from: private */
    public IEditTextDoneListener listener;
    public TextInputLayout textInputLayout;
    private View view;

    /* renamed from: hu.gov.virusradar.Utilities.ValidateTextView$FontSpan */
    private static final class FontSpan extends MetricAffectingSpan {
        private final Typeface mNewFont;

        private FontSpan(Typeface typeface) {
            this.mNewFont = typeface;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setTypeface(this.mNewFont);
        }

        public void updateMeasureState(TextPaint textPaint) {
            textPaint.setTypeface(this.mNewFont);
        }
    }

    public void errorTextViewVisibility(int i) {
    }

    public void addEditorActionListener(IEditTextDoneListener iEditTextDoneListener) {
        this.listener = iEditTextDoneListener;
    }

    public ValidateTextView(Context context2) {
        super(context2);
        this.editorActionListener = new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                if (ValidateTextView.this.listener != null) {
                    ValidateTextView.this.listener.clickKeyboardDone();
                }
                return true;
            }
        };
        this.view = LayoutInflater.from(context2).inflate(C1130R.layout.custom_edit_textview, this, false);
        this.context = context2;
    }

    public ValidateTextView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.editorActionListener = new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                if (ValidateTextView.this.listener != null) {
                    ValidateTextView.this.listener.clickKeyboardDone();
                }
                return true;
            }
        };
        this.view = LayoutInflater.from(context2).inflate(C1130R.layout.custom_edit_textview, this, false);
        this.context = context2;
        initViews(context2, attributeSet);
    }

    public ValidateTextView(Context context2, AttributeSet attributeSet, int i) {
        this(context2, attributeSet);
        this.view = LayoutInflater.from(context2).inflate(C1130R.layout.custom_edit_textview, this, false);
        this.context = context2;
        initViews(context2, attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void initViews(Context context2, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context2.getTheme().obtainStyledAttributes(attributeSet, C1130R.styleable.ValidateTextView, 0, 0);
        try {
            this.hintText = obtainStyledAttributes.getString(2);
            this.inputType = obtainStyledAttributes.getString(4);
            this.idNextfocus = obtainStyledAttributes.getInteger(5, 0);
            this.imeOption = obtainStyledAttributes.getString(3);
            this.idResource = obtainStyledAttributes.getInteger(0, 0);
            obtainStyledAttributes.recycle();
            addView(this.view);
            this.textInputLayout = (TextInputLayout) findViewById(C1130R.C1133id.textInputLayout);
            setupEditText();
            this.editText.addTextChangedListener(txtEntered());
            this.editText.setOnEditorActionListener(this.editorActionListener);
            this.editText.setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                }
            });
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public String getInputText() {
        return this.editText.getText().toString();
    }

    public void setInputText(String str) {
        if (str != null) {
            this.editText.setText(str);
        }
    }

    public void setHintText(String str) {
        this.editText.setHint(str);
    }

    public void clearInputText() {
        this.editText.getText().clear();
    }

    private void setupEditText() {
        this.editText = (EditText) findViewById(C1130R.C1133id.editTextCustom);
        int i = this.idResource;
        if (i != 0) {
            this.editText.setId(i);
        }
        this.textInputLayout.setHint(this.hintText);
        this.editText.setNextFocusForwardId(this.idNextfocus);
        this.editText.setSingleLine();
        setImeOptions();
        setEditTextInputType();
    }

    public void validate(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            clearErrorText();
            return;
        }
        this.textInputLayout.setErrorEnabled(true);
        this.textInputLayout.setError(getErrorMessage(arrayList));
    }

    public void clearErrorText() {
        this.textInputLayout.setError(null);
        this.textInputLayout.setErrorEnabled(false);
    }

    private String getErrorMessage(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append((String) arrayList.get(0));
        for (int i = 1; i < arrayList.size(); i++) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\n");
            sb2.append((String) arrayList.get(i));
            sb.append(sb2.toString());
        }
        return sb.toString();
    }

    private void setEditTextInputType() {
        String str = this.inputType;
        if (str != null) {
            if (str.equals("text")) {
                this.editText.setInputType(128);
            } else if (this.inputType.equals("textPassword")) {
                this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else if (this.inputType.equals("number")) {
                this.editText.setInputType(2);
            } else if (this.inputType.equals("phone")) {
                this.editText.setInputType(3);
            } else if (this.inputType.equals("textCapSentences")) {
                this.editText.setInputType(16384);
            } else if (this.inputType.equals("textMultiLine")) {
                this.editText.setSingleLine(false);
                this.editText.setImeOptions(1073741824);
            }
        }
    }

    private void setImeOptions() {
        String str = this.imeOption;
        if (str != null) {
            if (str.equals("actionNext")) {
                this.editText.setImeOptions(5);
            } else if (this.imeOption.equals("actionDone")) {
                this.editText.setImeOptions(6);
            }
        }
    }

    public TextWatcher txtEntered() {
        return new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
    }

    private void changeFontStyle() {
        Typeface createFromAsset = Typeface.createFromAsset(this.context.getAssets(), "fonts/SkolaSans_Medium.ttf");
        this.editText.setTypeface(createFromAsset);
        this.textInputLayout.setTypeface(createFromAsset);
    }
}
