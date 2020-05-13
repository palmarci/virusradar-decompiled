package p008hu.gov.virusradar.Modules;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* renamed from: hu.gov.virusradar.Modules.NotificationModel */
public class NotificationModel implements Serializable {
    private static final String keyCreatedOn = "createdOn";
    private static final String keyId = "id";
    private static final String keyMessage = "message";
    private static final String keyObjectId = "objectId";
    private static final String keyObjectType = "objectType";
    private static final String keyRead = "read";
    private static final String keySent = "sent";
    private static final String keyTitle = "title";
    @SerializedName("createdOn")
    public String CreatedOn;
    @SerializedName("id")

    /* renamed from: Id */
    public Integer f85Id;
    @SerializedName("message")
    public String Message;
    @SerializedName("objectId")
    public String ObjectId;
    @SerializedName("objectType")
    public Integer ObjectType;
    @SerializedName("read")
    public Boolean Read;
    @SerializedName("sent")
    public Boolean Sent;
    @SerializedName("title")
    public String Title;
}
