package industria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    // Formatadores
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(new Locale("pt", "BR"));

    static {
        NUMBER_FORMAT.setMinimumFractionDigits(2);
        NUMBER_FORMAT.setMaximumFractionDigits(2);
    }

    // Utilitário: formata salário com ponto milhar e vírgula decimal
    private static String formatarSalario(BigDecimal salario) {
        return NUMBER_FORMAT.format(salario);
    }

    private static final String BORDA =
        "+--------------+-----------------+--------------+---------------+";

    // Utilitário: imprime cabecalho da tabela
    private static void imprimirCabecalho() {
        System.out.println(BORDA);
        System.out.printf("| %-12s | %-15s | %-12s | %-13s |%n",
                "Nome", "Data Nascimento", "Salario", "Funcao");
        System.out.println(BORDA);
    }

    // Utilitário: imprime rodape da tabela
    private static void imprimirRodape() {
        System.out.println(BORDA);
    }

    // Utilitário: imprime dados de um funcionário
    private static void imprimirFuncionario(Funcionario f) {
        System.out.printf("| %-12s | %-15s | %12s | %-13s |%n",
                f.getNome(),
                f.getDataNascimento().format(DATE_FORMATTER),
                formatarSalario(f.getSalario()),
                f.getFuncao());
    }

    public static void main(String[] args) {

        // ─────────────────────────────────────────────────
        // 3.1 – Inserir todos os funcionários
        // ─────────────────────────────────────────────────
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria",   LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João",    LocalDate.of(1990,  5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio",    LocalDate.of(1961,  5,  2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel",  LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice",   LocalDate.of(1995,  1,  5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor",  LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur",  LocalDate.of(1993,  3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura",   LocalDate.of(1994,  7,  8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003,  5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena",  LocalDate.of(1996,  9,  2), new BigDecimal("2799.93"), "Gerente")
        ));

        System.out.println("==============================================================");
        System.out.println(" 3.1 - FUNCIONARIOS INSERIDOS");
        System.out.println("==============================================================");
        imprimirCabecalho();
        funcionarios.forEach(Principal::imprimirFuncionario);
        imprimirRodape();
        System.out.println();

        // copia da lista completa para uso no 3.3
        List<Funcionario> todos = new ArrayList<>(funcionarios);

        // ─────────────────────────────────────────────────
        // 3.2 – Remover o funcionário "João"
        // ─────────────────────────────────────────────────
        funcionarios.removeIf(f -> f.getNome().equals("João"));
        System.out.println("+--------------------------------------------------------------+");
        System.out.println("|     Funcionario 'Joao' removido da lista.                    |");
        System.out.println("+--------------------------------------------------------------+\n");
        System.out.println("==============================================================");
        System.out.println(" 3.2 - LISTA APOS REMOCAO DO JOAO");
        System.out.println("==============================================================");
        imprimirCabecalho();
        funcionarios.forEach(Principal::imprimirFuncionario);
        imprimirRodape();
        System.out.println();

        // ─────────────────────────────────────────────────
        // 3.3 – Imprimir todos os funcionários
        // ─────────────────────────────────────────────────
        System.out.println("==============================================================");
        System.out.println(" 3.3 - LISTA DE FUNCIONARIOS");
        System.out.println("==============================================================");
        imprimirCabecalho();
        todos.forEach(Principal::imprimirFuncionario);
        imprimirRodape();
        System.out.println();

        // ─────────────────────────────────────────────────
        // 3.4 – Aumento de 10% no salário
        // ─────────────────────────────────────────────────
        funcionarios.forEach(f ->
                f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP))
        );
        System.out.println("==============================================================");
        System.out.println(" 3.4 - SALARIOS APOS AUMENTO DE 10%");
        System.out.println("==============================================================");
        funcionarios.forEach(f ->
                System.out.printf("  %-12s -> R$ %s%n", f.getNome(), formatarSalario(f.getSalario()))
        );
        System.out.println();

        // ─────────────────────────────────────────────────
        // 3.5 – Agrupar funcionários por função (Map)
        // ─────────────────────────────────────────────────
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // ─────────────────────────────────────────────────
        // 3.6 – Imprimir funcionários agrupados por função
        // ─────────────────────────────────────────────────
        System.out.println("==============================================================");
        System.out.println(" 3.6 - FUNCIONARIOS AGRUPADOS POR FUNCAO");
        System.out.println("==============================================================");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("  > " + funcao.toUpperCase() + ":");
            imprimirCabecalho();
            lista.forEach(Principal::imprimirFuncionario);
            imprimirRodape();
        });
        System.out.println();

        // ─────────────────────────────────────────────────
        // 3.8 – Funcionários que fazem aniversário em outubro (10) ou dezembro (12)
        // ─────────────────────────────────────────────────
        System.out.println("==============================================================");
        System.out.println(" 3.8 - ANIVERSARIANTES DE OUTUBRO (10) E DEZEMBRO (12)");
        System.out.println("==============================================================");
        imprimirCabecalho();
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10
                          || f.getDataNascimento().getMonthValue() == 12)
                .forEach(Principal::imprimirFuncionario);
        imprimirRodape();
        System.out.println();

        // ─────────────────────────────────────────────────
        // 3.9 – Funcionário com maior idade
        // ─────────────────────────────────────────────────
        System.out.println("==============================================================");
        System.out.println(" 3.9 - FUNCIONARIO COM MAIOR IDADE");
        System.out.println("==============================================================");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow();
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.printf("  Nome: %s | Idade: %d anos%n%n", maisVelho.getNome(), idade);

        // ─────────────────────────────────────────────────
        // 3.10 – Lista por ordem alfabética
        // ─────────────────────────────────────────────────
        System.out.println("==============================================================");
        System.out.println(" 3.10 - FUNCIONARIOS EM ORDEM ALFABETICA");
        System.out.println("==============================================================");
        imprimirCabecalho();
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(Principal::imprimirFuncionario);
        imprimirRodape();
        System.out.println();

        // ─────────────────────────────────────────────────
        // 3.11 – Total dos salários
        // ─────────────────────────────────────────────────
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("==============================================================");
        System.out.println(" 3.11 - TOTAL DOS SALARIOS");
        System.out.println("==============================================================");
        System.out.printf("  Total: R$ %s%n%n", formatarSalario(totalSalarios));

        // ─────────────────────────────────────────────────
        // 3.12 – Quantos salários mínimos cada funcionário ganha
        // ─────────────────────────────────────────────────
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("==============================================================");
        System.out.println(" 3.12 - SALARIOS EM MULTIPLOS DO SALARIO MINIMO (R$ 1.212,00)");
        System.out.println("==============================================================");
        funcionarios.forEach(f -> {
            BigDecimal multiplos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("  %-12s -> %s salarios minimos%n",
                    f.getNome(), NUMBER_FORMAT.format(multiplos));
        });
        System.out.println();
    }
}
