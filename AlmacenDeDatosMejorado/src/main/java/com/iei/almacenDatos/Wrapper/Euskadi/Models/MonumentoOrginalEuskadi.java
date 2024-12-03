package com.iei.almacenDatos.Wrapper.Euskadi.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MonumentoOrginalEuskadi {
    private String documentName;
    private String documentDescription;
    private String municipality;
    private String address;
    private String latwgs84;
    private String lonwgs84;
    private String territory;
    private String postalCode;

    public MonumentoOrginalEuskadi() {
    }

    public MonumentoOrginalEuskadi(String documentName, String documentDescription, String locality, String address, String latwgs84, String lonwgs84, String territory, String postalcode) {
        this.documentName = documentName;
        this.documentDescription = documentDescription;
        this.municipality = locality;
        this.address = address;
        this.latwgs84 = latwgs84;
        this.lonwgs84 = lonwgs84;
        this.territory = territory;
        this.postalCode = postalcode;
    }

    @JsonProperty("documentName")
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @JsonProperty("documentDescription")
    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescripction) {
        this.documentDescription = documentDescripction;
    }

    @JsonProperty("municipality")
    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @JsonProperty("latwgs84")
    public String getLatwgs84() {
        return latwgs84;
    }

    public void setLatwgs84(String latwgs84) {
        this.latwgs84 = latwgs84;
    }

    @JsonProperty("lonwgs84")
    public String getLonwgs84() {
        return lonwgs84;
    }

    public void setLonwgs84(String lonwgs84) {
        this.lonwgs84 = lonwgs84;
    }

    @JsonProperty("territory")
    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "MonumentoOrginalEuskadi{" +
                "documentName='" + documentName + '\'' +
                ", documentDescription='" + documentDescription + '\'' +
                ", municipality='" + municipality + '\'' +
                ", address='" + address + '\'' +
                ", latwgs84='" + latwgs84 + '\'' +
                ", lonwgs84='" + lonwgs84 + '\'' +
                ", territory='" + territory + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
