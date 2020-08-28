package cordova.plugin.saurusprinter;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.saurus.saurusframework.CallBackEvent;
import br.com.saurus.sauruspos.connection.UsbConnection;
import br.com.saurus.sauruspos.connection.UsbPrintersConnections;
import br.com.saurus.sauruspos.modelos.POS_A910.Registro_Imprimir;
import br.com.saurus.sauruspos.modelos.TradutorImpressao;

/**
 * This class echoes a string called from JavaScript.
 */
public class PluginPrinter extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
