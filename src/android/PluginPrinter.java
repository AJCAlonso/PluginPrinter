package cordova.plugin.saurusprinter;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

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
        String xModeloEquipamento = "POS_A9X";
        String xPortaEquipamento = "";

        final Activity xActivity = cordova.getActivity();
        final String maisTexto = "Olha só";

        equip.InicializarEquipamento(xModeloEquipamento, xPortaEquipamento, (Activity) xActivity, new CallBackEvent() {
            @Override
            public <T> void Metodo(int i, String s, T t) {
                if (i == 0) {
                    equip.Imprimir(message, new CallBackEvent() {
                        @Override
                        public <T> void Metodo(int i2, String s2, T t2) {
                            Toast.makeText(
                                    xActivity,
                                    s2,
                                    Toast.LENGTH_LONG
                            ).show();
                            callbackContext.success(maisTexto);
                        }
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
