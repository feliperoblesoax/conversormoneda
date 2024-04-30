package modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conversor {
    private String monedaBase;
    private String monedaDestino;
    private Double valor;
    private  Double valorFinal;

    public Conversor(String monedaBase, String monedaDestino, Double valor) {
        this.monedaBase = monedaBase;
        this.monedaDestino = monedaDestino;
        if (valor.isNaN()) throw new IllegalArgumentException("No has introducido un número");
        this.valor = valor;
    }

    public Conversor(ConversorExchage conversorExchage) {
        this.monedaBase = conversorExchage.base_code();
        this.monedaDestino = conversorExchage.target_code();
        this.valorFinal = conversorExchage.conversion_result();
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "[" + monedaBase + "]" +
                " corresponde al valor final de " + valorFinal + "[" +
                monedaDestino + "]";
    }

    public void leerValor() {
        Scanner lectura = new Scanner(System.in);
        System.out.println("Introduce el valor a convertir: ");
        valor = lectura.nextDouble();
    }
    public void realizarConversion() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


        String direccion = "https://v6.exchangerate-api.com/v6/e584da2ad78386fe9dca73e4/pair/" +
                monedaBase + "/" + monedaDestino + "/" + valor;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            //System.out.println(json);

            ConversorExchage conversorExchage = gson.fromJson(json, ConversorExchage.class);
            //System.out.println(conversorExchage);
            //System.out.println(direccion);

            Conversor miConversor = new Conversor(conversorExchage);
            System.out.println("El valor " + valor + miConversor);

        }catch (IOException e){
            System.out.println("Ocurrio un error al conectarse al servicio de converción, " +
                    "revise su conexión e intente nuevamente");
            //System.out.println(e.getMessage());
        }catch (InterruptedException e){
            System.out.println("Ocurrio un error inesperado, intente nuevamente");
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println("Ocurrio un error desconocido, intente nuevamente" + e.getMessage());
        }
    }
}
