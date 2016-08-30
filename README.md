# README #

Projeto de DAC: WebScholar

### Como usar ?##

* na pasta raiz, execute o script 'deploy.sh'.
* ou execute as linhas contidas no 'deploy.sh' uma por uma manualmente.


### Implementado até agora: ###

* CRUD de Administrador e professor;
* Autenticação de administrador e professor;
* Requisição de permissão para cadastrar professor;
* Envio de email para professor em caso de permissão concedida.

### Arquitetura ###

* Módulo Shared
   
Contém as entidades e as interfaces que darão acesso aos session beans remotos.
Esse módulo é útil para que outras aplicações possam se comunicar com os serviços oferecidos pelo WebScholar-web.

* Módulo WebScholar-web

Esse módulo abrange 3 escopos:

1- Contexto de persistencia
Nessa camada, as entidades são gerenciadas pelo container jee e pelo jpa.

2- Session Beans
São os beans gerenciados pelo container jee, são responsáveis por fornecer os serviços de autênticação, cadastrdo de professor, alteração de dados de administrador, entre outros. Os session beans utilizam do contexto de persistencia e do gerenciamento de transação fornecido pelo container ejb.

3- Páginas e componentes JSF (Managed Beans)
Os managed beans são responsáveis por abstrair as requisições http e relacionar os dados da requisição com o modelo. Funcionam como controladores para as visões. Também são clientes dos session beans citados acima.



### DOCKER ###

* O projeto é dividido em 2 imagens:
1- Uma imagem com glassfish para implantar a aplicação web.
2- Uma imagem com o postgres para criar o banco de dados.

* As imagens estão configuradas com o docker-compose.
* Lembre de utilizar o 'deploy.sh' para implantar o WebScholar automaticamente.