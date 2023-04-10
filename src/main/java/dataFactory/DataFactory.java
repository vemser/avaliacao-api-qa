package dataFactory;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class DataFactory {
    public static Faker faker = new Faker(new Locale("pt-BR"));
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
}
