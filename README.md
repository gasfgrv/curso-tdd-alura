# Testes de Automatizados com Java

## Motivação dos testes automatizados

- Testes manuais:
    - Chatos
    - Lentos
    - Sujeitos a falhas (fator humano)
- Testes automatizados:
    - Automatizados
    - Feedback mais rápido
    - Segurança ao mexer no código

## Vantagens dos testes automatizados

- Garantir que o código da aplicação atendem corretamente os requisitos de negócio;
- Facilitar a manutenção de software

## Junit

- É a biblioteca padrão para a realização de testes automatizados em Java
- Criada em 1995 por Kent Beck e Erich Gamma
- Gratuita e open source
- Foco em teste de unidade (testes unitários)
- Por convenção, as classes de teste no Junit possui a seguinte nomenclatura:
    - `CalculadoraTest -> Nome da classe a ser testada com o sufixo "Test"`
- Uma boa prática é deixar as classes de teste em um diretório separado das demais classes.

## TDD - Test Driven Development

- Consiste em transformar o fluxo de desenvolvimento, alterando o workflow para a seguinte maneira:

![TDD-Workflow](https://developer.ibm.com/developer/default/articles/5-steps-of-test-driven-development/images/tdd-red-green-refactoring-v3.png)

### Vantagens do TDD:

- Código já sai "testado"
- Evita testes "Viciados" na implementação
- Refatoração faz parte do processo
- Ajuda a manter o foco
- Temos uma "Tendencia" em escrever um código mais simples

<aside>
**Agumas anotações do Junit**

`@BeforeEach`: Executa antes de todos os testes
`@AfterEach`: Executa depois de cada teste
`@BeforeAll`: Executa antes de todos os testes (necessita ser um método estático)
`@AfterAll`: Executa depois de todos os testes (necessita ser um método estático)

</aside>

## Mocks em Java

### O que é um mock?

É uma classe que simula os comportamentos de outra classe.

## Criando Mocks

Podem ser criados a partir do segunte código `Mockito.mock(Classe.class);`

Para automatizar a criação de mocks, existem também a anotação `@Mock` que server para indicar que o atributo é um mock, e os mocks com o seguinte código: `MockitoAnnotations.initMocks(this);`

```java
@Mock
private PagamentoDao dao;

@Mock
private Clock clock;

@Captor
private ArgumentCaptor<Pagamento> captor;

@BeforeEach
void setUp() {
	MockitoAnnotations.initMocks(this);
  gerador = new GeradorDePagamento(dao, clock);
}
```

## Manipulando Mocks - Comportamentos

Para definir o comportamento do mock é necessário o seguinte comando: `Mockito.when(funcao.chamar()).thenReturn(valor)`

O método `verify()` serve para informar se um determindo método foi executado, já o `verifyNoInterations()` serve para o contrário, verificar se nenhum método do mock foi acionado. Para usar-los deve seguir os seguintes comandos:

`Mockito.verify(mock).funcaoDoMock(parametro);`

`Mockito.verifyNoInterations(mock);`

## Manipulando mocks - lançando exceções

```java
doThrow(new RuntimeException("Erro ao salvar")).when(dao).salvar(any());

try {
	service.finalizarLeiloesExpirados();
  fail();
} catch (RuntimeException e) {
  verifyNoInteractions(enviadorDeEmails);
}
```

## Capturando Objetos

Para captar componentes internos da classe mockada. o mockito utiliza-se do ArgumentCaptor.

```java
@Captor
private ArgumentCaptor<Pagamento> captor;
```

`captor.capture()` → Método que captura o objeto passado por parâmetro da função a ser testada

`captor.getValue()` →pega o valor do objeto capturado

## Testes de integração

### Tipos de testes

![https://devporai.com.br/wp-content/uploads/2020/02/Pir%C3%A2mide-testes.png](https://devporai.com.br/wp-content/uploads/2020/02/Pir%C3%A2mide-testes.png)

- Testes de unidade:
    - Mais simples
    - Mais rápidos
    - Manutenção simples
    - Mais fácil de escrever
    - Testa uma unidade de maneira isolada do sistema
- Testes de Integração:
    - Um pouco mais trabalhoso que os testes de unidade, por lidar com toda a configuração do sistema e a integração
- Testes de Interface: São mais lentos e custosos por ter que usar a aplicação em funcionamento

### Exemplos de testes de integração

- Integração com o banco de dados
- Integração com API externa
- Integração com outros módulos
- Integração com serviços de mensageria
