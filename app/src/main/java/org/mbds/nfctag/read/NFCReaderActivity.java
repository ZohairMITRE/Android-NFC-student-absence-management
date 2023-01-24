package org.mbds.nfctag.read;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.mbds.nfctag.MainActivity;
import org.mbds.nfctag.R;
import org.mbds.nfctag.model.TagContent;
import org.mbds.nfctag.utils.Animation;
import org.mbds.nfctag.write.NFCWriterActivity;


import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mbds.nfctag.MainActivity;
import org.mbds.nfctag.R;
import org.mbds.nfctag.model.TagContent;
import org.mbds.nfctag.utils.Animation;
import org.mbds.nfctag.write.NFCWriterActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
public class NFCReaderActivity extends AppCompatActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    public static String TAG = "TAG";
    private NfcReaderViewModel nfcReaderViewModel;
    TextView label1 , res ;
TextView text ;
    // TODO Lire le contenu d'un tag et effectuer les actions en fonction du contenu
    // TODO Si c'est un numéro de téléphone, lancer un appel
    // TODO Si c'est une page web lancer un navigateur pour afficher la page
    // TODO Sinon afficher le contenu dans la textviewx
    // TODO utiliser le view binding
    // TODO Faire en sorte que l'app ne crash pas si le tag est vierge ou mal formatté
    // TODO Demander à l'utilisateur d'activer le NFC, s'il ne l'est pas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
label1= findViewById(R.id.label);
res= findViewById(R.id.txtView1);
        setContentView(R.layout.read_tag_layout);

        nfcReaderViewModel = new ViewModelProvider(this).get(NfcReaderViewModel.class);

        //Get default NfcAdapter and PendingIntent instances
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        // check NFC feature:
        if (nfcAdapter == null) {
            // TODO Afficher un message d'erreur si le téléphone n'est pas compatible NFC
            Toast.makeText(this, "le téléphone n'est pas compatible NFC", Toast.LENGTH_SHORT).show();
            finish();
        }
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Enable NFC foreground detection
        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled()) {
                // TODO Afficher un popup demandant à l'utilisateur d'activer le NFC
                // TODO rediriger l'utilisateur vers les paramètres du téléphone
                Toast.makeText(this, "This device  NFC is not activated.", Toast.LENGTH_LONG).show();

                // TODO rediriger l'utilisateur vers les paramètres du téléphone
                startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                finish();
            } else {
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
            }
        } else {
            // TODO indiquer à l'utilisateur que son téléphone n'a pas de NFC
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        nfcReaderViewModel.getReadFailed().observe(this, readFailed -> {
            Toast.makeText(this, readFailed.getMessage(), Toast.LENGTH_SHORT).show();
        });

        nfcReaderViewModel.getTagRead().observe(this, readSuccess -> {
            for (TagContent s : readSuccess) {
                //TODO Réaliser les actions en fonction du contenu du tag
                // TODO Si c'est un numéro de téléphone, lancer un appel
                // TODO Si c'est une page web lancer un navigateur pour afficher la page
                // TODO Sinon afficher le contenu dans la textview
                //Ecirture ce fait dans writer  a travers tel:// et http://
               String content = s.getContent().toString();
                Toast.makeText(this, content, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), details.class);
                intent.putExtra("matricule", content);
                startActivity(intent);

            }
        });

        animateNfcTag();

    }

    private void animateNfcTag() {
        Animation.animateCard(findViewById(R.id.card_image));
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Disable NFC foreground detection
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }

    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        // check the event was triggered by the tag discovery
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            // get the tag object from the received intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            // TODO Vérifier que le tag est bien formaté en NDEF
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            nfcReaderViewModel.processNfcTag(rawMsgs);
            // TODO Si non, afficher un message d'erreur et rediriger l'utilisateur vers l'activité d'écriture
            Toast.makeText(this, "Erreur!!", Toast.LENGTH_SHORT).show();

       //     startActivity(new Intent(this, NFCWriterActivity.class));
        }
    }
}
