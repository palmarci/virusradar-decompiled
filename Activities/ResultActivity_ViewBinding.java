package p008hu.gov.virusradar.Activities;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.ResultActivity_ViewBinding */
public class ResultActivity_ViewBinding implements Unbinder {
    private ResultActivity target;
    private View view7f0800aa;

    public ResultActivity_ViewBinding(ResultActivity resultActivity) {
        this(resultActivity, resultActivity.getWindow().getDecorView());
    }

    public ResultActivity_ViewBinding(final ResultActivity resultActivity, View view) {
        this.target = resultActivity;
        resultActivity.rvDevices = (RecyclerView) Utils.findOptionalViewAsType(view, C1130R.C1133id.rvDevices, "field 'rvDevices'", RecyclerView.class);
        resultActivity.tvNoItems = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvNoItems, "field 'tvNoItems'", TextView.class);
        resultActivity.tvTitle = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvTitle, "field 'tvTitle'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, C1130R.C1133id.ivBack, "method 'backClick'");
        this.view7f0800aa = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                resultActivity.backClick();
            }
        });
    }

    public void unbind() {
        ResultActivity resultActivity = this.target;
        if (resultActivity != null) {
            this.target = null;
            resultActivity.rvDevices = null;
            resultActivity.tvNoItems = null;
            resultActivity.tvTitle = null;
            this.view7f0800aa.setOnClickListener(null);
            this.view7f0800aa = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
