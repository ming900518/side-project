import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.Messages;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Leek {

    public static Timer t = new Timer();

    public static Timer start() {
        String ans = Messages.showInputDialog("請輸入欲查詢的股票代號（每分鐘查詢一次）", "輸入股票代號", Messages.getQuestionIcon());
        String[] options = {Messages.getOkButton()};
        if (ans != null && (!ans.isBlank())) {
            t.scheduleAtFixedRate(
                    new TimerTask() {
                        public void run() {
                            query(ans);
                        }
                    },
                    0,
                    60000);
            Notification notification = new Notification("StockNG", "操作成功", ans + "將會於每分鐘以通知形式更新", NotificationType.IDE_UPDATE);
            Notifications.Bus.notify(notification);
            return t;
        } else {
            Notification notification = new Notification("StockNG", "操作失敗", "未輸入股票代號", NotificationType.WARNING);
            Notifications.Bus.notify(notification);
            return null;
        }
    }

    public static void stop() {
        t.cancel();
    }

    public static void query(String ans) {
        JSONObject json = new JSONObject();
        try {
            json = JSONReader.readJsonFromUrl("https://query1.finance.yahoo.com/v8/finance/chart/" + ans + "?interval=1d");
        } catch (Exception ex) {
            ex.printStackTrace();
            Notification notification = new Notification("StockNG", "處理失敗", "無法正常取得資料", NotificationType.WARNING);
            Notifications.Bus.notify(notification);
            stop();
        }
        JSONObject chart = json.getJSONObject("chart");
        JSONArray result = chart.getJSONArray("result");
        JSONObject meta = result.getJSONObject(0).getJSONObject("meta");
        int regularMarketPrice = meta.getInt("regularMarketPrice");
        String currency = meta.getString("currency");
        Notification notification = new Notification("StockNG", ans, regularMarketPrice + " " + currency, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }
}
