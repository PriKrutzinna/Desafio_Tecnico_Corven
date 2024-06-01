package com.corven.crud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.corven.crud.service.PersonaService;

@SpringBootTest
class CrudApplicationTests {

    @Autowired
    private PersonaService personaService;

    @Test
    String testOperationsPerSecond() {
        long startTime = System.nanoTime();
        long endTime = startTime + 1_000_000_000L; // 1 segundo en nanosegundos

        int operations = 0;
        while (System.nanoTime() < endTime) {
            personaService.finEstadisticasDTO();
            operations++;
        }

        long elapsedTime = System.nanoTime() - startTime;
        double opsPerSecond = (double) operations / (elapsedTime / 1_000_000_000.0);

        System.out.println("Operaciones por segundo: " + opsPerSecond);
		return "Operaciones por segundo: " + opsPerSecond;
    }
}
