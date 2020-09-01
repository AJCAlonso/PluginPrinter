package cordova.plugin.saurusprinter;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.widget.Toast;


//import br.com.saurus.sauruspos.Equipamento;
//import br.com.saurus.saurusframework.CallBackEvent;
//import br.com.saurus.sauruspos.connection.UsbConnection;
//import br.com.saurus.sauruspos.connection.UsbPrintersConnections;
//import br.com.saurus.sauruspos.modelos.POS_A910.Registro_Imprimir;
//import br.com.saurus.sauruspos.modelos.TradutorImpressao;
import br.com.saurus.sauruspos.Equipamento;
import br.com.saurus.saurusframework.CallBackEvent;
/**
 * This class echoes a string called from JavaScript.
 */
public class PluginPrinter extends CordovaPlugin {
    
    final Equipamento equip = new Equipamento();

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

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
        // if (message != null && message.length() > 0) {
        //     callbackContext.success(message);
        // } else {
        //     callbackContext.error("Expected one non-empty string argument.");
        // }
        String xModeloEquipamento = "POS_A9X0";
        String xPortaEquipamento = "";

        final Activity xActivity = cordova.getActivity();
        final String maisTexto = "Olha só";

        equip.InicializarEquipamento(xModeloEquipamento, xPortaEquipamento, (Activity) xActivity, new CallBackEvent() {
            @Override
            public <T> void Metodo(int i, String s, T t) {
                if (i == 0) {
                    equip.Imprimir(message, (i2, s2, t2) -> {
                            Toast.makeText(
                                    //xActivity,
                                    webView.getContext(),
                                    "texto: " + s2,
                                    Toast.LENGTH_LONG
                            ).show();
                            callbackContext.success(s2);
                    });

                } else {
                    Toast.makeText(
                            xActivity,
                            "Erro ao gerar a via de fidelidade: Detalhe: "+s,
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }
}
