# Recrutamento Tecsinapse - Prova Desenvolvedor JAVA 1

## Leia atentamente as instruções e requisitos detalhados abaixo

### Antes de iniciar

Essa prova possuí alguns requisitos necessários para realização. Certifique-se que você tenha instalado e pronto para uso:
* Java: JDK na versão 8 ou superior [(download)](https://www.oracle.com/technetwork/pt/java/javase/overview/index.html)
* IDE: ferramenta de desenvolvimento de software de sua preferência [Intellij IDEA](https://www.jetbrains.com/idea/) | [Eclipse](https://www.eclipse.org/downloads/) | [Netbeans](https://netbeans.org/) | etc... 
* Apache Maven [(download)](https://maven.apache.org/)
    * Fique atento que versões mais novas da JDK requerem versões mais novas do Maven
* Cliente Git [(download)](https://git-scm.com/downloads)
    * Muitas IDEs já possuem o cliente Git instalado e ele também pode ser utilizado

### Detalhes técnicos

A arquitetura desse projeto utiliza [Spring Boot](https://spring.io/projects/spring-boot) para facilitar a padronização e a execução da aplicação e foi construída utilizando os padrões de construção básicos fornecidos em [http://start.spring.io](http://start.spring.io).
As dependências são as mais simples possíveis para possibilitar a exposição de endpoints REST (Controllers) via http.
O banco de dados também já está configurado e é do tipo [H2](http://www.h2database.com) executado em memória, ou seja, toda vez que a aplicação for reiniciada, o banco de dados também será recriado.
Toda a parte de conexão e leitura/escrita do banco de dados deverá ser configurada pelo candidato e faz parte da solução do desafio. 

É **importante** destacar que para a realização desse desafio não é necessário nenhum tipo de conhecimento prévio dessa arquitetura e dessas tecnologias. Tudo já está configurado e deve funcionar corretamente sem a necessidade de qualquer ajuste.
De qualquer forma é esperado que durante o desenvolvimento sejam necessárias mudanças que envolvam essas tecnologias, mas isso faz parte do desafio e do grau de senioridade exigido para a vaga. 

#### Como executar o projeto

Localize a classe **Application** (br.com.tecsinapse.prova.Application) e execute o método **main**. Ao acessar no navegador:

[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

Caso visualize o retorno de sucesso **{"status":"UP"}**, significa que o sistema está funcionando corretamente.

### Sobre a prova

Essa prova tem dois objetivos principais:
1. Desenvolvimento do código nos pontos identificados pelo comentário "TODO implementar método" para que a aplicação se comporte como esperado
2. Alterações necessárias para que os testes da classe "VendaRestControllerTest.java" passem com sucesso

#### Requisitos técnicos

A ideia principal é simular um sistema bem simples de registro de vendas. Existem 2 endpoints que garantem um fluxo simples de cadastro / busca das vendas e estão sendo expostos na classe "VendaRestController". São eles:
* POST /api/vendas - realiza o cadastro no banco de dados de uma nova venda
* GET /api/vendas/{codigo} - retorna uma venda cadastrada no banco de dados a partir de seu código

Apesar dos endpoints já estarem expostos, os métodos de serviço (VendaService) ainda precisam ser desenvolvidos e as operações com o banco de dados [H2](http://www.h2database.com) já configurado precisam ser implementadas.
Além disso, existem testes unitários que validam esses serviços, mas hoje eles estão "quebrados". Após o desenvolvimento das funcionalidades, a execução desses testes devem ter o resultado de sucesso.
Esse é um bom indício de que tudo está funcionando corretamente e é pré-requisito para o processo. Caso os testes estejam quebrados ao final do desenvolvimento, o candidato será automaticamente desclassificado.

Além das operações básicas, é necessário que se desenvolva uma busca para a exibição de dados em um relatório anual de vendas. Esse endpoint está exposto na classe "RelatorioRestController".
* GET /api/relatorios/vendas/{ano} - retorna uma estrutura de dados contendo as vendas do ano, agrupadas por mês

**Importante:**
* Fique atento aos comentários que estão no código. Eles podem ajudar a entender o que é esperado na prova
* Alguns pontos técnicos estão em aberto justamente para explorar a criatividade de cada candidato
* Os testes utilizam o próprio Spring Boot para rodar, junto com um framework [Rest Assured](http://rest-assured.io/) para facilitar as chamadas aos endpoints. Para executá-los, basta localizar os métodos anotados com `@Test` e rodar pela própria IDE. Outra alternativa é executar a fase de testes do Maven.

#### O que é esperado que eu faça?
É esperado que os requisitos técnicos sejam alcançados e os testes passem, somente isso. Porém, é natural que o código seja analisado após a submissão completa do desafio, então é sempre importante ter cuidados com a qualidade e legibilidade do que é desenvolvido.

#### O que posso fazer?
Além da solução esperado, o candidato pode:
* Adicionar e utilizar bibliotecas que não estão no classpath da aplicação
    * Apenas é necessário garantir que as bibliotecas novas estejam no repositório [maven central](https://mvnrepository.com/repos/central)
* Utilizar qualquer forma de comunicação com o banco de dados já configurado
* Adicionar novas classes e modificar qualquer classe dos pacotes, desde que não alterem o funcionamento atual da aplicação
* Adicionar novas configurações nos arquivos "yml", desde que não alterem o funcionamento atual da aplicação
* Criar novos testes unitários, desde que ao final todos eles estejam executando com sucesso. 

#### O que não posso fazer?
Qualquer um desses pontos eliminará automaticamente o candidato:
* Alterar o código dos testes para que eles passem. O código deve fazer o que o teste espera e não o contrário
* Alterar as configurações pré-definidas, de modo que a aplicação e os testes parem de rodar por esse motivo
* Modificar os caminhos ou objetos de retorno esperados dos endpoints ou o HttpMethod que cada um deles utiliza

## Qualquer dúvida

Entrar em contato com [recrutamento@tecsinapse.com.br](recrutamento@tecsinapse.com.br)

### Boa sorte!
