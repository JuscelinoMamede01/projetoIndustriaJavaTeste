package industria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SimpleTestRunner {

    public static void main(String[] args) {
        int passed = 0;
        int total = 0;

        total++; if (testRemoveJoao()) passed++;
        total++; if (testIncreaseSalariesBy10Percent()) passed++;
        total++; if (testGroupByFunction()) passed++;
        total++; if (testBirthdaysOctAndDec()) passed++;
        total++; if (testOldestEmployee()) passed++;
        total++; if (testSortAlphabetical()) passed++;

        System.out.println();
        System.out.printf("Resultado: %d/%d testes aprovados.%n", passed, total);
        if (passed != total) {
            System.out.println("Alguns testes falharam.");
            System.exit(1);
        } else {
            System.out.println("Todos os testes passaram.");
        }
    }

    private static List<Funcionario> createFuncionarios() {
        return new ArrayList<>(Arrays.asList(
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
    }

    private static boolean testRemoveJoao() {
        System.out.print("testRemoveJoao: ");
        List<Funcionario> list = createFuncionarios();
        list.removeIf(f -> f.getNome().equals("João"));
        boolean ok = list.stream().noneMatch(f -> f.getNome().equals("João")) && list.size() == 9;
        System.out.println(ok ? "OK" : "FAIL");
        return ok;
    }

    private static boolean testIncreaseSalariesBy10Percent() {
        System.out.print("testIncreaseSalariesBy10Percent: ");
        List<Funcionario> list = createFuncionarios();
        list.removeIf(f -> f.getNome().equals("João"));

        BigDecimal expectedTotal = BigDecimal.ZERO;
        for (Funcionario f : list) {
            BigDecimal increased = f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP);
            expectedTotal = expectedTotal.add(increased);
        }

        list.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP)));
        BigDecimal actualTotal = list.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        boolean ok = expectedTotal.compareTo(actualTotal) == 0;
        System.out.println(ok ? "OK" : "FAIL");
        return ok;
    }

    private static boolean testGroupByFunction() {
        System.out.print("testGroupByFunction: ");
        List<Funcionario> list = createFuncionarios();
        list.removeIf(f -> f.getNome().equals("João"));
        Map<String, List<Funcionario>> grouped = list.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
        boolean ok = grouped.containsKey("Operador") && grouped.get("Operador").size() == 2;
        System.out.println(ok ? "OK" : "FAIL");
        return ok;
    }

    private static boolean testBirthdaysOctAndDec() {
        System.out.print("testBirthdaysOctAndDec: ");
        List<Funcionario> list = createFuncionarios();
        list.removeIf(f -> f.getNome().equals("João"));
        List<Funcionario> bornOctDec = list.stream().filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12).collect(Collectors.toList());
        boolean ok = bornOctDec.size() == 2 && bornOctDec.stream().anyMatch(f -> f.getNome().equals("Maria")) && bornOctDec.stream().anyMatch(f -> f.getNome().equals("Miguel"));
        System.out.println(ok ? "OK" : "FAIL");
        return ok;
    }

    private static boolean testOldestEmployee() {
        System.out.print("testOldestEmployee: ");
        List<Funcionario> list = createFuncionarios();
        list.removeIf(f -> f.getNome().equals("João"));
        Funcionario oldest = list.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).orElse(null);
        boolean ok = oldest != null && "Caio".equals(oldest.getNome());
        System.out.println(ok ? "OK" : "FAIL");
        return ok;
    }

    private static boolean testSortAlphabetical() {
        System.out.print("testSortAlphabetical: ");
        List<Funcionario> list = createFuncionarios();
        list.removeIf(f -> f.getNome().equals("João"));
        List<String> namesSorted = list.stream().map(Funcionario::getNome).sorted().collect(Collectors.toList());
        List<String> expected = Arrays.asList("Alice","Arthur","Caio","Heitor","Helena","Heloísa","Laura","Maria","Miguel");
        boolean ok = expected.equals(namesSorted);
        System.out.println(ok ? "OK" : "FAIL");
        return ok;
    }

}
