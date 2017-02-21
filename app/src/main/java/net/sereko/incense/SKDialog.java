package net.sereko.incense;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

/**
 * Created by steve on 2/17/17.
 */

public class SKDialog extends DialogFragment {
        private int resource;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Inflate and set the layout for the dialog
            LayoutInflater inflater = getActivity().getLayoutInflater();

            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.first_dialog, null))
                    .setPositiveButton(R.string.continue_, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id){
                            // Go to next attribute dialog

                        }

                    })
                    .setNeutralButton(R.string.save_draft, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id){
                            // Save as or draft somewheres
                            // Nothing required
                        }
                    })
                    .setNegativeButton(R.string.discard, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

                    return builder.create();


        }


}


