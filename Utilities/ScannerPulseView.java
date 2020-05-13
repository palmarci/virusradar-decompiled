package p008hu.gov.virusradar.Utilities;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: hu.gov.virusradar.Utilities.ScannerPulseView */
public class ScannerPulseView extends View {
    private static final int PULSE_ANIMATION_DURATION = 2000;
    private int size = 0;

    /* renamed from: hu.gov.virusradar.Utilities.ScannerPulseView$ResizeAnimation */
    public class ResizeAnimation extends Animation {
        private int deltaHeight;
        private int deltaWidth;
        private boolean fillEnabled = false;
        private int originalHeight;
        private int originalWidth;
        private int startHeight;
        private int startWidth;

        public ResizeAnimation(int i, int i2, int i3, int i4) {
            this.startWidth = i;
            this.deltaWidth = i2 - i;
            this.startHeight = i3;
            this.deltaHeight = i4 - i3;
            this.originalHeight = ScannerPulseView.this.getHeight();
            this.originalWidth = ScannerPulseView.this.getWidth();
        }

        public void setFillEnabled(boolean z) {
            this.fillEnabled = z;
            super.setFillEnabled(z);
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            if (((double) f) != 1.0d || this.fillEnabled) {
                if (this.deltaHeight != 0) {
                    ScannerPulseView.this.getLayoutParams().height = (int) (((float) this.startHeight) + (((float) this.deltaHeight) * f));
                }
                if (this.deltaWidth != 0) {
                    ScannerPulseView.this.getLayoutParams().width = (int) (((float) this.startWidth) + (((float) this.deltaWidth) * f));
                }
            } else {
                ScannerPulseView.this.getLayoutParams().height = this.originalHeight;
                ScannerPulseView.this.getLayoutParams().width = this.originalWidth;
            }
            ScannerPulseView.this.requestLayout();
        }
    }

    public ScannerPulseView(Context context) {
        super(context);
    }

    public ScannerPulseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScannerPulseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.size == 0 && i > 0 && i2 > 0) {
            this.size = Math.max(i, i2);
            animatePulse();
        }
    }

    public void animatePulse() {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{0.1f, 1.0f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{0.1f, 1.0f}), PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f})});
        ofPropertyValuesHolder.setDuration(2000);
        ofPropertyValuesHolder.setRepeatCount(-1);
        ofPropertyValuesHolder.setRepeatMode(1);
        ofPropertyValuesHolder.start();
    }
}
