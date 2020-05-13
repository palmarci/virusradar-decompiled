package p008hu.gov.virusradar.Activities.Dialog;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.wang.avi.AVLoadingIndicatorView;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Utilities.Util;

/* renamed from: hu.gov.virusradar.Activities.Dialog.ProgressBarDialog */
public class ProgressBarDialog extends DialogFragment {
    private static ProgressBarDialog instance;
    private AVLoadingIndicatorView circle;
    private View decorView;
    boolean hasAddedOnView = true;
    private Window window;

    public static ProgressBarDialog getInstance() {
        if (instance == null) {
            instance = new ProgressBarDialog();
        }
        return instance;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        return onCreateDialog;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.window = getDialog().getWindow();
        this.window.setBackgroundDrawable(new ColorDrawable(0));
        this.window.setLayout(-1, -1);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1130R.layout.dialog_progress_bar, viewGroup, false);
        this.circle = (AVLoadingIndicatorView) inflate.findViewById(C1130R.C1133id.avi);
        this.circle.setVisibility(0);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        this.decorView = getDialog().getWindow().getDecorView();
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.decorView, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{0.5f, 1.0f})});
        ofPropertyValuesHolder.setDuration(100);
        ofPropertyValuesHolder.start();
        if (!this.hasAddedOnView && getDialog() != null) {
            getDialog().dismiss();
        }
    }

    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return keyEvent.getAction() == 1 && i == 4;
            }
        });
    }

    public static void showDialog(FragmentManager fragmentManager, String str) {
        getInstance().show(fragmentManager, str);
    }

    public void show(FragmentManager fragmentManager, String str) {
        if (!isAdded()) {
            super.show(Util.getAnimatedFragmentTransaction(fragmentManager.beginTransaction()), "");
        }
    }

    public int show(FragmentTransaction fragmentTransaction, String str) {
        if (!isAdded()) {
            return super.show(fragmentTransaction, str);
        }
        return 0;
    }

    public void dismiss() {
        View view = this.decorView;
        if (view == null) {
            this.hasAddedOnView = false;
            return;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f})});
        ofPropertyValuesHolder.setDuration(500);
        ofPropertyValuesHolder.start();
        ofPropertyValuesHolder.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (ProgressBarDialog.this.getDialog() != null && ProgressBarDialog.this.getView() != null) {
                    ProgressBarDialog.this.getDialog().dismiss();
                    ProgressBarDialog.this.getView().setFocusableInTouchMode(false);
                    ProgressBarDialog.this.getView().clearFocus();
                }
            }
        });
    }

    public static void dismissDialog() {
        getInstance().dismiss();
    }
}
