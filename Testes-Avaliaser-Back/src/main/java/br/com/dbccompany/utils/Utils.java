package br.com.dbccompany.utils;

import br.com.dbccompany.model.*;

import com.google.gson.Gson;

import net.datafaker.Faker;

import org.junit.Assert;

import java.util.Locale;

public class Utils {

    public static Faker faker = new Faker(new Locale("pt-BR"));

    public enum Env {
        DEV, PRD
    }

    public static String getBaseUrl() {
        String baseUrl = null;
        Env env = envStringToEnum();

        switch (env) {
            case DEV -> {
                baseUrl = "http://localhost:8080";
            }
            case PRD -> {
                baseUrl = "http://vemser-dbc.dbccompany.com.br:39000/vemser";
            }
            default -> {
                Assert.fail("Ambiente não configurado");
            }
        }
        return baseUrl;
    }

    public static Env envStringToEnum() {
        try {
            return Env.valueOf(getEnv());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Ambiente não existente: " + getEnv());
        }
    }

    public static String getEnv() {
        return Manipulation.getProp().getProperty("prop.env");
    }

    public static String convertProgramaToJson(Programa programa) { return new Gson().toJson(programa); }
}
