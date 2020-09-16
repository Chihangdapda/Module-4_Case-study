package com.codegym.formatter;

import com.codegym.model.product.Producer;
import com.codegym.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ProducerFormatter implements Formatter<Producer> {

    private ProducerService producerService;

    @Autowired
    public ProducerFormatter(ProducerService producerService) {
        this.producerService = producerService;
    }

    @Override
    public Producer parse(String text, Locale locale) throws ParseException {
        return producerService.findById(Long.parseLong(text));
    }


    @Override
    public String print(Producer object, Locale locale) {
        return "[" + object.getProducerId() + ", " +object.getProducerName() + "]";
    }
}
