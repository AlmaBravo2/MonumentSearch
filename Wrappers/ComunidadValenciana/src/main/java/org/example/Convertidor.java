package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

import static org.example.CSVMonumento.getMonumentos;

public class Convertidor {

    private static List<MonumentoOriginal> monumentos = getMonumentos("src/main/java/org/example/monumentos.csv");

    public static List<MonumentoConvertido> convertir() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Habilitar modo headless
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        monumentos.remove(0);
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://padeepro.com/converterutm.html");
        List<MonumentoConvertido> monumentosConvertidos = new ArrayList<>();

        WebElement utmEsteInput = driver.findElement(By.xpath("/html/body/div[1]/p[4]/input[1]"));
        WebElement utmNorteInput = driver.findElement(By.xpath("/html/body/div[1]/p[4]/input[2]"));
        WebElement zoneInput = driver.findElement(By.xpath("/html/body/div[1]/p[4]/input[3]"));
        WebElement convertButton = driver.findElement(By.xpath("/html/body/div[1]/p[4]/input[5]"));
        WebElement latitudInput = driver.findElement(By.xpath("/html/body/div[1]/p[3]/input[1]"));
        WebElement longitudInput = driver.findElement(By.xpath("/html/body/div[1]/p[3]/input[2]"));

        // Aceptar las cookies
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]/p")).click();

        for (MonumentoOriginal monumento : monumentos) {
            String utmEste = monumento.getUtmeste();
            String utmNorte = monumento.getUtmnorte();

            //Borrar las comillas de las coordenadas
            utmEste = utmEste.replace("\"", "");
            utmNorte = utmNorte.replace("\"", "");


            // Estan al revés habrá que cambiarlo
            utmEsteInput.clear();
            utmEsteInput.sendKeys(utmNorte);

            utmNorteInput.clear();
            utmNorteInput.sendKeys(utmEste);

            zoneInput.clear();
            zoneInput.sendKeys(String.valueOf(30));

            convertButton.click();

            String latitud = latitudInput.getAttribute("value");
            String longitud = longitudInput.getAttribute("value");

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
        }
        driver.quit();

        return monumentosConvertidos;
    }
    public static void main(String[] args) {
        List<MonumentoConvertido> monumentos = convertir();
        CSVMonumento.writeCSV(monumentos, "src/main/java/org/example/monumentos_convertidos.csv");
    }
}
