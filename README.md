# Avaliação QA - Back-end / API

<a name="readme-top">Repositório de testes para a API de Avaliação: Back-end</a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]


<br />
<div align="center">
  <a href="https://www.dbccompany.com.br/">
    <img src="https://www.dbccompany.com.br/app/themes/dbccompany/public/images/headers/Vem-Ser-DBC-04_1920x600.374ad9.jpg" alt="Logo" >
  </a>

  <h3 align="center">Documentação e Tecnologias utilizadas</h3>

  <p align="center">
    <br />
    <a href="https://docs.google.com/document/d/1oF3X_u_c3ol7XRCZgz2CpVQyGNGa0JKZ/edit"><strong>Documentação</strong></a>
    <br />
    <br />
    <a href="http://vemser-dbc.dbccompany.com.br:39000/vemser/avaliacao-back/swagger-ui/index.html#/">Documentação Swagger UI</a>
    ·
    <a href="https://docs.google.com/document/d/11h7qCMG_SH4adgtSLSYzwGLOa0yHAaYCRB0RcPviP6Q/edit">Idealização do projeto</a>
    ·
    <a href="https://trello.com/b/67rMFFKK/vs13-avalia%C3%A7%C3%A3o">Trello - VS13</a>
  </p>
</div>


<details>
  <summary>Tópicos</summary>
  <ol>
    <li>
      <a href="#o-projeto">O projeto</a>
      <ul>
        <li><a href="#tecnologias-envolvidas">Tecnologias envolvidas</a></li>
      </ul>
    </li>
    <li>
      <a href="#clonando-repositório">Clonando repositório</a>
      <ul>
        <li><a href="#pre-requisitos">Pre-requisitos</a></li>
        <li><a href="#instalação">Instalação</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<br><br>
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
## O projeto

- Sistema de avaliação e gestão dos colaboradores do [Vem Ser](https://www.dbccompany.com.br/vem-ser/):
  - Metrifica e avalia o desempenho dos alunos participantes.
    
<br>

- Funcionalidades:
  - Instrutores podem adicionar feedback detalhado sobre o progresso e habilidades dos estagiários, tanto soft skills quanto hard skills.
  - Gestão de pessoas pode criar agendamentos eficientes de reuniões individuais (1:1) entre estagiários, instrutores, supervisores e o proprio GP.
  - Responsáveis pelo projeto podem criar programas personalizados e suas trilhas de aprendizagem.
    
<br>

- Benefícios:
  - Facilita a avaliação do desempenho dos estagiários para expor aos clientes.
  - Histórico dos programas anteriores, podendo gerar metricas mais acertivas para planejamento.
  - Auxilia os instrutores e GP a identificar colaboradores que necessitam de alguma atenção a mais relacionado a soft ou hard skills.
  - Promove um ambiente de aprendizado colaborativo e estruturado.
    
<br>
  
- Objetivo:
  - Alinhar os estagiários aos objetivos da empresa e fornecer orientação para o crescimento e desenvolvimento profissional.

<p align="right">(<a href="#readme-top">voltar ao topo</a>)</p>



<br><br>
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
### Tecnologias envolvidas

Esta sessão é destinada a documentação realcionada as tecnologias e dependencias utilizadas na criação da aplicação, assim como suas respectivas versões. O intuito é facilitar o entendimento das futuras equipes do Vem Ser que ficarem responsáveis por esta funcionalidade. 

* ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white) ----------------v17
* ![Jenkins](https://img.shields.io/badge/jenkins-%232C5263.svg?style=for-the-badge&logo=jenkins&logoColor=white) ----------------v2.440.1
* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ----------------JDK v20.0.2
* ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) ----------------v2023.3
* ![REST Assured](https://rest-assured.io/img/logo-transparent.png) ----------------v5.4.0
* ![Allure report](https://allurereport.org/assets/logo_header.d16b5587.png) ----------------v2.14.0


#### Dependencias

<br>

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original.svg" height="50" width="50"/> [JUnit5](https://junit.org/junit5/assets/img/junit5-logo.png) v5.9.1
<br>
<img src="https://raw.githubusercontent.com/faker-js/faker/HEAD/docs/public/logo.svg" height="50" width="50"/> [Java Faker](https://mvnrepository.com/artifact/com.github.javafaker/javafaker) v2.0.2
<br>
<img src="https://avatars.githubusercontent.com/u/45949248?s=280&v=4" height="50" width="50"/> [Project Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok) v1.18.24

<br>

<p align="right">(<a href="#readme-top">voltar ao topo</a>)</p>


<br><br>
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
## Clonando repositório

Sessão destinada ao guia de instalação e configuração do ambiente de testes.

### Pre-requisitos

[Java JDK 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)
<br>
[IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/?section=windows)


### Instalação

1. Clonar repositório
   ```sh
   git clone git@github.com:vemser/avaliacao-api-qa.git
   ```
2. Abra a pasta onde clonou o repositório com o IntelliJ.

3. Rode o Maven para carregar as dependencias do projeto
   ```mvn
   mvn dependency:resolve
   ```
4. Abra o menu do Maven na barra lateral direita, selecione "Lifecycle", e depois clique em "test". 

Se tudo foi devidamente configurado, os testes vão começar a ser executados.

<p align="right">(<a href="#readme-top">voltar ao topo</a>)</p>


<br><br>
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
## Testes realizados / futuros

#### Este tópico lista os testes que já foram cobertos ou que ainda deverão ser criados para garantir a qualidade da aplicação. 

- [x] Todos os metodos do endpoint ```/avaliacao```
- [x] Todos os metodos do endpoint ```/feedback```
- [x] Todos os metodos do endpoint ```/modulo```
- [x] Todos os metodos do endpoint ```/programa```
- [x] Todos os metodos do endpoint ```/trilha```
- [ ] Todos os metodos do endpoint ```/instrutor```
- [ ] Todos os metodos do endpoint ```/estagiario```
- [ ] Todos os metodos do endpoint ```/agendamento```
- [ ] Todos os metodos do endpoint ```/acompanhamento```
- [ ] Todos os metodos do endpoint ```/task```
- [ ] Todos os metodos do endpoint ```/relatorio```
- [ ] Todos os metodos do endpoint ```/feedback-modulo```
- [ ] Todos os metodos do endpoint ```/sorteio-controller```

#### :warning: Endpoints que não estão marcados foram realizados testes pontuais ou completos em apenas alguns métodos (GET, POST, etc) :warning: </strong>
   
<br><br>
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
## Colaboradores

<table>
    <tr>
      <td align="center">
        <a href="https://github.com/Vinicius-Oliveira-Bk">
          <img src="https://avatars.githubusercontent.com/u/37668297?v=4" width="100px;" alt="Foto de Vinicius-Oliveira-Bk no GitHub"/><br>
          <sub>
            <b>Vinicius Oliveira VS-13</b>
          </sub>
        </a>
      </td>
       <td align="center">
        <a href="https://github.com/wcavalheiro">
          <img src="https://avatars.githubusercontent.com/u/63076498?v=4" width="100px;" alt="Foto de wcavalheiro no GitHub"/><br>
          <sub>
            <b>Willian Cavalheiro VS-13</b>
          </sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/GabrielJohann">
          <img src="https://avatars.githubusercontent.com/u/114251516?v=4" width="100px;" alt="Foto de GabrielJohann no GitHub"/><br>
          <sub>
            <b>Gabriel A. Johann VS-13</b>
          </sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/gregsnn">
          <img src="https://avatars.githubusercontent.com/u/74692139?v=4" width="100px;" alt="Foto de gregsnn no GitHub"/><br>
          <sub>
            <b>Grégori Nunes VS-13</b>
          </sub>
        </a>
      </td>
  </table>

<br><br>
<!------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------->
## Melhorias sugeridas

##### :warning: Tópico em construção :warning: </strong>

<!-- Adicionar contato e QAs envolvidos em cada edição do Vem Ser
## Contact

Your Name - [@your_twitter](https://twitter.com/your_username) - email@example.com

Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)

<p align="right">(<a href="#readme-top">voltar ao topo</a>)</p>

-->

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/vemser/avaliacao-web-qa/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/vemser/avaliacao-web-qa/network/members
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/vemser/avaliacao-web-qa/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/company/dbc-company/mycompany/

[product-screenshot]: images/screenshot.png
