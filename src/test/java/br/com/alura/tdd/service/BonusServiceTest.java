package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BonusServiceTest {

    private BonusService service;

    @BeforeEach
    void setUp() {
        service = new BonusService();
    }

    @Test
    @DisplayName("Bonus deveria ser R$0 para funcionário com salário muito alto")
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
        Funcionario funcionario = new Funcionario("Teste", LocalDate.now(), new BigDecimal("25000"));
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> service.calcularBonus(funcionario));
        assertEquals("Funcionário com salário maior que R$1000 não pode receber bonus", exception.getMessage());
    }

    @Test
    @DisplayName("Bonus deveria ser 10% do salário")
    void bonusDeveriaSerDezPorcentoDoSalario() {
        BigDecimal bonus = service.calcularBonus(new Funcionario("Teste", LocalDate.now(), new BigDecimal("2500")));
        assertEquals(new BigDecimal("250.00"), bonus);
    }

    @Test
    @DisplayName("Bonus deveria ser 10% para salário exatamente de R$1000")
    void bonusDeveriaSerDezPorcentoDoSalarioParaSalarioExatamenteDeMilReais() {
        BigDecimal bonus = service.calcularBonus(new Funcionario("Teste", LocalDate.now(), new BigDecimal("1000")));
        assertEquals(new BigDecimal("100.00"), bonus);
    }
}