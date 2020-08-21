# CrawlerInfoMoney


Escreva um programa em java que:
- Encontre todas as notícias nas 3 primeiras página na seção Mercado do site "https://www.infomoney.com.br/mercados/“:
- Basta pegar as notícias que estão abaixo da Frase “Últimas notícias"
- Para cada notícia encontrada imprima:
- A URL da notícia;
- O título da notícia;
- O subtítulo da notícia;
- Autor
- A data de publicação no formato (dia/mês/ano hora:minuto);
- O conteúdo da notícia, sem tags html e sem quebras de linha.

Dicas:
- Faça o mínimo de requisições que puder para o site.
- Os navegadores modernos tem ferramentas de desenvolvimento que permitem acompanhar as requisições feitas enquanto se utiliza um site. Use isso para descobrir o que é feito pelo site.
- Você pode utilizar bibliotecas de terceiros se achar necessário.
- A paginação se da pelo botão “Carregar Mais”

## Solução
Consulta para saber descobrir boas ferramentas:
https://www.quora.com/What-are-good-web-crawlers-in-Java-like-Scrapy-in-Python
https://mobilemonitoringsolutions.com/8-most-popular-java-web-crawling-scraping-libraries/

Jsoup
crawler4j 