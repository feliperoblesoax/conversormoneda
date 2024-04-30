package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Conversor;
import modelos.ConversorExchage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Double.isNaN;

public class Principal {
    public static void main(String[] args) {
        int menu = 1;
        int opcion;
        while (menu == 1) {
            try {
                Scanner lectura = new Scanner(System.in);
                opcion = 0;
                System.out.println("""
                ***************************************************
                Sea Bienvenido(a) al Conversor de Moneda
                
                1) Dólar =>> Peso Argentino
                2) Peso Argentino =>> Dólar
                3) Dólar =>> Real Brasileño
                4) Real Brasileño =>> Dólar
                5) Dólar =>> Peso Colombiano
                6) Peso Colombiano =>> Dólar
                7) Salir
                Elija una opción válida:
                ***************************************************    
                """);
                opcion = lectura.nextInt();
                double valor;
                Conversor conversor = new Conversor("", "", 0.0);
                switch (opcion) {
                    case 1:
                        System.out.println("Introduce el valor a convertir:");
                        valor = lectura.nextDouble();
                        conversor = new Conversor("USD", "ARS", valor);
                        conversor.realizarConversion();
                        break;
                    case 2:
                        System.out.println("Introduce el valor a convertir:");
                        valor = lectura.nextDouble();
                        conversor = new Conversor("ARS", "USD", valor);
                        conversor.realizarConversion();
                        break;
                    case 3:
                        System.out.println("Introduce el valor a convertir:");
                        valor = lectura.nextDouble();
                        conversor = new Conversor("USD", "BRL", valor);
                        conversor.realizarConversion();
                        break;
                    case 4:
                        System.out.println("Introduce el valor a convertir:");
                        valor = lectura.nextDouble();
                        conversor = new Conversor("BRL", "USD", valor);
                        conversor.realizarConversion();
                        break;
                    case 5:
                        System.out.println("Introduce el valor a convertir:");
                        valor = lectura.nextDouble();
                        conversor = new Conversor("USD", "COP", valor);
                        conversor.realizarConversion();
                        break;
                    case 6:
                        System.out.println("Introduce el valor a convertir:");
                        valor = lectura.nextDouble();
                        conversor = new Conversor("COP", "USD", valor);
                        conversor.realizarConversion();
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        menu = 0;
                        break;
                    default:
                        System.out.println("Elige una opción válida");
                        break;
                }

            } catch (InputMismatchException e) {
                opcion = 0;
                System.out.println("Entrada incorrecta...intenta nuevamente ");
            }
        }


    }
}
