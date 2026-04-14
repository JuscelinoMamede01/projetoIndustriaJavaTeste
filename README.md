# Teste Pratico de Programacao - Projeto Industria (Java)

## Descricao

Implementacao do teste pratico de programacao em Java que gerencia funcionarios de uma industria.
O projeto foi desenvolvido com foco em clareza, boas praticas e atendimento completo aos requisitos.

## Estrutura do Projeto

```
teste-projedata/
  src/
    main/java/industria/
      Pessoa.java          # Classe base
      Funcionario.java     # Estende Pessoa
      Principal.java       # Classe main com todos os requisitos
    test/java/industria/
      SimpleTestRunner.java # Testes automatizados sem dependencias externas
  out/                     # Arquivos compilados (ignorado pelo git)
```

## Classes

### Pessoa
- `nome` (String)
- `dataNascimento` (LocalDate)
- Construtor, getters e setters

### Funcionario (extends Pessoa)
- `salario` (BigDecimal)
- `funcao` (String)
- Construtor, getters e setters

### Principal
Classe main com todos os requisitos implementados.

## Requisitos Implementados

| Item  | Descricao                                                                 |
|-------|---------------------------------------------------------------------------|
| 3.1   | Inserir todos os funcionarios conforme tabela, imprime lista completa     |
| 3.2   | Remover funcionario "Joao" da lista, imprime lista apos remocao           |
| 3.3   | Imprimir todos os funcionarios (incluindo Joao) formatados em tabela      |
| 3.4   | Aplicar aumento de 10% no salario de todos                                |
| 3.5   | Agrupar funcionarios por funcao em um Map                                 |
| 3.6   | Imprimir funcionarios agrupados por funcao em tabela                      |
| 3.8   | Listar aniversariantes dos meses 10 (outubro) e 12 (dezembro)             |
| 3.9   | Exibir funcionario mais velho com nome e idade                            |
| 3.10  | Listar funcionarios em ordem alfabetica                                   |
| 3.11  | Exibir total dos salarios                                                 |
| 3.12  | Exibir quantos salarios minimos cada funcionario ganha (base: R$ 1.212,00)|

## Tecnologias e Decisoes Tecnicas

- **BigDecimal** para precisao em calculos monetarios
- **LocalDate** + `DateTimeFormatter.ofPattern("dd/MM/yyyy")` para datas
- **NumberFormat** com `Locale.of("pt", "BR")` para formato brasileiro (ex: 1.234,56)
- **Streams API**: `groupingBy`, `filter`, `sorted`, `reduce`, `min`
- **RoundingMode.HALF_UP** no arredondamento de salarios
- Salario minimo considerado: R$ 1.212,00
- Saida formatada em tabela ASCII para facilitar leitura

## Testes Automatizados

O projeto inclui `SimpleTestRunner.java` — runner de testes sem dependencias externas (sem JUnit).

**6 testes cobrindo:**
- Remocao do Joao da lista
- Aumento de 10% nos salarios
- Agrupamento por funcao
- Aniversariantes de outubro e dezembro
- Funcionario mais velho
- Ordem alfabetica

### Rodar os testes

```powershell
cd teste-projedata
$files = Get-ChildItem -Path src\main\java -Recurse -Filter *.java | ForEach-Object { $_.FullName }
$files += 'src\test\java\industria\SimpleTestRunner.java'
javac -encoding UTF-8 -d out $files
java -cp out industria.SimpleTestRunner
```

Resultado esperado: `Resultado: 6/6 testes aprovados.`

## Como Compilar e Executar

### Terminal (PowerShell)

```powershell
cd teste-projedata
$files = Get-ChildItem -Path src\main\java -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $files
java -cp out industria.Principal
```

### Eclipse

1. **File -> New -> Java Project** — nome sem hifens (ex: `testeProjetoIndustria`)
2. Deletar `module-info.java` gerado automaticamente
3. Criar pacote `industria` dentro de `src`
4. Copiar `Pessoa.java`, `Funcionario.java` e `Principal.java` para o pacote
5. Botao direito em `Principal.java` -> **Run As -> Java Application**

## Exemplo de Saida

```
==============================================================
 3.1 - FUNCIONARIOS INSERIDOS
==============================================================
+--------------+-----------------+--------------+---------------+
| Nome         | Data Nascimento | Salario      | Funcao        |
+--------------+-----------------+--------------+---------------+
| Maria        | 18/10/2000      |     2.009,44 | Operador      |
| Joao         | 12/05/1990      |     2.284,38 | Operador      |
| Caio         | 02/05/1961      |     9.836,14 | Coordenador   |
| ...          | ...             |          ... | ...           |
+--------------+-----------------+--------------+---------------+
```
