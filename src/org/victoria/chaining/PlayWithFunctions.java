package org.victoria.chaining;

import org.victoria.chaining.function.Function;
import org.victoria.chaining.model.Meteo;

public class PlayWithFunctions {

    public static void main(String[] args) {

        Meteo meteo = new Meteo(20);

        Function<Meteo, Integer> readCelsius = m -> m.getTemperature();
        Function<Integer, Double> celsiusToFahrenheit = t -> t * 9d / 5d + 32d;

        // chaining: apply the first function and then the second with the result of the first one
        Function<Meteo, Double> readFahrenheit = readCelsius.andThen(celsiusToFahrenheit);

        /* composition: apply the second function and then the first with the result of the second one.
        Only possible with the Function interface*/
        readFahrenheit = celsiusToFahrenheit.compose(readCelsius);

        System.out.println("Meteo is F " + readFahrenheit.apply(meteo));
    }
}
