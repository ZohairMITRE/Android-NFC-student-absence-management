package org.mbds.nfctag.write;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.mbds.nfctag.model.TagType;

import java.io.IOException;

public class NfcTagViewModel extends ViewModel {

    private MutableLiveData<Exception> onWriteFailed = new MutableLiveData<>();
    private MutableLiveData<Void> onTagWritten = new MutableLiveData<>();

    public LiveData<Void> getTagWritten() {
        return onTagWritten;
    }

    public LiveData<Exception> getWrittenFailed() {
        return onWriteFailed;
    }

    private NdefMessage createNdefForText(String text) {
        NdefRecord[] records = new NdefRecord[1];
        records[0] = NdefRecord.createTextRecord("en", text);
        return new NdefMessage(records);
    }
    private NdefMessage createNdefMessage(String text, TagType tagType) {
        switch (tagType) {
            case TEXT:
                return createNdefForText(text);
            case URL:
                //TODO create NdefMessage for URL
                //return createNdefForUrl(text);
            case PHONE:
                //TODO create NdefMessage for PHONE
                //return createNdefForPhone(text);
            default:
                return null;
        }
        /*// create the NDEF mesage:
        //========================
        // dimension is the int number of entries of ndefRecords:
        int dimension = 1;
        NdefRecord[] ndefRecords = new NdefRecord[dimension];
        // Example with an URI NDEF record:
        //String uriTxt = "http://www.mbds-fr.org"; // your URI in String format
        //NdefRecord ndefRecord = NdefRecord.createUri(uriTxt);
        NdefRecord ndefRecord = NdefRecord.createTextRecord("en", text);
        // Add the record to the NDEF message:
        ndefRecords[0] = ndefRecord;

        return new NdefMessage(ndefRecords);*/
    }

    private void write(NdefMessage ndefMessage, Ndef ndef) {
        if (ndef != null) {
            try {
                ndef.connect();
                // write the NDEF message on the tag
                ndef.writeNdefMessage(ndefMessage);
                ndef.close();
                onTagWritten.setValue(null);
            } catch (IOException | FormatException e1) {
                e1.printStackTrace();
                onWriteFailed.setValue(e1);
            }
        }
    }

    private void formatAndWrite(NdefMessage ndefMessage, Tag tag) {
        NdefFormatable format = NdefFormatable.get(tag);
        if (format != null) {
            // can you format the tag?
            try {
                format.connect();
                //Format and write the NDEF message on the tag
                format.format(ndefMessage);
                //Example of tag locked in writing:
                //formatable.formatReadOnly(message);
                format.close();
                onTagWritten.setValue(null);
            } catch (IOException | FormatException e1) {
                e1.printStackTrace();
                onWriteFailed.setValue(e1);
            }
        }
    }

    /**
     * This method is called when a new intent is received by the activity while its running.
     *
     * @param ndefMessage
     * @param tag
     */
    private void writeNdefMessage(NdefMessage ndefMessage, Tag tag) {
        Ndef ndef = Ndef.get(tag);
        if (ndef == null) {
            formatAndWrite(ndefMessage, tag);
        } else {
            write(ndefMessage, ndef);
        }
    }

    public void writeTag(String text, Tag tag, TagType tagType) {
        NdefMessage ndefMessage = createNdefMessage(text, tagType);
        writeNdefMessage(ndefMessage, tag);
    }

}
