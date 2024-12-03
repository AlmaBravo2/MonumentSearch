package org.example.Utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListaMonumentos {
    @JacksonXmlElementWrapper(localName = "monumentos")
    @JacksonXmlProperty(localName = "monumento")
    private List<Monumento> monumentos;

    public List<Monumento> getMonumentos() {
        return monumentos;
    }

    public void setMonumentos(List<Monumento> monumentos) {
        this.monumentos = monumentos;
    }
}
