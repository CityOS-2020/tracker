package com.cityos.frano.tracker;

/**
 * Created by Frano on 9.5.2015..
 */
public class ObilazakPoint {
    private String Vrijeme;
    private String Longitude;
    private String Latitude;
    private String Opis;
    private String FileImagePath;

    public String getVrijeme() {
        return Vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        Vrijeme = vrijeme;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public String getFileImagePath() {
        return FileImagePath;
    }

    public void setFileImagePath(String fileImagePath) {
        FileImagePath = fileImagePath;
    }

    public ObilazakPoint(String strVrijeme,
                         String strLongitude,
                         String strLatitude,
                         String strOpis,
                         String strFileNamePath){
        Vrijeme = strVrijeme;
        Longitude = strLongitude;
        Latitude = strLatitude;
        Opis = strOpis;
        FileImagePath = strFileNamePath;

    }
}
