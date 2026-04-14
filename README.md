# Teste Pratico de Programacao - Projeto Industria (Java)

## Descricao

Implementacao do teste pratico de programacao em Java que gerencia funcionarios de uma industria.

## Estrutura das Classes

- **Pessoa**: classe base com `nome` (String) e `dataNascimento` (LocalDate)
- **Funcionario**: estende `Pessoa`, adiciona `salario` (BigDecimal) e `funcao` (String)
- **Principal**: classe main com todas as acoes do teste (3.1 a 3.12)

## Requisitos Implementados

| Item | Descricao |
|------|-----------|
| 3.1  | Inserir todos os funcionarios conforme tabela |
| 3.2  | Remover funcionario "Joao" da lista |
| 3.3  | Imprimir todos os funcionarios com data (dd/MM/yyyy) e salario formatado |
| 3.4  | Aumento de 10% no salario de todos |
| 3.5  | Agrupar funcionarios por funcao em um Map |
| 3.6  | Imprimir funcionarios agrupados por funcao |
| 3.8  | Listar aniversariantes dos meses 10 (outubro) e 12 (dezembro) |
| 3.9  | Exibir funcionario mais velho com nome e idade |
| 3.10 | Listar funcionarios em ordem alfabetica |
| 3.11 | Exibir total dos salarios |
| 3.12 | Exibir quantos salarios minimos cada funcionario ganha (base: R$ 1.212,00) |

## Tecnologias

- Java 11+
- `BigDecimal` para precisao monetaria
- `LocalDate` / `DateTimeFormatter` para datas
- `NumberFormat` com Locale pt-BR para formatacao de valores
- Streams API (groupingBy, filter, sorted, reduce)

## Como Executar

### Pelo terminal (PowerShell)

```powershell
cd teste-projedata
$files = Get-ChildItem -Path src\main\java -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d out $files
java -cp out industria.Principal
```

### Pelo Eclipse

1. **File → New → Java Project** — nome: `testeProjetoIndustria`
2. Deletar `module-info.java` gerado automaticamente
3. Criar pacote `industria` dentro de `src`
4. Copiar `Pessoa.java`, `Funcionario.java` e `Principal.java` para o pacote
5. Botao direito em `Principal.java` → **Run As → Java Application**

## Saida (exemplo)

```
==============================================================
 3.1 - FUNCIONARIOS INSERIDOS
==============================================================
+--------------+-----------------+--------------+---------------+
| Nome         | Data Nascimento | Salario      | Funcao        |
+--------------+-----------------+--------------+---------------+
| Maria        | 18/10/2000      |     2.009,44 | Operador      |
| Joao         | 12/05/1990      |     2.284,38 | Operador      |
| ...          | ...             |          ... | ...           |
+--------------+-----------------+--------------+---------------+
```
