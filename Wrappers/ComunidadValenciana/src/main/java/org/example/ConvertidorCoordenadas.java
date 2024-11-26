package org.example;

import org.example.Models.MonumentoConvertido;
import org.example.Models.MonumentoOriginal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import static org.example.CSVMonumento.getMonumentos;

public class ConvertidorCoordenadas{
    private static final String CONVERTIDOR_URL = "https://padeepro.com/converterutm.html";
    private static List<MonumentoOriginal> monumentos = getMonumentos("src/main/java/org/example/Data/monumentos.csv");


    public static List<MonumentoConvertido> convertirCoordenadas(){

        monumentos.remove(0);
        List<MonumentoConvertido> monumentosConvertidos = new ArrayList<>();
        HashMap<String,String> coordenadas = new HashMap<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Habilitar modo headless
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver();

        driver.get(CONVERTIDOR_URL);

        WebElement rechazarCookies = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div[2]/button[2]"));
        rechazarCookies.click();

        WebElement selectorZona = driver.findElement(By.xpath("/html/body/div[1]/p[4]/input[3]"));
        selectorZona.clear();
        selectorZona.sendKeys("30");

        WebElement utmEsteInput = driver.findElement(By.xpath("/html/body/div[1]/p[4]/input[2]"));
        WebElement utmNorteInput = driver.findElement(By.xpath("/html/body/div[1]/p[4]/input[1]"));

        WebElement calcButton = driver.findElement(By.xpath("/html/body/div[1]/p[4]/input[5]"));

        for (MonumentoOriginal monumento : monumentos) {
            String utmEste = monumento.getUtmeste();
            String utmNorte = monumento.getUtmnorte();

            //Borrar las comillas de las coordenadas
            utmEste = utmEste.replace("\"", "");
            utmNorte = utmNorte.replace("\"", "");

            utmEsteInput.clear();
            utmEsteInput.sendKeys(utmEste);

            utmNorteInput.clear();
            utmNorteInput.sendKeys(utmNorte);

            calcButton.click();


            WebElement latitudInput = driver.findElement(By.xpath("/html/body/div[1]/p[3]/input[1]"));
            WebElement longitudInput = driver.findElement(By.xpath("/html/body/div[1]/p[3]/input[2]"));

            coordenadas.put("latitud", latitudInput.getAttribute("value"));
            coordenadas.put("longitud", longitudInput.getAttribute("value"));


            String latitud = coordenadas.get("latitud");
            String longitud = coordenadas.get("longitud");

            MonumentoConvertido monumentoConvertido = new MonumentoConvertido(
                    monumento.getIgpcv(),
                    monumento.getDenominacion(),
                    monumento.getProvincia(),
                    monumento.getMunicipio(),
                    latitud,
                    longitud,
                    monumento.getCodclasificacion(),
                    monumento.getClasificacion(),
                    monumento.getCodcategoria(),
                    monumento.getCategoria()
            );
            monumentosConvertidos.add(monumentoConvertido);
            System.out.println("Nombre: " + monumentoConvertido.getDenominacion() + "{ " + latitud + ", " + longitud +" }");
        }

        driver.quit();
        return monumentosConvertidos;
    }


    public static void main(String[] args) {
        System.out.println(convertirCoordenadas());
    }
}
