package com.fincontrol.demo.models;

import org.apache.juli.logging.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements InvestimentoPrecoObserver{

    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    @Override
    public void update(Investimento investimento) {
        this.registrarOperacao(investimento);
    }

    @Override
    public void registrarOperacao(Investimento investimento) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write("Preço atualizado para: " + investimento.getPreco() + " do investimento de ID: " + investimento.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
