import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String ans = Messages.showInputDialog("請輸入欲查詢的股票代號（一分鐘查詢一次）", "輸入股票代號", Messages.getQuestionIcon());
        String[] options = {Messages.getOkButton()};
        if (ans != null && (!ans.isBlank())) {
            Timer t = new Timer();

            t.scheduleAtFixedRate(
                    new TimerTask() {
                        public void run() {
                            query(ans);
                        }
                    },
                    0,
                    60000);
        } else {
            Messages.showDialog("資料為空", "錯誤", options, 1, Messages.getErrorIcon());
        }
    }

    public void query(String ans) {
        JSONObject json = new JSONObject();
        try {
            json = JSONReader.readJsonFromUrl("https://query1.finance.yahoo.com/v8/finance/chart/" + ans + "?interval=1d");
        } catch (Exception ex) {
            ex.printStackTrace();
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
