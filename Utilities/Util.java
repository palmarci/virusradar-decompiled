package p008hu.gov.virusradar.Utilities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.chaos.view.PinView;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Listeners.IFilterListener;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;
import p008hu.gov.virusradar.Modules.LanguageModel;

/* renamed from: hu.gov.virusradar.Utilities.Util */
public class Util {
    private static final int PIN_LENGTH = 6;
    private static final String dateHourFormat = "dd MMM yyyy HH:mm";
    private static final String responseDateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss";

    public static int substream(byte[] bArr, byte[] bArr2) {
        if (bArr.length > bArr2.length) {
            int i = 0;
            for (int i2 = 0; i2 < bArr.length; i2++) {
                if (i >= bArr2.length) {
                    return i2;
                }
                i = bArr[i2] == bArr2[i] ? i + 1 : 0;
            }
        }
        return -1;
    }

    public static String formatDate(Long l, String str) {
        String str2 = "";
        if (l == null) {
            return str2;
        }
        try {
            return new SimpleDateFormat(str).format(new Date(l.longValue()));
        } catch (Exception unused) {
            return str2;
        }
    }

    public static void hideKeyboard(View view, Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void startErrorDialog(String str, Context context) {
        startMessageErrorDialog(context, context.getString(C1130R.string.error), str, null);
    }

    public static void startMessageErrorDialog(Context context, String str, String str2, final INoNetworkListener iNoNetworkListener) {
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(C1130R.layout.custom_dialog_error, null, false);
        TextView textView = (TextView) inflate.findViewById(C1130R.C1133id.tvMessage);
        TextView textView2 = (TextView) inflate.findViewById(C1130R.C1133id.tvOK);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(str2);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                INoNetworkListener iNoNetworkListener = iNoNetworkListener;
                if (iNoNetworkListener != null) {
                    iNoNetworkListener.onClickRefresh();
                }
                create.dismiss();
            }
        });
    }

    public static void startPinDialog(final Context context, String str, final IFilterListener iFilterListener) {
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(C1130R.layout.dialog_pin, null, false);
        TextView textView = (TextView) inflate.findViewById(C1130R.C1133id.tvMessage);
        final RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C1130R.C1133id.frameError);
        relativeLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Util.hideKeyboard(relativeLayout, context);
            }
        });
        TextView textView2 = (TextView) inflate.findViewById(C1130R.C1133id.tvNo);
        final TextView textView3 = (TextView) inflate.findViewById(C1130R.C1133id.tvYes);
        final PinView pinView = (PinView) inflate.findViewById(C1130R.C1133id.pinViewSendData);
        pinView.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 6) {
                    Util.setBtnEnabling(textView3, 1.0f, true);
                } else {
                    Util.setBtnEnabling(textView3, 0.5f, false);
                }
            }
        });
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(str);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
            }
        });
        textView3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                IFilterListener iFilterListener = iFilterListener;
                if (iFilterListener != null) {
                    iFilterListener.getNewValue(pinView.getText().toString());
                }
                create.dismiss();
            }
        });
    }

    public static void startAgreeDialog(Context context, String str, final INoNetworkListener iNoNetworkListener) {
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(C1130R.layout.dialog_agree, null, false);
        TextView textView = (TextView) inflate.findViewById(C1130R.C1133id.tvMessage);
        TextView textView2 = (TextView) inflate.findViewById(C1130R.C1133id.tvYes);
        TextView textView3 = (TextView) inflate.findViewById(C1130R.C1133id.tvNo);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(str);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
        textView3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
            }
        });
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                INoNetworkListener iNoNetworkListener = iNoNetworkListener;
                if (iNoNetworkListener != null) {
                    iNoNetworkListener.onClickRefresh();
                }
                create.dismiss();
            }
        });
    }

    public static void startConfirmDialog(Context context, String str, final INoNetworkListener iNoNetworkListener) {
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(C1130R.layout.dialog_confirm, null, false);
        TextView textView = (TextView) inflate.findViewById(C1130R.C1133id.tvMessage);
        TextView textView2 = (TextView) inflate.findViewById(C1130R.C1133id.tvNo);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C1130R.C1133id.rlYes);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(str);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
            }
        });
        relativeLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                INoNetworkListener iNoNetworkListener = iNoNetworkListener;
                if (iNoNetworkListener != null) {
                    iNoNetworkListener.onClickRefresh();
                }
                create.dismiss();
            }
        });
    }

    public static void startDoneDialog(Context context, String str, INoNetworkListener iNoNetworkListener) {
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(C1130R.layout.dialog_done, null, false);
        TextView textView = (TextView) inflate.findViewById(C1130R.C1133id.tvMessage);
        TextView textView2 = (TextView) inflate.findViewById(C1130R.C1133id.tvOk);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(str);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    public static void setBtnEnabling(View view, float f, boolean z) {
        view.setAlpha(f);
        view.setEnabled(z);
        view.setClickable(z);
    }

    public static FragmentTransaction getAnimatedFragmentTransaction(FragmentTransaction fragmentTransaction) {
        return fragmentTransaction.setCustomAnimations(C1130R.anim.enter, C1130R.anim.exit, C1130R.anim.enter, C1130R.anim.exit);
    }

    public static void saveObjectToFile(Object obj, File file) {
        String json = new Gson().toJson(obj);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(json);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getObjectFromFile(Class cls, File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[((int) file.length())];
            fileInputStream.read(bArr);
            fileInputStream.close();
            String str = new String(bArr, StandardCharsets.UTF_8);
            return new Gson().fromJson(str.substring(str.indexOf("[")), cls);
        } catch (Exception unused) {
            return null;
        }
    }

    public static float convertDpToPx(int i) {
        return TypedValue.applyDimension(1, (float) i, VirusTraceApp.getInstance().getResources().getDisplayMetrics());
    }

    public static Bitmap textAsBitmap(String str, int i, int i2) {
        Paint paint = new Paint(1);
        paint.setTextSize(convertDpToPx(i));
        paint.setColor(i2);
        paint.setTextAlign(Align.LEFT);
        float f = -paint.ascent();
        Bitmap createBitmap = Bitmap.createBitmap((int) (paint.measureText(str) + 0.0f), (int) (paint.descent() + f + 0.0f), Config.ARGB_8888);
        new Canvas(createBitmap).drawText(str, 0.0f, f, paint);
        return createBitmap;
    }

    public static void setLocale(Context context, LanguageModel languageModel) {
        if (languageModel != null && languageModel.getLocale() != null) {
            Locale.setDefault(languageModel.getLocale());
            Configuration configuration = new Configuration();
            configuration.setLocale(languageModel.getLocale());
            context.getApplicationContext().createConfigurationContext(configuration);
            context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
            AppLanguagesUtil.saveDefaultLocaleInSharedPreferences(VirusTraceApp.getInstance(), languageModel);
        }
    }

    public static String convertDuration(Long l) {
        String str;
        if (l == null) {
            return "0 minutes";
        }
        long longValue = l.longValue() / 86400000;
        Long valueOf = Long.valueOf(l.longValue() - (86400000 * longValue));
        long longValue2 = valueOf.longValue() / 3600000;
        long longValue3 = (long) ((int) (Long.valueOf(valueOf.longValue() - (3600000 * longValue2)).longValue() / 60000));
        String str2 = "%d %s";
        if (longValue > 0) {
            Object[] objArr = new Object[2];
            objArr[0] = Long.valueOf(longValue);
            objArr[1] = longValue != 1 ? "days" : "day";
            str = String.format(str2, objArr);
        } else {
            str = "";
        }
        String str3 = ", %d %s";
        if (longValue2 > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str4 = str.length() == 0 ? str2 : str3;
            Object[] objArr2 = new Object[2];
            objArr2[0] = Long.valueOf(longValue2);
            objArr2[1] = longValue2 != 1 ? "hours" : "hour";
            sb.append(String.format(str4, objArr2));
            str = sb.toString();
        }
        if (str.length() == 0 || longValue3 > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            if (str.length() == 0) {
                str3 = str2;
            }
            Object[] objArr3 = new Object[2];
            objArr3[0] = Long.valueOf(longValue3);
            objArr3[1] = longValue3 != 1 ? "minutes" : "minute";
            sb2.append(String.format(str3, objArr3));
            str = sb2.toString();
        }
        return str;
    }

    private static String convertDateFormat(String str, String str2, String str3) {
        if (str == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        try {
            str = new SimpleDateFormat(str3).format(simpleDateFormat.parse(str));
        } catch (ParseException unused) {
        }
        return str;
    }

    public static String formatDateHour(String str) {
        return convertDateFormat(str, responseDateTimeFormat, dateHourFormat);
    }

    public static boolean checkForUpdate(Context context, String str) {
        String str2;
        Boolean bool;
        new PackageInfo();
        Boolean.valueOf(false);
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            str2 = "";
        }
        if (compareVersionNames(str2, str) < 0) {
            bool = Boolean.valueOf(true);
        } else {
            bool = Boolean.valueOf(false);
        }
        return bool.booleanValue();
    }

    public static int compareVersionNames(String str, String str2) {
        String str3 = "\\.";
        String[] split = str.split(str3);
        String[] split2 = str2.split(str3);
        int min = Math.min(split.length, split2.length);
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= min) {
                break;
            }
            int intValue = Integer.valueOf(split[i2]).intValue();
            int intValue2 = Integer.valueOf(split2[i2]).intValue();
            if (intValue < intValue2) {
                i = -1;
                break;
            } else if (intValue > intValue2) {
                i = 1;
                break;
            } else {
                i2++;
            }
        }
        if (i != 0 || split.length == split2.length) {
            return i;
        }
        return split.length > split2.length ? 1 : -1;
    }

    public static byte[] mergeArrays(byte[]... bArr) {
        byte[] bArr2 = new byte[0];
        int length = bArr.length;
        byte[] bArr3 = bArr2;
        int i = 0;
        while (i < length) {
            byte[] bArr4 = bArr[i];
            byte[] bArr5 = new byte[(bArr3.length + bArr4.length)];
            System.arraycopy(bArr3, 0, bArr5, 0, bArr3.length);
            System.arraycopy(bArr4, 0, bArr5, bArr3.length, bArr4.length);
            i++;
            bArr3 = bArr5;
        }
        return bArr3;
    }

    public static void presentActivity(Context context, Class cls, Integer num) {
        Intent intent = new Intent(context, cls);
        if (num != null) {
            intent.setFlags(num.intValue());
        }
        context.startActivity(intent);
    }

    public static boolean sleep(int i) {
        try {
            Thread.sleep((long) i);
            return true;
        } catch (InterruptedException unused) {
            return false;
        }
    }

    public static void startConfirmDeleteDialog(Context context, String str, final INoNetworkListener iNoNetworkListener) {
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(C1130R.layout.dialog_delete_confirm, null, false);
        TextView textView = (TextView) inflate.findViewById(C1130R.C1133id.tvMessage);
        TextView textView2 = (TextView) inflate.findViewById(C1130R.C1133id.tvNo);
        TextView textView3 = (TextView) inflate.findViewById(C1130R.C1133id.tvYes);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(str);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        create.show();
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
            }
        });
        textView3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                INoNetworkListener iNoNetworkListener = iNoNetworkListener;
                if (iNoNetworkListener != null) {
                    iNoNetworkListener.onClickRefresh();
                }
                create.dismiss();
            }
        });
    }
}
