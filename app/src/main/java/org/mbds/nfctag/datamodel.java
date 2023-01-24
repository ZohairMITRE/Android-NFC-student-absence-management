package org.mbds.nfctag;

import android.widget.EditText;

public class datamodel {
    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getJustife() {
        return justife;
    }

    public void setJustife(String justife) {
        this.justife = justife;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    private String matiere;
    private String justife;
    private String matricule;
    public datamodel(String justife, String matricule , String matiere) {
        this.matiere = matiere;
        this.matricule = matricule;
        this.justife=justife ;
    }



}
