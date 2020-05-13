package p008hu.gov.virusradar.Adapters.DevicesAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.util.ArrayList;
import p005br.com.sapereaude.maskedEditText.MaskedEditText;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceModel;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.TimeFrame;
import p008hu.gov.virusradar.Utilities.Util;

/* renamed from: hu.gov.virusradar.Adapters.DevicesAdapter.DevicesAdapter */
public class C1123DevicesAdapter extends Adapter<DevicesHolder> {
    private static final String dateTimeFormatKey = "dd.MM.yyyy HH:mm:ss";
    private Context context;
    private ArrayList<DeviceModel> devices = new ArrayList<>();

    public C1123DevicesAdapter(Context context2) {
        this.context = context2;
    }

    public DevicesHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DevicesHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1130R.layout.item_device, viewGroup, false), viewGroup.getContext());
    }

    public void onBindViewHolder(DevicesHolder devicesHolder, int i) {
        DeviceModel deviceModel = (DeviceModel) this.devices.get(i);
        TextView textView = devicesHolder.tvAlias;
        StringBuilder sb = new StringBuilder();
        sb.append(this.context.getString(C1130R.string.alias));
        String str = MaskedEditText.SPACE;
        sb.append(str);
        sb.append(deviceModel.f84id);
        textView.setText(sb.toString());
        TextView textView2 = devicesHolder.tvDistance;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.context.getString(C1130R.string.distance));
        sb2.append(str);
        sb2.append(String.format("%.2fm", new Object[]{((TimeFrame) deviceModel.timeFrames.get(0)).distance}));
        textView2.setText(sb2.toString());
        TextView textView3 = devicesHolder.tvStartDate;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.context.getString(C1130R.string.start_time));
        sb3.append(str);
        Long valueOf = Long.valueOf(((TimeFrame) deviceModel.timeFrames.get(0)).startTime);
        String str2 = dateTimeFormatKey;
        sb3.append(Util.formatDate(valueOf, str2));
        textView3.setText(sb3.toString());
        TextView textView4 = devicesHolder.tvEndDate;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(this.context.getString(C1130R.string.end_time));
        sb4.append(str);
        sb4.append(Util.formatDate(Long.valueOf(((TimeFrame) deviceModel.timeFrames.get(0)).endTime), str2));
        textView4.setText(sb4.toString());
        devicesHolder.tvDuration.setText(String.format("%s %s", new Object[]{this.context.getString(C1130R.string.duration), ((TimeFrame) deviceModel.timeFrames.get(0)).getDurationMinutes()}));
    }

    public int getItemCount() {
        return this.devices.size();
    }

    public void addDevices(ArrayList<DeviceModel> arrayList) {
        this.devices = arrayList;
        notifyDataSetChanged();
    }
}
